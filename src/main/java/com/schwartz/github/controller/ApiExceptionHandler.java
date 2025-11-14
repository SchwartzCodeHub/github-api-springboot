package com.schwartz.github.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGenericException(Exception ex) {
        return ResponseEntity.internalServerError().body("An error occurred in the API.");
    }

    @ExceptionHandler
    public ResponseEntity<?> handleNoResource(NoResourceFoundException noResourceException) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(RestClientResponseException.class)
    public ResponseEntity<?> handleRestClientException(RestClientResponseException restClientException) {
        switch (restClientException.getStatusCode()) {
            case HttpStatus.NOT_FOUND:
                return ResponseEntity.notFound().build();
            case HttpStatus.NO_CONTENT:
                return ResponseEntity.noContent().build();
            default:
                return ResponseEntity.internalServerError().body("An error occurred in the API.");
        }
    }
}