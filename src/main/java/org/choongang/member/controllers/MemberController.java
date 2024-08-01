package org.choongang.member.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.choongang.board.entities.Board;
import org.choongang.board.repositories.BoardRepository;
import org.choongang.global.exceptions.ExceptionProcessor;
import org.choongang.global.exceptions.script.AlertRedirectException;
import org.choongang.member.MemberInfo;
import org.choongang.member.MemberUtil;
import org.choongang.member.services.MemberSaveService;
import org.choongang.member.validators.JoinValidator;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

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
        boolean result = false;
        if (!result) {
            throw new AlertRedirectException("테스트 예외", "/mypage", HttpStatus.BAD_REQUEST);
        }

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
    @GetMapping("/test")
    public void test(Principal principal){
        log.info("로그인 아이디: {}", principal.getName());
    }

    @ResponseBody
    @GetMapping("/test2")
    public void test2(@AuthenticationPrincipal MemberInfo memberInfo) {
        log.info("로그인 회원: {}", memberInfo.toString());
    }

    @ResponseBody
    @GetMapping("/test3")
    public void test3() {
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();


      log.info("로그인 상태: {}", authentication.isAuthenticated());
      if (authentication.isAuthenticated() && authentication.getPrincipal() instanceof MemberInfo) {
          // 로그인 상태 - UserDetails 구현체 (getPrincipal())
            MemberInfo memberInfo = (MemberInfo) authentication.getPrincipal();
            log.info("로그인 회원: {}", memberInfo.toString());
      } else { // 미로그인 상태 - String / anonymousUser (getPrincipal())
          log.info("getPrincipal(): {}", authentication.getPrincipal());

      }
    }

    @ResponseBody
    @GetMapping("/test4")
    public void test4() {
        log.info("로그인 여부: {}", memberUtil.isLogin());
        log.info("로그인 회원: {}", memberUtil.getMember());
    }

    @ResponseBody
    @GetMapping("/test5")
    public void test5() {
        /*
        Board board = Board.builder()
                .bid("freetalk")
                .bname("자게")
                .build();
                */
        Board board = boardRepository.findById("freetalk").orElse(null);
        board.setBname("(수정)자유게시판");
        boardRepository.saveAndFlush(board);

    }
}