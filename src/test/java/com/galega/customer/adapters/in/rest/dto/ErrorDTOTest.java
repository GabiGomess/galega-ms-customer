package com.galega.customer.adapters.in.rest.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.BDDAssertions.then;

public class ErrorDTOTest {

    @Test
    public void testNoArgsConstructor() {
        // Given
        ErrorDTO errorDTO = new ErrorDTO();

        // Then
        then(errorDTO.getMessage()).isNull();
    }

    @Test
    public void testAllArgsConstructor() {
        // Given
        ErrorDTO errorDTO = new ErrorDTO("Error message");

        // Then
        then(errorDTO.getMessage()).isEqualTo("Error message");
    }

    @Test
    public void testSetMessage() {
        // Given
        ErrorDTO errorDTO = new ErrorDTO();

        // When
        errorDTO.setMessage("New error message");

        // Then
        then(errorDTO.getMessage()).isEqualTo("New error message");
    }
}