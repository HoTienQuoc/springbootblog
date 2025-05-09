package com.k64cnttclc1.springbootblogapp.controller;

import com.k64cnttclc1.springbootblogapp.dto.PostDto;
import com.k64cnttclc1.springbootblogapp.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class BlogController {
    private PostService postService;

    public BlogController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/")
    public String viewBlogPosts(Model model) {
        List<PostDto> postsResponse = postService.findAllPosts();
        model.addAttribute("postsResponse", postsResponse);
        return "view_posts";
    }
}
