package com.inventory.controller;

import com.inventory.domain.Customer;
import com.inventory.repositories.CustomerRepository;

import java.util.List;

public class CustomerController {
    private final CustomerRepository customerRepo;

    public CustomerController() {
        this.customerRepo = new CustomerRepository();
    }

    public List<Customer> getAllCustomers() {
        return customerRepo.getAllCustomers();
    }

    public int updateCustomerDetails(String id, String name, String contact_info) {
        return customerRepo.updateCustomerDetails(id, name, contact_info);
    }

    public int createNewCustomer(String name, String contact_info) {
        return customerRepo.createNewCustomer(name, contact_info);
    }

    public int deleteCustomer(String id) {
        return customerRepo.deleteCustomer(id);
    }
}
