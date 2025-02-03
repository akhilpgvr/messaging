package com.medicus_connect.messaging.configuration;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@org.springframework.boot.context.properties.ConfigurationProperties(prefix = "app.messages")
@EnableConfigurationProperties
public class ConfigurationProperties {

    private String delay;
    private String cancellation;
    private String success;
}