package org.choongang.tour.controllers;

import org.choongang.global.exceptions.ExceptionProcessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/tour")
public class TourController implements ExceptionProcessor {

    @GetMapping("/view/{seq}")
    public String view(@PathVariable("id") Long seq, Model model) {

        model.addAttribute("addCommonScript", List.of("map"));
        model.addAttribute("addScript", List.of("tour/view"));

        return "front/tour/view";
    }
}


