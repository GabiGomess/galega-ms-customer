package com.galega.customer.application.ports.input;

import com.galega.customer.domain.model.Customer;

public interface CreateCustomerUseCase {

    public Customer createCustomer(Customer customer);

}
