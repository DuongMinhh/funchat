package com.duongminh.funchat.core.dao.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duongminh.funchat.core.constant.MessageResponse;
import com.duongminh.funchat.core.dao.RoomDao;
import com.duongminh.funchat.core.dto.RoomDto;
import com.duongminh.funchat.core.entity.Room;
import com.duongminh.funchat.core.entity.User;
import com.duongminh.funchat.core.exception.CustomException;
import com.duongminh.funchat.core.mapper.RoomMapper;
import com.duongminh.funchat.core.repository.RoomRepository;

@Service
public class RoomDaoImpl implements RoomDao {

    @Autowired
    private RoomMapper roomMapper;
    @Autowired
    private RoomRepository roomRepository;

    @Override
    public RoomDto getOne(Long id, boolean lazy) {
        Room room = roomRepository.findById(id).orElse(null);
        
        if (lazy) {
            return Objects.isNull(room) ? null : roomMapper.toDtoWLZ(room);
        }
        
        return Objects.isNull(room) ? null : roomMapper.toDto(room);
    }
    
    @Override
    public RoomDto create(RoomDto room) throws CustomException {
        try {
            if (roomRepository.existsByNameAndUserId(room.getName(), room.getOwner().getId())) {
                throw new CustomException(MessageResponse.ROOM_NAME_EXISTED);
            }
            
            Room entity = roomMapper.toEntity(room);
            List<User> members = Arrays.asList(entity.getOwner());
            entity.setMembers(members);
            
            return roomMapper.toDto(roomRepository.save(entity));
        } catch (Exception e) {
            if (e instanceof CustomException) {
                throw e;
            }
            
            throw new CustomException(MessageResponse.SERVER_ERROR);
        }
    }

    @Override
    public List<RoomDto> getAll(Long memberId) throws CustomException {
        try {
            return roomRepository.findAllByMemberId(memberId).stream().map(roomMapper::toDto).collect(Collectors.toList());
        } catch (Exception e) {
            throw new CustomException(MessageResponse.SERVER_ERROR);
        }
    }

}
