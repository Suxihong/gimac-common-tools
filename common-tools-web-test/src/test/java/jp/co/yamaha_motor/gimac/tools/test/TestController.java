package jp.co.yamaha_motor.gimac.tools.test;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @PostMapping("/test")
    public String test() {
        return "test";
    }
}
