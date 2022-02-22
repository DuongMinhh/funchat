package com.duongminh.funchat.core.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.duongminh.funchat.core.dto.RoomDto;
import com.duongminh.funchat.core.entity.Room;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring", uses = { UserMapper.class })
public interface RoomMapper {

    Room toEntity(RoomDto object);

    RoomDto toDto(Room object);

    @Mapping(target = "members", ignore = true)
    RoomDto toDtoWLZ(Room object);

}
