package com.eastchat.easychatloginservice.common.utils;

import com.eastchat.easychatloginservice.common.Exception.BizException;
import com.eastchat.easychatloginservice.common.beans.PackedResult;
import com.eastchat.easychatloginservice.common.enums.RegisterErrorCode;
import com.eastchat.easychatloginservice.services.VerificationCode.VerificationService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 功能：
 * 作者：yml
 * 日期：2026/5/2115:32
 */
// TODO 为什么这里用 Component
@Component
public class VerificationUtils {

    @Resource
    VerificationService verificationService;

    public void VerifyTheCaptcha(String captchaId, List<String> captchaWords, Integer businessType) {
        Integer checkCode = verificationService.check(captchaId, captchaWords, businessType);
        if (checkCode == 1) {
            throw new BizException(RegisterErrorCode.USER_REGISTER_CODE_ERROR);
        } else if (checkCode == 2) {
            throw new BizException(RegisterErrorCode.USER_REGISTER_CODE_EXPIRED);
        }
    }
}
