package com.eastchat.easychatloginservice.domain.request;

import com.eastchat.easychatloginservice.domain.vo.TokenVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 功能：
 * 作者：yml
 * 日期：2026/6/1510:04
 */

@Data
public class UserLogOutInfoRequest {

    @Schema(description = "用户Id")
    private String userId;

    @Schema(description = "手机号")
    private String cellPhone;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "token")
    private TokenVo token;

}
