package com.evan.HTTPCatClone.web;

import com.evan.HTTPCatClone.model.HttpStatus;
import com.evan.HTTPCatClone.service.HttpStatusService;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
public class HttpStatusController {


    private final HttpStatusService HttpStatusService;

    public HttpStatusController(HttpStatusService httpStatusService) {
        this.HttpStatusService = httpStatusService;
    }

    @GetMapping("/")
    public Iterable<HttpStatus> getAllStatuses() {
        return HttpStatusService.get();

    }

    @GetMapping("/status/test")
    public String test() {


        return "Hello world!";


    }

    @GetMapping("/id/{id}")
    public ResponseEntity<byte[]> download(@PathVariable Long id){
        HttpStatus httpStatus = HttpStatusService.getById(id);
        if (httpStatus == null) throw new ResponseStatusException(org.springframework.http.HttpStatus.NOT_FOUND);
        byte[] image = httpStatus.getImage();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<>(image, headers, org.springframework.http.HttpStatus.OK);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<byte[]> download(@PathVariable String status){
        HttpStatus httpStatus = HttpStatusService.getByStatus(status);
        if (httpStatus == null) {
        httpStatus = HttpStatusService.getByStatus("404");
        }
        byte[] image = httpStatus.getImage();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<>(image, headers, org.springframework.http.HttpStatus.OK);
    }

}