package com.k64cnttclc1.springbootblogapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSpringSecurity {

    private UserDetailsService userDetailsService; // Đây sẽ là CustomUserDetailsService của bạn

    // Constructor Injection cho UserDetailsService
    public WebSpringSecurity(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorize) ->
                        authorize.requestMatchers(new AntPathRequestMatcher("/resources/**")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/register/**")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/admin/**"))
                                .hasAnyRole("ADMIN", "GUEST") // Spring Security tự thêm tiền tố "ROLE_"
                                .requestMatchers(new AntPathRequestMatcher("/")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/post/**")).permitAll()
                                .anyRequest().authenticated()
                )
                .formLogin( form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/admin/posts") // URL sau khi đăng nhập thành công
                        .loginProcessingUrl("/login")      // URL xử lý thông tin đăng nhập (phải khớp với action của form)
                        .permitAll()
                ).logout( logout -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .permitAll()
                );
        return http.build();
    }

    // PHẦN SỬA ĐỔI QUAN TRỌNG Ở ĐÂY:
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // Cấu hình AuthenticationManagerBuilder để sử dụng UserDetailsService của bạn
        // và PasswordEncoder để xác thực người dùng từ cơ sở dữ liệu.
        auth
                .userDetailsService(userDetailsService)  // Sử dụng CustomUserDetailsService để tải thông tin người dùng
                .passwordEncoder(passwordEncoder());     // Sử dụng BCryptPasswordEncoder để so sánh mật khẩu
    }
}