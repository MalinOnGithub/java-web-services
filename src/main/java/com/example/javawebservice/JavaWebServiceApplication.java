package com.example.javawebservice;

import com.example.javawebservice.enteties.AppUser;
import com.example.javawebservice.repo.AppUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JavaWebServiceApplication implements CommandLineRunner {
    @Autowired
    AppUserRepo appUserRepo;

    public static void main(String[] args) {
        SpringApplication.run(JavaWebServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        appUserRepo.save(new AppUser("Maximeistern", "1234"));
        appUserRepo.save(new AppUser("MalinOnIntellij", "5678"));
        appUserRepo.save(new AppUser("Tenghamn", "666"));
    }
}
