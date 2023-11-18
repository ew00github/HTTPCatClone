package com.evan.HTTPCatClone.web;

import com.evan.HTTPCatClone.model.HttpStatus;
import com.evan.HTTPCatClone.service.HttpStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController
public class HttpStatusController {

    private final HttpStatusService httpStatusService;

    @Autowired
    public HttpStatusController(HttpStatusService httpStatusService) {
        this.httpStatusService = httpStatusService;
    }

    @GetMapping("/")
    public Iterable<HttpStatus> getAllStatuses(){
        return httpStatusService.getAll();
    }

    @GetMapping("/status/test")
    public String test() {
        return "Hello world!";
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<byte[]> getFromDBID(@PathVariable Long id){
        HttpStatus httpStatus = httpStatusService.getById(id);
        if (httpStatus == null) throw new ResponseStatusException(org.springframework.http.HttpStatus.NOT_FOUND);
        byte[] image = httpStatus.getImage();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<>(image, headers, org.springframework.http.HttpStatus.OK);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<byte[]> getFromDBStatus(@PathVariable String status){
        HttpStatus httpStatus = httpStatusService.getByStatus(status);
        if (httpStatus == null) {
        httpStatus = httpStatusService.getByStatus("404");
        }
        byte[] image = httpStatus.getImage();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<>(image, headers, org.springframework.http.HttpStatus.OK);
    }

    @GetMapping("/statusGroup/{status}")
    public List<HttpStatus> getStatusGroup(@PathVariable String status){
        return httpStatusService.getStatusGroup(status);
    }

    @PostMapping("/save")
    public HttpStatus save(@RequestBody HttpStatus httpStatus) {
        return httpStatusService.save(httpStatus);
    }
}

