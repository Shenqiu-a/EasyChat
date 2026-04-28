package com.eastchat.easychatloginservice.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 功能：
 * 作者：yml
 * 日期：2026/4/2815:18
 */
@Data
public class UserBaseResponseVo {
    private Long id;

    @Schema(description = "用户id")
    private String userId;

    // 对外暴露的id(UUID/雪花ID/加密ID)
    @Schema(description = "用户名")
    private String userName;

    @Schema(description = "昵称")
    private String nickName;

    @Schema(description = "手机号")
    private String cellPhone;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "头像")
    private String avatar;

    @Schema(description = "性别")
    private String sex;

    @Schema(description = "年龄")
    private Integer age;

    @Schema(description = "好友码")
    private String friendNumber;

    @Schema(description = "个性签名")
    private String signature;

}
