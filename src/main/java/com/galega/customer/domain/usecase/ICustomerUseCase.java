package com.galega.customer.domain.usecase;

import java.util.List;
import com.galega.customer.domain.entity.Customer;
import com.galega.customer.adapters.in.rest.dto.PutCustomerDTO;

public interface ICustomerUseCase {

    Customer createCustomer (Customer customer);
    List<Customer> getCustomerByCpf(String cpf);
    List<Customer> getAllCustomers();
    PutCustomerDTO updateCustomer(PutCustomerDTO customer, String cpf);

}