package com.countryAppSpring.controller;

import com.countryAppSpring.model.Country;
import com.countryAppSpring.model.Game;
import com.countryAppSpring.service.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

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

    @GetMapping("/question")
    public String createQuestion(Model model){

        List<Country> countriesPool = countryRepository.findCountriesByRegion(game.getChosenRegion());
        List<String> answers = new ArrayList<>();
        Random random = new Random();

        Country questionCountry = countriesPool.get(random.nextInt(countriesPool.size()));
        answers.add(questionCountry.getCapital());

        for (int i = 0; i < 3; i++) {
            answers.add(countriesPool.get(random.nextInt(countriesPool.size())).getCapital());
        }

        Collections.shuffle(answers);

        model.addAttribute("region", game.getChosenRegion());
        model.addAttribute("country", questionCountry.getName());
        model.addAttribute("answers", answers);


        return "question";
    }

    @GetMapping
    public String menu() throws IOException {
        apiController.downloadData();
        return "index";
    }

    public CountryRepository getCountryRepository() {
        return countryRepository;
    }

    public ApiController getApiController() {
        return apiController;
    }

    public Game getGame() {
        return game;
    }
}
