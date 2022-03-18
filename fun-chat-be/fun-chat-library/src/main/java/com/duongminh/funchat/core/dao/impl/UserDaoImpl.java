package com.duongminh.funchat.core.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duongminh.funchat.core.constant.MessageResponse;
import com.duongminh.funchat.core.dao.UserDao;
import com.duongminh.funchat.core.dto.UserDto;
import com.duongminh.funchat.core.entity.Room;
import com.duongminh.funchat.core.entity.User;
import com.duongminh.funchat.core.exception.CustomException;
import com.duongminh.funchat.core.mapper.UserMapper;
import com.duongminh.funchat.core.repository.RoomRepository;
import com.duongminh.funchat.core.repository.UserRepository;

@Service
public class UserDaoImpl implements UserDao {
    
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoomRepository roomRepository;

    @Override
    public UserDto getOne(Long id) {
        User user = userRepository.findById(id).orElse(null);
        return Objects.isNull(user) ? null : userMapper.toDto(user);
    }

    @Override
    public UserDto create(UserDto dto) throws CustomException {
        try {
            User entity = userMapper.toEntity(dto);
            entity = userRepository.save(entity);
            return userMapper.toDto(entity);
        } catch (Exception e) {
            throw new CustomException(MessageResponse.SERVER_ERROR);
        }
    }

    @Override
    public Boolean existedBy(String email) {
        User entity = userRepository.findByEmail(email).orElse(null);
        return Objects.isNull(entity) ? false : true;
    }

    @Override
    public Boolean leaveRoom(Long id, Long roomId) throws CustomException {
        Room room = roomRepository.findById(roomId).orElseThrow(() -> new CustomException(MessageResponse.ROOM_NOT_FOUND));
        User user = userRepository.findById(id).orElseThrow(() -> new CustomException(MessageResponse.USER_NOT_FOUND));
        
        List<User> roomUsers = room.getMembers().stream().filter(u -> !u.getId().equals(user.getId())).collect(Collectors.toList());
        room.setMembers(roomUsers);
        roomRepository.save(room);
        
        return Boolean.TRUE;
    }

    @Override
    public List<UserDto> getUser(String searchText) throws CustomException {
        return StringUtils.isBlank(searchText) ? new ArrayList<>() : userRepository.findAllByNameOrEmail(searchText.trim().toLowerCase()).stream().map(userMapper::toDto).collect(Collectors.toList());
    }

}
