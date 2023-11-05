package com.evan.HTTPCatClone.web;

import com.evan.HTTPCatClone.model.HttpStatus;
import com.evan.HTTPCatClone.service.HttpStatusService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;

@RestController
public class HttpStatusController {

    private final HttpStatusService httpStatusService;

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
    public ResponseEntity<byte[]> download(@PathVariable Long id){
        HttpStatus httpStatus = httpStatusService.getById(id);
        if (httpStatus == null) throw new ResponseStatusException(org.springframework.http.HttpStatus.NOT_FOUND);
        byte[] image = httpStatus.getImage();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<>(image, headers, org.springframework.http.HttpStatus.OK);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<byte[]> download(@PathVariable String status){
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
    public ArrayList<String> getStatusGroup(@PathVariable String status){
        ArrayList<String> statusGroup = new ArrayList<>();
        if (!(status.equals("100") || status.equals("200") || status.equals("300") || status.equals("400") || status.equals("500"))){
            throw new ResponseStatusException(org.springframework.http.HttpStatus.BAD_REQUEST);
        }
        int upperBound = Integer.parseInt(status) + 99;
        for (int i = Integer.parseInt(status); i < upperBound; i++){
            HttpStatus httpStatus = httpStatusService.getByStatus(String.valueOf(i));
            if ((httpStatus != null && httpStatus.getStatus() != null)){
                statusGroup.add(httpStatusService.getByStatus(String.valueOf(i)).getStatus());
            }
        }
        return statusGroup;
    }

    @PostMapping("/save")
    public HttpStatus save(@RequestBody HttpStatus httpStatus) {
        return httpStatusService.save(httpStatus);
    }
}

