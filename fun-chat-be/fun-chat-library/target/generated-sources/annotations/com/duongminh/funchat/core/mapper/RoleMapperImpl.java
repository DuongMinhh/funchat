package com.duongminh.funchat.core.mapper;

import com.duongminh.funchat.core.dto.RoleDto;
import com.duongminh.funchat.core.entity.Role;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-02-11T08:48:26+0700",
    comments = "version: 1.4.2.Final, compiler: Eclipse JDT (IDE) 1.4.0.v20210708-0430, environment: Java 16.0.2 (Oracle Corporation)"
)
@Component
public class RoleMapperImpl implements RoleMapper {

    @Override
    public Role toEntity(RoleDto object) {
        if ( object == null ) {
            return null;
        }

        Role role = new Role();

        role.setCreatedAt( object.getCreatedAt() );
        role.setUpdatedAt( object.getUpdatedAt() );
        role.setId( object.getId() );
        role.setName( object.getName() );

        return role;
    }

    @Override
    public RoleDto toDto(Role object) {
        if ( object == null ) {
            return null;
        }

        RoleDto roleDto = new RoleDto();

        roleDto.setCreatedAt( object.getCreatedAt() );
        roleDto.setUpdatedAt( object.getUpdatedAt() );
        roleDto.setId( object.getId() );
        roleDto.setName( object.getName() );

        return roleDto;
    }
}
