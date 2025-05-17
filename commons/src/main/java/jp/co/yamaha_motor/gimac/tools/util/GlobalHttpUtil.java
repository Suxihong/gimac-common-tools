package jp.co.yamaha_motor.gimac.tools.util;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * 簡易版のグローバルHTTPクライアントユーティリティクラス
 */
public final class GlobalHttpUtil {

    // デフォルト設定
    private static final Duration DEFAULT_CONNECT_TIMEOUT = Duration.ofSeconds(10);
    private static final Duration DEFAULT_REQUEST_TIMEOUT = Duration.ofSeconds(30);

    // グローバルHttpClientインスタンス
    private static final HttpClient HTTP_CLIENT = HttpClient.newBuilder()
            .connectTimeout(DEFAULT_CONNECT_TIMEOUT)
            .version(HttpClient.Version.HTTP_2)
            .followRedirects(HttpClient.Redirect.NORMAL)
            .build();

    // インスタンス化を防ぐためのプライベートコンストラクタ
    private GlobalHttpUtil() {
    }

    /* ========== コアリクエストメソッド ========== */

    /**
     * GETリクエスト
     */
    public static String get(String url) throws Exception {
        return get(url, null, DEFAULT_REQUEST_TIMEOUT);
    }

    /**
     * カスタムヘッダーとタイムアウトを指定したGETリクエスト
     */
    public static String get(String url, Map<String, String> headers, Duration timeout) throws Exception {
        HttpRequest request = buildRequest(url, "GET", null, headers, timeout);
        return HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString()).body();
    }

    /**
     * POSTリクエスト
     */
    public static String post(String url, String body) throws Exception {
        return post(url, body, null, DEFAULT_REQUEST_TIMEOUT);
    }

    /**
     * カスタムヘッダーとタイムアウトを指定したPOSTリクエスト
     */
    public static String post(String url, String body, Map<String, String> headers, Duration timeout) throws Exception {
        HttpRequest request = buildRequest(url, "POST", body, headers, timeout);
        return HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString()).body();
    }

    /**
     * 非同期GETリクエスト
     */
    public static CompletableFuture<String> getAsync(String url) {
        return getAsync(url, null, DEFAULT_REQUEST_TIMEOUT);
    }

    /**
     * カスタムヘッダーとタイムアウトを指定した非同期GETリクエスト
     */
    public static CompletableFuture<String> getAsync(String url, Map<String, String> headers, Duration timeout) {
        HttpRequest request = buildRequest(url, "GET", null, headers, timeout);
        return HTTP_CLIENT.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body);
    }

    /**
     * 非同期POSTリクエスト
     */
    public static CompletableFuture<String> postAsync(String url, String body) {
        return postAsync(url, body, null, DEFAULT_REQUEST_TIMEOUT);
    }

    /**
     * カスタムヘッダーとタイムアウトを指定した非同期POSTリクエスト
     */
    public static CompletableFuture<String> postAsync(String url, String body, Map<String, String> headers,
            Duration timeout) {
        HttpRequest request = buildRequest(url, "POST", body, headers, timeout);
        return HTTP_CLIENT.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body);
    }

    /* ========== 内部メソッド ========== */

    private static HttpRequest buildRequest(String url, String method, String body,
            Map<String, String> headers, Duration timeout) {
        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(timeout != null ? timeout : DEFAULT_REQUEST_TIMEOUT);

        // リクエストメソッドを設定
        if ("GET".equals(method)) {
            builder.GET();
        } else if ("POST".equals(method)) {
            builder.POST(HttpRequest.BodyPublishers.ofString(body))
                    .header("Content-Type", "application/json");
        }

        // リクエストヘッダーを追加
        if (headers != null) {
            headers.forEach(builder::header);
        }

        return builder.build();
    }

    /**
     * グローバルHttpClientインスタンスを取得（特殊な要件向け）
     */
    public static HttpClient getHttpClient() {
        return HTTP_CLIENT;
    }
}