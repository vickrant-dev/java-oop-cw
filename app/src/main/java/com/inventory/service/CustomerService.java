package com.inventory.service;

import com.inventory.domain.Customer;

import java.util.List;

public interface CustomerService {
    String createCustomer(Customer customer);

    List<Customer> fetchAllCustomers();

    String updateCustomerDetails(Customer customer);

    String deleteCustomer(Customer customer);
}
