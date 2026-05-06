package com.eastchat.easychatloginservice.common.utils.encryption;

import java.security.MessageDigest;

/**
 * 功能：SHA-256 加密
 * 作者：yml用于查询
 * 日期：2026/5/212:37
 */

public class HashUtilSha256 {
    public static String sha256(String data){
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(data.getBytes());
            StringBuilder stringBuilder = new StringBuilder();
            for (byte b : hash) {
                String s = Integer.toHexString(0xff & b);
                if (s.length() == 1) {
                    stringBuilder.append('0');
                }
                stringBuilder.append(s);
            }
            return stringBuilder.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
