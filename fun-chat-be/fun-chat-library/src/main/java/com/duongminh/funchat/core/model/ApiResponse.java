package com.duongminh.funchat.core.model;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;

@Getter
@JsonInclude(Include.NON_NULL)
public class ApiResponse<T> {

    private int status;
    
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime timestamp;
    private String message;
    private boolean success;
    private T data;

    public ApiResponse(HttpStatus status) {
        this.status = status.value();
        this.success = status.is2xxSuccessful();
        
        if (!this.success) {
            this.timestamp = LocalDateTime.now();
        }
    }
    
    public ApiResponse(HttpStatus status,T data) {
        this(status);
        this.data = data;
    }
    
    public ApiResponse(HttpStatus status, Throwable e) {
        this(status);
        this.message = e.getLocalizedMessage();
    }
    
    public ApiResponse(HttpStatus status, String message) {
        this(status);
        this.message = message;
    }
    
    public static <T>ApiResponse<T> ok() {
        return new ApiResponse<>(HttpStatus.OK);
    }
    
    public static <T>ApiResponse<T> ok(T data) {
        return new ApiResponse<>(HttpStatus.OK, data);
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public ResponseEntity<T> build() {
        return new ResponseEntity(this, HttpStatus.valueOf(this.status));
    }
}
