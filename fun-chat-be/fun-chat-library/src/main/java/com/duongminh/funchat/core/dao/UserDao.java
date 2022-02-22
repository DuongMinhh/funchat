package com.duongminh.funchat.core.dao;

import com.duongminh.funchat.core.dto.UserDto;
import com.duongminh.funchat.core.exception.CustomException;

public interface UserDao {

    UserDto getOne(Long id);
    
    Boolean existedBy(String email);
    
    UserDto create(UserDto dto) throws CustomException;
    
}
