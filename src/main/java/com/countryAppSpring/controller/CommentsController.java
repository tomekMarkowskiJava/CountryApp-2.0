package com.countryAppSpring.controller;

import com.countryAppSpring.model.Comment;
import com.countryAppSpring.service.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/comments")
public class CommentsController {

    private CommentRepository commentRepository;

    @Autowired
    public CommentsController(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @PostMapping
    public String addComment(@ModelAttribute("comment") Comment comment, Model model) {
        commentRepository.save(comment);
        model.addAttribute("allComments", commentRepository.findAll());
        return "redirect:/comments";
    }
}
