package com.k64cnttclc1.springbootblogapp.config;

import com.k64cnttclc1.springbootblogapp.entity.Role;
import com.k64cnttclc1.springbootblogapp.entity.User;
import com.k64cnttclc1.springbootblogapp.repository.RoleRepository;
import com.k64cnttclc1.springbootblogapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional; // Thêm import

import java.util.Arrays;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional // Quan trọng để đảm bảo các thao tác DB là nhất quán
    public void run(String... args) throws Exception {
        // Tạo role ROLE_ADMIN nếu chưa có
        Role adminRole = roleRepository.findByName("ROLE_ADMIN");
        if (adminRole == null) {
            adminRole = new Role();
            adminRole.setName("ROLE_ADMIN");
            roleRepository.save(adminRole);
            System.out.println("INFO: ROLE_ADMIN was not found and has been created by DataInitializer.");
        }

        // Tạo role ROLE_GUEST nếu chưa có
        Role guestRole = roleRepository.findByName("ROLE_GUEST");
        if (guestRole == null) {
            guestRole = new Role();
            guestRole.setName("ROLE_GUEST");
            roleRepository.save(guestRole);
            System.out.println("INFO: ROLE_GUEST was not found and has been created by DataInitializer.");
        }

        // Tạo user admin nếu chưa có
        if (userRepository.findByEmail("hotienquoc0429@gmail.com") == null) {
            User adminUser = new User();
            adminUser.setName("Admin User");
            adminUser.setEmail("hotienquoc0429@gmail.com");
            adminUser.setPassword(passwordEncoder.encode("Quoc")); // Mật khẩu bạn muốn, sẽ được mã hóa
            adminUser.setRoles(Arrays.asList(adminRole)); // Gán ROLE_ADMIN
            userRepository.save(adminUser);
            System.out.println("INFO: Created ADMIN user: hotienquoc0429@gmail.com by DataInitializer.");
        }
    }
}