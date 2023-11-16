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

    public WebController(WebService webService) {
        this.webService = webService;
    }

    @GetMapping("/catAPI/status/{statusCode}")
    public Mono<ResponseEntity<byte[]>> getStatusFromCat(@PathVariable String statusCode){
        return this.webService.getStatusFromCat(statusCode);
    }

    @GetMapping("/catAPI/sendImage/id/{id}")
    public ResponseEntity<byte[]> sendStatusFromId(@PathVariable Long id){
        return this.webService.sendImageFromId(id);
    }

    @GetMapping("/catAPI/sendImage/status/{status}")
    public ResponseEntity<byte[]> sendStatusFromStatus(@PathVariable String status){
        return this.webService.sendImageFromStatus(status);
    }

    @GetMapping("/catAPI/sendStatusCode/id/{id}")
    public Mono<String> sendStatusCodeFromId(@PathVariable Long id){
        return this.webService.sendStatusFromId(id);
    }
}
