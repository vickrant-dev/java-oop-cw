package com.inventory.ui.dashboard.customer_management;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;


public class TransactionForm extends JFrame{
    public  TransactionForm(String username, String email){
        setTitle("Customer Transaction");
        setSize(600,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel header = new JLabel("Transaction History", SwingConstants.CENTER);
        header.setFont(new Font("Segoe UI", Font.BOLD,20));
        header.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        add(header, BorderLayout.NORTH);

        JPanel info = new JPanel(new GridLayout(2,1));
        info.setBorder(BorderFactory.createEmptyBorder(5,10,5,10));

        JLabel userlbl = new JLabel("Username: " +username);
        JLabel emaillbl = new JLabel("Email: " + email);

        info.add(userlbl);
        info.add(emaillbl);
        add(info, BorderLayout.SOUTH);

        String[] colms = {"Transaction ID", "Created at", "Total amount", "Payment method",
                "Created by"};

        Object[][] data = {
                {"abc287", "2025-10-08", "Rs.5000", "Cash", "some cashier guy"},
                {"abd548", "2025-06-12", "Rs.12500", "Card", "some cashier guy"},
                {"abd556", "2025-12-18", "Rs.49000", "Cash", "some cashier guy"}
        };

        JTable tbl = new JTable(new DefaultTableModel(data,colms));
        tbl.setRowHeight(28);

        add(new JScrollPane(tbl), BorderLayout.CENTER);
        setVisible(true);
    }
}
