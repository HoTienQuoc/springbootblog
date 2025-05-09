package com.k64cnttclc1.springbootblogapp.repository;

import com.k64cnttclc1.springbootblogapp.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
