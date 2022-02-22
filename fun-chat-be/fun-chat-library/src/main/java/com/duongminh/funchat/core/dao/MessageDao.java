package com.duongminh.funchat.core.dao;

import java.util.List;

import com.duongminh.funchat.core.dto.MessageDto;
import com.duongminh.funchat.core.exception.CustomException;

public interface MessageDao {

    MessageDto create(MessageDto dto) throws CustomException;
    
    List<MessageDto> getAll(Long roomId) throws CustomException;
    
}
