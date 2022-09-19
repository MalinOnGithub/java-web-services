package com.example.javawebservice;

import com.example.javawebservice.enteties.AppUser;
import com.example.javawebservice.repo.AppUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class JavaWebServiceApplication implements CommandLineRunner {
    @Autowired
    AppUserRepo appUserRepo;

    @Autowired
    PasswordEncoder passwordEncoder;


    public static void main(String[] args) {
        SpringApplication.run(JavaWebServiceApplication.class, args);
    }

    //Skapar värden att ha i JPA medans vi använder applikationen
    @Override
    public void run(String... args) throws Exception {
        appUserRepo.save(new AppUser("Maximeistern", passwordEncoder.encode("1234")));
        appUserRepo.save(new AppUser("MalinOnIntellij", passwordEncoder.encode("5678")));
        appUserRepo.save(new AppUser("Tenghamn", passwordEncoder.encode("666")));
    }
}