package com.eastchat.easychatloginservice.domain.vo;

import lombok.Data;

import java.util.*;
/**
 * 功能：这地方准备实现一个语序选词验证码
 * 作者：yml
 * 日期：2026/5/1814:50
 */
@Data
public class VerificationVo {
    private String captchaId;

    private String tips;

    private List<String> captchaWord;

    private Long expireTime;

}
