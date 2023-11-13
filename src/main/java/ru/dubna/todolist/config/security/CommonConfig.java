package ru.dubna.todolist.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableJpaAuditing
@RequiredArgsConstructor
public class CommonConfig {
//    private final AuthenticationConfiguration configuration;
//
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
//
//    @Bean
//    AuthenticationManager authenticationManager() throws Exception {
//        return configuration.getAuthenticationManager();
//    }
//
//    @Bean
//    UsernamePasswordAuthenticationFilter authenticationFilter() throws Exception {
//        return new UsernamePasswordAuthenticationFilter(authenticationManager());
//    }
}
