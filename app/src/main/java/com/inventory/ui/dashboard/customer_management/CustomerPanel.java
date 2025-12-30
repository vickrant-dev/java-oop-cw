package com.inventory.ui.dashboard.customer_management;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;
public class CustomerPanel extends JPanel {

    JTable custable;
    DefaultTableModel model;

    public CustomerPanel() {


        setLayout(new BorderLayout());

        // add(new JLabel("Customer Management", SwingConstants.CENTER), BorderLayout.CENTER);
        // setBackground(Color.WHITE);

        JLabel header = new JLabel("Customer Management", SwingConstants.CENTER);
        header.setFont(new Font("Segoe UI", Font.BOLD, 20));
        header.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        add(header, BorderLayout.NORTH);

        String[] colums = {"Username", "Email", "Password"};

        Object[][] data = {
                {"Vickrant","viki@gmail.com","********"},
                {"Gihan", "gihan@gmail.com", "*********"},
                {"Juman", "bmb@gmail.com", "********"}
        };

        model = new DefaultTableModel(data, colums){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        custable = new JTable(model);
        custable.setRowHeight(30);
        custable.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        custable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));

        JScrollPane scroll = new JScrollPane(custable);
        add(scroll, BorderLayout.CENTER);

        custable.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e){
                if(e.getClickCount() == 2){
                    int row = custable.getSelectedRow();

                    String username = model.getValueAt(row, 0).toString();
                    String email = model.getValueAt(row, 1).toString();

                    new TransactionForm(username , email);
                }
            }
        });
    }
}
