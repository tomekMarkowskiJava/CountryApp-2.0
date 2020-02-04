package com.countryAppSpring.controller;

import com.countryAppSpring.model.Game;
import com.countryAppSpring.service.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequestMapping("/")
public class MenuController {

    private CountryRepository countryRepository;
    private ApiController apiController;
    private Game game;


    @Autowired
    public MenuController(CountryRepository countryRepository, ApiController apiController, Game game) {
        this.countryRepository = countryRepository;
        this.apiController = apiController;
        this.game = game;
    }

    @GetMapping("/answers")
    public String showAnswers(Model model) {
        model.addAttribute("countryList", countryRepository.findAll());
        return "answers";
    }

    @GetMapping("/play")
    public String start() {

        return "play";
    }

    @GetMapping
    public String menu() throws IOException {
        apiController.downloadData();
        return "index";
    }



}
