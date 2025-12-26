package com.inventory.ui.auth.signup;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.inventory.ui.auth.login.*;
import com.inventory.utils.handleAuth;
import com.inventory.utils.handleEmailValidation;
import com.inventory.utils.handleValidateFields;

public class Signup extends JFrame {

    private String username = "";
    private String email = "";
    private char[] password = {};

    private JTextField usernameField;
    private JTextField emailField;
    private JPasswordField passwordField;

    private JButton signupButton;

    private JLabel usernameLabel;
    private JLabel emailLabel;
    private JLabel passwordLabel;

    private JButton loginButton;
    private JLabel loginLabel;

    public Signup() {

        setTitle("Signup");
        setSize(375, 325);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Username
        usernameLabel = new JLabel("Username");
        usernameField = new JTextField();
        usernameLabel.setBounds(30, 0, 100, 50);
        usernameField.setBounds(30, 40, 200, 25);
        add(usernameLabel);
        add(usernameField);

        // Email
        emailLabel = new JLabel("Email");
        emailField = new JTextField();
        emailLabel.setBounds(30, 70, 100, 25);
        emailField.setBounds(30, 100, 200, 25);
        add(emailLabel);
        add(emailField);

        // Password
        passwordLabel = new JLabel("Password");
        passwordField = new JPasswordField();
        passwordLabel.setBounds(30, 130, 100, 25);
        passwordField.setBounds(30, 160, 200, 25);
        add(passwordLabel);
        add(passwordField);

        // Signup button
        signupButton = new JButton("Signup");
        signupButton.setBounds(30, 200, 100, 30);
        add(signupButton);

        signupButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                signupButton.setEnabled(false);

                handleValidateFields validate_fields = new handleValidateFields();
                String field_err = validate_fields.validateFields(usernameField, emailField, passwordField);

                // validate fields (not empty)
                if (field_err != null) {
                    FailureDialog failure = new FailureDialog();
                    failure.setFailureDialog("Invalid. Please fill in all the blanks");
                    signupButton.setEnabled(true);
                    return;
                }

                // validate email
                handleEmailValidation validate_email = new handleEmailValidation();
                String email_err = validate_email.isEmailValid(emailField);

                if (email_err != null) {
                    FailureDialog failure = new FailureDialog();
                    failure.setFailureDialog("Invalid email. Please enter a valid email.");
                    signupButton.setEnabled(true);
                    return;
                }

                handleAuth signup = new handleAuth
                (
                        usernameField.getText(), emailField.getText(), passwordField.getPassword()
                );

                int status = signup.signupUser(); // assuming you have this method

                if(status == 200) {
                    SuccessDialog success = new SuccessDialog();
                    success.setSuccessDialog("Signup Success");
                    signupButton.setEnabled(true);
                } else if (status == 503) {
                    FailureDialog failure = new FailureDialog();
                    failure.setFailureDialog("Connection failed.");
                    signupButton.setEnabled(true);
                } else if (status == 409) {
                    FailureDialog failure = new FailureDialog();
                    failure.setFailureDialog("Username or email already exists.");
                    signupButton.setEnabled(true);
                } else {
                    FailureDialog failure = new FailureDialog();
                    failure.setFailureDialog("Signup Failed. Invalid Details.");
                    signupButton.setEnabled(true);
                }
            }
        });

        // Login label
        loginLabel = new JLabel("Don't have an account?");
        loginLabel.setBounds(30, 240, 150, 25);
        add(loginLabel);

        // Login button
        loginButton = new JButton("Login");
        loginButton.setBounds(70, 240, 250, 25);
        loginButton.setBorderPainted(false);
        loginButton.setContentAreaFilled(false);
        loginButton.setForeground(Color.BLUE);
        loginButton.setFont(new Font("Arial", Font.PLAIN, 12));
        loginButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR)); // making it like a link
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Signup.this.setVisible(false);

                Login Login = new Login();
                Login.setVisible(true);
            }
        });
        add(loginButton);

    }
}
