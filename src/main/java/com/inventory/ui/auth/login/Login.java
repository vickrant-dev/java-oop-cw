package com.inventory.ui.auth.login;

import java.awt.event.*;
import javax.swing.*;

import com.inventory.utils.handleLogin;

// Simpler to extend than including it inside.
// Allows us to control the visibility outside easily.
public class Login extends JFrame {

    // 
    private String username = "";
    private char[] password = {};

    // Initializing fields, labels and button
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel usernameLabel;
    
    public Login () {

        setTitle("Login");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // centering the app dialog relative to the screen
        setLayout(null);

        usernameLabel = new JLabel("Username");
        usernameField = new JTextField();
        usernameLabel.setBounds(30, 0, 100, 50);
        usernameField.setBounds(30, 40, 150, 25);
        add(usernameField);
        add(usernameLabel);

        JLabel passwwordLabel = new JLabel("Password");
        passwordField = new JPasswordField();
        passwwordLabel.setBounds(30, 70, 150, 25);
        passwordField.setBounds(30, 100, 150, 25);
        add(passwordField);
        add(passwwordLabel);

        loginButton = new JButton("Login");
        loginButton.setBounds(30, 150, 80, 25);
        add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loginButton.setEnabled(false);
                
                // calling login logic
                handleLogin login = new handleLogin(usernameField.getText(), passwordField.getPassword());

                if(login.loginUser()) {
                    SuccessDialog loginSuccess = new SuccessDialog();
                    loginSuccess.setSuccessDialog("Login Success");
                    loginButton.setEnabled(true);
                }
                else {
                    FailureDialog loginFailure = new FailureDialog();
                    loginFailure.setFailureDialog("Login Failed. Invalid Credentials");
                    loginButton.setEnabled(true);
                }
            }
        });
        
    }
}
