package jp.co.yamaha_motor.gimac.tools.web.app.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "ValidateController", description = "validate user login status")
public class ValidateController {

    @Operation(summary = "Validate user login status", security = {@SecurityRequirement(name = "X-Auth-Token")})
    @PostMapping("/validate")
    public String validate() {

        return "valid";
    }
}
