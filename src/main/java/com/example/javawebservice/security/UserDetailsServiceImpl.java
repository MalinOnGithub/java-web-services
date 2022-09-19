package com.example.javawebservice.security;

import com.example.javawebservice.enteties.AppUser;
import com.example.javawebservice.repo.AppUserRepo;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//Userdetailsservice "Ett interface" som ska hantera kopplingen mellan spring security och användarna som vi har lagrade i vår databas.
//Har en metod (loaduserbyusername)som säger när ngn loggar in, vart ska du gå och vad ska du jämnföra emot.
//Userdetails(interface) representerar en användare (rättare sagt den info som en användare behöver för att spring ska kunna använda den)
@Service
public class UserDetailsServiceImpl implements UserDetailsService { //Skapa serviceklass som impl userdetailsservice

    private final AppUserRepo appUserRepo; //Hämtar vårt repo

    public UserDetailsServiceImpl(AppUserRepo appUserRepo){
        this.appUserRepo = appUserRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appuser = appUserRepo.findAppUserByUsername(username).orElseThrow();
        return User.builder()
                .username(appuser.getUsername())
                .password(appuser.getPassword())
                .authorities(new SimpleGrantedAuthority("ADMIN_ROLE"))
                .build();
    }
}
//Man skall kunna implementera Userdetails i Appuser? Så kan man returnera en appUser ist för User. ?
