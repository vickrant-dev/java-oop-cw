package com.inventory.utils;

import javax.swing.*;

public class handleValidateFields {

    public String validateString(String input) {
        if (input.trim().isEmpty()){
            return "401a";
        }
        return null;
    }

    // used for validating supplier fields
    public String validateString(String name, String contact_info) {
        if (name.trim().isEmpty() || contact_info.trim().isEmpty()) {
            return "401a";
        }
        return null;
    }

    // used for validating product fields
    public String validateString(int product_id, String name, String category,
                                 double price, int stock_quantity) {

        if (name == null || name.trim().isEmpty()) {
            return "401a"; // invalid name
        }

        if (category == null || category.trim().isEmpty()) {
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


    public String validateFields(JTextField field1, JPasswordField field2) {
        if (field1.getText().trim().isEmpty() || field2.getPassword().length == 0) {
            return "401a";
        }
        return null;
    }

    public String validateFields(JTextField field1, JTextField field2, JPasswordField field3) {
        if (field1.getText().trim().isEmpty() || field2.getText().trim().isEmpty() || field3.getPassword().length == 0) {
            return "401a";
        }
        return null;
    }
}
