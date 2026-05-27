package com.eastchat.easychatloginservice.domain.vo;

import lombok.Data;

/**
 * 功能：
 * 作者：yml
 * 日期：2026/5/2715:22
 */

@Data
public class TokenVo {
    private String accessToken;

    private String refreshToken;

    private Long expiresIn;
}
