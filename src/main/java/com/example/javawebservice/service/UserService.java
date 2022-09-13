package com.example.javawebservice.service;

import com.example.javawebservice.dto.User;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class UserService {

    private final WebClient webClient;

    public UserService(WebClient webClient) {
        this.webClient = webClient;
    }

    public List<User> getAllUsers() {
       return webClient
               .get()
               .uri("users/")
               .exchangeToFlux(clientResponse -> clientResponse.bodyToFlux(User.class))
               .buffer()
               .blockLast();
    }

    public User getUser(int id) {
        return webClient
                .get()
                .uri("users/" + id)
                .exchangeToMono(clientResponse -> clientResponse.bodyToMono(User.class))
                .block();
    }

    public User addUser(User user) {

        return webClient
                .post()
                .uri("users/")
                .body(Mono.just(user),User.class)
                .exchangeToMono(clientResponse -> clientResponse.bodyToMono(User.class))
                .block();
    }
}
