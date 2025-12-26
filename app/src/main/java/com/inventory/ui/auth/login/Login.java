package com.inventory.ui.auth.login;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;

import com.inventory.ui.auth.signup.Signup;
import com.inventory.utils.*;

public class Login extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JButton signupButton;
    private JLabel signupLabel;

    public Login() {

        setTitle("Login");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // center the window
        setLayout(null);

        // Username
        usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(50, 30, 100, 25);
        usernameField = new JTextField();
        usernameField.setBounds(50, 60, 250, 25);
        add(usernameLabel);
        add(usernameField);

        // Password
        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(50, 100, 100, 25);
        passwordField = new JPasswordField();
        passwordField.setBounds(50, 130, 250, 25);
        add(passwordLabel);
        add(passwordField);

        // Login button
        loginButton = new JButton("Login");
        loginButton.setBounds(50, 180, 100, 30);
        add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loginButton.setEnabled(false);

                handleValidateFields validate_fields = new handleValidateFields();
                String err = validate_fields.validateFields(usernameField, passwordField);

                if (err != null) {
                    System.out.println("err: " + err);
                    FailureDialog loginFailure = new FailureDialog();
                    loginFailure.setFailureDialog("Invalid. Please fill in all the blanks");
                    loginButton.setEnabled(true);
                    return;
                }

                handleAuth login = new handleAuth(usernameField.getText(), passwordField.getPassword());
                int status = login.loginUser();

                if (status == 200) {
                    SuccessDialog loginSuccess = new SuccessDialog();
                    loginSuccess.setSuccessDialog("Login Success");
                    loginButton.setEnabled(true);
                } else if (status == 4201) {
                    FailureDialog loginFailure = new FailureDialog();
                    loginFailure.setFailureDialog("User doesn't exist. Please signup.");
                    loginButton.setEnabled(true);
                } else if (status == 503) {
                    FailureDialog loginFailure = new FailureDialog();
                    loginFailure.setFailureDialog("Connection failed.");
                    loginButton.setEnabled(true);
                } else {
                    FailureDialog loginFailure = new FailureDialog();
                    loginFailure.setFailureDialog("Login Failed. Invalid Credentials");
                    loginButton.setEnabled(true);
                }
            }
        });

        // Signup label
        signupLabel = new JLabel("Don't have an account?");
        signupLabel.setBounds(50, 220, 150, 25);
        add(signupLabel);

        // Signup button. Link like style.
        signupButton = new JButton("Sign up");
        signupButton.setBounds(180, 220, 80, 25);
        signupButton.setBorderPainted(false);
        signupButton.setContentAreaFilled(false);
        signupButton.setForeground(Color.BLUE); // when hovered.
        signupButton.setFont(new Font("Arial", Font.PLAIN, 12));
        signupButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR)); // making it like a link

        signupButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // hide login form
                Login.this.setVisible(false);

                // open signup form
                Signup signupForm = new Signup();
                signupForm.setVisible(true);
            }
        });

        add(signupButton);
    }
}
