package com.countryAppSpring.controller;

import com.countryAppSpring.model.Game;
import com.countryAppSpring.model.Question;
import com.countryAppSpring.service.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequestMapping("/")
public class GameController {

    private CountryRepository countryRepository;
    private ApiController apiController;
    private Game game;

    private Question question;


    @Autowired
    public GameController(CountryRepository countryRepository, ApiController apiController, Game game, Question question) {
        this.countryRepository = countryRepository;
        this.apiController = apiController;
        this.game = game;
        this.question = question;
    }

    @GetMapping("/answers")
    public String showAnswers(Model model) {
        model.addAttribute("countryList", countryRepository.findAll());
        return "answers";
    }

    @GetMapping("/chooseregion")
    public String start() {

        return "chooseregion";
    }

    @GetMapping("/question")
    public String play(Model model) {
        if (game.getRound() > game.getNumberOfQuestions()) {
            model.addAttribute("points", game.getPoints());
            model.addAttribute("result", game.getResult());
            return "result";
        } else {

            question.createQuestion();

            model.addAttribute("region", game.getChosenRegion());
            model.addAttribute("country", question.getQuestionCountry().getName());
            model.addAttribute("answers", question.getAnswers());


            return "question";
        }
    }

    @GetMapping
    public String menu() throws IOException {
        apiController.downloadData();
        return "index";
    }

}
