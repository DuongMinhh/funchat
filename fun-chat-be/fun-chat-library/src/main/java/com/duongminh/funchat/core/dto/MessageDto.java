package com.duongminh.funchat.core.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EqualsAndHashCode(callSuper = false)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageDto extends BaseDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    
    private UserDto author;
    
    private RoomDto room;
    
    private String content;
    
}
