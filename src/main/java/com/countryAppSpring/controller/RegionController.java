package com.countryAppSpring.controller;

import com.countryAppSpring.model.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/chooseregion")
public class RegionController {

    private Game game;


    @Autowired
    public RegionController(Game game) {
        this.game = game;

    }

    @PostMapping
    public RedirectView chooseRegion(String region, Model model) {
        model.addAttribute("region", region);
        game.setChosenRegion(region);
        return new RedirectView("/question");
    }
}
