package com.eastchat.easychatloginservice.mapper;

import com.eastchat.easychatloginservice.entity.UserInfoPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 功能：
 * 作者：yml
 * 日期：2026/5/69:55
 */
@Mapper
public interface UserInfoMapper {
    UserInfoPO selectByPhone(String phoneSha);

    UserInfoPO selectByEmail(String emailSha);

    void insert(UserInfoPO userInfoPo);
}
