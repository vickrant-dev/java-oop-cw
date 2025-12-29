package com.inventory.service_manager;

import com.inventory.domain.Customer;
import com.inventory.repositories.CustomerRepository;
import com.inventory.service.CustomerService;
import com.inventory.utils.handleValidateFields;

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
        if(new handleValidateFields().validateFields(customer) == null) {
            int update_res = customerRepo.updateCustomerDetails(customer);
            if (update_res == 200) {
                System.out.println("Updated customer successfully for: " + customer.getCustomerId());
            }
            else {
                System.out.println("Customer updating failed for: " + customer.getCustomerId() + ". Error: " + update_res);
            }
            return "200";
        }
        else {
            return "401a";
        }
    }

    public String createCustomer(Customer customer) {
        if(new handleValidateFields().validateFields(customer) == null) {
            int create_res = customerRepo.createCustomer(customer);
            if (create_res == 200) {
                System.out.println("created a new customer successfully for: " + customer.getCustomerId());
            }
            else {
                System.out.println("Customer creation failed for: " + customer.getCustomerId() + ". Error: " + create_res);
            }
            return "200";
        }
        else {
            return "401a";
        }
    }

    public String deleteCustomer(Customer customer) {
        if(new handleValidateFields().validateFields(customer) == null) {
            int delete_res = customerRepo.deleteCustomer(customer);
            if (delete_res == 200) {
                System.out.println("Deleted customer successfully for: " + customer.getCustomerId());
            }
            else {
                System.out.println("Customer deletion failed for: " + customer.getCustomerId() + ". Error: " + delete_res);
            }
            return "200";
        }
        else {
            return "401a";
        }
    }
}
