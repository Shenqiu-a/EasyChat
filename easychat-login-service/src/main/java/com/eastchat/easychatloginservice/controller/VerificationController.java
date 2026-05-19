package com.eastchat.easychatloginservice.controller;

import com.eastchat.easychatloginservice.common.beans.PackedResult;
import com.eastchat.easychatloginservice.domain.request.VerificationRequest;
import com.eastchat.easychatloginservice.domain.vo.VerificationVo;
import com.eastchat.easychatloginservice.services.VerificationCode.VerificationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 功能：验证码
 * 作者：yml
 * 日期：2026/5/1814:40
 */
@Slf4j
@Tag(name = "验证码服务接口")
@RestController
@RequestMapping("/api/verification")
public class VerificationController {

    @Resource
    VerificationService verificationService;

    @PostMapping("/getCaptchaWord")
    public PackedResult<VerificationVo> get(@RequestBody VerificationRequest verificationRequest) {
        VerificationVo verificationResult = verificationService.get(verificationRequest.getBusinessType());
        return PackedResult.success(verificationResult);
    }

    @PostMapping("/refresh")
    public PackedResult<VerificationVo> refresh(@RequestBody VerificationRequest verificationRequest) {
        VerificationVo verificationResult = verificationService.refresh(verificationRequest.getCaptchaId(), verificationRequest.getBusinessType());
        return PackedResult.success(verificationResult);
    }
}
