package com.evan.HTTPCatClone.web;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
public class WebController {

    private final WebClient webClient;

    public WebController() {
        this.webClient = WebClient.create();
    }
    @GetMapping("/catAPI/status/{status_code}")
    public Mono<ResponseEntity<byte[]>> getStatusFromCat(@PathVariable String status_code) {
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
