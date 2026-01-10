package com.inventory.ui.dashboard.customer_management;

import com.inventory.controller.CustomerController;
import com.inventory.domain.Customer;
import com.inventory.domain.Product;
import com.inventory.ui.dashboard.transaction_management.TransactionLifecycleTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CustomerLifecycleTest {
    private CustomerController customerController = new CustomerController();
    private Customer curr_customer;

    public void testCustomer(Product curr_product) {
        // CREATING CUSTOMER
        Customer customer = new Customer("test-customer", "1000");
        String res = customerController.createCustomer(customer);
        curr_customer = customer;
        assertEquals("200", res, "Failed to create customer");


        // READING CUSTOMER
        String id = customer.getCustomerId();
        String name = customer.getCustomerName();
        String contact_info = customer.getCustomerContactInfo();

        assertNotNull(id, "Invalid or empty id");
        assertEquals("test-customer", name, "Invalid or empty customer name");
        assertEquals("1000", contact_info,
                "Invalid or empty customer contact info");


        // UPDATING CUSTOMER
        String new_name = "test-customer-2";
        String new_contact_info = "2000";

        curr_customer.updateCustomer(new_name, new_contact_info);
        String res_2 = customerController.updateCustomerDetails(curr_customer);

        assertEquals("200", res_2, "Failed to update customer");


        // TRANSACTION TESTING
        TransactionLifecycleTest transactionLifecycleTest = new TransactionLifecycleTest();
        transactionLifecycleTest.testTransaction(curr_product, curr_customer);


        // DELETING CUSTOMER
        String res_3 = customerController.deleteCustomer(curr_customer);
        assertEquals("200", res_3, "Failed to delete customer");

    }

}
