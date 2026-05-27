package com.eastchat.easychatloginservice.services.UserService.Impl;

import com.eastchat.easychatloginservice.entity.UserInfoPO;
import com.eastchat.easychatloginservice.mapper.UserInfoMapper;
import com.eastchat.easychatloginservice.services.UserService.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * 功能：
 * 作者：yml
 * 日期：2026/5/2714:57
 */

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserInfoMapper userInfoMapper;

    @Override
    public UserInfoPO getUserInfoByPhone(String saltedCellPhoneNumber) {
        return userInfoMapper.selectByEmail(saltedCellPhoneNumber);
    }

    @Override
    public UserInfoPO getUserInfoByEmail(String saltedEmail) {
        return userInfoMapper.selectByEmail(saltedEmail);
    }

    @Override
    public void insertUserInfo(UserInfoPO userInfoPO) {
        userInfoMapper.insert(userInfoPO);
    }
}
