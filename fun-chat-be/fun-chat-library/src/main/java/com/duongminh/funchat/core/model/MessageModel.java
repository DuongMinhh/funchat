package com.duongminh.funchat.core.model;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import com.duongminh.funchat.core.constant.MessageResponse;
import com.duongminh.funchat.core.exception.CustomException;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MessageModel {

    private Long authorId;
    private Long roomId;
    private String content;
    
    public void validate() throws CustomException {
        if (Objects.isNull(authorId) || Objects.isNull(roomId) || StringUtils.isBlank(content)) {
            throw new CustomException(MessageResponse.MESSAGE_INVALID);
        }
    }
    
}
