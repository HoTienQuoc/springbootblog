package com.k64cnttclc1.springbootblogapp.controller;

import com.k64cnttclc1.springbootblogapp.dto.PostDto;
import com.k64cnttclc1.springbootblogapp.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

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
        return "blog/view_posts";
    }

    @GetMapping("/post/{postUrl}")
    private String showPost(@PathVariable("postUrl") String postUrl, Model model) {
        PostDto postDto = postService.findPostByUrl(postUrl);
        model.addAttribute("post", postDto);
        return "blog/blog_post";
    }

    @GetMapping("/page/search")
    public String searchPosts(@RequestParam (value="query") String query, Model model) {
        List<PostDto> postsResponse = postService.searchPosts(query);
        model.addAttribute("postsResponse", postsResponse);
        return "blog/view_posts";
    }
}
