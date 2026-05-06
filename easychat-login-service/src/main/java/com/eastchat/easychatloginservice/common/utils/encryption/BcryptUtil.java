package com.eastchat.easychatloginservice.common.utils.encryption;

import org.mindrot.jbcrypt.BCrypt;

/**
 * 功能：Bcrypt算法对密码加密及验证
 * 作者：yml
 * 日期：2026/4/3014:20
 */

public class BcryptUtil {
    public static String encryptionPassword(String password) {
        String salt = BCrypt.gensalt(8);
        return BCrypt.hashpw(password, salt);
    }

    public static boolean checkPassword(String password,String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }
}
