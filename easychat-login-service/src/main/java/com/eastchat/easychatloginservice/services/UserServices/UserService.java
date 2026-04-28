package com.eastchat.easychatloginservice.services.UserServices;

import com.eastchat.easychatloginservice.domain.request.UserLoginInfoRequest;
import com.eastchat.easychatloginservice.domain.vo.UserBaseResponseVo;

/**
 * 功能：
 * 作者：yml
 * 日期：2026/4/2815:12
 */

public interface UserService {
    UserBaseResponseVo login(UserLoginInfoRequest request);
}
