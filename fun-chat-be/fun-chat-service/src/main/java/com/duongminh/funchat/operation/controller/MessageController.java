package com.duongminh.funchat.operation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.duongminh.funchat.core.dto.MessageDto;
import com.duongminh.funchat.core.exception.CustomException;
import com.duongminh.funchat.core.model.ApiResponse;
import com.duongminh.funchat.core.model.MessageModel;
import com.duongminh.funchat.operation.service.MessageService;

@RestController
@RequestMapping("/message")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class MessageController {

    @Autowired
    private MessageService messageService;
    
    @PostMapping
    public ResponseEntity<MessageDto> create(@RequestBody MessageModel model) {
        try {
            return ApiResponse.ok(messageService.create(model)).build();
        } catch (CustomException e) {
            return new ApiResponse(e.getCode(), e.getMessage()).build();
        }
    }
    
    @GetMapping("/all/{roomId}")
    public ResponseEntity<List<MessageDto>> getAll(@PathVariable("roomId") Long roomId) {
        try {
            return ApiResponse.ok(messageService.getAll(roomId)).build();
        } catch (CustomException e) {
            return new ApiResponse(e.getCode(), e.getMessage()).build();
        }
    }
    
}
