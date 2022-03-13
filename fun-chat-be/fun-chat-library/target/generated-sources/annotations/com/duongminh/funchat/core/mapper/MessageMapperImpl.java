package com.duongminh.funchat.core.mapper;

import com.duongminh.funchat.core.dto.MessageDto;
import com.duongminh.funchat.core.dto.RoomDto;
import com.duongminh.funchat.core.entity.Message;
import com.duongminh.funchat.core.entity.Room;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-02-25T15:37:45+0700",
    comments = "version: 1.4.2.Final, compiler: Eclipse JDT (IDE) 1.4.50.v20210914-1429, environment: Java 17.0.1 (Eclipse Adoptium)"
)
@Component
public class MessageMapperImpl implements MessageMapper {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoomMapper roomMapper;

    @Override
    public Message toEntity(MessageDto object) {
        if ( object == null ) {
            return null;
        }

        Message message = new Message();

        message.setCreatedAt( object.getCreatedAt() );
        message.setUpdatedAt( object.getUpdatedAt() );
        message.setAuthor( userMapper.toEntity( object.getAuthor() ) );
        message.setContent( object.getContent() );
        message.setId( object.getId() );
        message.setRoom( roomMapper.toEntity( object.getRoom() ) );

        return message;
    }

    @Override
    public MessageDto toDto(Message object) {
        if ( object == null ) {
            return null;
        }

        MessageDto messageDto = new MessageDto();

        messageDto.setRoom( roomToRoomDto( object.getRoom() ) );
        messageDto.setCreatedAt( object.getCreatedAt() );
        messageDto.setUpdatedAt( object.getUpdatedAt() );
        messageDto.setAuthor( userMapper.toDto( object.getAuthor() ) );
        messageDto.setContent( object.getContent() );
        messageDto.setId( object.getId() );

        return messageDto;
    }

    @Override
    public RoomDto roomToRoomDto(Room entity) {
        if ( entity == null ) {
            return null;
        }

        RoomDto roomDto = new RoomDto();

        roomDto.setCreatedAt( entity.getCreatedAt() );
        roomDto.setUpdatedAt( entity.getUpdatedAt() );
        roomDto.setDescription( entity.getDescription() );
        roomDto.setId( entity.getId() );
        roomDto.setName( entity.getName() );
        roomDto.setOwner( userMapper.toDto( entity.getOwner() ) );

        return roomDto;
    }
}
