package com.duongminh.funchat.core.model;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import com.duongminh.funchat.core.constant.MessageResponse;
import com.duongminh.funchat.core.dto.UserDto;
import com.duongminh.funchat.core.exception.CustomException;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomModel {

    private String name;
    private String description;
    private UserDto owner;
    
    public void validate() throws CustomException {
        if (StringUtils.isBlank(name) || Objects.isNull(owner) || Objects.isNull(owner.getId())) {
            throw new CustomException(MessageResponse.ROOM_INVALID);
        }
    }
    
}
