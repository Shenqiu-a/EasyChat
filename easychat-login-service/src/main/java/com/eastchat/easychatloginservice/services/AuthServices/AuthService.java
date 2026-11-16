package com.eastchat.easychatloginservice.services.AuthServices;

import com.eastchat.easychatloginservice.domain.request.UserLoginInfoRequest;
import com.eastchat.easychatloginservice.domain.request.UserRegisterInfoRequest;
import com.eastchat.easychatloginservice.domain.vo.TokenVo;
import com.eastchat.easychatloginservice.domain.vo.UserBaseResponseVo;
import com.eastchat.easychatloginservice.domain.vo.UserInfoVo;

/**
 * 功能：
 * 作者：yml
 * 日期：2026/4/2815:12
 */

public interface AuthService {
    UserInfoVo login(UserLoginInfoRequest request);

    String register(UserRegisterInfoRequest request);
}
