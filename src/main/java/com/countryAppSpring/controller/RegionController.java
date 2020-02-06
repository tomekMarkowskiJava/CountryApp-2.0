package com.countryAppSpring.controller;

import com.countryAppSpring.model.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
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
    public String chooseRegion(String region, Model model) {
        model.addAttribute("region", region);
        game.setChosenRegion(region);
        System.out.println(game.getChosenRegion());
        return "question";
    }



}
