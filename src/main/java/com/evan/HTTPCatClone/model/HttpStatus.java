package com.evan.HTTPCatClone.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.springframework.stereotype.Component;



@Component
@Entity
@Table(name = "HTTP_STATUS")

public class HttpStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String status;
    @Column
    @Lob
    private byte[] image;




    public HttpStatus(){

    }

    public HttpStatus(String status, byte[] image){
        this.status = status;
        this.image = image;
    }


    @JsonProperty("image")
    public byte[] getImage(){

        return image;
    }
    public void setImage(byte[] image){

        this.image = image;

    }




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
