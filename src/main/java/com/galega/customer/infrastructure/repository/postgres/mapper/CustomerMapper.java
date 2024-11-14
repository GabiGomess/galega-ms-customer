package com.galega.customer.infrastructure.repository.postgres.mapper;

import com.galega.customer.domain.entity.Customer;
import org.springframework.jdbc.core.RowMapper;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.UUID;

public abstract class CustomerMapper {

    public static RowMapper<Customer> listMapper = new RowMapper<Customer>() {
        
        @Override
        public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
            Customer customer = new Customer();
            customer.setId(UUID.fromString(rs.getString("id")));
            customer.setCpf(rs.getString("cpf"));
            customer.setName(rs.getString("name"));
            customer.setEmail(rs.getString("email"));
            return customer;
        }
    };
}