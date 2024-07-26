package org.choongang.global.member.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class MemberController {

    @GetMapping("/join")
    public String join() {
        return "front/member/join"; // PC 화면
    }

    @PostMapping("/join")
    public String joinPs() {

        return "redirect:/member/login/";
    }

    @GetMapping("/login")
    public String login() {
        return "front/member/login";
    }

}
