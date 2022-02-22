package com.duongminh.funchat.operation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duongminh.funchat.core.constant.MessageResponse;
import com.duongminh.funchat.core.dao.RoomDao;
import com.duongminh.funchat.core.dto.RoomDto;
import com.duongminh.funchat.core.exception.CustomException;
import com.duongminh.funchat.core.model.RoomModel;
import com.duongminh.funchat.operation.service.RoomService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class RoomServiceImpl implements RoomService {
    
    @Autowired
    private RoomDao roomDao;

    @Override
    public RoomDto create(RoomModel model) throws CustomException {
        try {
            model.validate();
            
            RoomDto room = new RoomDto();
            room.setName(model.getName().trim());
            room.setDescription(model.getDescription());
            room.setOwner(model.getOwner());
            
            return roomDao.create(room);
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
    public List<RoomDto> getAll(Long memberId) throws CustomException {
        return roomDao.getAll(memberId);
    }
    
}
