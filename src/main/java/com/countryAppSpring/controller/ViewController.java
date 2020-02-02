package com.countryAppSpring.controller;

import com.countryAppSpring.service.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequestMapping("/")
public class ViewController {

    private CountryRepository countryRepository;
    private ApiController apiController;

    @Autowired
    public ViewController(CountryRepository countryRepository, ApiController apiController) {
        this.countryRepository = countryRepository;
        this.apiController = apiController;
    }

    @GetMapping("/answers")
    public String showAnswers(Model model){
        model.addAttribute("countryList", countryRepository.findAll());
        return "answers";
    }

    @GetMapping
    public String menu() throws IOException {
        apiController.downloadData();
        return "index";
    }
}
