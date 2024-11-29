package com.galega.customer.adapters.in.rest.controller;

import com.galega.customer.adapters.in.rest.dto.PutCustomerDTO;
import com.galega.customer.domain.entity.Customer;
import com.galega.customer.domain.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

public class CustomerControllerTest {

    private CustomerController customerController;
    private CustomerService customerService;

    @BeforeEach
    public void setUp() {
        customerService = mock(CustomerService.class);
        customerController = new CustomerController();
        customerController.customerService = customerService;
    }

    @Test
    void testGetCustomerByCpf() {
        // Given
        String cpf = "12345678900";
        Customer customer = new Customer();
        given(customerService.getCustomerByCpf(cpf)).willReturn(List.of(customer));

        // When
        ResponseEntity<List<Customer>> response = customerController.getCustomerByCpf(cpf);

        // Then
        then(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(response.getBody()).hasSize(1);
        then(response.getBody().get(0)).isEqualTo(customer);
    }

    @Test
    void testGetCustomerByCpfNoContent() {
        // Given
        String cpf = "12345678900";
        given(customerService.getCustomerByCpf(cpf)).willReturn(List.of());

        // When
        ResponseEntity<List<Customer>> response = customerController.getCustomerByCpf(cpf);

        // Then
        then(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    void testGetAllCustomers() {
        // Given
        Customer customer = new Customer();
        given(customerService.getAllCustomers()).willReturn(List.of(customer));

        // When
        ResponseEntity<List<Customer>> response = customerController.getAllCustomers();

        // Then
        then(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(response.getBody()).hasSize(1);
        then(response.getBody().get(0)).isEqualTo(customer);
    }

    @Test
    void testGetAllCustomersNoContent() {
        // Given
        given(customerService.getAllCustomers()).willReturn(List.of());

        // When
        ResponseEntity<List<Customer>> response = customerController.getAllCustomers();

        // Then
        then(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    void testCreateCustomer() {
        // Given
        Customer customer = new Customer();
        given(customerService.createCustomer(any(Customer.class))).willReturn(customer);

        // When
        ResponseEntity<Customer> response = customerController.createCustomer(customer);

        // Then
        then(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        then(response.getBody()).isEqualTo(customer);
    }

    @Test
    void testCreateCustomerBadRequest() {
        // Given
        Customer customer = new Customer();
        given(customerService.createCustomer(any(Customer.class))).willReturn(null);

        // When
        ResponseEntity<Customer> response = customerController.createCustomer(customer);

        // Then
        then(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void updateCustomerByCpf() {
        // Given
        String cpf = "12345678910";
        PutCustomerDTO customer = new PutCustomerDTO();
        customer.setEmail("newemail@gmail.com");
        Customer updatedCustomer = new Customer();
        updatedCustomer.setEmail(customer.getEmail());
        updatedCustomer.setCpf(cpf);

        given(customerService.updateCustomer(any(PutCustomerDTO.class), anyString())).willReturn(customer);
        given(customerService.getCustomerByCpf(anyString())).willReturn(Collections.singletonList(updatedCustomer));

        // When
        ResponseEntity<List<Customer>> response = customerController.updateCustomerByCpf(customer, cpf);

        // Then
        then(Objects.requireNonNull(response.getBody()).size()).isEqualTo(1);
        then(response.getStatusCode()).isEqualTo(HttpStatus.ACCEPTED);
        then(response.getBody().get(0)).usingRecursiveComparison().isEqualTo(updatedCustomer);
    }

    @Test
    void updateCustomerByCpfWhenNull() {
        // Given
        String cpf = "12345678910";
        PutCustomerDTO customer = new PutCustomerDTO();

        given(customerService.updateCustomer(any(PutCustomerDTO.class), anyString())).willReturn(null);

        // When
        ResponseEntity<List<Customer>> response = customerController.updateCustomerByCpf(customer, cpf);

        // Then
        then(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }
}