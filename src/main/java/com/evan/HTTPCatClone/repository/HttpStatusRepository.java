package com.evan.HTTPCatClone.repository;

import com.evan.HTTPCatClone.model.HttpStatus;
import org.springframework.data.repository.CrudRepository;
public interface HttpStatusRepository extends CrudRepository<HttpStatus, Long> {
    HttpStatus findByStatus(String status);
}