package com.galega.customer.domain.usecase;

import com.galega.customer.adapters.in.rest.dto.PutCustomerDTO;
import com.galega.customer.domain.entity.Customer;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class ICustomerUseCaseTest {

    @Test
    public void testCreateCustomer() {
        // Given
        ICustomerUseCase useCase = mock(ICustomerUseCase.class);
        Customer customer = new Customer();
        given(useCase.createCustomer(any(Customer.class))).willReturn(customer);

        // When
        Customer result = useCase.createCustomer(customer);

        // Then
        then(result).isEqualTo(customer);
        verify(useCase, times(1)).createCustomer(customer);
    }

    @Test
    public void testGetCustomerByCpf() {
        // Given
        ICustomerUseCase useCase = mock(ICustomerUseCase.class);
        Customer customer = new Customer();
        customer.setCpf("12345678900");
        given(useCase.getCustomerByCpf(anyString())).willReturn(List.of(customer));

        // When
        List<Customer> result = useCase.getCustomerByCpf("12345678900");

        // Then
        then(result).hasSize(1);
        then(result.get(0).getCpf()).isEqualTo("12345678900");
        verify(useCase, times(1)).getCustomerByCpf("12345678900");
    }

    @Test
    public void testGetAllCustomers() {
        // Given
        ICustomerUseCase useCase = mock(ICustomerUseCase.class);
        Customer customer = new Customer();
        given(useCase.getAllCustomers()).willReturn(List.of(customer));

        // When
        List<Customer> result = useCase.getAllCustomers();

        // Then
        then(result).hasSize(1);
        verify(useCase, times(1)).getAllCustomers();
    }

    @Test
    public void testUpdateCustomer() {
        ICustomerUseCase useCase = mock(ICustomerUseCase.class);
        PutCustomerDTO customerDTO = new PutCustomerDTO();
        when(useCase.updateCustomer(any(PutCustomerDTO.class), anyString())).thenReturn(customerDTO);

        PutCustomerDTO result = useCase.updateCustomer(customerDTO, "12345678900");
        assertEquals(customerDTO, result);
        verify(useCase, times(1)).updateCustomer(customerDTO, "12345678900");
    }
}
