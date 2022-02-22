package com.duongminh.funchat.core.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

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
@JsonInclude(Include.NON_NULL)
public class UserDto extends BaseDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    
    private String name;
    
    private String email;
    
    @JsonIgnore
    private String password;
    
    private String provider;

    private String providerId;
    
    private String photoUrl;
    
    private RoleDto role;
    
}
