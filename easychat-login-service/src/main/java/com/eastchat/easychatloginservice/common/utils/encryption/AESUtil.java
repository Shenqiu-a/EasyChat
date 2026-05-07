package com.eastchat.easychatloginservice.common.utils.encryption;

import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/**
 * 功能：AES加密工具
 * 作者：yml
 * 日期：2026/4/3014:59
 */

public class AESUtil {
    private static final String AES_KEY = "1234567890123456";

    public static String encryption(String data) {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            SecretKeySpec secretKeySpec = new SecretKeySpec(AES_KEY.getBytes(), "AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            byte[] encrypted  = cipher.doFinal(data.getBytes());
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception e) {
            throw new RuntimeException("加密失败");
        }
    }

    public static final String decryption(String encryptedData) {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            SecretKeySpec secretKeySpec = new SecretKeySpec(AES_KEY.getBytes(), "AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            byte[] decrypted = Base64.getDecoder().decode(encryptedData);
            return new String(cipher.doFinal(decrypted));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
