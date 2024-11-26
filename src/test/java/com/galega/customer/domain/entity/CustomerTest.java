package com.galega.customer.domain.entity;

import org.junit.jupiter.api.Test;
import java.util.UUID;
import static org.assertj.core.api.BDDAssertions.then;

public class CustomerTest {

    @Test
    public void testSetId() {
        // Given
        Customer customer = new Customer();
        UUID id = UUID.randomUUID();

        // When
        customer.setId(id);

        // Then
        then(customer.getId()).isEqualTo(id);
    }

    @Test
    public void testSetCpf() {
        // Given
        Customer customer = new Customer();

        // When
        customer.setCpf("12345678900");

        // Then
        then(customer.getCpf()).isEqualTo("12345678900");
    }

    @Test
    public void testSetName() {
        // Given
        Customer customer = new Customer();

        // When
        customer.setName("John Doe");

        // Then
        then(customer.getName()).isEqualTo("John Doe");
    }

    @Test
    public void testSetEmail() {
        // Given
        Customer customer = new Customer();

        // When
        customer.setEmail("john.doe@example.com");

        // Then
        then(customer.getEmail()).isEqualTo("john.doe@example.com");
    }
}
