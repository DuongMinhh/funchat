package com.duongminh.funchat.core.mapper;

import com.duongminh.funchat.core.dto.RoomDto;
import com.duongminh.funchat.core.dto.UserDto;
import com.duongminh.funchat.core.entity.Room;
import com.duongminh.funchat.core.entity.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-02-11T08:48:54+0700",
    comments = "version: 1.4.2.Final, compiler: Eclipse JDT (IDE) 1.4.0.v20210708-0430, environment: Java 16.0.2 (Oracle Corporation)"
)
@Component
public class RoomMapperImpl implements RoomMapper {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Room toEntity(RoomDto object) {
        if ( object == null ) {
            return null;
        }

        Room room = new Room();

        room.setCreatedAt( object.getCreatedAt() );
        room.setUpdatedAt( object.getUpdatedAt() );
        room.setDescription( object.getDescription() );
        room.setId( object.getId() );
        room.setMembers( userDtoListToUserList( object.getMembers() ) );
        room.setName( object.getName() );
        room.setOwner( userMapper.toEntity( object.getOwner() ) );

        return room;
    }

    @Override
    public RoomDto toDto(Room object) {
        if ( object == null ) {
            return null;
        }

        RoomDto roomDto = new RoomDto();

        roomDto.setCreatedAt( object.getCreatedAt() );
        roomDto.setUpdatedAt( object.getUpdatedAt() );
        roomDto.setDescription( object.getDescription() );
        roomDto.setId( object.getId() );
        roomDto.setMembers( userListToUserDtoList( object.getMembers() ) );
        roomDto.setName( object.getName() );
        roomDto.setOwner( userMapper.toDto( object.getOwner() ) );

        return roomDto;
    }

    @Override
    public RoomDto toDtoWLZ(Room object) {
        if ( object == null ) {
            return null;
        }

        RoomDto roomDto = new RoomDto();

        roomDto.setCreatedAt( object.getCreatedAt() );
        roomDto.setUpdatedAt( object.getUpdatedAt() );
        roomDto.setDescription( object.getDescription() );
        roomDto.setId( object.getId() );
        roomDto.setName( object.getName() );
        roomDto.setOwner( userMapper.toDto( object.getOwner() ) );

        return roomDto;
    }

    protected List<User> userDtoListToUserList(List<UserDto> list) {
        if ( list == null ) {
            return null;
        }

        List<User> list1 = new ArrayList<User>( list.size() );
        for ( UserDto userDto : list ) {
            list1.add( userMapper.toEntity( userDto ) );
        }

        return list1;
    }

    protected List<UserDto> userListToUserDtoList(List<User> list) {
        if ( list == null ) {
            return null;
        }

        List<UserDto> list1 = new ArrayList<UserDto>( list.size() );
        for ( User user : list ) {
            list1.add( userMapper.toDto( user ) );
        }

        return list1;
    }
}
