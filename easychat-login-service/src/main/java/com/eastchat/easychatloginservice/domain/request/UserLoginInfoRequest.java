package com.eastchat.easychatloginservice.domain.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 功能：用户登录请求参数
 * 作者：yml
 * 日期：2026/4/289:35
 */
@Data
public class UserLoginInfoRequest {

    @Schema(description = "手机号")
    private String cellPhone;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "密码")
    private String password;

    @Schema(description = "验证码")
    private String code;
}
