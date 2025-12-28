package com.inventory.utils;

import com.inventory.domain.Transaction;
import com.inventory.domain.TransactionDetails;

import javax.swing.*;
import java.util.List;

public class handleValidateFields {

    public String validateFields(String input) {
        if (input.trim().isEmpty()){
            return "401a";
        }
        return null;
    }

    // used for validating transaction fields
    public String validateFields(String customer_id, String transaction_date,
                                 double total_amount, String created_by)
    {
        if (customer_id.trim().isEmpty() || transaction_date.trim().isEmpty()
                || total_amount < 0 || created_by.trim().isEmpty()) {
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

    public String validateListFields(List<TransactionDetails> transaction_details)
    {
        for (TransactionDetails transaction : transaction_details) {
            if (validateFields(transaction.getProductId(), transaction.getQuantity(),
                    transaction.getPrice()) != null) {
                return "401a";
            }
        }
        return null;
    }

    // used for validating supplier fields
    public String validateFields(String name, String contact_info)
    {
        if (name.trim().isEmpty() || contact_info.trim().isEmpty()) {
            return "401a";
        }
        return null;
    }

    // used for validating product fields
    public String validateFields(int product_id, String name, String category,
                                 double price, int stock_quantity)
    {

        if (product_id == 0 || product_id < 0) {
            return "401a";
        }
        if (name.trim().isEmpty()) {
            return "401a"; // invalid name
        }

        if (category.trim().isEmpty()) {
            return "401a"; // invalid category
        }

        if (price < 0) {
            return "401a"; // invalid price
        }

        if (stock_quantity < 0) {
            return "401a"; // invalid stock quantity
        }

        return null; // valid
    }

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
