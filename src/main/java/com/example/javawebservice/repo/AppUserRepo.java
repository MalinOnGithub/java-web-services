package com.example.javawebservice.repo;

import com.example.javawebservice.enteties.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepo extends JpaRepository<AppUser, Integer> {
}
