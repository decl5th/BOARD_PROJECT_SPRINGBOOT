package org.choongang.main.controllers;

import org.choongang.global.exceptions.ExceptionProcessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController implements ExceptionProcessor {
    //메인 페이지
    @GetMapping
    public String index() {


        return "front/main/index";
    }
}
