package com.evan.HTTPCatClone.tools;

import com.evan.HTTPCatClone.model.HttpStatus;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;
public class JSONConverter {
    private final ObjectMapper objectMapper;
    public JSONConverter(){
       this.objectMapper = new ObjectMapper();
    }
    public List<HttpStatus> convert() throws IOException {
        File file = new File("src/main/resources/static/data.json");
        TypeReference<List<HttpStatus>> typeReference = new TypeReference<>() {
        };

        return objectMapper.readValue(file, typeReference);
    }
}
