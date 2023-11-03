package com.evan.HTTPCatClone.repository;

import com.evan.HTTPCatClone.service.HttpStatusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
    @Bean
    CommandLineRunner initDatabase(HttpStatusRepository repository, HttpStatusService service) {
        return args -> {
            if (repository.count() >= 71){
                log.info("All statuses found!");
                return;
            }
            repository.saveAll(service.get());
            log.info("Database pre-populated successfully");
        };
    }
}
