package com.galega.customer.adapters.in.rest.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.BDDAssertions.then;

public class PutCustomerDTOTest {

    @Test
    public void testSetName() {
        // Given
        PutCustomerDTO dto = new PutCustomerDTO();

        // When
        dto.setName("John Doe");

        // Then
        then(dto.getName()).isEqualTo("John Doe");
    }

    @Test
    public void testSetEmail() {
        // Given
        PutCustomerDTO dto = new PutCustomerDTO();

        // When
        dto.setEmail("john.doe@example.com");

        // Then
        then(dto.getEmail()).isEqualTo("john.doe@example.com");
    }
}