package com.k64cnttclc1.springbootblogapp.service;

import com.k64cnttclc1.springbootblogapp.dto.PostDto;

import java.util.List;

public interface PostService {
    List<PostDto> getAllPosts();
    void createPost(PostDto postDto);
    PostDto findPostById(Long id);
    void updatePost(PostDto postDto);
    void deletePost(Long id);

    PostDto findPostByUrl(String postUrl);
    List<PostDto> searchPosts(String query);
}
