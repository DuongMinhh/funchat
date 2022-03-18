package com.duongminh.funchat.core.dao.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duongminh.funchat.core.constant.MessageResponse;
import com.duongminh.funchat.core.dao.RoomDao;
import com.duongminh.funchat.core.dto.RoomDto;
import com.duongminh.funchat.core.entity.Message;
import com.duongminh.funchat.core.entity.Room;
import com.duongminh.funchat.core.entity.User;
import com.duongminh.funchat.core.exception.CustomException;
import com.duongminh.funchat.core.mapper.RoomMapper;
import com.duongminh.funchat.core.repository.MessageRepository;
import com.duongminh.funchat.core.repository.RoomRepository;
import com.duongminh.funchat.core.repository.UserRepository;

@Service
public class RoomDaoImpl implements RoomDao {

    @Autowired
    private RoomMapper roomMapper;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private UserRepository userRepository;

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

    @Override
    public Boolean delete(Long id) throws CustomException {
        Room room = roomRepository.findById(id).orElseThrow(() -> new CustomException(MessageResponse.ROOM_NOT_FOUND));
        
        List<Message> messages = messageRepository.findAllByRoomId(id);
        messageRepository.deleteAll(messages);
        
        room.setMembers(new ArrayList<>());
        roomRepository.save(room);
        roomRepository.delete(room);
        
        return Boolean.TRUE;
    }

    @Override
    public Boolean addUser(Long id, Long userId) throws CustomException {
        Room room = roomRepository.findById(id).orElseThrow(() -> new CustomException(MessageResponse.ROOM_NOT_FOUND));
        User user = userRepository.findById(userId).orElseThrow(() -> new CustomException(MessageResponse.USER_NOT_FOUND));
        
        Set<User> members = new HashSet<>(room.getMembers());
        members.add(user);
        room.setMembers(new ArrayList<>(members));
        
        roomRepository.save(room);
        
        return Boolean.TRUE;
    }

}
