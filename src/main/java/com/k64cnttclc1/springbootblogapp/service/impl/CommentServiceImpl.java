package com.k64cnttclc1.springbootblogapp.service.impl;


import com.k64cnttclc1.springbootblogapp.dto.CommentDto;
import com.k64cnttclc1.springbootblogapp.entity.Comment;
import com.k64cnttclc1.springbootblogapp.entity.Post;
import com.k64cnttclc1.springbootblogapp.entity.User;
import com.k64cnttclc1.springbootblogapp.mapper.CommentMapper;
import com.k64cnttclc1.springbootblogapp.repository.CommentRepository;
import com.k64cnttclc1.springbootblogapp.repository.PostRepository;
import com.k64cnttclc1.springbootblogapp.repository.UserRepository;
import com.k64cnttclc1.springbootblogapp.service.CommentService;
import com.k64cnttclc1.springbootblogapp.util.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private PostRepository postRepository;
    private UserRepository userRepository;

    public CommentServiceImpl(CommentRepository commentRepository,
                              PostRepository postRepository,
                              UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void createComment(String postUrl, CommentDto commentDto) {

        Post post = postRepository.findByUrl(postUrl).get();
        Comment comment = CommentMapper.mapToComment(commentDto);
        comment.setPost(post);
        commentRepository.save(comment);
    }

    @Override
    public List<CommentDto> findAllComments() {
        List<Comment> comments = commentRepository.findAll();
        return comments.stream()
                .map(CommentMapper::mapToCommentDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }

    @Override
    public List<CommentDto> findCommentsByPost() {
        String email = SecurityUtils.getCurrentUser().getUsername();
        User createdBy = userRepository.findByEmail(email);
        Long userId = createdBy.getId();
        List<Comment> comments = commentRepository.findCommentsByPost(userId);
        return comments.stream()
                .map((comment) -> CommentMapper.mapToCommentDto(comment))
                .collect(Collectors.toList());
    }
}
