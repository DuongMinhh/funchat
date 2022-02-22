package com.duongminh.funchat.operation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.duongminh.funchat.core.dto.UserDto;
import com.duongminh.funchat.core.exception.CustomException;
import com.duongminh.funchat.core.model.ApiResponse;
import com.duongminh.funchat.operation.service.UserService;
import com.duongminh.funchat.operation.util.ContextUtils;

@RestController
@RequestMapping("/user")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/me")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<UserDto> getCurrentUser() throws Exception {
        try {
            return ApiResponse.ok(userService.getUser(ContextUtils.getUserId())).build();
        } catch (CustomException e) {
            return new ApiResponse(e.getCode(), e.getMessage()).build();
        }
    }
    
}
