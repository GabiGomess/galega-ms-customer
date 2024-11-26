package com.galega.customer.domain.service;

import com.galega.customer.adapters.in.rest.dto.PutCustomerDTO;
import com.galega.customer.domain.entity.Customer;
import com.galega.customer.infrastructure.repository.postgres.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class CustomerServiceTest {

    private CustomerService customerService;
    private CustomerRepository customerRepository;

    @BeforeEach
    public void setUp() {
        customerRepository = mock(CustomerRepository.class);
        customerService = new CustomerService();
        customerService.customerRepository = customerRepository;
    }

    @Test
    public void testCreateCustomer() {
        // Given
        Customer customer = new Customer();
        given(customerRepository.create(any(Customer.class))).willReturn(1);

        // When
        Customer result = customerService.createCustomer(customer);

        // Then
        then(result).isEqualTo(customer);
        verify(customerRepository, times(1)).create(customer);
    }

    @Test
    public void testCreateCustomerFailure() {
        // Given
        Customer customer = new Customer();
        given(customerRepository.create(any(Customer.class))).willReturn(0);

        // When
        Customer result = customerService.createCustomer(customer);

        // Then
        then(result).isNull();
        verify(customerRepository, times(1)).create(customer);
    }

    @Test
    public void testGetCustomerByCpf() {
        // Given
        Customer customer = new Customer();
        customer.setCpf("12345678900");
        given(customerRepository.getByCpf(anyString())).willReturn(List.of(customer));

        // When
        List<Customer> result = customerService.getCustomerByCpf("12345678900");

        // Then
        then(result).hasSize(1);
        then(result.get(0).getCpf()).isEqualTo("12345678900");
        verify(customerRepository, times(1)).getByCpf("12345678900");
    }

    @Test
    public void testGetAllCustomers() {
        // Given
        Customer customer = new Customer();
        given(customerRepository.getAll()).willReturn(List.of(customer));

        // When
        List<Customer> result = customerService.getAllCustomers();

        // Then
        then(result).hasSize(1);
        verify(customerRepository, times(1)).getAll();
    }

    @Test
    public void testUpdateCustomer() {
        // Given
        PutCustomerDTO customerDTO = new PutCustomerDTO();
        given(customerRepository.update(any(PutCustomerDTO.class), anyString())).willReturn(1);

        // When
        PutCustomerDTO result = customerService.updateCustomer(customerDTO, "12345678900");

        // Then
        then(result).isEqualTo(customerDTO);
        verify(customerRepository, times(1)).update(customerDTO, "12345678900");
    }

    @Test
    public void testUpdateCustomerFailure() {
        // Given
        PutCustomerDTO customerDTO = new PutCustomerDTO();
        given(customerRepository.update(any(PutCustomerDTO.class), anyString())).willReturn(0);

        // When
        PutCustomerDTO result = customerService.updateCustomer(customerDTO, "12345678900");

        // Then
        then(result).isNull();
        verify(customerRepository, times(1)).update(customerDTO, "12345678900");
    }
}