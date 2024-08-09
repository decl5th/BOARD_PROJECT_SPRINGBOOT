package org.choongang.member.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.choongang.board.repositories.BoardRepository;
import org.choongang.global.Utils;
import org.choongang.global.exceptions.ExceptionProcessor;
import org.choongang.member.MemberUtil;
import org.choongang.member.services.MemberSaveService;
import org.choongang.member.validators.JoinValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
@SessionAttributes("requestLogin")
public class MemberController implements ExceptionProcessor {

    private final JoinValidator joinValidator;
    private final MemberSaveService memberSaveService;
    private final MemberUtil memberUtil;
    private final BoardRepository boardRepository;
    private final Utils utils;

    @ModelAttribute
    public RequestLogin getRequestLogin() {
        return new RequestLogin();
    }

    @GetMapping("/join")
    public String join(@ModelAttribute RequestJoin form, Model model) {
        commonProcess("join", model);
        /*
        boolean result = false;
        if (!result) {
            throw new AlertRedirectException("테스트 예외", "/mypage", HttpStatus.BAD_REQUEST);
        }

         */

        return utils.tpl("member/join");
    }

    @PostMapping("/join")
    public String joinPs(@Valid RequestJoin form, Errors errors, Model model) {
        commonProcess("join", model);

        joinValidator.validate(form, errors);

        if (errors.hasErrors()) {
            return utils.tpl("member/join");
        }

        // 서비스 개발 코드 입력 공간 -> 회원 추가, 수정 가능토록
        // jpa는 수정 시 무조건 조회를 먼저 하고 상태를 수정 및 삭제 진행
        memberSaveService.save(form); // 회원 가입 처리

        return "redirect:/member/login";
    }

    @GetMapping("/login")
    public String login(@Valid @ModelAttribute RequestLogin form, Errors errors, Model model) {
        commonProcess("login", model);

        //세션 범위에서 유지 시키도록 설정 필요
        String code = form.getCode();
        if (StringUtils.hasText(code)) {
            errors.reject(code, form.getDefalutMessage());

            // 비번 만료인 겨우 비번 재설정 페이지 이동
            if (code.equals("CredentialsExpired.Login")) {
                return "redirect:/member/password/reset";
            }
        }

        return utils.tpl("member/login");
    }

    /**
     * 회원 관련 컨트롤러 공통 처리
     *
     * @param mode
     * @param model
     */
  private void commonProcess(String mode, Model model) {
    mode = Objects.requireNonNullElse(mode, "join");

      List<String> addCss = new ArrayList<>();
      List<String> addCommonScript = new ArrayList<>();
      List<String> addScript = new ArrayList<>();

      addCss.add("member/style"); // 회원 공통 스타일
      if (mode.equals("join")) {
          addCss.add("member/join");
          addCommonScript.add("fileManager");
          addScript.add("member/join");

      } else if (mode.equals("login")) {
          addCss.add("member/login");
      }

      model.addAttribute("addCss", addCss);
      model.addAttribute("addCommonScript", addCommonScript);
      model.addAttribute("addScript", addScript);
  }
}