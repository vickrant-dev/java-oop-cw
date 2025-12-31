package com.inventory.ui.dashboard.product_management;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.net.URL;
import java.awt.event.ActionEvent;




public class UpdateProduct  extends JFrame {

    private Image image;
    private JLabel Productid;
    private JLabel ProductName;
    private JLabel Category;
    private JLabel Price;
    private JLabel UpdateLabel;

    private JTextField Productidfield;
    private JTextField ProductNamefield;
    private JTextField Categoryfield;
    private JTextField Pricefield;

    public UpdateProduct() {
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

        UpdateLabel = new JLabel("Update Product");
        UpdateLabel.setBounds(170, 5, 200, 40);
        UpdateLabel.setFont(new Font("Times New Roman", Font.BOLD, 23));
        UpdateLabel.setForeground(new Color(0,70,0));
        add(UpdateLabel);

        Productid = new JLabel("Product ID");
        Productid.setBounds(4, 30, 250, 80);
        Productid.setForeground(new Color(0,70,0));
        Productidfield = new JTextField();
        Productidfield.setBounds(130, 55, 250, 25);
        add(Productid);
        add(Productidfield);


        ProductName = new JLabel("Product Name");
        ProductName.setBounds(4, 100, 200, 40);
        ProductName.setForeground(new Color(0,70,0));
        ProductNamefield = new JTextField();
        ProductNamefield.setBounds(130, 107, 250, 25);
        add(ProductName);
        add(ProductNamefield);


        Category = new JLabel("Category");
        Category.setBounds(4, 150, 200, 40);
        Category.setForeground(new Color(0,70,0));
        Categoryfield = new JTextField();
        Categoryfield.setBounds(130, 157, 250, 25);
        add(Category);
        add(Categoryfield);


        Price = new JLabel("Price");
        Price.setBounds(4, 205, 200, 40);
        Price.setForeground(new Color(0,70,0));
        Pricefield = new JTextField();
        Pricefield.setBounds(130, 207, 250, 25);
        add(Price);
        add(Pricefield);



    }

    public UpdateProduct(String id, String Name, String Category, String Price){

        this();
        Productidfield.setText(id);
        ProductNamefield.setText(Name);
        Categoryfield.setText(Category);
        Pricefield.setText(Price);

    }


}
