package com.inventory.utils;

import javax.swing.*;

public class handleValidateFields {

    public String validateString(String input) {
        if (input.trim().isEmpty()) {
            return "401a";
        }
        return null;
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
