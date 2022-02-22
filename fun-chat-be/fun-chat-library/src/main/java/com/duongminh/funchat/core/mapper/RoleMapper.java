package com.duongminh.funchat.core.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.duongminh.funchat.core.dto.RoleDto;
import com.duongminh.funchat.core.entity.Role;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface RoleMapper {

    Role toEntity(RoleDto object);
    
    RoleDto toDto(Role object);
    
}
