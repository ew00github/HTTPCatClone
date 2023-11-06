package com.evan.HTTPCatClone.web;

import com.evan.HTTPCatClone.service.WebService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class WebController {

    private final WebService webService;

    public WebController(WebService webService){
        this.webService = webService;
    }

    @GetMapping("/catAPI/status/{statusCode}")
    public Mono<ResponseEntity<byte[]>> getStatusFromCat(@PathVariable String statusCode){
        return this.webService.getStatusFromCat(statusCode);
    }
}
