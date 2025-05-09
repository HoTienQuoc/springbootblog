package com.k64cnttclc1.springbootblogapp.repository;

import com.k64cnttclc1.springbootblogapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
