package com.galega.customer.infrastructure.repository.postgres;

import com.galega.customer.infrastructure.repository.postgres.mapper.CustomerMapper;
import com.galega.customer.domain.repository.ICustomerRepository;
import com.galega.customer.adapters.in.rest.dto.PutCustomerDTO;
import com.galega.customer.domain.entity.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository("PGCustomerRepository")
public class CustomerRepository implements ICustomerRepository {

    private JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return this.jdbcTemplate;
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int create (Customer customer) {
        String sql = "INSERT INTO public.customer (id, cpf, name, email) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(
                sql,
                customer.getId(),
                customer.getCpf(),
                customer.getName(),
                customer.getEmail()
        );
    }

    @Override
    public List<Customer> getByCpf(String cpf) {
        String sql = "SELECT * FROM customer WHERE cpf = ?";
        return jdbcTemplate.query(
                sql,
                CustomerMapper.listMapper,
                cpf);
    }

    @Override
    public Customer getById(UUID id) {
        String sql = "SELECT * FROM customer WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(
                sql,
                CustomerMapper.listMapper,
                id);
        }
        catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Customer> getAll() {
        String sql = "SELECT * FROM customer ORDER BY name";
        return jdbcTemplate.query(
                sql,
                CustomerMapper.listMapper);
    }

    @Override
    public int update(PutCustomerDTO customer, String cpf) {
        String sql = "UPDATE customer SET name = ?, email = ? WHERE cpf = ?";

        return jdbcTemplate.update(
                sql,
                customer.getName(),
                customer.getEmail(),
                cpf
        );
    }
}