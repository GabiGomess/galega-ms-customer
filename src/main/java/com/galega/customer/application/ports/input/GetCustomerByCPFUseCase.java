package com.galega.customer.application.ports.input;

import com.galega.customer.domain.exception.CustomerNotFound;
import com.galega.customer.domain.model.Customer;

public interface GetCustomerByCPFUseCase {

    public Customer getCustomerByCPF(String cpf) throws CustomerNotFound;

}
