package com.duongminh.funchat.operation.service;

import java.util.List;

import com.duongminh.funchat.core.dto.MessageDto;
import com.duongminh.funchat.core.exception.CustomException;
import com.duongminh.funchat.core.model.MessageModel;
import com.duongminh.funchat.core.model.QueryParam;

public interface MessageService {

    MessageDto create(MessageModel model) throws CustomException;
    
    List<MessageDto> getAll(Long roomId, QueryParam params) throws CustomException;
    
    Long countMessages(Long roomId, QueryParam params) throws CustomException;
    
}
