package com.hackathon.javatemplate.auth.repository;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomResponseWithPage<T> {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private HttpStatus status;
    private Integer statusCode;
    private String message;
    private T data;

    private Paging paging;

    public CustomResponseWithPage(HttpStatus status, T data, Paging paging, String message){

        this.timestamp=LocalDateTime.now();
        this.status=status;
        this.statusCode=status.value();
        this.message=message;
        this.data=data;
        this.paging=paging;
    }
    public CustomResponseWithPage(HttpStatus status, Throwable throwable, String message){
        this.timestamp=LocalDateTime.now();
        this.status=status;
        this.statusCode=status.value();
        this.message=message;
        this.data=null;
    }
    public CustomResponseWithPage(HttpStatus status, String message){
        this.timestamp=LocalDateTime.now();
        this.status=status;
        this.statusCode=status.value();
        this.message=message;
        this.data=null;
    }
}
