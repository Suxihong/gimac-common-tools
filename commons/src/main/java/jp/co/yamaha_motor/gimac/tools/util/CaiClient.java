package jp.co.yamaha_motor.gimac.tools.util;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;
import java.util.concurrent.atomic.AtomicLong;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * OAuth2.0認証とCDI（APIの呼び出し機能を提供します。
 */
public class CaiClient {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final HttpClient HTTP_CLIENT = HttpClient.newHttpClient();

    private String clientId;
    private String clientSecret;
    private String accessToken;
    private AtomicLong tokenExpireTime = new AtomicLong(0);

    /**
     * クライアント認証情報を使用してインスタンスを初期化します。
     *
     * @param clientId クライアントID
     * @param clientSecret クライアントシークレット
     */
    public CaiClient(String clientId, String clientSecret) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    /**
     * OAuth2.0認証トークン（アクセストークン）を取得します。
     * トークンが有効な場合はキャッシュから取得し、無効または未取得の場合は新たに取得してキャッシュを更新します。
     *
     * @return OAuth2.0認証アクセストークン
     */
    public synchronized String getAccessToken() throws IOException, InterruptedException {
        long now = System.currentTimeMillis();
        if (accessToken != null && now < tokenExpireTime.get()) {
            return accessToken;
        }
        String credentials = Base64.getEncoder().encodeToString((clientId + ":" + clientSecret).getBytes());
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://dm-apne.informaticacloud.com/authz-service/oauth/token"))
                .header("Authorization", "Basic " + credentials)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString("grant_type=client_credentials"))
                .build();
        HttpResponse<String> response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        JsonNode root = OBJECT_MAPPER.readTree(response.body());
        accessToken = root.get("access_token").asText();
        int expiresIn = root.get("expires_in").asInt(3600); // 1時間がデフォルトです
        tokenExpireTime.set(now + (expiresIn - 60) * 1000L); // 1分前にリフレッシュします
        return accessToken;
    }

    // CAIインターフェースを呼び出します。
    public String callCdi(String inUrl, String inUserId, String inPassword, String inParamsJson)
            throws IOException, InterruptedException {
        String token = getAccessToken();
        String requestBody = String.join("&",
                "inUrl=" + inUrl,
                "inUserId=" + inUserId,
                "inPassword=" + inPassword,
                "inParams=" + inParamsJson);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jp-dev.ym-dtintg.com/api/p_cajpz0003_01"))
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
        HttpResponse<String> response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
}