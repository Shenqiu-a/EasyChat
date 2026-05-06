package com.eastchat.easychatloginservice.domain.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 功能：
 * 作者：yml
 * 日期：2026/4/309:45
 */
@Data
public class UserRegisterInfoRequest {

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "手机号")
    private String cellPhone;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "密码")
    private String password;

    @Schema(description = "验证码")
    private String code;

}
