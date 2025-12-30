package com.inventory.service;

import com.inventory.domain.Customer;
import com.inventory.domain.Transaction;

import java.util.List;

public interface CustomerService {
    String createCustomer(Customer customer);

    List<Customer> fetchAllCustomers();

    String updateCustomerDetails(Customer customer);

    String deleteCustomer(Customer customer);

    List<Transaction> fetchCustomerTransactions(Customer customer);
}
