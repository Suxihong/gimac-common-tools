package jp.co.yamaha_motor.gimac.tools.test.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import jp.co.yamaha_motor.gimac.tools.test.TraningTestApplication;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Sample test for web module.
 * Use the custom {@link TraningTestApplication} to run the test so the unnecessary part can be excluded.
 * Just focus on the specific functions to be tested.
 */
@SpringBootTest(classes = {TraningTestApplication.class})
@ActiveProfiles({"development-test", "h2-test"})
@Rollback
@AutoConfigureMockMvc
class WebTestCase {

    @Autowired
    private MockMvc target;

    @Test
    void test() throws Exception {

        MvcResult result = target.perform(MockMvcRequestBuilders.post("/test")).andExpect(status().isOk()).andReturn();
        Assertions.assertEquals("test", result.getResponse().getContentAsString());
    }
}
