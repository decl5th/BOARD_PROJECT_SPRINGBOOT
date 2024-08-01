package org.choongang.global.configs;

import org.choongang.member.services.LoginFailureHandler;
import org.choongang.member.services.LoginSuccessHandler;
import org.choongang.member.services.MemberAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        /* 로그인, 로그아웃 B */
        http.formLogin(f -> {
           f.loginPage("/member/login")
                   .usernameParameter("email")
                   .passwordParameter("password")
                   .successHandler(new LoginSuccessHandler())
                   .failureHandler(new LoginFailureHandler()); // 로그인 설정
        });

        http.logout(f -> {
            f.logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                    .logoutSuccessUrl("/member/login");
        });
        /* 로그인, 로그아웃 D */

        /* 인가(접근 통제) 설정 B */
         /*
            c.requestMatchers("/member/**").anonymous()
                    .requestMatchers("/admin/**").hasAnyAuthority("ADMIN")
                    .anyRequest().authenticated();
            */
        /**
         * This code snippet is configuring the authorization rules for HTTP requests
         * in a Spring Security configuration.
         * The `authorizeHttpRequests` method is used to specify the rules
         * for which requests should be allowed or denied access.
         * The `c` parameter is a lambda expression that represents
         * the configuration for the authorization rules.
         * However, in the provided code snippet, the lambda expression is empty,
         * so no specific authorization rules are defined.
         */
        http.authorizeHttpRequests(c -> {
            c.requestMatchers("/mypage/**").authenticated() // 회원 전용
            .requestMatchers("/admin/**").hasAnyAuthority("ADMIN")
                    .anyRequest().permitAll();
        });
        http.exceptionHandling(c -> {
            c.authenticationEntryPoint(new MemberAuthenticationEntryPoint());
        });
        /* 인가(접근 통제) 설정 D */
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // 비번을 bcrypt로 해시화 변형 후 비번 검증까지
    }
}
