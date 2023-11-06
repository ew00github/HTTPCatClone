package com.evan.HTTPCatClone.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class WebService {

    private final WebClient webClient;

    @Autowired
    public WebService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<ResponseEntity<byte[]>> getStatusFromCat(String status_code) {
        Mono<byte[]> imageBytes =  webClient.get()
                .uri("https://http.cat/" + status_code)
                .retrieve()
                .bodyToMono(byte[].class);
        return imageBytes.map(catImage -> {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            return new ResponseEntity<>(catImage, headers, org.springframework.http.HttpStatus.OK);
        });
    }
}
