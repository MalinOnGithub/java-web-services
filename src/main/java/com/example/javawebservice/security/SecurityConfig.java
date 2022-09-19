package com.example.javawebservice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

//Klass för att konfigurera hur spring security funkar
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService){
     this.userDetailsService = userDetailsService;

    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity)throws Exception{
        httpSecurity
                .csrf().disable()
                .authorizeRequests(auth -> auth
                        .antMatchers("/api/auth/**").permitAll()
                        .anyRequest().authenticated())
                .userDetailsService(userDetailsService)
                .httpBasic();
                return httpSecurity.build();
    }
}
