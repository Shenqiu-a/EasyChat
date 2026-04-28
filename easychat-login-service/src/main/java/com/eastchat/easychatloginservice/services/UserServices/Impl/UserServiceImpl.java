package com.eastchat.easychatloginservice.services.UserServices.Impl;

import com.eastchat.easychatloginservice.common.Exception.BizException;
import com.eastchat.easychatloginservice.common.beans.PackedAssert;
import com.eastchat.easychatloginservice.common.enums.ErrorCode;
import com.eastchat.easychatloginservice.common.utils.PhoneNumberValidator;
import com.eastchat.easychatloginservice.domain.request.UserLoginInfoRequest;
import com.eastchat.easychatloginservice.domain.vo.UserBaseResponseVo;
import com.eastchat.easychatloginservice.services.UserServices.UserService;
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
    @Override
    public UserBaseResponseVo login(UserLoginInfoRequest request) {
        // 邮箱和手机号规则验证
        String cellPhoneNumber = request.getCellPhone();
        String email = request.getEmail();
        if (cellPhoneNumber != null && !PhoneNumberValidator.isValida(cellPhoneNumber)) {
            throw new BizException(ErrorCode.USER_MOBILE_ERROR);
        } else if (email != null && !EmailValidator.getInstance().isValid(email)){
            throw new BizException(ErrorCode.USER_EMAIL_ERROR);
        }
        return null;
    }
}
