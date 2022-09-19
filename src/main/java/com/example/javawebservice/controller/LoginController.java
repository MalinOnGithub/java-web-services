package com.example.javawebservice.controller;

import com.example.javawebservice.dto.JwtRequestDTO;
import com.example.javawebservice.service.LoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService){
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> authenticate(@RequestBody JwtRequestDTO jwtRequestDTO){
        return loginService.authenticate(jwtRequestDTO.username(), jwtRequestDTO.password());
    }

    @GetMapping("/validate")
    public Boolean validate(@RequestParam String token){
        return loginService.validate(token);


    }
}
