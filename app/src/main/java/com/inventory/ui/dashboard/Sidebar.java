package com.inventory.ui.dashboard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Sidebar extends JPanel {

    private JButton productButton;
    private JButton supplierButton;
    private JButton customerButton;
    private JButton transactionButton;

    public Sidebar() {
        setLayout(new GridLayout(0, 1, 0, 5));
        setPreferredSize(new Dimension(200, 0));

        productButton = new JButton("Product Management");
        supplierButton = new JButton("Supplier Management");
        customerButton = new JButton("Customer Management");
        transactionButton = new JButton("Transactions");

        add(productButton);
        add(supplierButton);
        add(customerButton);
        add(transactionButton);
    }

    public void setProductButtonAction(ActionListener listener) {
        productButton.addActionListener(listener);
    }

    public void setSupplierButtonAction(ActionListener listener) {
        supplierButton.addActionListener(listener);
    }

    public void setCustomerButtonAction(ActionListener listener) {
        customerButton.addActionListener(listener);
    }

    public void setTransactionButton(ActionListener listener) {
        transactionButton.addActionListener(listener);
    }

}
