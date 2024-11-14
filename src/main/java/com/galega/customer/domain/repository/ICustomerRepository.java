package com.galega.customer.domain.repository;

import java.util.List;
import com.galega.customer.domain.entity.Customer;
import com.galega.customer.adapters.in.rest.dto.PutCustomerDTO;

public interface ICustomerRepository {

    int create (Customer customer);
    List<Customer> getByCpf(String cpf);
    List<Customer> getAll();
    int update(PutCustomerDTO customer, String cpf);
    
}