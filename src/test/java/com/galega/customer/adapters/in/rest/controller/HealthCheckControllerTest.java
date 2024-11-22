package com.galega.customer.adapters.in.rest.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HealthCheckControllerTest {

    @Test
    void testHealthCheck() {
        HealthCheckController controller = new HealthCheckController();
        ResponseEntity<String> response = controller.test();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("API is up and running", response.getBody());
    }
}