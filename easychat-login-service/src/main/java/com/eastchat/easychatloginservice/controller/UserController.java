package com.eastchat.easychatloginservice.controller;

import com.eastchat.easychatloginservice.common.beans.PackedAssert;
import com.eastchat.easychatloginservice.common.beans.PackedResult;
import com.eastchat.easychatloginservice.common.enums.ErrorCode;
import com.eastchat.easychatloginservice.common.enums.LoginErrorCode;
import com.eastchat.easychatloginservice.common.enums.RegisterErrorCode;
import com.eastchat.easychatloginservice.domain.request.UserLoginInfoRequest;
import com.eastchat.easychatloginservice.domain.request.UserRegisterInfoRequest;
import com.eastchat.easychatloginservice.domain.vo.UserBaseResponseVo;
import com.eastchat.easychatloginservice.services.UserServices.UserService;
import com.eastchat.easychatloginservice.services.VerificationCode.VerificationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Tag(name = "基础用户/账号服务接口")
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Resource
    UserService userService;

    @Resource
    VerificationService verificationService;

    /**
     * 功能：用户登录
     */
    @PostMapping("/login")
    public PackedResult<UserBaseResponseVo> Login(@RequestBody UserLoginInfoRequest request) {
        // 参数校验
        String accountInfo = request.getCellPhone() != null ? request.getCellPhone() : request.getEmail();
        PackedAssert.isNotBlank(accountInfo, LoginErrorCode.USER_ACCOUNT_IS_NULL);
        PackedAssert.isNotBlank(request.getPassword(), LoginErrorCode.USER_PASSWORD_IS_NULL);
        PackedAssert.isNotNull(request.getCaptcha(),LoginErrorCode.USER_CODE_IS_NULL);
        PackedAssert.isNotBlank(request.getCaptchaId(),LoginErrorCode.USER_CODE_ID_IS_NULL);
        Integer checkCode = verificationService.check(request.getCaptchaId(), request.getCaptcha(), 1);
        if(checkCode == 1) {
            return PackedResult.error(LoginErrorCode.USER_CODE_ERROR.getCode(), LoginErrorCode.USER_CODE_ERROR.getMsg());
        } else if (checkCode == 2) {
            return PackedResult.error(LoginErrorCode.USER_CODE_EXPIRED.getCode(), LoginErrorCode.USER_CODE_EXPIRED.getMsg());
        }
        return PackedResult.success(userService.login(request));
    }

    /**
     * 功能：用户注册
     */
    @PostMapping("/register")
    public PackedResult<String> register(@RequestBody UserRegisterInfoRequest request) {
        String accountInfo = request.getCellPhone() != null ? request.getCellPhone() : request.getEmail();
        PackedAssert.isNotBlank(accountInfo, RegisterErrorCode.USER_ACCOUNT_IS_NULL);
        PackedAssert.isNotNull(request.getCaptcha(),RegisterErrorCode.USER_REGISTER_CODE_IS_NULL);
        PackedAssert.isNotBlank(request.getCaptchaId(),RegisterErrorCode.USER_REGISTER_CODE_ID_IS_NULL);
        if (request.getUsername() == null) {
            request.setUsername(accountInfo);
        }
        Integer checkCode = verificationService.check(request.getCaptchaId(), request.getCaptcha(), 2);
        if (checkCode == 1) {
            return PackedResult.error(RegisterErrorCode.USER_REGISTER_CODE_ERROR.getCode(), RegisterErrorCode.USER_REGISTER_CODE_ERROR.getMsg());
        } else if (checkCode == 2) {
            return PackedResult.error(RegisterErrorCode.USER_REGISTER_CODE_EXPIRED.getCode(), RegisterErrorCode.USER_REGISTER_CODE_EXPIRED.getMsg());
        }
        return PackedResult.success(userService.register(request));
    }
}
