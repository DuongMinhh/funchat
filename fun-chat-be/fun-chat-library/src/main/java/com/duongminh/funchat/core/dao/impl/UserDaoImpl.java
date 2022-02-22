package com.duongminh.funchat.core.dao.impl;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duongminh.funchat.core.constant.MessageResponse;
import com.duongminh.funchat.core.dao.UserDao;
import com.duongminh.funchat.core.dto.UserDto;
import com.duongminh.funchat.core.entity.User;
import com.duongminh.funchat.core.exception.CustomException;
import com.duongminh.funchat.core.mapper.UserMapper;
import com.duongminh.funchat.core.repository.UserRepository;

@Service
public class UserDaoImpl implements UserDao {
    
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

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

}
