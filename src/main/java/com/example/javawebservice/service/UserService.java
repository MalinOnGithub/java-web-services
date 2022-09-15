package com.example.javawebservice.service;

import com.example.javawebservice.dto.Post;
import com.example.javawebservice.dto.ResponseUser;
import com.example.javawebservice.enteties.AppUser;
import com.example.javawebservice.repo.AppUserRepo;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class UserService {

    private final WebClient webClient;
    private AppUserRepo appUserRepo;

    public UserService(WebClient webClient, AppUserRepo appUserRepo) {
        this.webClient = webClient;
        this.appUserRepo = appUserRepo;
    }


    public ResponseUser getUser(int id) {
        AppUser appUser = appUserRepo.getReferenceById(id);
        return new ResponseUser(appUser.getId(), appUser.getUsername());
    }

    public ResponseUser addAppUser(AppUser appUser) {
        AppUser newAppUser = new AppUser(appUser.getUsername(), appUser.getPassword());
        appUserRepo.save(newAppUser);
        return new ResponseUser(newAppUser.getId(), newAppUser.getUsername());
    }

    public ResponseUser updateAppUser(AppUser newAppUser, int id) {
        AppUser existingAppUser = appUserRepo.getReferenceById(id);

        if(newAppUser.getUsername() != null){
            existingAppUser.setUsername(newAppUser.getUsername());
        }
        if (newAppUser.getPassword() != null){
            existingAppUser.setPassword(newAppUser.getPassword());
        }
        return new ResponseUser(existingAppUser.getId(), existingAppUser.getUsername());
    }

    public void terminateAppUser(int id) {
        appUserRepo.deleteById(id);
    }

    public List<Post> getAllPostsByUser(int id) {
        return webClient.get()
                .uri("users/" + id + "/posts")
                .exchangeToFlux(clientResponse -> clientResponse.bodyToFlux(Post.class))
                .buffer()
                .blockLast();
    }
}
