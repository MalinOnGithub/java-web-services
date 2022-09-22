package com.example.javawebservice.service;

import com.example.javawebservice.enteties.AppUser;
import com.example.javawebservice.enteties.Role;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.security.Principal;

@Service
public class AuthService {

    public static boolean isCorrectUserLogin(int id) {
        AppUser appUser = (AppUser) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        System.out.println(appUser.getAuthorities());
        if(appUser.getAuthorities().stream().anyMatch(role -> role.getAuthority().equals("ROLE_ADMIN"))){
            return true;
        }
        return appUser.getId() == id;
    }
}
