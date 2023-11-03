package com.evan.HTTPCatClone.web;

import com.evan.HTTPCatClone.model.HttpStatus;
import com.evan.HTTPCatClone.service.HttpStatusService;
import org.springframework.web.bind.annotation.*;


@RestController
public class HttpStatusController {


    private final HttpStatusService HttpStatusService;

    public HttpStatusController(HttpStatusService httpStatusService){
        this.HttpStatusService = httpStatusService;
    }



    @GetMapping("/")
    public Iterable<HttpStatus> getAllStatuses(){
        return HttpStatusService.get();

    }

    @GetMapping("/status/test")
    public String test (){


        return "Hello world!";


    }

    @GetMapping("/status/{id}")
    public HttpStatus get(@PathVariable Long id){

        if (HttpStatusService.get(id) == null){
            long notFound = 24;
            return HttpStatusService.get((Long)notFound);
        } // this will have to be modified or removed should the order of the JSON information be disordered, but I thought it was a neat addition

        return HttpStatusService.get(id);

    }







}
