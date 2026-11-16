package com.eastchat.easychatloginservice.domain.vo;

import com.eastchat.easychatloginservice.entity.UserInfoPO;
import lombok.Data;

/**
 * 功能：
 * 作者：yml
 * 日期：2026/6/1510:07
 */

@Data
public class UserInfoVo {
    private UserInfoPO userInfoPO;

    private TokenVo tokenVo;
}
