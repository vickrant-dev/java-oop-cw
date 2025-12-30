package com.inventory.ui.auth.signup;

import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import javax.swing.*;

import com.inventory.ui.auth.login.*;
import com.inventory.ui.auth.login.Panel;
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
    private Image image;
    private JLabel SignupLabel;

    public Signup() {

        setTitle("Signup");
        // set icon
        URL iconURL = getClass().getResource("/shop.png");
        if (iconURL != null) {
            Image icon = new ImageIcon(iconURL).getImage();
            setIconImage(icon);
        }
        setSize(1000, 540);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // add image to signup
        Panel ImagePanel = new Panel();
        ImagePanel.setBounds(0, 0, 500,500);
        add(ImagePanel);

        //signup label
        SignupLabel = new JLabel("Signup");
        SignupLabel.setBounds(700, 40, 200, 40);
        SignupLabel.setFont(new Font("Times New Roman", Font.BOLD, 27));
        SignupLabel.setForeground(new Color(0, 70, 0));
        add(SignupLabel);

        // Username
        usernameLabel = new JLabel("Username");
        usernameField = new JTextField();
        usernameLabel.setBounds(650, 130, 100, 20);
        usernameField.setBounds(650, 160, 250, 25);
        add(usernameLabel);
        add(usernameField);

        // Email
        emailLabel = new JLabel("Email");
        emailField = new JTextField();
        emailLabel.setBounds(650, 200, 100, 25);
        emailField.setBounds(650, 230, 250, 25);
        add(emailLabel);
        add(emailField);

        // Password
        passwordLabel = new JLabel("Password");
        passwordField = new JPasswordField();
        passwordLabel.setBounds(650, 270, 100, 25);
        passwordField.setBounds(650, 300, 250, 25);
        add(passwordLabel);
        add(passwordField);

        // Signup button
        signupButton = new JButton("Signup");
        signupButton.setBounds(650, 350, 100, 30);
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
        loginLabel.setBounds(650, 400, 150, 25);
        add(loginLabel);

        // Login button
        loginButton = new JButton("Login");
        loginButton.setBounds(680, 400, 250, 25);
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
