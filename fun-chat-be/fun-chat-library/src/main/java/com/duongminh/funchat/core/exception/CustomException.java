package com.duongminh.funchat.core.exception;

import org.springframework.http.HttpStatus;

import com.duongminh.funchat.core.constant.MessageResponse;

import lombok.Getter;

@Getter
public class CustomException extends Exception {

    private static final long serialVersionUID = 1L;
    
    private HttpStatus code;
    private String message;
    
    public CustomException(String message, HttpStatus code) {
        this.message = message;
        this.code = code;
    }
    
    public CustomException(String message) {
        this.message = message;
        this.code = HttpStatus.BAD_REQUEST;
    }
    
    public CustomException() {
        this.message = MessageResponse.SERVER_ERROR;
        this.code = HttpStatus.INTERNAL_SERVER_ERROR;
    }
    
}
