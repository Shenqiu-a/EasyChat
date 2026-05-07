package com.eastchat.easychatloginservice.services.UserServices.Impl;

import com.eastchat.easychatloginservice.common.Exception.BizException;
import com.eastchat.easychatloginservice.common.beans.PackedAssert;
import com.eastchat.easychatloginservice.common.enums.LoginErrorCode;
import com.eastchat.easychatloginservice.common.enums.RegisterErrorCode;
import com.eastchat.easychatloginservice.common.utils.encryption.AESUtil;
import com.eastchat.easychatloginservice.common.utils.encryption.BcryptUtil;
import com.eastchat.easychatloginservice.common.utils.PhoneNumberValidator;
import com.eastchat.easychatloginservice.common.utils.encryption.HashUtilSha256;
import com.eastchat.easychatloginservice.domain.request.UserLoginInfoRequest;
import com.eastchat.easychatloginservice.domain.request.UserRegisterInfoRequest;
import com.eastchat.easychatloginservice.domain.vo.UserBaseResponseVo;
import com.eastchat.easychatloginservice.entity.UserInfoPO;
import com.eastchat.easychatloginservice.mapper.UserInfoMapper;
import com.eastchat.easychatloginservice.services.UserServices.UserService;

import java.time.LocalDateTime;
import java.util.*;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Service;

/**
 * 功能：
 * 作者：yml
 * 日期：2026/4/2815:15
 */

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final String SALT = "48e29h#&TY$*(H";

    @Resource
    private UserInfoMapper userInfoMapper;

    @Override
    public UserBaseResponseVo login(UserLoginInfoRequest request) {
        // 邮箱和手机号规则验证
        String cellPhoneNumber = request.getCellPhone();
        String email = request.getEmail();
        if (cellPhoneNumber != null && !PhoneNumberValidator.isValida(cellPhoneNumber)) {
            throw new BizException(LoginErrorCode.USER_MOBILE_ERROR);
        } else if (email != null && !EmailValidator.getInstance().isValid(email)){
            throw new BizException(LoginErrorCode.USER_EMAIL_ERROR);
        }
        // 验证用户名和密码
        UserInfoPO userInfoPO = userInfoMapper.selectByPhone(HashUtilSha256.sha256(cellPhoneNumber + SALT));
        if (userInfoPO == null) {
            throw new BizException(LoginErrorCode.USER_NOT_EXIST);
        }
        if (!BcryptUtil.checkPassword(request.getPassword(), userInfoPO.getPassword())){
            throw new BizException(LoginErrorCode.USER_PASSWORD_ERROR);
        }
        return returnMethod(userInfoPO);
    }

    @Override
    public String register(UserRegisterInfoRequest request) {
        String password = request.getPassword();
        PackedAssert.isNotBlank(password, RegisterErrorCode.USER_PASSWORD_IS_NULL);

        String cellPhoneNumber = request.getCellPhone();
        String email = request.getEmail();
        String nickName = null;
        if (request.getUsername() != null) {
            nickName = request.getUsername();
        } else {
            nickName = "nick_" + UUID.randomUUID().toString().substring(0,7);
        }


        log.info("\nNickName: {}\n",nickName);

        String phoneEncrypt = AESUtil.encryption(cellPhoneNumber);
        String emailEncrypt = AESUtil.encryption(email);
        String passwordBcrypt = BcryptUtil.encryptionPassword(password);

        String phoneSha = HashUtilSha256.sha256(cellPhoneNumber + SALT);
        String emailSha = HashUtilSha256.sha256(email + SALT);

        // 先查后插
        if (userInfoMapper.selectByPhone(phoneSha) != null || userInfoMapper.selectByEmail(emailSha) != null) {
            throw new BizException(RegisterErrorCode.USER_IS_EXIST);
        }

        UserInfoPO userInfoPo = new UserInfoPO();
        userInfoPo.setGuid(UUID.randomUUID().toString());
        userInfoPo.setUserId(UUID.randomUUID().toString());
        userInfoPo.setRoleCode("1");
        userInfoPo.setPhoto("D://url");
        userInfoPo.setUserName(nickName);
        userInfoPo.setStatus("1");
        userInfoPo.setNickName(nickName);
        userInfoPo.setPassword(passwordBcrypt);
        userInfoPo.setPhoneEncrypt(phoneEncrypt);
        userInfoPo.setEmailEncrypt(emailEncrypt);
        userInfoPo.setPhoneHash(phoneSha);
        userInfoPo.setEmailHash(emailSha);
        userInfoPo.setCreateBy("root");
        userInfoPo.setCreateDate(new Date());
        userInfoPo.setUpdateBy("root");
        userInfoPo.setUpdateTime(new Date());
        userInfoPo.setVersion("1");
        log.info("userInfoPo: {}", userInfoPo);
        userInfoMapper.insert(userInfoPo);
        return "注册成功";
    }

    private UserBaseResponseVo returnMethod(UserInfoPO userInfoPO) {
        UserBaseResponseVo userBaseResponseVo = new UserBaseResponseVo();
        // TODO 返回用户信息
        userBaseResponseVo.setUserId(userInfoPO.getUserId());
        userBaseResponseVo.setUserName(userInfoPO.getUserName());
        userBaseResponseVo.setNickName(userInfoPO.getNickName());
        userBaseResponseVo.setCellPhone(AESUtil.encryption(userInfoPO.getPhoneHash()));
        userBaseResponseVo.setEmail(AESUtil.encryption(userInfoPO.getEmailHash()));
        return userBaseResponseVo;
    }
}
