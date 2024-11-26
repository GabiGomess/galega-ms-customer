package com.galega.customer.infrastructure.repository.postgres;

import com.galega.customer.domain.entity.Customer;
import com.galega.customer.infrastructure.repository.postgres.mapper.CustomerMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

public class CustomerRepositoryTest {

    private CustomerRepository customerRepository;
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    public void setUp() {
        jdbcTemplate = mock(JdbcTemplate.class);
        customerRepository = new CustomerRepository();
        customerRepository.setJdbcTemplate(jdbcTemplate);
    }

    @Test
    public void testCreate() {
        // Given
        Customer customer = new Customer();
        customer.setId(UUID.randomUUID());
        customer.setCpf("12345678900");
        customer.setName("John Doe");
        customer.setEmail("john.doe@example.com");

        given(jdbcTemplate.update(anyString(), any(), any(), any(), any())).willReturn(1);

        // When
        int result = customerRepository.create(customer);

        // Then
        then(result).isEqualTo(1);
        verify(jdbcTemplate, times(1)).update(
                anyString(),
                eq(customer.getId()),
                eq(customer.getCpf()),
                eq(customer.getName()),
                eq(customer.getEmail())
        );
    }

    @Test
    public void testGetByCpf() {
        // Given
        Customer customer = new Customer();
        customer.setCpf("12345678900");

        given(jdbcTemplate.query(anyString(), any(CustomerMapper.listMapper.getClass()), anyString())).willReturn(List.of(customer));

        // When
        List<Customer> result = customerRepository.getByCpf("12345678900");

        // Then
        then(result).hasSize(1);
        then(result.get(0).getCpf()).isEqualTo("12345678900");
        verify(jdbcTemplate, times(1)).query(
                anyString(),
                any(CustomerMapper.listMapper.getClass()),
                eq("12345678900")
        );
    }
}
