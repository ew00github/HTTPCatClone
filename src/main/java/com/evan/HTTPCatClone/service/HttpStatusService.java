package com.evan.HTTPCatClone.service;

import com.evan.HTTPCatClone.model.HttpStatus;
import com.evan.HTTPCatClone.repository.HttpStatusRepository;
import org.springframework.stereotype.Service;

@Service
public class HttpStatusService {
    private final HttpStatusRepository httpStatusRepository;
    public HttpStatusService(HttpStatusRepository httpStatusRepository) {
        this.httpStatusRepository = httpStatusRepository;
    }

    public HttpStatus getById(Long id) {
        return httpStatusRepository.findById(id).orElse(null);
    }

    public Iterable<HttpStatus> getAll() {
        return httpStatusRepository.findAll();
    }

    public void saveAll() {
        if (httpStatusRepository.count() == 0){
            httpStatusRepository.saveAll(getAll());
        }
    }
  
    public HttpStatus save(HttpStatus httpStatus) {
        return httpStatusRepository.save(httpStatus);
    }

}
