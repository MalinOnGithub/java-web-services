package com.example.javawebservice.service;

import com.example.javawebservice.enteties.AppUser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    public boolean isCorrectUserLogin(int id) {
        AppUser appUser = getAppuser();
        if(isAdmin()){
            return true;
        }
        return appUser.getId() == id;
    }
    public boolean isAdmin(){
        AppUser appUser = getAppuser();
        return appUser.getAuthorities().stream()
                .anyMatch(role -> role.getAuthority()
                        .equals("ROLE_ADMIN"));
    }
    private AppUser getAppuser(){
        return (AppUser) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }
}
