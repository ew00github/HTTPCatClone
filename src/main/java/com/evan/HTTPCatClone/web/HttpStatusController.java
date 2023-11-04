package com.evan.HTTPCatClone.web;

import com.evan.HTTPCatClone.model.HttpStatus;
import com.evan.HTTPCatClone.service.HttpStatusService;
import org.springframework.web.bind.annotation.*;

@RestController
public class HttpStatusController {
    private final HttpStatusService httpStatusService;
    public HttpStatusController(HttpStatusService httpStatusService){
        this.httpStatusService = httpStatusService;
    }

    @GetMapping("/")
    public Iterable<HttpStatus> getAllStatuses(){
        return httpStatusService.getAll();
    }
    @GetMapping("/status/{id}")
    public HttpStatus get(@PathVariable Long id){
        if (httpStatusService.getById(id) == null){
            long notFound = 24;
            return httpStatusService.getById(notFound);
        } // this will have to be modified or removed should the order of the JSON information be disordered, but I thought it was a neat addition

        return httpStatusService.getById(id);
    }





    @PostMapping("/save")
    public HttpStatus save(@RequestBody HttpStatus httpStatus) {
        return HttpStatusService.save(httpStatus);
    }

}
