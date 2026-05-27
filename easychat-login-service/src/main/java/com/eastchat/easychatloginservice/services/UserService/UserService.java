package com.eastchat.easychatloginservice.services.UserService;

import com.eastchat.easychatloginservice.entity.UserInfoPO;

public interface UserService {

        UserInfoPO getUserInfoByPhone(String saltedCellPhoneNumber);

        UserInfoPO getUserInfoByEmail(String saltedEmail);

        void insertUserInfo(UserInfoPO userInfoPO);

}
