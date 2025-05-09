package com.k64cnttclc1.springbootblogapp.repository;

import com.k64cnttclc1.springbootblogapp.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findByUrl(String url);

    @Query("SELECT p from Post p where "+
            " p.title like concat('%',:query,'%') OR "+
            " p.shortDescription like concat('%',:query,'%') "
    )
    List<Post> searchPosts(String query);
}
