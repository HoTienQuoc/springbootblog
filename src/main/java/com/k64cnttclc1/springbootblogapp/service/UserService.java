package com.k64cnttclc1.springbootblogapp.service;


import com.k64cnttclc1.springbootblogapp.dto.RegistrationDto;
import com.k64cnttclc1.springbootblogapp.entity.User;

public interface UserService {
    void saveUser(RegistrationDto registrationDto);

    User findByEmail(String email);
}
