package com.k64cnttclc1.springbootblogapp.mapper;

import com.k64cnttclc1.springbootblogapp.dto.CommentDto;
import com.k64cnttclc1.springbootblogapp.dto.PostDto;
import com.k64cnttclc1.springbootblogapp.entity.Comment;
import com.k64cnttclc1.springbootblogapp.entity.Post;

public class CommentMapper {
    public static CommentDto mapToCommentDto(Comment comment) {
        return CommentDto
                .builder()
                .id(comment.getId())
                .name(comment.getName())
                .content(comment.getContent())
                .createdOn(comment.getCreatedOn())
                .updatedOn(comment.getUpdatedOn())
                .build();
    }
    public static Comment mapToComment(CommentDto commentDto) {
        return Comment
                .builder()
                .id(commentDto.getId())
                .name(commentDto.getName())
                .content(commentDto.getContent())
                .createdOn(commentDto.getCreatedOn())
                .updatedOn(commentDto.getUpdatedOn())
                .build();
    }
}
