package com.evan.HTTPCatClone.service;

import com.evan.HTTPCatClone.model.HttpStatus;
import com.evan.HTTPCatClone.repository.HttpStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Service
public class HttpStatusService {

    private static final List<String> validStatusGroupCodes = new ArrayList<>();
    static {
        Collections.addAll(validStatusGroupCodes,"100","200","300","400","500");
    }

    private final HttpStatusRepository httpStatusRepository;

    @Autowired
    public HttpStatusService(HttpStatusRepository httpStatusRepository) {
        this.httpStatusRepository = httpStatusRepository;
    }

    public HttpStatus getById(Long id) {
        return httpStatusRepository.findById(id).orElse(null);
    }
    
    public HttpStatus getByStatus(String status){
        return httpStatusRepository.findByStatus(status);
    }
    
    public Iterable<HttpStatus> getAll() {
        return httpStatusRepository.findAll();
    }
    
    public HttpStatus save(HttpStatus httpStatus) {
        return httpStatusRepository.save(httpStatus);
    }
    
    public void saveAll() {
        if (httpStatusRepository.count() == 0){
            httpStatusRepository.saveAll(getAll());
        }
    }

    public List<HttpStatus> getStatusGroup(String status){
        List<HttpStatus> statusGroup = new ArrayList<>();
        if (!(validStatusGroupCodes.contains(status))){
            throw new ResponseStatusException(org.springframework.http.HttpStatus.BAD_REQUEST);
        }
        int upperBound = Integer.parseInt(status) + 99;
        for (int i = Integer.parseInt(status); i <= upperBound; i++){
            HttpStatus httpStatus = getByStatus(String.valueOf(i));
            if ((httpStatus != null && httpStatus.getStatus() != null)){
                statusGroup.add(httpStatus);
            }
        }
        return statusGroup;
    }
}
