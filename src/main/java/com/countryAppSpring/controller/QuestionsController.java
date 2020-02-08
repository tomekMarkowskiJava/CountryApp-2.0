package com.countryAppSpring.controller;

import com.countryAppSpring.model.Country;
import com.countryAppSpring.model.Game;
import com.countryAppSpring.service.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/question")
public class QuestionsController {

    private Game game;
    private CountryRepository repository;




    @Autowired
    public QuestionsController(Game game, CountryRepository repository) {
        this.game = game;
        this.repository = repository;
    }

    @PostMapping
    public RedirectView nextQuestion() {
        return new RedirectView("/question");
    }

    public Game getGame() {
        return game;
    }
}
