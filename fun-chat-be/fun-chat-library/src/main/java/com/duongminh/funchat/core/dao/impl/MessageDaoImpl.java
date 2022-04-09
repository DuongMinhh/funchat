package com.duongminh.funchat.core.dao.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duongminh.funchat.core.dao.MessageDao;
import com.duongminh.funchat.core.dto.MessageDto;
import com.duongminh.funchat.core.entity.Message;
import com.duongminh.funchat.core.exception.CustomException;
import com.duongminh.funchat.core.mapper.MessageMapper;
import com.duongminh.funchat.core.model.QueryParam;
import com.duongminh.funchat.core.repository.MessageRepository;

@Service
public class MessageDaoImpl implements MessageDao {
    
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private MessageMapper messageMapper;

    @Override
    public MessageDto create(MessageDto dto) throws CustomException {
        
        Message entity = messageMapper.toEntity(dto);
        
        return messageMapper.toDto(messageRepository.save(entity));
    }

    @Override
    public List<MessageDto> getAll(Long roomId, QueryParam params) throws CustomException {
        params.setSortBy("updated_at");
        return messageRepository.findAllByRoomId(roomId, params.getPageable()).stream()
                .map(messageMapper::toDto)
                .sorted((o1, o2) -> o1.getUpdatedAt().compareTo(o2.getUpdatedAt()))
                .collect(Collectors.toList());
    }

    @Override
    public Long countMessages(Long roomId, QueryParam params) throws CustomException {
        Long countMessages = messageRepository.countByRoomId(roomId);
        Long pageSize = Long.valueOf(params.getPageSize());
        
        if (countMessages % pageSize == 0) {
            return countMessages / pageSize;
        } else {
            return countMessages / pageSize + 1; 
        }
    }

}
