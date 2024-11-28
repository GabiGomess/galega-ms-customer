package com.galega.customer.adapters.in.rest.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.assertj.core.api.BDDAssertions.then;

public class HealthCheckControllerTest {

    @Test
    public void testHealthCheck() {
        // Given
        HealthCheckController controller = new HealthCheckController();

        // When
        ResponseEntity<String> response = controller.test();

        // Then
        then(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(response.getBody()).isEqualTo("API is up and running");
    }

    @Test
    public void testHealthCheckWithCustomMessage() {
        // Given
        HealthCheckController controller = new HealthCheckController();

        // When
        ResponseEntity<String> response = controller.test();

        // Then
        then(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(response.getBody()).isEqualTo("API is up and running");
    }
}
