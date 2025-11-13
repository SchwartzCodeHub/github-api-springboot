package com.schwartz.github.configuration;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfiguration {
    @Bean
    public RestTemplate restTemplate(@Value("${rest.client.socket.timeout}") long socketTimeout,
            @Value("${rest.client.connect.timeout}") long connectTimeout) {
        return new RestTemplateBuilder()
                .connectTimeout(Duration.ofSeconds(socketTimeout))
                .readTimeout(Duration.ofSeconds(connectTimeout))
                .build();
    }
}
