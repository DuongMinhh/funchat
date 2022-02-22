package com.duongminh.funchat.operation.service;

import com.duongminh.funchat.core.dto.UserDto;
import com.duongminh.funchat.core.exception.CustomException;
import com.duongminh.funchat.core.model.UserModel;

public interface UserService {

    UserDto getUser(Long id) throws CustomException;
    UserDto create(UserModel model) throws CustomException;
    
}
