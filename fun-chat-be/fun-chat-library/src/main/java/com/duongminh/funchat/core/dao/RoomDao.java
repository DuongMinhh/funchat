package com.duongminh.funchat.core.dao;

import java.util.List;

import com.duongminh.funchat.core.dto.RoomDto;
import com.duongminh.funchat.core.exception.CustomException;

public interface RoomDao {
    
    RoomDto getOne(Long id, boolean lazy);

    RoomDto create(RoomDto room) throws CustomException;
    
    List<RoomDto> getAll(Long memberId) throws CustomException;
    
    Boolean delete(Long id) throws CustomException;
    
    Boolean addUser(Long id, Long userId) throws CustomException;
    
}
