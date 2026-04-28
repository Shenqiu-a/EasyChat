package com.eastchat.easychatloginservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 登录注册服务启动类
 */
@SpringBootApplication
public class EasychatLoginServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EasychatLoginServiceApplication.class, args);
        StringBuilder sb = new StringBuilder();
        sb.append("===========================================================");
        sb.append("\n");
        sb.append("==================== 登录注册服务启动成功! ====================");
        sb.append("\n");
        sb.append("===========================================================");
        System.out.println(sb);
    }

}
