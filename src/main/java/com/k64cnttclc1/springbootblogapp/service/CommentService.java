package com.k64cnttclc1.springbootblogapp.service;

import com.k64cnttclc1.springbootblogapp.dto.CommentDto;

public interface CommentService {
    void createComment(String postUrl,CommentDto commentDto);
}
