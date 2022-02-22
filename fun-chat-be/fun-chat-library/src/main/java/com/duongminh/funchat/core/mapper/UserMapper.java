package com.duongminh.funchat.core.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.duongminh.funchat.core.dto.UserDto;
import com.duongminh.funchat.core.entity.User;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring", uses = { RoleMapper.class })
public interface UserMapper {

    User toEntity(UserDto object);
    
    UserDto toDto(User object);

}
