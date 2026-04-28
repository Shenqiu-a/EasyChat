package com.eastchat.easychatloginservice.common.utils;

import com.eastchat.easychatloginservice.common.Exception.BizException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;

/**
 * 功能：
 * 作者：yml
 * 日期：2026/4/2815:37
 */
public class PhoneNumberValidator {
    private static final PhoneNumberUtil PHONE_NUMBER_UTIL = PhoneNumberUtil.getInstance();

    public static boolean isValida(String phoneNumber) {
        try {
            String isoCountryCodeByPhoneNumber = getIsoCountryCodeByPhoneNumber(phoneNumber);
            Phonenumber.PhoneNumber number = PHONE_NUMBER_UTIL.parse(phoneNumber, isoCountryCodeByPhoneNumber);
            return PHONE_NUMBER_UTIL.isValidNumber(number);
        } catch (Exception e) {
            return false;
        }
    }

    public static String getIsoCountryCodeByPhoneNumber(String phoneNumber) {
        try {
            Phonenumber.PhoneNumber number = PHONE_NUMBER_UTIL.parse(phoneNumber, null);
            return PHONE_NUMBER_UTIL.getRegionCodeForNumber(number);
        } catch (Exception e) {
            return null;
        }
    }
}
