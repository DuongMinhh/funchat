package com.duongminh.funchat.core.dao;

import com.duongminh.funchat.core.dto.RoleDto;
import com.duongminh.funchat.core.enums.UserRole;

public interface RoleDao {

    RoleDto getOne(UserRole role);
    
}
