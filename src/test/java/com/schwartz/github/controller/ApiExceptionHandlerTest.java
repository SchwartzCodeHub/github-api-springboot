package com.schwartz.github.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

public class ApiExceptionHandlerTest {

    private final ApiExceptionHandler handler = new ApiExceptionHandler();

    @Test
    void handleGenericExceptionShouldReturn500() {
        Exception ex = new Exception("Something went wrong");

        ResponseEntity<?> response = handler.handleGenericException(ex);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(response.getBody()).isEqualTo("An error occurred in the API.");
    }

    @Test
    void handleNoResourceShouldReturn404() {
        NoResourceFoundException ex = new NoResourceFoundException(HttpMethod.GET, "/api/users/unknown");

        ResponseEntity<?> response = handler.handleNoResource(ex);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void handleRestClientExceptionShouldReturn404ForNotFound() {
        RestClientResponseException ex = new RestClientResponseException(
                "Not Found", HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                null, null, null);

        ResponseEntity<?> response = handler.handleRestClientException(ex);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void handleRestClientExceptionShouldReturn204ForNoContent() {
        RestClientResponseException ex = new RestClientResponseException(
                "No Content", HttpStatus.NO_CONTENT.value(),
                HttpStatus.NO_CONTENT.getReasonPhrase(),
                null, null, null);

        ResponseEntity<?> response = handler.handleRestClientException(ex);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    void handleRestClientExceptionShouldReturn500ForOtherErrors() {
        RestClientResponseException ex = new RestClientResponseException(
                "Bad Request", HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                null, null, null);

        ResponseEntity<?> response = handler.handleRestClientException(ex);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(response.getBody()).isEqualTo("An error occurred in the API.");
    }
}
