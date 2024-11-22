package com.galega.customer.adapters.in.rest.controller;

import com.galega.customer.domain.service.CustomerService;
import com.galega.customer.domain.entity.Customer;
import com.galega.customer.adapters.in.rest.dto.PutCustomerDTO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CustomerControllerTest {

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCustomerByCpf() {
        String cpf = "12345678900";
        List<Customer> customers = List.of(new Customer());
        when(customerService.getCustomerByCpf(cpf)).thenReturn(customers);

        ResponseEntity<List<Customer>> response = customerController.getCustomerByCpf(cpf);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(customers, response.getBody());
    }

    @Test
    void testGetCustomerByCpfNoContent() {
        String cpf = "12345678900";
        when(customerService.getCustomerByCpf(cpf)).thenReturn(Collections.emptyList());

        ResponseEntity<List<Customer>> response = customerController.getCustomerByCpf(cpf);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void testGetAllCustomers() {
        List<Customer> customers = List.of(new Customer());
        when(customerService.getAllCustomers()).thenReturn(customers);

        ResponseEntity<List<Customer>> response = customerController.getAllCustomers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(customers, response.getBody());
    }

    @Test
    void testGetAllCustomersNoContent() {
        when(customerService.getAllCustomers()).thenReturn(Collections.emptyList());

        ResponseEntity<List<Customer>> response = customerController.getAllCustomers();

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void testCreateCustomer() {
        Customer customer = new Customer();
        when(customerService.createCustomer(customer)).thenReturn(customer);

        ResponseEntity<Customer> response = customerController.createCustomer(customer);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(customer, response.getBody());
    }

    @Test
    void testCreateCustomerBadRequest() {
        Customer customer = new Customer();
        when(customerService.createCustomer(customer)).thenReturn(null);

        ResponseEntity<Customer> response = customerController.createCustomer(customer);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    /*@Test
    void testUpdateCustomerByCpf() {
        String cpf = "12345678900";
        PutCustomerDTO customerDTO = new PutCustomerDTO();
        List<Customer> customers = List.of(new Customer());
        when(customerService.updateCustomer(customerDTO, cpf)).thenReturn(new Customer());
        when(customerService.getCustomerByCpf(cpf)).thenReturn(customers);

        ResponseEntity<List<Customer>> response = customerController.updateCustomerByCpf(customerDTO, cpf);

        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertEquals(customers, response.getBody());
    }*/

    @Test
    void testUpdateCustomerByCpfBadRequest() {
        String cpf = "12345678900";
        PutCustomerDTO customerDTO = new PutCustomerDTO();
        when(customerService.updateCustomer(customerDTO, cpf)).thenReturn(null);

        ResponseEntity<List<Customer>> response = customerController.updateCustomerByCpf(customerDTO, cpf);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}