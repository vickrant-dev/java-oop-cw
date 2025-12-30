package com.inventory.utils;

import com.inventory.domain.*;

import javax.swing.*;
import java.util.List;

public class handleValidateFields {

    public String validateFields(String input) {
        if (input.trim().isEmpty()){
            return "401a";
        }
        return null;
    }

    // used for validating TRANSACTION fields
    public String validateFields(Transaction transaction)
    {
        if (transaction.getCustomerId().trim().isEmpty()
                || transaction.getTransactionDate().trim().isEmpty()
                || transaction.getTotalAmount() < 0
                || transaction.getPaymentMethod().trim().isEmpty()
                || transaction.getCreatedBy().trim().isEmpty()
                || transaction.getCreatedAt().trim().isEmpty()
                || transaction.getTransactionDetails().isEmpty()) {
            return "401a";
        }
        return null;
    }
    public String validateFields(String product_id, int quantity, double price)
    {
        if (product_id.trim().isEmpty() || quantity <= 0 || price < 0) {
            return "401a";
        }
        return null;
    }
    public String validateFields(List<TransactionDetails> transactionDetails)
    {
        for (TransactionDetails transaction : transactionDetails) {
            if (validateFields(transaction.getProductId(), transaction.getQuantity(),
                    transaction.getPrice()) != null) {
                return "401a";
            }
        }
        return null;
    }

    // used for validating SUPPLIER fields
    public String validateFields(Supplier supplier)
    {
        if (supplier.getSupplierName().trim().isEmpty() ||
                supplier.getSupplierContactInfo().trim().isEmpty()) {
            return "401a";
        }
        return null;
    }


    // used for validating PRODUCT fields
    public String validateFields(Product product)
    {

        if (product.getProductId() == 0 || product.getProductId() < 0) {
            return "401a";
        }
        if (product.getProductName().trim().isEmpty()) {
            return "401a"; // invalid name
        }

        if (product.getProductCategory().trim().isEmpty()) {
            return "401a"; // invalid category
        }

        if (product.getProductPrice() < 0) {
            return "401a"; // invalid price
        }

        if (product.getProductStockQuantity() < 0) {
            return "401a"; // invalid stock quantity
        }

        if (product.getSupplierName().trim().isEmpty()) {
            return "401a";
        }

        return null; // valid
    }


    // used for validating CUSTOMER fields
    public String validateFields(Customer customer) {
        if (customer.getCustomerId().trim().isEmpty()) {
            return "401a";
        }
        if (customer.getCustomerName().trim().isEmpty()) {
            return "401a";
        }
        if (customer.getCustomerContactInfo().trim().isEmpty()) {
            return "401a";
        }
        return null;
    }


    // used for validating LOGIN/SIGNUP
    public String validateFields(JTextField field1, JPasswordField field2)
    {
        if (field1.getText().trim().isEmpty() || field2.getPassword().length == 0) {
            return "401a";
        }
        return null;
    }

    public String validateFields(JTextField field1, JTextField field2, JPasswordField field3)
    {
        if (field1.getText().trim().isEmpty() || field2.getText().trim().isEmpty() || field3.getPassword().length == 0) {
            return "401a";
        }
        return null;
    }
}
