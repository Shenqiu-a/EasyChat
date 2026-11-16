package com.eastchat.easychatloginservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

/**
 * 功能：
 * 作者：yml
 * 日期：2026/6/1510:22
 */
@EnableWebSecurity
@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 禁用CSRF（因为使用JWT，不需要session）
                .csrf(csrf -> csrf.disable())

                // 设置会话管理为无状态
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // 配置授权规则
                .authorizeHttpRequests(auth -> auth
                        // 放行验证码相关接口
                        .requestMatchers("/api/verification/**").permitAll()

                        // 放行登录和注册接口
                        .requestMatchers("/api/auth/login", "/api/auth/register").permitAll()

                        // Swagger文档相关接口放行
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()

                        // 其他所有请求需要认证
                        .anyRequest().authenticated()
                )

                // 显式禁用oauth2 resource server的默认JWT验证
                // 后续可以自定义JWT过滤器来实现token验证
                .oauth2ResourceServer(oauth2 -> oauth2.disable());

        return http.build();
    }
}
