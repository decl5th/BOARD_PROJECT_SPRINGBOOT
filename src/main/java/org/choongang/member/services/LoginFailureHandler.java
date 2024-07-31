package org.choongang.member.services;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;

public class LoginFailureHandler implements AuthenticationFailureHandler {

    @Override
    // 로그인 실패시에 유입되는 메서드
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        // request 와 response 있는 상태에서 페이지 이동 => response의 Redirect사용 = 로그인 실패시 로그인 페이지이동
        response.sendRedirect(request.getContextPath() + "/member/login");
    }
}
