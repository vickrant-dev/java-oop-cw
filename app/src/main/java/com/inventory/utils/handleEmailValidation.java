package com.inventory.utils;

import javax.swing.*;

public class handleEmailValidation {
    public String isEmailValid(JTextField field1) {
        String email = field1.getText().trim();

        // basic email pattern
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

        // invalid
        if (!email.matches(emailRegex)) {
            return "401b";
        }

        return null;
    }
}
