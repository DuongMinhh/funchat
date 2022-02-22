package com.duongminh.funchat.operation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.duongminh.funchat.core.dto.RoomDto;
import com.duongminh.funchat.core.exception.CustomException;
import com.duongminh.funchat.core.model.ApiResponse;
import com.duongminh.funchat.core.model.RoomModel;
import com.duongminh.funchat.operation.service.RoomService;
import com.duongminh.funchat.operation.util.ContextUtils;

@RestController
@RequestMapping("/room")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class RoomController {

    @Autowired
    private RoomService roomService;
    
    @PostMapping
    public ResponseEntity<RoomDto> create(@RequestBody RoomModel model) {
        try {
            return ApiResponse.ok(roomService.create(model)).build();
        } catch (CustomException e) {
            return new ApiResponse(e.getCode(), e.getMessage()).build();
        }
    }
    
    @GetMapping("/all")
    public ResponseEntity<List<RoomDto>> getAll() {
        try {
            return ApiResponse.ok(roomService.getAll(ContextUtils.getUserId())).build();
        } catch (CustomException e) {
            return new ApiResponse(e.getCode(), e.getMessage()).build();
        }
    }
}
