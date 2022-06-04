package com.duongminh.funchat.operation.controller;

import com.duongminh.funchat.core.model.ApiResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/root")
public class RootController {

    @GetMapping
    public ResponseEntity<String> ping() {
        log.info("Server OK!!!");
        return ApiResponse.ok("Server OK!!!").build();
    }

}
