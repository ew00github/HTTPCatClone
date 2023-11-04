package com.evan.HTTPCatClone.service;

import com.evan.HTTPCatClone.model.HttpStatus;
import com.evan.HTTPCatClone.repository.HttpStatusRepository;
import com.evan.HTTPCatClone.tools.JSONConverter;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class HttpStatusService {
    private final HttpStatusRepository httpStatusRepository;
    public HttpStatusService(HttpStatusRepository httpStatusRepository) {
        this.httpStatusRepository = httpStatusRepository;
    }
    public HttpStatus get(Long id) {
        return httpStatusRepository.findById(id).orElse(null);
    }

    public Iterable<HttpStatus> get() {
        JSONConverter converter = new JSONConverter();
        try {
            return converter.convert();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveAll() {
        if (httpStatusRepository.count() == 0){
            httpStatusRepository.saveAll(get());
        }
    }
  
    public HttpStatus save(HttpStatus httpStatus) {
        return httpStatusRepository.save(httpStatus);
    }
}
