package com.example.news.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("spring.datasource")
public class DataSourceProperties {
    @Value("${url}")
    public static final String url = "";
    @Value("${username}")
    public static final String username = "";
    @Value("${password}")
    public static final String password = "";
}
