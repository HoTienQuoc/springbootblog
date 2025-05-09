package com.k64cnttclc1.springbootblogapp.mapper;

import com.k64cnttclc1.springbootblogapp.dto.PostDto;
import com.k64cnttclc1.springbootblogapp.entity.Post;

public class PostMapper {
    public static PostDto mapToPostDto(Post post) {
        return PostDto
                .builder()
                .id(post.getId())
                .title(post.getTitle())
                .url(post.getUrl())
                .content(post.getContent())
                .shortDescription(post.getShortDescription())
                .createOn(post.getCreateOn())
                .updateOn(post.getUpdateOn())
                .build();
    }
    public static Post mapToPost(PostDto postDto) {
        return Post
                .builder()
                .id(postDto.getId())
                .title(postDto.getTitle())
                .url(postDto.getUrl())
                .content(postDto.getContent())
                .shortDescription(postDto.getShortDescription())
                .createOn(postDto.getCreateOn())
                .updateOn(postDto.getUpdateOn())
                .build();
    }
}
