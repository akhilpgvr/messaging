package com.medicus_connect.messaging.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@org.springframework.boot.context.properties.ConfigurationProperties(prefix = "spring.messages")
@EnableConfigurationProperties
public class ConfigurationProperties {

    private String delay;
    private String cancellation;
    private String success;
}