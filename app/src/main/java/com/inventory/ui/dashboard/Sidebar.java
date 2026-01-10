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
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(45,50,65));
        setPreferredSize(new Dimension(170, getHeight()));
        setBorder(BorderFactory.createEmptyBorder(10,10,10,10));


        productButton = styleButton(new JButton("Product Management"));
        supplierButton = styleButton(new JButton("Supplier Management"));
        customerButton = styleButton(new JButton("Customer Management"));
        transactionButton = styleButton(new JButton("Transactions"));

        add(productButton);
        add(supplierButton);
        add(customerButton);
        add(transactionButton);
    }

    private JButton styleButton(JButton button) {
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(45,50,65));
        button.setFont(new Font("Poppins",Font.BOLD,10));
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE,45));

        //Hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(65,70,85));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(45,50,65));
            }
        });

        return button;
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
