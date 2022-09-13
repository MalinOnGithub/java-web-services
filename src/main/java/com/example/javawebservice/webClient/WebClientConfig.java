package com.example.javawebservice.webClient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
//Skapar klassen, config säger att här finns bönor.
@Configuration
public class WebClientConfig {
//En Bean förbestämmer en implementationsvärde.
    @Bean
    public WebClient webClient(){
        return WebClient.builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
}
