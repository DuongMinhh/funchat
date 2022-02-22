package com.duongminh.funchat.operation.service;

import com.duongminh.funchat.core.exception.CustomException;
import com.duongminh.funchat.core.model.LoginModel;

public interface AuthService {
    
    String processLogin(LoginModel model) throws CustomException;
    
}
