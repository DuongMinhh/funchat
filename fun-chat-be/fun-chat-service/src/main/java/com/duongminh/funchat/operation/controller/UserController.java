package com.duongminh.funchat.operation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    
    @PutMapping("/leave/room/{roomId}")
    public ResponseEntity<Boolean> leaveRoom(@PathVariable("roomId") Long roomId) {
        try {
            return ApiResponse.ok(userService.leaveRoom(ContextUtils.getUserId(), roomId)).build();
        } catch (CustomException e) {
            return new ApiResponse(e.getCode(), e.getMessage()).build();
        }
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<UserDto>> searchUser(@RequestParam("key") String searchKey) {
        try {
            return ApiResponse.ok(userService.getUser(searchKey)).build();
        } catch (CustomException e) {
            return new ApiResponse(e.getCode(), e.getMessage()).build();
        }
    }
    
}
