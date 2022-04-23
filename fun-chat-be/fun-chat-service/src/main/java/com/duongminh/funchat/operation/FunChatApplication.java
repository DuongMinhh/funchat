package com.duongminh.funchat.operation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Configuration
@SpringBootApplication
@ComponentScan(basePackages = { "com.duongminh.funchat.core", "com.duongminh.funchat.operation" })
@EnableJpaRepositories("com.duongminh.funchat.core.repository")
@EntityScan(basePackages = { "com.duongminh.funchat.core.entity" })
public class FunChatApplication {

    @Autowired
    private Environment env;
    
    public static void main(String[] args) {
        SpringApplication.run(FunChatApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void afterStartUp() {
        log.info("==================================================");
        log.info(">>>>> Fun-chat operation is running");
        log.info(">>>>> Context    	: " + env.getProperty("server.servlet.context-path"));
        log.info(">>>>> Environment	: " + env.getProperty("spring.profiles.active"));
        log.info(">>>>> Port       	: " + env.getProperty("server.port"));
        log.info("==================================================");
    }

}
