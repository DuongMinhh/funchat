package com.duongminh.funchat.core.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import com.duongminh.funchat.core.dto.MessageDto;
import com.duongminh.funchat.core.dto.RoomDto;
import com.duongminh.funchat.core.entity.Message;
import com.duongminh.funchat.core.entity.Room;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring", uses = { UserMapper.class, RoomMapper.class })
public interface MessageMapper {

    Message toEntity(MessageDto object);

    @Mapping(target = "room", source = "room", qualifiedByName = "roomToRoomDtoWLZ")
    MessageDto toDto(Message object);

    @Named("roomToRoomDtoWLZ")
    @Mapping(target = "members", ignore = true)
    RoomDto roomToRoomDto(Room entity);

}
