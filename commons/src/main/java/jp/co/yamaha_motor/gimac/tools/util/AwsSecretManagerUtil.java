package jp.co.yamaha_motor.gimac.tools.util;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;

/**
 * AWS Secrets Managerからシークレット情報を安全に取得するためのユーティリティクラス。
 */
public class AwsSecretManagerUtil {

    /**
     * AWS Secrets Managerから指定されたシークレットの値を取得します。
     *
     * @param secretName 取得するシークレットの名前
     * @return シークレット値のJSON文字列
     */
    public static String getSecret(String secretName) {

        // シークレットマネージャクライアントを作成します
        SecretsManagerClient client = SecretsManagerClient.builder()
                .region(Region.of("us-east-1")) // us-east-1エリアをデフォルトで使用します
                .build();
        GetSecretValueRequest getSecretValueRequest = GetSecretValueRequest.builder()
                .secretId(secretName)
                .build();

        GetSecretValueResponse getSecretValueResponse;

        try {
            getSecretValueResponse = client.getSecretValue(getSecretValueRequest);
        } catch (Exception e) {
            throw e;
        }

        return getSecretValueResponse.secretString();
    }
}