package com.example.javawebservice.security;

import com.example.javawebservice.enteties.Role;
import com.example.javawebservice.service.AuthService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//Klass för att konfigurera hur spring security funkar
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig {
    private final UserDetailsService userDetailsService;
    private final JwtRequestFilter jwtRequestFilter;
    public SecurityConfig(UserDetailsService userDetailsService, JwtRequestFilter jwtRequestFilter){
     this.userDetailsService = userDetailsService;
     this.jwtRequestFilter = jwtRequestFilter;
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
                        .antMatchers("/api/users/**/posts").hasRole(Role.ADMIN.toString())
                        .antMatchers("/api/appusers/{id}").access("@authService.isCorrectUserLogin(#id)")
                        .antMatchers("/api/appusers/{id}").hasRole(Role.ADMIN.toString())
                        .anyRequest().authenticated())
                .userDetailsService(userDetailsService)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
                return httpSecurity.build();
    }
}
