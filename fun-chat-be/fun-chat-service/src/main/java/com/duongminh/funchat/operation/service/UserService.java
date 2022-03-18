package com.duongminh.funchat.operation.service;

import java.util.List;

import com.duongminh.funchat.core.dto.UserDto;
import com.duongminh.funchat.core.exception.CustomException;
import com.duongminh.funchat.core.model.UserModel;

public interface UserService {

    UserDto getUser(Long id) throws CustomException;
    UserDto create(UserModel model) throws CustomException;
    
    Boolean leaveRoom(Long id, Long roomId) throws CustomException;
    
    List<UserDto> getUser(String searchText) throws CustomException;
    
}
