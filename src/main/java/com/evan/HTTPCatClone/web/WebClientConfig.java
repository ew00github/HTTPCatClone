package com.evan.HTTPCatClone.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient.Builder webClientBuilder(){
        return WebClient.builder();
    }
    @Bean
    public WebClient webClient(){
        return webClientBuilder().build();
    }
    @Bean
    public WebClient webClientCat() {
        return WebClient.builder()
                .baseUrl("https://http.cat/")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

}
