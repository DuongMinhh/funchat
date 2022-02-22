package com.duongminh.funchat.operation.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duongminh.funchat.core.constant.MessageResponse;
import com.duongminh.funchat.core.dao.MessageDao;
import com.duongminh.funchat.core.dao.RoomDao;
import com.duongminh.funchat.core.dao.UserDao;
import com.duongminh.funchat.core.dto.MessageDto;
import com.duongminh.funchat.core.dto.RoomDto;
import com.duongminh.funchat.core.dto.UserDto;
import com.duongminh.funchat.core.exception.CustomException;
import com.duongminh.funchat.core.model.MessageModel;
import com.duongminh.funchat.operation.service.MessageService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class MessageServiceImpl implements MessageService {
    
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoomDao roomDao;
    @Autowired
    private MessageDao messageDao;

    @Override
    public MessageDto create(MessageModel model) throws CustomException {
        try {
            model.validate();
            UserDto user = userDao.getOne(model.getAuthorId());
            if (Objects.isNull(user)) {
                throw new CustomException(MessageResponse.USER_NOT_FOUND);
            }
            
            RoomDto room = roomDao.getOne(model.getRoomId(), true);
            if (Objects.isNull(room)) {
                throw new CustomException(MessageResponse.ROOM_NOT_FOUND);
            }
            
            MessageDto dto = new MessageDto();
            dto.setAuthor(user);
            dto.setRoom(room);
            dto.setContent(model.getContent());
            
            return messageDao.create(dto);
        } catch (Exception e) {
            if (e instanceof CustomException) {
                log.error(e.getMessage());
                throw e;
            }
            
            log.error(e.getMessage(), e);
            throw new CustomException(MessageResponse.SERVER_ERROR);
        }
    }

    @Override
    public List<MessageDto> getAll(Long roomId) throws CustomException {
        return messageDao.getAll(roomId);
    }

}
