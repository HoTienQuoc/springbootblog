package com.k64cnttclc1.springbootblogapp.service.impl;

import com.k64cnttclc1.springbootblogapp.dto.CommentDto;
import com.k64cnttclc1.springbootblogapp.entity.Comment;
import com.k64cnttclc1.springbootblogapp.entity.Post;
import com.k64cnttclc1.springbootblogapp.mapper.CommentMapper;
import com.k64cnttclc1.springbootblogapp.repository.CommentRepository;
import com.k64cnttclc1.springbootblogapp.repository.PostRepository;
import com.k64cnttclc1.springbootblogapp.service.CommentService;

public class CommentServiceImpl implements CommentService {
    private CommentRepository commentRepository;
    private PostRepository postRepository;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @Override
    public void createComment(String postUrl, CommentDto commentDto) {
        Post post = postRepository.findByUrl(postUrl).get();
        Comment comment = CommentMapper.mapToComment(commentDto);
        comment.setPost(post);
        commentRepository.save(comment);
    }
}
