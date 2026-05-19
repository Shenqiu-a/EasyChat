package com.eastchat.easychatloginservice.services.VerificationCode.Impl;

import com.eastchat.easychatloginservice.common.enums.LoginErrorCode;
import com.eastchat.easychatloginservice.domain.vo.VerificationVo;
import com.eastchat.easychatloginservice.services.VerificationCode.VerificationService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 功能：验证码
 * 作者：yml
 * 日期：2026/5/1815:00
 */

@Service
@Slf4j
public class VerificationServiceImpl implements VerificationService {

    public static final List<List<String>> WORD_BANK = List.of(
            List.of("一言","九鼎","一九","言鼎"),
            List.of("柳暗","花明","柳花","暗明"),
            List.of("春花","秋月","春秋","花月"),
            List.of("春生","夏长","春夏","生长"),
            List.of("秋收","冬藏","秋冬","收藏"),
            List.of("阿猫","阿狗","阿阿","猫狗"),
            List.of("照猫","画虎","照画","猫虎"),
            List.of("泰来","否往","泰否","来往")
    );

    @Resource
    RedisTemplate redisTemplate;

    /**
     * 功能：获取验证码
     */
    @Override
    public VerificationVo get(Integer businessType) {
        String captchaId = UUID.randomUUID().toString();
        VerificationVo verificationVo = new VerificationVo();
        Random random = new Random();
        List<String> captchaWord = WORD_BANK.get(random.nextInt(WORD_BANK.size()));
        log.info("获取验证码成功: {}", captchaWord);

        // 打乱验证码
        List<String> captchaWords = new ArrayList<>(captchaWord);
        Collections.shuffle(captchaWords);
        log.info("验证码已打乱: {}", captchaWords);
        // 组装正确答案
        List<String> correctAnswer = new ArrayList<>();
        correctAnswer.add(captchaWord.get(0));
        correctAnswer.add(captchaWord.get(1));
        log.info("正确答案已组装好");
        String BizCaptchaId = null;
        if (businessType == 1) {
            BizCaptchaId = "captchaId:login:" + captchaId;
        } else if (businessType == 2) {
            BizCaptchaId = "captchaId:register:" + captchaId;
        }
        redisTemplate.opsForValue().set(
                BizCaptchaId,
                correctAnswer,
                120L,
                TimeUnit.MINUTES
        );
        log.info("已存入Redis");

        // 返回
        verificationVo.setCaptchaId(captchaId);
        verificationVo.setTips("请依次点击“叠加的文字”，组成成语");
        verificationVo.setCaptchaWord(captchaWords);
        verificationVo.setExpireTime(120L);
        log.info("已返回验证码");
        return verificationVo;
    }

    /**
     * 功能：验证码验证
     */
    @Override
    public Integer check(String captchaId, List<String> captcha, Integer businessType) {
        log.info("开始验证验证码");
        String bizCaptchaId = null;
        if (businessType == 1) {
            bizCaptchaId = "captchaId:login:" + captchaId;
        } else if (businessType == 2) {
            bizCaptchaId = "captchaId:register:" + captchaId;
        }
        Object correctAnswerObj = redisTemplate.opsForValue().get(bizCaptchaId);
        if (correctAnswerObj == null) {
            log.error(LoginErrorCode.USER_CODE_EXPIRED.getMsg());
            return 2;
        }
        List<String> correctAnswer = (List<String>) correctAnswerObj;
        if (correctAnswer.equals(captcha)) {
            log.info("验证成功");
            return 0;
        } else {
            log.error(LoginErrorCode.USER_CODE_ERROR.getMsg());
        }
        if (redisTemplate.delete("captchaId:string:" + captchaId)) {
            log.info("已删除验证码");
        } else {
            log.error("删除验证码失败");
        }
        return 1;
    }

    /**
     * 功能：刷新验证码
     */
    @Override
    public VerificationVo refresh(String captchaId, Integer businessType    ) {
        log.info("开始刷新验证码");
        String bizCaptchaId = null;
        if (businessType == 1) {
            bizCaptchaId = "captchaId:login:" + captchaId;
        } else if (businessType == 2) {
            bizCaptchaId = "captchaId:register:" + captchaId;
        }
        if(redisTemplate.delete(bizCaptchaId)) {
            log.info("已删除验证码");
        } else {
            log.error("删除验证码失败");
        }
        return get(businessType);
    }
}
