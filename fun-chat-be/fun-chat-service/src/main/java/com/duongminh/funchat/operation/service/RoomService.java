package com.duongminh.funchat.operation.service;

import java.util.List;

import com.duongminh.funchat.core.dto.RoomDto;
import com.duongminh.funchat.core.exception.CustomException;
import com.duongminh.funchat.core.model.RoomModel;

public interface RoomService {

    RoomDto create(RoomModel model) throws CustomException;
    
    List<RoomDto> getAll(Long memberId) throws CustomException;
}
