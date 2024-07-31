package org.choongang.member.services;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.choongang.member.controllers.RequestLogin;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;

public class LoginFailureHandler implements AuthenticationFailureHandler {

    @Override
    // 로그인 실패시에 유입되는 메서드
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        // 세션을 가져와서 모델로 -> 세션을 어케 가져올까?
        HttpSession session = request.getSession();

        RequestLogin form = new RequestLogin();
        form.setEmail(request.getParameter("email"));
        form.setPassword(request.getParameter("password"));

        if (exception instanceof BadCredentialsException) {
            // 아디 또는 비번이 일치하지 않는 경우
            form.setCode("BadCredentials.Login");
        } else if (exception instanceof DisabledException) {
           // 탈퇴한 회원에 대한 예외처리
           form.setCode("Disabled.Login");
        } else if (exception instanceof CredentialsExpiredException) {
            // 비번 유효기간 만료
            form.setCode("CredentialsExpired.Login");
        } else if (exception instanceof AccountExpiredException) {
            // 사용자 계정 유효기간만료
            form.setCode("AccountExpired.Login");
        } else if (exception instanceof LockedException) {
            // 사용자 계정 잠겨있는 경우
            form.setCode("Locked.Login");
        } else {
            form.setCode("Fail.Login");
        }

        form.setDefalutMessage(exception.getMessage());

        form.setSuccess(false);
        session.setAttribute("requestLogin", form);

        // request 와 response 있는 상태에서 페이지 이동 => response의 Redirect사용 = 로그인 실패시 로그인 페이지이동
        response.sendRedirect(request.getContextPath() + "/member/login");
    }
}
