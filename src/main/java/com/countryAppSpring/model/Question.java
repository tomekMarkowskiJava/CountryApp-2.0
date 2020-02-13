package com.countryAppSpring.model;

import com.countryAppSpring.service.CountryRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Component
public class Question {

    private Game game;
    private CountryRepository countryRepository;
    private Country questionCountry;
    private String correctAnswer;
    private List<String> answers = new ArrayList<>();
    private int numberOfAnswers = 4;

    public Question(Game game, CountryRepository countryRepository) {
        this.game = game;
        this.countryRepository = countryRepository;
    }

    public void createQuestion() {

        Random random = new Random();

        setQuestionCountry(game.countriesPool.get(random.nextInt(game.countriesPool.size())));
        game.countriesPool.remove(getQuestionCountry());



        for (int i = 0; i < getNumberOfAnswers() - 1; i++) {
            String newAnswer = game.countriesPool.get(random.nextInt(game.countriesPool.size())).getCapital();
            if (!addAnswer(newAnswer)) {
                i--;
            }
        }
        shuffleAnswers();
    }

    private boolean addAnswer(String answer) {
        if (!answers.contains(answer)){
            answers.add(answer);
            return true;
        } else {
            return false;
        }
   }

    private void shuffleAnswers() {
        Collections.shuffle(answers);
    }

    private void setQuestionCountry(Country questionCountry) {
        this.questionCountry = questionCountry;
        answers.clear();
        setCorrectAnswer(questionCountry.getCapital());
    }

    public void checkAnswer(String answer){
       if (answer.equals(correctAnswer)){
           game.addPoint();
       }
    }

    private void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
        addAnswer(correctAnswer);
    }

    public Country getQuestionCountry() {
        return questionCountry;
    }

    public int getNumberOfAnswers() {
        return numberOfAnswers;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }
}
