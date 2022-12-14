package com.example.javawebservice.service;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
@Service
public class LoginService {
    private final JwtUtils jwtUtils;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    public LoginService (JwtUtils jwtUtils, UserDetailsService userDetailsService, PasswordEncoder passwordEncoder){
        this.jwtUtils = jwtUtils;
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }
    public ResponseEntity <String> authenticate(String username, String password) {
        UserDetails user = userDetailsService.loadUserByUsername(username);
        if(passwordEncoder.matches(password, user.getPassword())){
            return ResponseEntity.ok(jwtUtils.generateToken(username));
        }else{
            return ResponseEntity.status(401).body("Username or password incorrect");
        }
    }
    public Boolean validate(String token) {
        return jwtUtils.validate(token);
    }
}