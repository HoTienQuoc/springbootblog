package com.k64cnttclc1.springbootblogapp.repository;

import com.k64cnttclc1.springbootblogapp.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findByUrl(String title);
}
