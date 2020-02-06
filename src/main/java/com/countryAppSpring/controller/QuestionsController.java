package com.countryAppSpring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/question")
public class QuestionsController {

    @PostMapping
    public String nextQuestion() {
        System.out.println("Next question");
        return "/question";
    }
}
