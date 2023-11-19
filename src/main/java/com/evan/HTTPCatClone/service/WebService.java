package com.evan.HTTPCatClone.service;

import com.evan.HTTPCatClone.model.HttpStatus;
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

    private final HttpStatusService statusService;

    @Autowired
    public WebService(WebClient webClient, HttpStatusService statusService) {
        this.webClient = webClient;
        this.statusService = statusService;
    }

    public Mono<ResponseEntity<byte[]>> getStatusFromCat(String statusCode) {
        Mono<byte[]> imageBytes =  webClient.get()
                .uri("https://http.cat/" + statusCode)
                .retrieve()
                .bodyToMono(byte[].class);
        return imageBytes.map(catImage -> {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            return new ResponseEntity<>(catImage, headers, org.springframework.http.HttpStatus.OK);
        });
    }

    public HttpStatus getHttpStatusById(Long id) {
        return statusService.getById(id);
    }
}
