package com.duongminh.funchat.core.dao;

import java.util.List;

import com.duongminh.funchat.core.dto.UserDto;
import com.duongminh.funchat.core.exception.CustomException;

public interface UserDao {

    UserDto getOne(Long id);
    
    Boolean existedBy(String email);
    
    UserDto create(UserDto dto) throws CustomException;
    
    Boolean leaveRoom(Long id, Long roomId) throws CustomException;
    
    List<UserDto> getUser(String searchText) throws CustomException;
    
}
