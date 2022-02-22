package com.duongminh.funchat.core.dao.impl;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duongminh.funchat.core.dao.RoleDao;
import com.duongminh.funchat.core.dto.RoleDto;
import com.duongminh.funchat.core.entity.Role;
import com.duongminh.funchat.core.enums.UserRole;
import com.duongminh.funchat.core.mapper.RoleMapper;
import com.duongminh.funchat.core.repository.RoleRepository;

@Service
public class RoleDaoImpl implements RoleDao {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private RoleMapper roleMapper;
    
    @Override
    public RoleDto getOne(UserRole role) {
        Role object = roleRepository.findByName(role.name());
        return Objects.isNull(object) ? null : roleMapper.toDto(object);
    }

}
