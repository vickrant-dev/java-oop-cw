package com.inventory.service_manager;

import com.inventory.domain.Customer;
import com.inventory.domain.Transaction;
import com.inventory.repositories.CustomerRepository;
import com.inventory.service.CustomerService;
import com.inventory.utils.handleValidateFields;

import java.util.ArrayList;
import java.util.List;

public class CustomerMgr implements CustomerService {
    private final CustomerRepository customerRepo;

    public CustomerMgr() {
        this.customerRepo = new CustomerRepository();
    }

    public List<Customer> fetchAllCustomers() {
        return customerRepo.getAllCustomers();
    }

    public String updateCustomerDetails(Customer customer) {
        int update_res = customerRepo.updateCustomerDetails(customer);
        if (update_res == 200) {
            System.out.println("Updated customer successfully for: " + customer.getCustomerId());
            return "200";
        }
        else {
            System.out.println("Customer updating failed for: " + customer.getCustomerId() + ". Error: " + update_res);
            return "401a";
        }
    }

    public String createCustomer(Customer customer) {
        int create_res = customerRepo.createCustomer(customer);
        if (create_res == 200) {
            System.out.println("created a new customer successfully for: " + customer.getCustomerId());
            return "200";
        }
        else {
            System.out.println("Customer creation failed for: " + customer.getCustomerName() + ". Error: " + create_res);
            return "401a";
        }
    }

    public boolean checkCusTransactions(Customer customer) {
        return customerRepo.checkCusTransactions(customer);
    }

    public String deleteCustomer(Customer customer) {
        int delete_res = customerRepo.deleteCustomer(customer);
        if (delete_res == 200) {
            System.out.println("Deleted customer successfully for: " + customer.getCustomerId());
            return "200";
        }
        else {
            System.out.println("Customer deletion failed for: " + customer.getCustomerId() + ". Error: " + delete_res);
            return "401a";
        }
    }

    public List<Transaction> fetchCustomerTransactions(Customer customer) {
        List<Transaction> customer_transactions;
        customer_transactions = customerRepo.fetchCustomerTransactions(customer);
        return customer_transactions;
    }
}
