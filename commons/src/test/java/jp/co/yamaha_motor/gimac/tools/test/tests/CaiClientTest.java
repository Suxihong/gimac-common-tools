package jp.co.yamaha_motor.gimac.tools.test.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jp.co.yamaha_motor.gimac.tools.util.AwsSecretManagerUtil;
import jp.co.yamaha_motor.gimac.tools.util.CaiClient;

public class CaiClientTest {

    private String clientId;
    private String clientSecret;

    /**
     * clientId と clientSecret は AWS Secrets Manager に保管され、AwsSecretManagerUtil.getSecret() メソッドを呼び出して取得します。
     */
    @BeforeEach
    public void setUp() throws Exception {
        // AWS Secrets Manager からシークレットを取得
        String secretJson = AwsSecretManagerUtil.getSecret(
            "dev/AwsSecretManagerUtil/CaiOauth2"
        );
        ObjectMapper mapper = new ObjectMapper();
        JsonNode secretNode = mapper.readTree(secretJson);
        clientId = secretNode.get("clientId").asText();
        clientSecret = secretNode.get("clientSecret").asText();
    }

    /**
     * OAuth2 認証サーバーから CAI 呼び出し用の一時トークンを取得します。
     */
    @Test
    public void testGetAccessToken() throws Exception {
        CaiClient client = new CaiClient(clientId, clientSecret);

        String accessToken = client.getAccessToken();
        assertNotNull(accessToken, "accessToken が null であってはいけません");
        assertTrue(accessToken.length() > 0, "accessToken が空であってはいけません");
        System.out.println("accessToken: " + accessToken);
    }

    /**
     * CDI（CAI）インターフェースを呼び出します。
     */
    @Test
    public void testCallCdi() throws Exception {
        CaiClient client = new CaiClient(clientId, clientSecret);

        String inUrl = "https://apne2.dm-apne.informaticacloud.com/active-bpel/rt/tf_cdjp00371";
        String inUserId = "jp-dev-lin_yulin@ymsl.com.cn";
        String inPassword = "xiamen-202502";
        String inParamsJson = "{\"inFileName\":\"cdjp00371.json\"}";

        String result = client.callCdi(inUrl, inUserId, inPassword, inParamsJson);
        assertNotNull(result, "CDI 呼び出し結果が null であってはいけません");
        System.out.println("CDI Result: " + result);
    }
}