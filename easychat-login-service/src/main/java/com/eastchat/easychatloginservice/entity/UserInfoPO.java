package com.eastchat.easychatloginservice.entity;

import lombok.Data;
import java.util.*;

/**
 * 功能：
 * 作者：yml
 * 日期：2026/5/69:46
 */
@Data
public class UserInfoPO {

    private String guid;

    private String userId;

    private String userName;

    private String nickName;

    private String photo;

    private String phoneEncrypt;

    private String emailEncrypt;

    private String phoneHash;

    private String emailHash;

    private String password;

    private String signature;

    private String status;

    private String roleCode;

    private String createBy;

    private Date createDate;

    private String updateBy;

    private Date updateTime;

    private String version;
}
