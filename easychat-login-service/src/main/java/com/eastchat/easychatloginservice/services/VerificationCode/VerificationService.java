package com.eastchat.easychatloginservice.services.VerificationCode;

import com.eastchat.easychatloginservice.domain.vo.VerificationVo;
import java.util.*;

public interface VerificationService {

    VerificationVo get(Integer businessType);

    Integer check(String captchaId, List<String> captcha, Integer businessType);

    VerificationVo refresh(String captchaId, Integer businessType);
}
