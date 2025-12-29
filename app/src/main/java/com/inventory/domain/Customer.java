package com.inventory.domain;

import com.inventory.controller.CustomerController;
import com.inventory.utils.handleValidateFields;
import com.inventory.utils.loadTransactions;

import java.util.List;

public class Customer extends Person {
    public Customer (String id, String name, String contact_info) {
        this.id = id;
        this.name = name;
        this.contact_info = contact_info;
    }

    public Customer (String name, String contact_info) {
        this.name = name;
        this.contact_info = contact_info;
    }

    // GETTERS
//    public List<Transaction> ViewCustomerTransactions() {
//        return customerTransactions();
//    }

    // SETTERS
    public String createCustomer() {
        return createNewCustomer();
    }
    public String updateCustomer(String name, String contact_info) {
        this.name = name;
        this.contact_info = contact_info;
        return updateCustomerDetails();
    }
    public String delCustomer() {
        return deleteCustomer();
    }

    private String updateCustomerDetails() {
        if(new handleValidateFields().validateFields(this.name, this.contact_info) == null) {
            int update_res = new CustomerController().updateCustomerDetails(this.id, this.name,
                    this.contact_info);
            if (update_res == 200) {
                System.out.println("Updated customer successfully for: " + this.id);
            }
            else {
                System.out.println("Customer updating failed for: " + this.id + ". Error: " + update_res);
            }
            return "200";
        }
        else {
            return "401a";
        }
    }
    private String createNewCustomer() {
        if(new handleValidateFields().validateFields(this.name, this.contact_info) == null) {
            int create_res = new CustomerController().createNewCustomer(this.name,
                    this.contact_info);
            if (create_res == 200) {
                System.out.println("created a new customer successfully for: " + this.id);
            }
            else {
                System.out.println("Customer creation failed for: " + this.id + ". Error: " + create_res);
            }
            return "200";
        }
        else {
            return "401a";
        }
    }
    private String deleteCustomer() {
        if(new handleValidateFields().validateFields(this.name, this.contact_info) == null) {
            int delete_res = new CustomerController().deleteCustomer(this.id);
            if (delete_res == 200) {
                System.out.println("Deleted customer successfully for: " + this.id);
            }
            else {
                System.out.println("Customer deletion failed for: " + this.id + ". Error: " + delete_res);
            }
            return "200";
        }
        else {
            return "401a";
        }
    }

//    private List<Transaction> customerTransactions() {
//        if(new handleValidateFields().validateFields(this.id) == null) {
//            List<Transaction> cus_res = new loadTransactions().loadAllCustomerTransactions();
//            if (!cus_res.isEmpty()) {
//                System.out.println("Fetched all customer transactions for: " + this.id);
//            }
//            else {
//                System.out.println("Fetching customer transactions failed for: " + this.id + ". Error: " + cus_res);
//            }
//            return cus_res;
//        }
//        return null;
//    }
}
