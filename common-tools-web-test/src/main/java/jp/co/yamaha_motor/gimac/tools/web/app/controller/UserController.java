package jp.co.yamaha_motor.gimac.tools.web.app.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ymsl.solid.web.restful.json.model.RestProcessAware;
import com.ymsl.solid.web.usercontext.UserDetailsAccessor;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jp.co.yamaha_motor.gimac.tools.web.app.config.auth.PJUserDetails;
import jp.co.yamaha_motor.gimac.tools.web.app.form.UserInfoModel;

/**
 * Controller for user info.
 */
@RestController
@RequestMapping("/user")
@Tag(name = "UserController", description = "user info")
public class UserController implements RestProcessAware {

    @Operation(summary = "Get user info", security = {@SecurityRequirement(name = "X-Auth-Token")})
    @PostMapping("/info")
    public UserInfoModel userinfo() {
        PJUserDetails ud = UserDetailsAccessor.DEFAULT.get();
        return new UserInfoModel(ud.getUserCode(), ud.getCompanyCode(), ud.getUserCode());
    }
}
