package com.countryAppSpring.controller;

import com.countryAppSpring.model.Country;
import com.countryAppSpring.model.Game;
import com.countryAppSpring.model.Question;
import com.countryAppSpring.service.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;
import java.util.Random;

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

    @GetMapping("/play")
    public String start() {

        return "play";
    }

    @GetMapping("/question")
    public String createQuestion(Model model) {
        if (game.getRound() > game.getNumberOfQuestions()) {
            model.addAttribute("points", game.getPoints());
            return "result";
        } else {

            List<Country> countriesPool = countryRepository.findCountriesByRegion(game.getChosenRegion());
            Random random = new Random();

            question.setQuestionCountry(countriesPool.remove(random.nextInt(countriesPool.size())));


            for (int i = 0; i < question.getNumberOfAnswers() - 1; i++) {
                String newAnswer = countriesPool.get(random.nextInt(countriesPool.size())).getCapital();
                if (question.addAnswer(newAnswer)) {
                } else {
                    i--;
                }
            }

            question.shuffleAnswers();

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

    public CountryRepository getCountryRepository() {
        return countryRepository;
    }

    public ApiController getApiController() {
        return apiController;
    }

    public Game getGame() {
        return game;
    }

    public Question getQuestion() {
        return question;
    }
}
