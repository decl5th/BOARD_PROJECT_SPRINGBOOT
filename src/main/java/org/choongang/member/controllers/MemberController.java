package org.choongang.member.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.choongang.member.services.MemberSaveService;
import org.choongang.member.validators.JoinValidator;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final JoinValidator joinValidator;
    private  final MemberSaveService memberSaveService;

    @GetMapping("/join")
    public String join(@ModelAttribute RequestJoin form) {
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
    public String login() {

        return "front/member/login";
    }
}