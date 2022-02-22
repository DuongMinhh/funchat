package com.duongminh.funchat.operation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.duongminh.funchat.core.dto.UserDto;
import com.duongminh.funchat.core.exception.CustomException;
import com.duongminh.funchat.core.model.ApiResponse;
import com.duongminh.funchat.core.model.LoginModel;
import com.duongminh.funchat.core.model.UserModel;
import com.duongminh.funchat.operation.service.AuthService;
import com.duongminh.funchat.operation.service.UserService;

@RestController
@RequestMapping("/auth")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class AuthController {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signUp(@RequestBody UserModel model) {
        try {
            return ApiResponse.ok(userService.create(model)).build();
        } catch (CustomException e) {
            return new ApiResponse(e.getCode(), e.getMessage()).build();
        }
    }
    
    @PostMapping("/login")
    public ResponseEntity<String> logIn(@RequestBody LoginModel model) {
        try {
            return ApiResponse.ok(authService.processLogin(model)).build();
        } catch (CustomException e) {
            return new ApiResponse(e.getCode(), e.getMessage()).build();
        }
    }
    

}
