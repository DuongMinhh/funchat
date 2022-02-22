package com.duongminh.funchat.core.model;

import org.apache.commons.lang3.StringUtils;

import com.duongminh.funchat.core.constant.MessageResponse;
import com.duongminh.funchat.core.exception.BadRequestException;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {

    private String name;
    private String email;
    private String password;
    
    public void validate() throws BadRequestException {
        if (StringUtils.isBlank(this.name) || StringUtils.isBlank(this.email) || StringUtils.isBlank(this.password)) {
            throw new BadRequestException(MessageResponse.USER_INVALID);
        }
    }
    
}
