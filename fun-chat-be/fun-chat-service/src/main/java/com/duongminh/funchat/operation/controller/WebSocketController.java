package com.duongminh.funchat.operation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

import com.duongminh.funchat.core.dto.MessageDto;
import com.duongminh.funchat.core.exception.CustomException;
import com.duongminh.funchat.core.model.MessageModel;
import com.duongminh.funchat.operation.service.MessageService;

@RestController
public class WebSocketController {
    
    @Autowired
    private MessageService messageService;

    @MessageMapping("/wsroom/{roomId}")
    @SendTo(value = {"/res/room/{roomId}"})
    public MessageDto sendMessage(@DestinationVariable(value = "roomId") Long roomId, @Payload MessageModel model) throws CustomException {
        System.out.println(model);
        return messageService.create(model);
    }

}