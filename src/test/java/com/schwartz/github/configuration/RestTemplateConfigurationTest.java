package com.schwartz.github.configuration;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

public class RestTemplateConfigurationTest {

    private final RestTemplateConfiguration configuration = new RestTemplateConfiguration();

    @Test
    void restTemplateBeanShouldBeCreated() {
        long socketTimeout = 5L;
        long connectTimeout = 10L;

        RestTemplate restTemplate = configuration.restTemplate(socketTimeout, connectTimeout);

        assertThat(restTemplate).isNotNull();
    }
}
