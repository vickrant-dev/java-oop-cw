package com.inventory.domain;

import com.inventory.controller.CustomerController;
import com.inventory.utils.handleValidateFields;

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

    public String getCustomerId() {
        return id;
    }
    public String getCustomerName() {
        return name;
    }
    public String getCustomerContactInfo() {
        return contact_info;
    }

}
