package com.galega.customer.domain.service;

import com.galega.customer.infrastructure.repository.postgres.CustomerRepository;
import com.galega.customer.adapters.in.rest.dto.PutCustomerDTO;
import com.galega.customer.domain.usecase.ICustomerUseCase;
import com.galega.customer.domain.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class CustomerService implements ICustomerUseCase {

    @Autowired
    @Qualifier("PGCustomerRepository")
    public CustomerRepository customerRepository;

    @Override
    public Customer createCustomer(Customer customer) {
        customer.setId(UUID.randomUUID());

        if(customerRepository.create(customer) == 1) return customer;
        return null;
    }

    @Override
    public List<Customer> getCustomerByCpf(String cpf) {
        return customerRepository.getByCpf(cpf);
    }

    @Override
    public Customer getCustomerById(UUID id) {
        return customerRepository.getById(id.toString());
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.getAll();
    }

    @Override
    public PutCustomerDTO updateCustomer(PutCustomerDTO customer, String cpf) {
        if(customerRepository.update(customer, cpf) == 1) return customer;
        return null;
    }
}