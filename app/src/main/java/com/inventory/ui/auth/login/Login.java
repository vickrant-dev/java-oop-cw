package com.inventory.ui.auth.login;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.AbstractDocument;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.inventory.ui.auth.signup.Signup;
import com.inventory.ui.dashboard.Dashboard;
import com.inventory.utils.*;
import java.net.URL;


public class Login extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JCheckBox passshowcheckbox;
    private JButton signupButton;
    private JLabel signupLabel;
    private Panel imagePanel;
    private Image image;
    private JLabel LoginLabel;

    public Login() {

        setTitle("Login");
        // Set icon
        URL iconURL = getClass().getResource("/shop.png");
        if (iconURL != null) {
            Image icon = new ImageIcon(iconURL).getImage();
            setIconImage(icon);
        }
        setSize(1000, 540);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // center the window
        setLayout(null);

        // add image
        Panel ImagePanel = new Panel();
        ImagePanel.setBounds(0, 0, 500,500);
        add(ImagePanel);

        // Login Label
        LoginLabel = new JLabel("Login");
        LoginLabel.setBounds(700, 40, 200, 40);
        LoginLabel.setFont(new Font("Times New Roman", Font.BOLD, 27));
        LoginLabel.setForeground(new Color(0,70,0));
        add(LoginLabel);

        // Username
        usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(650, 130, 100, 25);
        usernameField = new JTextField();
        usernameField.setBounds(650, 160, 250, 25);
        add(usernameLabel);
        add(usernameField);

        // Password
        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(650, 200, 100, 25);
        passwordField = new JPasswordField();
        passwordField.setBounds(650, 230, 250, 25);

        //Rahul(CheckBox if the user wants to see the password)
        char hidepass = passwordField.getEchoChar();
        passshowcheckbox = new JCheckBox("Show Password");
        passshowcheckbox.setBounds(650,260,150,20);

        passshowcheckbox.addActionListener(e ->{
            if(passshowcheckbox.isSelected()){
                passwordField.setEchoChar((char) 0);
            }
            else{
                passwordField.setEchoChar(hidepass);
            }
        });

        add(passwordLabel);
        add(passwordField);
        add(passshowcheckbox);

        // Login button
        loginButton = new JButton("Login");
        loginButton.setBounds(650, 320, 200, 30); //change the width 100 to 200 and the height 30 to 40
        loginButton.setForeground(new Color(0,70,0));


        //Rahul (Login button hover)
        loginButton.setFocusPainted(false);
        loginButton.setBorderPainted(false);
        loginButton.setOpaque(true);
        Color defaultcolor = new Color(211,211,211);
        Color aftercolor = new Color(169,169,169);

        loginButton.setBackground(defaultcolor);
        loginButton.setForeground(Color.BLACK);
        loginButton.setFont(new Font("Arial",Font.BOLD,14));
        add(loginButton);

        //Rahul(When the mouse touch the login button it has a hover effect)
        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e){
                loginButton.setBackground(aftercolor);
                loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public  void mouseExited(MouseEvent e){
                loginButton.setBackground(defaultcolor);
                loginButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });

        //Rahul(Added a new Error box Styles)
        class Errorbox {
            public static void errorshw(String message){

                //Dialog box
                JDialog Dialogbox = new JDialog();
                Dialogbox.setUndecorated(true);
                Dialogbox.setModal(true);
                Dialogbox.setAlwaysOnTop(true);

                //Dialog box
                JPanel ErrorPanel = new JPanel(new BorderLayout(10, 10));
                ErrorPanel.setBackground(new Color(245,245,245));
                ErrorPanel.setBorder(BorderFactory.createLineBorder(new Color(245,245,245),0));
                ErrorPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

                //Warning Sign
                JLabel warnsign = new JLabel("⚠");
                warnsign.setFont(new Font("Arial",Font.BOLD,24));
                warnsign.setForeground(new Color(200,0,0));

                //Error Label (Words)
                JLabel ErrorLabel = new JLabel(message);
                //ErrorLabel.setText(message);
                ErrorLabel.setForeground(new Color(200,0,0));
                ErrorLabel.setFont(new Font("Arial", Font.BOLD,15));
                //ErrorLabel.setHorizontalAlignment(SwingConstants.CENTER);

                //Message + Warning sign
                JPanel content = new JPanel(new FlowLayout(FlowLayout.LEFT,10,0));
                content.setBackground(new Color(245,245,245));
                content.add(warnsign);
                content.add(ErrorLabel);

                ErrorPanel.add(content, BorderLayout.CENTER);
                //JOptionPane.showMessageDialog(null,ErrorPanel,"Message", JOptionPane.PLAIN_MESSAGE);

                //Custom "OK" Button to the error box
                JButton okbtn = new JButton("Ok");
                okbtn.setFocusPainted(false);
                okbtn.setBorderPainted(false);
                okbtn.setOpaque(true);
                okbtn.setBackground(new Color(211,211,211));
                okbtn.setForeground(Color.BLACK);
                okbtn.setFont(new Font("Arial", Font.BOLD,14));
                okbtn.setPreferredSize(new Dimension(100,35));

                //hover to the OK button
                Color dftcolor = new Color(211,211,211);
                Color hvcolor = new Color(169,169,169);
                okbtn.addMouseListener(new MouseAdapter() {
                    @Override
                    public  void mouseEntered(MouseEvent e){
                        okbtn.setBackground(hvcolor);
                        okbtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    }
                    @Override
                    public void mouseExited(MouseEvent e) {
                        okbtn.setBackground(dftcolor);
                        okbtn.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                    }
                });

                okbtn.addActionListener(e -> Dialogbox.dispose());

                JPanel btnpanel = new JPanel();
                btnpanel.setBackground(new Color(245,245,245));
                btnpanel.add(okbtn);

                ErrorPanel.add(btnpanel, BorderLayout.SOUTH);

                Dialogbox.add(ErrorPanel);
                Dialogbox.pack();
                Dialogbox.setLocationRelativeTo(null);
                Dialogbox.setVisible(true);
            }
        }

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loginButton.setEnabled(false);

                handleValidateFields validate_fields = new handleValidateFields();
                String err = validate_fields.validateFields(usernameField, passwordField);

                //Rahul(I have change the names in the my class becs of the new error box)
                if (err != null) {
                    Errorbox.errorshw("Invalid. Please fill in all the blanks");
                    loginButton.setEnabled(true);
                    return;
                }

                handleAuth login = new handleAuth(usernameField.getText(), passwordField.getPassword());
                int status = login.loginUser();

                if (status == 200) {
                    SuccessDialog loginSuccess = new SuccessDialog();
                    loginSuccess.setSuccessDialog("Login Success");
                    new Dashboard().setVisible(true);
                    Login.this.setVisible(false);
                    loginButton.setEnabled(true);
                } else if (status == 4201) {
                    Errorbox.errorshw("User doesn't exist. Please signup.");
                    loginButton.setEnabled(true);
                } else if (status == 503) {
                    Errorbox.errorshw("Connection failed.");
                    loginButton.setEnabled(true);
                } else {
                    Errorbox.errorshw("Login Failed. Invalid Credentials");
                    loginButton.setEnabled(true);
                }
            }
        });

        // Signup label
        signupLabel = new JLabel("Don't have an account?");
        signupLabel.setBounds(650, 360, 150, 25);
        add(signupLabel);

        // Signup button. Link like style.
        signupButton = new JButton("Sign up");
        signupButton.setBounds(770, 360, 80, 25);
        signupButton.setBorderPainted(false);
        signupButton.setContentAreaFilled(false);
        signupButton.setFocusPainted(false);
        Color nmlclr = new Color(33, 153, 243);
        Color aftclr = new Color(30, 136, 229);
        signupButton.setForeground(nmlclr); // when hovered.
        signupButton.setFont(new Font("Arial", Font.PLAIN, 12));
        signupButton.setCursor(new Cursor(Cursor.HAND_CURSOR));// making it like a link

        signupButton.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e){
                signupButton.setForeground(aftclr);
                signupButton.setText("<html><u>Sign up</u></html>");
            }

            @Override
            public void mouseExited(MouseEvent e){
                signupButton.setForeground(nmlclr);
                signupButton.setText("Sign up");
            }
        });

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
