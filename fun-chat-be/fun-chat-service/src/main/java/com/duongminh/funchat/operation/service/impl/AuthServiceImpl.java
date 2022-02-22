package com.duongminh.funchat.operation.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.duongminh.funchat.core.exception.CustomException;
import com.duongminh.funchat.core.model.LoginModel;
import com.duongminh.funchat.operation.security.TokenProvider;
import com.duongminh.funchat.operation.service.AuthService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private TokenProvider tokenProvider;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public String processLogin(LoginModel model) throws CustomException {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(model.getEmail(), model.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            return tokenProvider.createToken(authentication);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new CustomException(e.getMessage());
        }
    }

}
