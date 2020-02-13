package com.countryAppSpring.model;

import com.countryAppSpring.service.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope("singleton")
public class Game {
    private int points;
    private int round;
    private int numberOfQuestions = 10;
    private String chosenRegion;
    List<Country> countriesPool;
    CountryRepository countryRepository;


    private String result;

    public String getResult() {
        if (points > 6) {
            result = "Great work! You know the capitals of the countries!";
        } else if (points > 3) {
            result = "Not bad. You know some capitals. But you can still do better.";
        } else if (points > 0) {
            result = "Unfortunatelly, You know only few capitals...";
        } else {
            result = "Really? You don't know any capitals?";
        }
        return result;
    }

    @Autowired
    public Game(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;

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
        countriesPool = countryRepository.findCountriesByRegion(getChosenRegion());
    }

    public int getRound() {
        return round;
    }
}
