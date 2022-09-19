package com.example.javawebservice.repo;

import com.example.javawebservice.enteties.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepo extends JpaRepository<AppUser, Integer> {

    Optional<AppUser> findAppUserByUsername(String username);
}
