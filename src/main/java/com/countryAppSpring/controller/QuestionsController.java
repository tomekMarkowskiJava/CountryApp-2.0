package com.countryAppSpring.controller;

import com.countryAppSpring.model.Game;
import com.countryAppSpring.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/question")
public class QuestionsController {

    private Game game;
    private Question question;


    @Autowired
    public QuestionsController(Game game, Question question) {
        this.game = game;
        this.question = question;
    }

    @PostMapping
    public RedirectView nextQuestion(String userAnswer) {
        System.out.println("Round: " + game.getRound() +
                ". User answer: " + userAnswer +
                ". Correct answer: " + question.getCorrectAnswer());
        question.checkAnswer(userAnswer);
        System.out.println("Points: " + game.getPoints() + "/" + game.getNumberOfQuestions());
        game.nextRound();
        return new RedirectView("/question");
    }

}
