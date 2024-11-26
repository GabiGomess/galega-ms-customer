package com.galega.customer.domain.repository;

import com.galega.customer.adapters.in.rest.dto.PutCustomerDTO;
import com.galega.customer.domain.entity.Customer;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class ICustomerRepositoryTest {

    @Test
    public void testCreate() {
        // Given
        ICustomerRepository repository = mock(ICustomerRepository.class);
        Customer customer = new Customer();
        given(repository.create(any(Customer.class))).willReturn(1);

        // When
        int result = repository.create(customer);

        // Then
        then(result).isEqualTo(1);
        verify(repository, times(1)).create(customer);
    }

    @Test
    public void testGetByCpf() {
        // Given
        ICustomerRepository repository = mock(ICustomerRepository.class);
        Customer customer = new Customer();
        customer.setCpf("12345678900");
        given(repository.getByCpf(anyString())).willReturn(List.of(customer));

        // When
        List<Customer> result = repository.getByCpf("12345678900");

        // Then
        then(result).hasSize(1);
        then(result.get(0).getCpf()).isEqualTo("12345678900");
        verify(repository, times(1)).getByCpf("12345678900");
    }

    @Test
    public void testGetAll() {
        // Given
        ICustomerRepository repository = mock(ICustomerRepository.class);
        Customer customer = new Customer();
        given(repository.getAll()).willReturn(List.of(customer));

        // When
        List<Customer> result = repository.getAll();

        // Then
        then(result).hasSize(1);
        verify(repository, times(1)).getAll();
    }

    @Test
    public void testUpdate() {
        // Given
        ICustomerRepository repository = mock(ICustomerRepository.class);
        PutCustomerDTO customerDTO = new PutCustomerDTO();
        given(repository.update(any(PutCustomerDTO.class), anyString())).willReturn(1);

        // When
        int result = repository.update(customerDTO, "12345678900");

        // Then
        then(result).isEqualTo(1);
        verify(repository, times(1)).update(customerDTO, "12345678900");
    }
}