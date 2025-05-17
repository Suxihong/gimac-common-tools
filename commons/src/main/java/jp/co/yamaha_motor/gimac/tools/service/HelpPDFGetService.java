package jp.co.yamaha_motor.gimac.tools.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ymsl.solid.base.exception.BusinessCodedException;

import jp.co.yamaha_motor.gimac.tools.model.DhAntiLiteralElementModel;
import jp.co.yamaha_motor.gimac.tools.repository.HelpPDFGetRepository;
import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.S3Exception;

@Service
@Slf4j
public class HelpPDFGetService {

    @Autowired
    private HelpPDFGetRepository helperPDFGetRepository;

    @Value("${gimac.module}")
    private String moduleCode;

    /**
     * S3バケットからファイルをダウンロードします。
     *
     * @param screenCode 画面コード
     * @return S3オブジェクトレスポンス
     */
    public ResponseInputStream<GetObjectResponse> downloadFileFromS3(String screenCode) {
        String userRegion = "ap-northeast-1"; // ユーザーのリージョンを指定
        String s3Region = "ap-northeast-1"; // S3バケットのリージョンを指定
        // ユーザーのリージョンを使用して一時的なAWSセッション認証情報を取得
        final AwsCredentials sessionCredentials = getAwsCredentials(userRegion);
        log.info("------sessionCredentials: " + sessionCredentials.toString());
        // TODO: siteCode、systemCode、screenIdFromは指定する必要があります
        String siteCode = "0000";
        String systemCode = "LZ";
        String screenIdFrom = moduleCode;
        DhAntiLiteralElementModel dhAntiLiteralElementModel = helperPDFGetRepository
                .getAttachFileControllParameter(siteCode, systemCode, screenIdFrom);
        if (dhAntiLiteralElementModel == null) {
            throw new BusinessCodedException("helper.getPdf.getPramsError");
        }
        String bucketName = dhAntiLiteralElementModel.getReplaceValue1(); // S3バケット名
        String folder = dhAntiLiteralElementModel.getReplaceValue2(); // S3フォルダ名
        String key = folder + "/" + screenCode + ".pdf"; // S3オブジェクトキー
        log.info("------bucketName: " + bucketName);
        log.info("------key: " + key);

        ResponseInputStream<GetObjectResponse> responseInputStream = null;

        try (S3Client s3Client = S3Client.builder()
                .region(Region.of(s3Region))
                .credentialsProvider(StaticCredentialsProvider.create(sessionCredentials))
                .build()) {

            // GetObjectRequestを作成
            try {
                GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                        .bucket(bucketName)
                        .key(key)
                        .build();
                log.info("------getObjectRequest: " + getObjectRequest.toString());
                responseInputStream = s3Client.getObject(getObjectRequest);
            } catch (S3Exception e) {
                // 如果未找到对象且screenCode不是以_01结尾，则尝试以_01结尾再次查找
                if ("NoSuchKey".equals(e.awsErrorDetails().errorCode()) && !screenCode.endsWith("_01")) {
                    try {
                        String newScreenCode = screenCode.replaceFirst("_(.*)$", "_01");
                        String newKey = folder + "/" + newScreenCode + ".pdf";
                        log.info("------retry with key: " + newKey);
                        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                                .bucket(bucketName)
                                .key(newKey)
                                .build();
                        responseInputStream = s3Client.getObject(getObjectRequest);
                    } catch (S3Exception err) {
                        if ("NoSuchKey".equals(err.awsErrorDetails().errorCode())) {
                            throw new BusinessCodedException(
                            "S3ダウンロードエラー: " + err.awsErrorDetails().errorMessage(),
                            err);
                        }
                    }
                } else {
                    throw new BusinessCodedException(
                            "S3ダウンロードエラー: " + e.awsErrorDetails().errorMessage(),
                            e);
                }
            }
        }
        return responseInputStream;
    }

    /**
     * AWSの認証情報を取得します。
     *
     * @param region リージョン
     * @return AWSの認証情報
     */
    public AwsCredentials getAwsCredentials(String region) {
        return DefaultCredentialsProvider.create().resolveCredentials();
    }
}
