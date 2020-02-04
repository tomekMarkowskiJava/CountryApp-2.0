package com.countryAppSpring.controller;

import com.countryAppSpring.model.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/play")
public class RegionController {

    private Game game;

    @Autowired
    public RegionController(Game game) {
        this.game = game;
    }

    @PostMapping
    public String chooseRegion(String region) {
        System.out.println(region);
        game.setChosenRegion(region);
        return ("/question");
    }
}
