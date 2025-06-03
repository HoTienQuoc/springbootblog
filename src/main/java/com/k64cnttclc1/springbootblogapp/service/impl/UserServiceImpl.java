package com.k64cnttclc1.springbootblogapp.service.impl;

import com.k64cnttclc1.springbootblogapp.dto.RegistrationDto;
import com.k64cnttclc1.springbootblogapp.entity.Role;
import com.k64cnttclc1.springbootblogapp.entity.User;
import com.k64cnttclc1.springbootblogapp.repository.RoleRepository;
import com.k64cnttclc1.springbootblogapp.repository.UserRepository;
import com.k64cnttclc1.springbootblogapp.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // Thêm import này

import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional // Nên thêm @Transactional để đảm bảo tính nhất quán khi thao tác với nhiều repository
    public void saveUser(RegistrationDto registrationDto) {
        User user = new User();
        user.setName(registrationDto.getFirstName() + " " + registrationDto.getLastName());
        user.setEmail(registrationDto.getEmail());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));

        // Tìm hoặc tạo ROLE_GUEST
        Role roleGuest = roleRepository.findByName("ROLE_GUEST");
        if (roleGuest == null) {
            roleGuest = new Role();
            roleGuest.setName("ROLE_GUEST");
            roleGuest = roleRepository.save(roleGuest); // Lưu và lấy lại đối tượng đã được quản lý bởi JPA
            System.out.println("INFO: ROLE_GUEST was not found and has been created.");
        }
        // Gán vai trò cho người dùng
        user.setRoles(Arrays.asList(roleGuest));
        userRepository.save(user);
        System.out.println("INFO: User " + user.getEmail() + " registered with role " + roleGuest.getName());
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}