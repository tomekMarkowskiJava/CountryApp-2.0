package com.countryAppSpring.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class Question {

    private Game game;
    private Country questionCountry;
    private String correctAnswer;
    private List<String> answers = new ArrayList<>();
    private int numberOfAnswers = 4;

    public Question(Game game) {
        this.game = game;
    }

    public boolean addAnswer(String answer){
        if (!answers.contains(answer)){
            answers.add(answer);
            return true;
        } else {
            return false;
        }
   }

    public void shuffleAnswers(){
        Collections.shuffle(answers);
    }

    public void setQuestionCountry(Country questionCountry) {
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
