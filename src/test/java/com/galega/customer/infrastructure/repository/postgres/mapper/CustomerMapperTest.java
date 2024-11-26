package com.galega.customer.infrastructure.repository.postgres.mapper;

import com.galega.customer.domain.entity.Customer;
import org.junit.jupiter.api.Test;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class CustomerMapperTest {

    @SuppressWarnings("null")
    @Test
    public void testMapRow() throws SQLException {
        // Given
        ResultSet rs = mock(ResultSet.class);
        given(rs.getString("id")).willReturn(UUID.randomUUID().toString());
        given(rs.getString("cpf")).willReturn("12345678900");
        given(rs.getString("name")).willReturn("John Doe");
        given(rs.getString("email")).willReturn("john.doe@example.com");

        // When
        Customer customer = CustomerMapper.listMapper.mapRow(rs, 1);

        // Then
        then(customer.getCpf()).isEqualTo("12345678900");
        then(customer.getName()).isEqualTo("John Doe");
        then(customer.getEmail()).isEqualTo("john.doe@example.com");
    }
}
