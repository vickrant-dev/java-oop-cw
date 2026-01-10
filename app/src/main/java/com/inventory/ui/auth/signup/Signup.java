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
    private JCheckBox shwpass;

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

        //(Added a new checkbox to show password)
        char hidepass = passwordField.getEchoChar();
        shwpass = new JCheckBox("Show password");
        shwpass.setBounds(650, 328, 150, 20);

        //(If the checkbox is clicked the password have to be shown)
        shwpass.addActionListener(e ->{
            if(shwpass.isSelected()){
                passwordField.setEchoChar((char) 0);
            }
            else{
                passwordField.setEchoChar(hidepass);
            }
        });

        add(shwpass);
        add(passwordLabel);
        add(passwordField);

        // Signup button Rahul(Change the signup button for hover effect)
        signupButton = new JButton("Signup");

        signupButton.setFocusPainted(false);
        signupButton.setBorderPainted(false);
        signupButton.setOpaque(true);

        Color nmlclr = new Color(33, 153, 243);
        Color aftclr = new Color(30, 136, 229);

        signupButton.setBackground(nmlclr);
        signupButton.setForeground(Color.WHITE);
        signupButton.setFont(new Font("Arial", Font.BOLD,14));
        signupButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
      
        signupButton.setBounds(650, 360, 100, 30);
        add(signupButton);
        
      
        signupButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e){
                signupButton.setBackground(aftclr);
            }

            @Override
            public void mouseExited(MouseEvent e){
                signupButton.setBackground(nmlclr);
            }
        });

        signupButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e){
                signupButton.setBackground(aftclr);
            }

            @Override
            public void mouseExited(MouseEvent e){
                signupButton.setBackground(nmlclr);
            }
        });

        signupButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                signupButton.setEnabled(false);

                handleValidateFields validate_fields = new handleValidateFields();
                String field_err = validate_fields.validateFields(usernameField, emailField, passwordField);

                //adding a new custom popbox for errors
                class Validatebox{
                    public static void vlidateshow(String msg){
                        JDialog validatebox = new JDialog();
                        validatebox.setModal(true);
                        validatebox.setUndecorated(true);
                        validatebox.setAlwaysOnTop(true);

                        JPanel mnpanel = new JPanel(new BorderLayout(10,10));
                        mnpanel.setBackground(new Color(222,222,222));
                        mnpanel.setBorder(BorderFactory.createLineBorder(new Color(0,0,0),2));
                        mnpanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

                        JLabel sign = new JLabel("⚠");
                        sign.setFont(new Font("Segoe UI", Font.BOLD,20));
                        sign.setForeground(new Color(200,225,0));

                        JLabel message = new JLabel(msg);
                        message.setFont(new Font("Segoe UI", Font.BOLD,15));
                        message.setForeground(new Color(0,0,0));

                        JPanel ctnmsg = new JPanel(new FlowLayout(FlowLayout.LEFT,10,0));
                        ctnmsg.setBackground(new Color(222,222,222));
                        ctnmsg.add(sign);
                        ctnmsg.add(message);

                        mnpanel.add(ctnmsg, BorderLayout.CENTER);

                        JButton okbtn = new JButton("Ok");
                        okbtn.setFocusPainted(false);
                        okbtn.setBorderPainted(false);
                        okbtn.setOpaque(true);
                        okbtn.setBackground(new Color(222,222,222));
                        okbtn.setForeground(Color.BLACK);
                        okbtn.setFont(new Font("Arial", Font.BOLD,14));
                        okbtn.setPreferredSize(new Dimension(100,35));


                        Color dftcolor = new Color(245,245,245);
                        Color hvcolor = new Color(171,171,171);

                        okbtn.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseEntered(MouseEvent e) {
                                okbtn.setBackground(hvcolor);
                            }

                            @Override
                            public void mouseExited(MouseEvent e) {
                                okbtn.setBackground(dftcolor);
                            }
                        });
                        okbtn.addActionListener(e -> validatebox.dispose());

                        JPanel buttonPanel = new JPanel();
                        buttonPanel.setBackground(new Color(245,245,245));
                        buttonPanel.add(okbtn);

                        validatebox.add(buttonPanel, BorderLayout.SOUTH);

                        validatebox.add(mnpanel);
                        validatebox.pack();
                        validatebox.setLocationRelativeTo(null);
                        validatebox.setVisible(true);
                    }
                }

                // validate fields (not empty)
                if (field_err != null) {
                    Validatebox.vlidateshow("Invalid. Please fill in all the blanks");
                    signupButton.setEnabled(true);
                    return;
                }

                // validate email
                handleEmailValidation validate_email = new handleEmailValidation();
                String email_err = validate_email.isEmailValid(emailField);

                if (field_err != null){
                    Validatebox.vlidateshow(field_err);
                    signupButton.setEnabled(true);
                    return;
                }
                if (email_err != null) {
                    Validatebox.vlidateshow("Invalid email. Please enter a valid email.");
                    signupButton.setEnabled(true);
                    return;
                }

                handleAuth signup = new handleAuth
                (
                        usernameField.getText(), emailField.getText(), passwordField.getPassword()
                );

                int status = signup.signupUser();

                if(status == 200) {
                    Validatebox.vlidateshow("Signup Success!");
                    signupButton.setEnabled(true);
                } else if (status == 503) {
                    Validatebox.vlidateshow("Connection failed.");
                    signupButton.setEnabled(true);
                } else if (status == 409) {
                    Validatebox.vlidateshow("Username or email already exists.");
                    signupButton.setEnabled(true);
                } else {
                    Validatebox.vlidateshow("Signup Failed. Invalid Details.");
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

        loginButton.setFocusPainted(false);
        Color nmllclr = new Color(33, 153, 243);
        Color afttclr = new Color(30, 136, 229);
        loginButton.setForeground(nmllclr); // when hovered.
        loginButton.setFont(new Font("Arial", Font.PLAIN, 12));
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));// making it like a link

        loginButton.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e){
                loginButton.setForeground(afttclr);
                loginButton.setText("<html><u>Login</u></html>");
            }

            @Override
            public void mouseExited(MouseEvent e){
                loginButton.setForeground(nmlclr);
                loginButton.setText("Login");
            }
        });

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
