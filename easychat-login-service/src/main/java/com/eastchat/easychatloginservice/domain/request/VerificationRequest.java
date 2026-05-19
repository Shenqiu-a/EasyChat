package com.eastchat.easychatloginservice.domain.request;

import lombok.Data;
import java.util.*;

/**
 * 功能：
 * 作者：yml
 * 日期：2026/5/1911:47
 */
@Data
public class VerificationRequest {

    private String captchaId;

    private List<String> captcha;

    private Integer businessType;
}
