package com.duongminh.funchat.core.enums;

import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AuthProvider {
    
    LOCAL("local"),
    FACEBOOK("facebook"),
    GOOGLE("google"),
    GITHUB("github");
    
    private final String value;
    
    public final static AuthProvider of(String value) {
        return StringUtils.isBlank(value) ? null 
                : Stream.of(AuthProvider.values()).filter(v -> v.getValue().equalsIgnoreCase(value)).findAny().orElse(null);
    }
    
}
