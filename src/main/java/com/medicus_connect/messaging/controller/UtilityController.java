package com.medicus_connect.messaging.controller;

import com.medicus_connect.messaging.configuration.ConfigurationProperties;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/utility")
public class UtilityController {

    @Autowired
    private ConfigurationProperties configurationProperties;

    @Operation(summary = "test", description = "")
    @GetMapping("/test")
    public String test() {

        return configurationProperties.getCancellation();
    }
}
