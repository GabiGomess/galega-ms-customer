package com.galega.customer.adapters.in.rest.controller;

import com.galega.customer.adapters.in.rest.dto.PutCustomerDTO;
import com.galega.customer.domain.service.CustomerService;
import com.galega.customer.domain.entity.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/{cpf}")
    public ResponseEntity<List<Customer>> getCustomerByCpf(@PathVariable String cpf) {
        var customers = customerService.getCustomerByCpf(cpf);

        if(customers.isEmpty()) {
            return ResponseEntity.noContent().build();
        }   return ResponseEntity.ok(customers);
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        var customers = customerService.getAllCustomers();

        if(customers.isEmpty()) {
            return ResponseEntity.noContent().build();
        }   return ResponseEntity.ok(customers);
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer (@RequestBody Customer customer) {
        if(customerService.createCustomer(customer) != null) {
          return ResponseEntity.status(HttpStatus.CREATED).body(customer);
        } else return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{cpf}")
    public ResponseEntity<List<Customer>> updateCustomerByCpf(@RequestBody PutCustomerDTO customerDTO, @PathVariable String cpf) {
        if(customerService.updateCustomer(customerDTO, cpf) != null) {
            var getCustomer = customerService.getCustomerByCpf(cpf);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(getCustomer);
        } else return ResponseEntity.badRequest().build();
    }
}