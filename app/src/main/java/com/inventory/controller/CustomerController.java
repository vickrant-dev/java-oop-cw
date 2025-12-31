package com.inventory.controller;

import com.inventory.domain.Customer;
import com.inventory.domain.Transaction;
import com.inventory.service.CustomerService;
import com.inventory.service_manager.CustomerMgr;

import java.util.List;

public class CustomerController {
    private final CustomerService customerService;

    public CustomerController() {
        this.customerService = new CustomerMgr();
    }

    public List<Customer> getAllCustomers() {
        return customerService.fetchAllCustomers();
    }

    public String updateCustomerDetails(Customer customer) {
        return customerService.updateCustomerDetails(customer);
    }

    public String createCustomer(Customer customer) {
        return customerService.createCustomer(customer);
    }

    public String deleteCustomer(Customer customer) {
        return customerService.deleteCustomer(customer);
    }

    public List<Transaction> fetchCustomerTransactions(Customer customer) {
        return customerService.fetchCustomerTransactions(customer);
    }
}
