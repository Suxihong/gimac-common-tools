package jp.co.yamaha_motor.gimac.tools.test.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jp.co.yamaha_motor.gimac.tools.util.EmailSenderUtil;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ses.model.SendEmailResponse;

public class EmailSenderTest {

    private EmailSenderUtil emailSender;

    @BeforeEach
    public void setUp() {
        emailSender = new EmailSenderUtil();
    }

    @AfterEach
    public void tearDown() {
        emailSender.close();
    }

    /**
     * AWS認証情報が正しく設定されているかを検証
     */
    @Test
    public void testAwsCredentials() {
        assertNotNull(DefaultCredentialsProvider.create().resolveCredentials(),
            "AWS認証情報が正しく設定されていません");
        assertEquals("ap-northeast-1", Region.AP_NORTHEAST_1.id(),
             "リージョン設定が正しくありません");
    }

    /**
     * 簡単なメール送信をテスト
     */
    @Test
    public void testSendSimpleEmail() {
        List<String> toAddresses = List.of("vp01317@globalymc.com");
        String subject = "Test Simple Email";
        String bodyHtml = "<h1 style=\"color: red\">Hello, this is a test email!</h1>";
        String bodyText = "Hello, this is a test email!";

        SendEmailResponse response = emailSender.sendSimpleEmail(toAddresses, subject, bodyHtml, bodyText);

        assertNotNull(response, "SendEmailResponse should not be null");
        System.out.println("SendEmailResponse: " + response);
    }

    /**
     * 複雑なメール送信をテスト
     */
    @Test
    public void testSendComplexEmail() throws Exception {
        List<String> toAddresses = List.of("vp01317@globalymc.com");
        List<String> ccAddresses = List.of("vp01317@globalymc.com");
        List<String> bccAddresses = List.of("vp01317@globalymc.com");
        String subject = "Test Complex Email";
        String bodyHtml = "<h1 style=\"color: blue\">Hello, this is a test email with attachments and inline resources!</h1><img src='cid:image1'/>";
        String bodyText = "Hello, this is a test email with attachments and inline resources!";

        // 動的に添付ファイルを生成
        File tempAttachment1 = File.createTempFile("txt-attachment", ".txt");
        try (java.io.FileWriter writer = new java.io.FileWriter(tempAttachment1)) {
            writer.write("txt attachment file 1");
        }
        File tempAttachment2 = File.createTempFile("pdf-attachment", ".pdf");
        try (java.io.FileOutputStream fos = new java.io.FileOutputStream(tempAttachment2)) {
            fos.write(new byte[]{0x25, 0x50, 0x44, 0x46, 0x2D}); // PDFファイルヘッダー
        }
        List<File> attachmentFiles = List.of(tempAttachment1, tempAttachment2);

        // 動的にインライン画像を生成（ここでは簡単なJPEGバイナリを作成）
        File tempInlineImage = File.createTempFile("inline", ".jpg");
        try (java.io.FileOutputStream fos = new java.io.FileOutputStream(tempInlineImage)) {
            fos.write(new byte[]{(byte)0xFF, (byte)0xD8, (byte)0xFF, (byte)0xE0}); // JPEGヘッダー
        }
        Map<String, File> inlineResources = Map.of("image1", tempInlineImage);

        boolean result = emailSender.sendComplexEmailWithAttachment(
            toAddresses, ccAddresses, bccAddresses, subject,
            bodyHtml, bodyText, attachmentFiles, inlineResources
        );

        assertTrue(result, "Failed to send complex email");

        // テスト後に一時ファイルを削除
        tempAttachment1.delete();
        tempAttachment2.delete();
        tempInlineImage.delete();
    }
}