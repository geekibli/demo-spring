package com.example.testspring.a04;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "java")
public class Bean4 {

    private String version;
    private String home;
}
