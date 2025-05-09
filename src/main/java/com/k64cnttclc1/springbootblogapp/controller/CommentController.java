package com.k64cnttclc1.springbootblogapp.controller;

import com.k64cnttclc1.springbootblogapp.dto.CommentDto;
import com.k64cnttclc1.springbootblogapp.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CommentController {
    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/{postUrl}/comments")
    public String addComment(@PathVariable("postUrl") String postUrl,
                             @ModelAttribute("comment")CommentDto commentDto, Model model) {
        commentService.createComment(postUrl, commentDto);
        return "redirect:/posts/" + postUrl;
    }
}
