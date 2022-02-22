package com.duongminh.funchat.operation.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest req, HttpServletResponse res, AuthenticationException e) throws IOException, ServletException {
        log.info(req.getRequestURI());
        log.error("Responding with unauthorized error. Message - {}", e.getMessage());
        res.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getLocalizedMessage());
    }
    
}
