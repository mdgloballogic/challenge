package com.bci.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.cors().disable()
                .csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("api/v1/user/**")
                .permitAll()
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/h2-console/")
                .permitAll()
                .and()
                .headers().frameOptions().disable().and()
                .build();

    }
}
