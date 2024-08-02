package org.choongang.member.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.choongang.board.repositories.BoardRepository;
import org.choongang.global.exceptions.ExceptionProcessor;
import org.choongang.member.MemberUtil;
import org.choongang.member.services.MemberSaveService;
import org.choongang.member.validators.JoinValidator;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
@SessionAttributes("requestLogin")
public class MemberController implements ExceptionProcessor {

    private final JoinValidator joinValidator;
    private  final MemberSaveService memberSaveService;
    private final MemberUtil memberUtil;
    private final BoardRepository boardRepository;

    @ModelAttribute
    public RequestLogin getRequestLogin() {
        return new RequestLogin();
    }

    @GetMapping("/join")
    public String join(@ModelAttribute RequestJoin form) {
        /*
        boolean result = false;
        if (!result) {
            throw new AlertRedirectException("테스트 예외", "/mypage", HttpStatus.BAD_REQUEST);
        }

         */

        return "front/member/join";
    }

    @PostMapping("/join")
    public String joinPs(@Valid RequestJoin form, Errors errors) {

        joinValidator.validate(form, errors);

        if (errors.hasErrors()) {
            return "front/member/join";
        }

        // 서비스 개발 코드 입력 공간 -> 회원 추가, 수정 가능토록
        // jpa는 수정 시 무조건 조회를 먼저 하고 상태를 수정 및 삭제 진행
        memberSaveService.save(form); // 회원 가입 처리

        return "redirect:/member/login";
    }

    @GetMapping("/login")
    public String login(@Valid @ModelAttribute RequestLogin form, Errors errors) {
        // 세션 범위에서 유지 시키도록 설정 필요
        String code = form.getCode();
        if (StringUtils.hasText(code)) {
            errors.reject(code, form.getDefalutMessage());

            // 비번 만료인 겨우 비번 재설정 페이지 이동
            if (code.equals("CredentialsExpired.Login")) {
                return "redirect:/member/password/reset";
            }
        }

        return "front/member/login";
    }

    @ResponseBody
    @GetMapping("/test1")
    @PreAuthorize("isAuthenticated()")
    public void test1() {
        log.info("test1 - 회원만 접근 가능");
    }

    @ResponseBody
    @GetMapping("/test2")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public void test2() {
        log.info("test2 - 관리자만 접근 가능");
    }
}