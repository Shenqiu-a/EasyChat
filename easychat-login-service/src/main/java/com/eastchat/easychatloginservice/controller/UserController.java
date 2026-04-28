package com.eastchat.easychatloginservice.controller;

import com.eastchat.easychatloginservice.common.beans.PackedAssert;
import com.eastchat.easychatloginservice.common.beans.PackedResult;
import com.eastchat.easychatloginservice.common.enums.ErrorCode;
import com.eastchat.easychatloginservice.common.enums.LoginErrorCode;
import com.eastchat.easychatloginservice.domain.request.UserLoginInfoRequest;
import com.eastchat.easychatloginservice.domain.vo.UserBaseResponseVo;
import com.eastchat.easychatloginservice.services.UserServices.UserService;
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

    @PostMapping("/login")
    public PackedResult<UserBaseResponseVo> Login(@RequestBody UserLoginInfoRequest request) {
        // 参数校验
        String accountInfo = request.getCellPhone() != null ? request.getCellPhone() : request.getEmail();
        PackedAssert.isNotBlank(accountInfo, LoginErrorCode.USER_ACCOUNT_IS_NULL);
        PackedAssert.isNotBlank(request.getPassword(), LoginErrorCode.USER_PASSWORD_IS_NULL);
        PackedAssert.isNotBlank(request.getCode(),LoginErrorCode.USER_CODE_IS_NULL);
        // 验证码处理

        return PackedResult.success(userService.login(request));
    }
}
