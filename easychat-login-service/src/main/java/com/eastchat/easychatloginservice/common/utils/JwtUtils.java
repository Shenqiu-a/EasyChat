package com.eastchat.easychatloginservice.common.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import java.util.*;
/**
 * 功能：
 * 作者：yml
 * 日期：2026/5/209:59
 */

public class JwtUtils {

    private static final long ACCESS_EXPIRATION_TIME = 1000 * 60 * 30;

    private static final long REFRESH_EXPIRATION_TIME = 1000 * 60 * 60 * 24 * 7;

    public String generateAccessToken(String userId){
        return Jwts.builder()
                .setSubject(userId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_EXPIRATION_TIME))
                .signWith(io.jsonwebtoken.SignatureAlgorithm.HS256,getSecretKey())
                .compact();
    }

    public String generateRefreshToken(String userId){
        return Jwts.builder()
                .setSubject(userId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_EXPIRATION_TIME))
                .compact();
    }

    private static SecretKey getSecretKey(){
        return Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }
}
