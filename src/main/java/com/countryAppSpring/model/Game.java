package com.countryAppSpring.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class Game {
    private int points;
    private int round;
    private int numberOfQuestions = 10;
    private String chosenRegion;

    public Game() {
    }

    private void reset() {
        points = 0;
        round = 1;
    }

    public void nextRound(){
        round++;
    }

    void addPoint() {
        points++;
    }

    public int getPoints() {
        return points;
    }

    public int getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public String getChosenRegion() {
        return chosenRegion;
    }

    public void setChosenRegion(String chosenRegion) {
        reset();
        this.chosenRegion = chosenRegion;
    }

    public int getRound() {
        return round;
    }
}
