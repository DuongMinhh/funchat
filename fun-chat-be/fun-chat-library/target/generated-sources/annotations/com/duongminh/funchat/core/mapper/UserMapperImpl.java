package com.duongminh.funchat.core.mapper;

import com.duongminh.funchat.core.dto.UserDto;
import com.duongminh.funchat.core.entity.User;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-03-13T23:19:12+0700",
    comments = "version: 1.4.2.Final, compiler: Eclipse JDT (IDE) 1.4.0.v20210708-0430, environment: Java 16.0.2 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public User toEntity(UserDto object) {
        if ( object == null ) {
            return null;
        }

        User user = new User();

        user.setCreatedAt( object.getCreatedAt() );
        user.setUpdatedAt( object.getUpdatedAt() );
        user.setEmail( object.getEmail() );
        user.setId( object.getId() );
        user.setName( object.getName() );
        user.setPassword( object.getPassword() );
        user.setPhotoUrl( object.getPhotoUrl() );
        user.setProvider( object.getProvider() );
        user.setProviderId( object.getProviderId() );
        user.setRole( roleMapper.toEntity( object.getRole() ) );

        return user;
    }

    @Override
    public UserDto toDto(User object) {
        if ( object == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setCreatedAt( object.getCreatedAt() );
        userDto.setUpdatedAt( object.getUpdatedAt() );
        userDto.setEmail( object.getEmail() );
        userDto.setId( object.getId() );
        userDto.setName( object.getName() );
        userDto.setPassword( object.getPassword() );
        userDto.setPhotoUrl( object.getPhotoUrl() );
        userDto.setProvider( object.getProvider() );
        userDto.setProviderId( object.getProviderId() );
        userDto.setRole( roleMapper.toDto( object.getRole() ) );

        return userDto;
    }
}
