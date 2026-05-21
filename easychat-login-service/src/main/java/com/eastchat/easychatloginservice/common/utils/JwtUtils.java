package com.eastchat.easychatloginservice.common.utils;

import com.eastchat.easychatloginservice.common.Exception.BizException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.SecretKey;
import java.util.*;
/**
 * 功能：
 * 作者：yml
 * 日期：2026/5/209:59
 */

@Slf4j
public class JwtUtils {

    private static final long ACCESS_EXPIRATION_TIME = 1000 * 60 * 30;

    private static final long REFRESH_EXPIRATION_TIME = 1000 * 60 * 60 * 24 * 7;

    private static final long RENEWAL_TIME = 1000 * 60 * 5;

    private static final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public static String generateAccessToken(String userId){
        return Jwts.builder()
                .setSubject(userId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_EXPIRATION_TIME))
                .signWith(io.jsonwebtoken.SignatureAlgorithm.HS256,SECRET_KEY)
                .compact();
    }

    public static String generateRefreshToken(String userId){
        return Jwts.builder()
                .setSubject(userId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_EXPIRATION_TIME))
                .signWith(io.jsonwebtoken.SignatureAlgorithm.HS256,SECRET_KEY)
                .compact();
    }

    public String AutoRenewalToken(String OldAccessToken, String OldRefreshToken){
        Long expirationTime = getExpirationTime(OldAccessToken);
        if (expirationTime - System.currentTimeMillis() < RENEWAL_TIME) {
            return JwtUtils.generateAccessToken(getUserId(OldAccessToken));
        }
        return null;
    }

    public String getUserId(String oldAccessToken) {
        try {
            return Jwts.parser()
                    .verifyWith(SECRET_KEY)
                    .build()
                    .parseSignedClaims(oldAccessToken)
                    .getPayload()
                    .getSubject();
        } catch (Exception e) {
            throw new BizException("解析token失败",e);
        }
    }

    public Long getExpirationTime(String token){
        try {
            return Jwts.parser()
                    .verifyWith(SECRET_KEY)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload()
                    .getExpiration()
                    .getTime();
        } catch (Exception e) {
            throw new BizException("解析token失败",e);
        }
    }
}
