package com.duongminh.funchat.operation.util;

import java.util.Objects;

import org.springframework.security.core.context.SecurityContextHolder;

import com.duongminh.funchat.core.exception.CustomException;
import com.duongminh.funchat.operation.security.UserPrincipal;

public class ContextUtils {

    public static Long getUserId() throws CustomException {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (Objects.isNull(userPrincipal)) {
            throw new IllegalAccessError("Authentication object not found");
        }
        return userPrincipal.getId();
    }
    
    public static String getUserEmail() throws CustomException {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (Objects.isNull(userPrincipal)) {
            throw new IllegalAccessError("Authentication object not found");
        }
        return userPrincipal.getEmail();
    }
    
}
