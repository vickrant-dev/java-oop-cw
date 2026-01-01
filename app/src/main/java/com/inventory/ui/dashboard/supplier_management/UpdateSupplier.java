package com.inventory.ui.dashboard.supplier_management;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.net.URL;
import java.awt.event.ActionEvent;


public class UpdateSupplier extends JFrame {

    private Image image;
    private JLabel supplierid;
    private JLabel supplierName;
    private JLabel email;
    private JLabel phone;
    private JLabel UpdateLabel;

    private JTextField supplieridField;
    private JTextField supplierNameField;
    private JTextField emailField;
    private JTextField phoneField;


    public UpdateSupplier() {

        setTitle("Update Product");
        // Set icon
        URL iconURL = getClass().getResource("/shop.png");
        if (iconURL != null) {
            Image icon = new ImageIcon(iconURL).getImage();
            setIconImage(icon);
        }
        setSize(450,390);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        // Create a panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

        //create two buttons
        JButton Updatebutton = new JButton("Update");
        JButton Cancelbutton = new JButton("Cancel");

        // add action to cancel button

        Cancelbutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        //add two buttons to the panel
        buttonPanel.add(Updatebutton);
        buttonPanel.add(Cancelbutton);

        // add button panel to the frame
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);

        //Set labels and text fields
        setLayout(null);

        UpdateLabel = new JLabel("Update Supplier");
        UpdateLabel.setBounds(170, 5, 200, 40);
        UpdateLabel.setFont(new Font("Times New Roman", Font.BOLD, 23));
        UpdateLabel.setForeground(new Color(0,70,0));
        add(UpdateLabel);

        supplierid = new JLabel("Supplier ID");
        supplierid.setBounds(4, 30, 250, 80);
        supplierid.setForeground(new Color(0,70,0));
        supplieridField = new JTextField();
        supplieridField.setBounds(130, 55, 250, 25);
        add(supplierid);
        add(supplieridField);


        supplierName = new JLabel("Supplier Name");
        supplierName.setBounds(4, 100, 200, 40);
        supplierName.setForeground(new Color(0,70,0));
        supplierNameField = new JTextField();
        supplierNameField.setBounds(130, 107, 250, 25);
        add(supplierName);
        add(supplierNameField);


        email = new JLabel("Email");
        email.setBounds(4, 150, 200, 40);
        email.setForeground(new Color(0,70,0));
        emailField = new JTextField();
        emailField.setBounds(130, 157, 250, 25);
        add(email);
        add(emailField);


        phone = new JLabel("Phone ");
        phone.setBounds(4, 205, 200, 40);
        phone.setForeground(new Color(0,70,0));
        phoneField = new JTextField();
        phoneField.setBounds(130, 207, 250, 25);
        add(phone);
        add(phoneField);




    }


    public UpdateSupplier(String supplierid, String supplierName, String email, String phone){
        this();
        // assign the values to the textField
        supplieridField.setText(supplierid);
        supplierNameField.setText(supplierName);
        emailField.setText(email);
        phoneField.setText(phone);

    }
}
