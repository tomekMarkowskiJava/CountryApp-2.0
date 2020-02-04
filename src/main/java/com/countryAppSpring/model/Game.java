package com.countryAppSpring.model;

import org.springframework.stereotype.Component;

@Component
public class Game {
    private int points = 0;
    private int round;
    private int numberOfQuestions = 10;
    private String chosenRegion;


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
        this.chosenRegion = chosenRegion;
    }
}
