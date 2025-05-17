package jp.co.yamaha_motor.gimac.tools.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.core.exception.SdkException;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ses.SesClient;
import software.amazon.awssdk.services.ses.model.Body;
import software.amazon.awssdk.services.ses.model.Content;
import software.amazon.awssdk.services.ses.model.Destination;
import software.amazon.awssdk.services.ses.model.Message;
import software.amazon.awssdk.services.ses.model.RawMessage;
import software.amazon.awssdk.services.ses.model.SendEmailRequest;
import software.amazon.awssdk.services.ses.model.SendEmailResponse;
import software.amazon.awssdk.services.ses.model.SendRawEmailRequest;

@Component
@Slf4j
public class EmailSenderUtil {

    private final SesClient sesClient;
    private static final String FROM_ADDRESS = "vp01317@globalymc.com";
    private static final int MAX_RETRIES = 3;

    public EmailSenderUtil() {
        // sesClientを初期化し、APIを直接使用
        this.sesClient = SesClient.builder()
                .region(Region.AP_NORTHEAST_1)
                .httpClientBuilder(software.amazon.awssdk.http.apache.ApacheHttpClient.builder())
                .build();
    }

    /**
     * SESを使用して簡単なメールを直接送信
     *
     * @param toAddresses 受信者のメールアドレスリスト
     * @param subject     メールの件名
     * @param bodyHtml    メールのHTML本文
     * @param bodyText    メールのテキスト本文（オプション）
     * @return SendEmailResponse 応答オブジェクト
     */
    public SendEmailResponse sendSimpleEmail(List<String> toAddresses, String subject, String bodyHtml, String bodyText) {
        try {
            SendEmailRequest sendEmailRequest = createSendEmailRequest(toAddresses, subject, bodyHtml, bodyText);
            log.info("SendEmailRequest: {}", sendEmailRequest);

            SendEmailResponse sendEmailResponse = null;
            int retryCount = 0;

            while (retryCount < MAX_RETRIES) {
                try {
                    sendEmailResponse = sesClient.sendEmail(sendEmailRequest);
                    break;
                } catch (SdkException e) {
                    log.error("メール送信中に例外が発生しました", e);
                    log.info("サービスが利用できないため再試行中: {}", e.getMessage());
                    retryCount++;
                }
            }

            return sendEmailResponse;
        } catch (SdkException e) {
            handleSendEmailException(e, toAddresses);
            close();
            throw e;
        }
    }

    private SendEmailRequest createSendEmailRequest(List<String> toAddresses, String subject, String bodyHtml,
            String bodyText) {
        Body.Builder bodyBuilder = Body.builder().html(Content.builder().data(bodyHtml).build());
        if (bodyText != null) {
            bodyBuilder.text(Content.builder().data(bodyText).build());
        }
        return SendEmailRequest.builder()
                .destination(Destination.builder().toAddresses(toAddresses).build())
                .message(Message.builder()
                        .body(bodyBuilder.build())
                        .subject(Content.builder().data(subject).build())
                        .build())
                .source(FROM_ADDRESS)
                .build();
    }

    /**
     * 複雑なメール（テスト：添付ファイル、HTML、画像など）
     *
     * @param toAddresses    受信者のメールアドレスリスト
     * @param subject        メールの件名
     * @param bodyHtml       メールのHTML本文
     * @param bodyText       メールのテキスト本文（オプション）
     * @param attachmentFiles 添付ファイル
     * @return 送信成功かどうか
     */
    public boolean sendComplexEmailWithAttachment(List<String> toAddresses, List<String> ccAddresses,
            List<String> bccAddresses, String subject, String bodyHtml, String bodyText, List<File> attachmentFiles,
            Map<String, File> inlineResources) {
        try {
            // MIME形式のメール内容を構築
            String rawEmailContent = createRawEmailContent(toAddresses, ccAddresses, bccAddresses, subject, bodyHtml,
                    bodyText, attachmentFiles, inlineResources);

            // バイト配列に変換
            byte[] rawMessageBytes = rawEmailContent.getBytes();

            // AWS SESを使用してメールを送信
            RawMessage rawMessage = RawMessage.builder()
                    .data(SdkBytes.fromByteArray(rawMessageBytes))
                    .build();

            SendRawEmailRequest rawEmailRequest = SendRawEmailRequest.builder()
                    .rawMessage(rawMessage)
                    .build();

            sesClient.sendRawEmail(rawEmailRequest);
            log.info("複雑なメールを正常に送信しました: {}", toAddresses);
            return true;
        } catch (Exception e) {
            log.error("添付ファイル付きの複雑なメール送信中にエラーが発生しました", e);
            return false;
        }
    }

    private String createRawEmailContent(List<String> toAddresses, List<String> ccAddresses, List<String> bccAddresses,
        String subject, String bodyHtml, String bodyText, List<File> attachmentFiles, Map<String, File> inlineResources)
        throws Exception {
        String boundary = "----=_Part_" + System.currentTimeMillis();

        StringBuilder rawEmail = new StringBuilder();

        // メールヘッダーを設定
        rawEmail.append("From: ").append(FROM_ADDRESS).append("\r\n");
        rawEmail.append("To: ").append(String.join(",", toAddresses)).append("\r\n");
        if (ccAddresses != null && !ccAddresses.isEmpty()) {
            rawEmail.append("Cc: ").append(String.join(",", ccAddresses)).append("\r\n");
        }
        if (bccAddresses != null && !bccAddresses.isEmpty()) {
            rawEmail.append("Bcc: ").append(String.join(",", bccAddresses)).append("\r\n");
        }
        rawEmail.append("Subject: ").append(subject).append("\r\n");
        rawEmail.append("MIME-Version: 1.0").append("\r\n");
        rawEmail.append("Content-Type: multipart/mixed; boundary=\"").append(boundary).append("\"\r\n");
        rawEmail.append("\r\n");

        // 本文部分を追加
        rawEmail.append("--").append(boundary).append("\r\n");
        rawEmail.append("Content-Type: multipart/alternative; boundary=\"").append(boundary).append("_alt\"\r\n");
        rawEmail.append("\r\n");

        // テキスト本文を追加
        if (bodyText != null) {
            rawEmail.append("--").append(boundary).append("_alt").append("\r\n");
            rawEmail.append("Content-Type: text/plain; charset=UTF-8").append("\r\n");
            rawEmail.append("Content-Transfer-Encoding: 7bit").append("\r\n");
            rawEmail.append("\r\n");
            rawEmail.append(bodyText).append("\r\n");
        }

        // HTML本文を追加
        if (bodyHtml != null) {
            rawEmail.append("--").append(boundary).append("_alt").append("\r\n");
            rawEmail.append("Content-Type: text/html; charset=UTF-8").append("\r\n");
            rawEmail.append("Content-Transfer-Encoding: 7bit").append("\r\n");
            rawEmail.append("\r\n");
            rawEmail.append(bodyHtml).append("\r\n");
        }

        rawEmail.append("--").append(boundary).append("_alt--").append("\r\n");

        // 添付ファイルを追加
        if (attachmentFiles != null) {
            for (File attachment : attachmentFiles) {
                if (attachment.exists()) {
                    rawEmail.append("--").append(boundary).append("\r\n");
                    rawEmail.append("Content-Type: application/octet-stream; name=\"").append(attachment.getName())
                            .append("\"\r\n");
                    rawEmail.append("Content-Transfer-Encoding: base64").append("\r\n");
                    rawEmail.append("Content-Disposition: attachment; filename=\"").append(attachment.getName())
                            .append("\"\r\n");
                    rawEmail.append("\r\n");
                    rawEmail.append(encodeFileToBase64(attachment)).append("\r\n");
                }
            }
        }

        // 埋め込みリソースを追加
        if (inlineResources != null) {
            for (Map.Entry<String, File> entry : inlineResources.entrySet()) {
                String contentId = entry.getKey();
                File resource = entry.getValue();
                if (resource.exists()) {
                    rawEmail.append("--").append(boundary).append("\r\n");
                    rawEmail.append("Content-Type: application/octet-stream; name=\"").append(resource.getName())
                            .append("\"\r\n");
                    rawEmail.append("Content-Transfer-Encoding: base64").append("\r\n");
                    rawEmail.append("Content-Disposition: inline; filename=\"").append(resource.getName())
                            .append("\"\r\n");
                    rawEmail.append("Content-ID: <").append(contentId).append(">\r\n");
                    rawEmail.append("\r\n");
                    rawEmail.append(encodeFileToBase64(resource)).append("\r\n");
                }
            }
        }

        rawEmail.append("--").append(boundary).append("--").append("\r\n");

        return rawEmail.toString();
    }

    private String encodeFileToBase64(File file) throws Exception {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            java.nio.file.Files.copy(file.toPath(), outputStream);
            return java.util.Base64.getEncoder().encodeToString(outputStream.toByteArray());
       }
    }

    /**
     * メール送信例外を処理
     *
     * @param e           例外オブジェクト
     * @param toAddresses
     */
    private void handleSendEmailException(SdkException e, List<String> toAddresses) {
        log.error("メール送信例外エラー: {}, toAddresses={}", e.getMessage(), toAddresses, e);
    }

    /**
     * SesClientリソースを閉じる
     */
    public void close() {
        try {
            if (sesClient != null) {
                sesClient.close();
            }
        } catch (Exception e) {
            log.error("SESクライアントのクローズに失敗しました", e);
        }
    }
}
