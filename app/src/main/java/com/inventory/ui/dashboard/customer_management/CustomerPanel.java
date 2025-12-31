package com.inventory.ui.dashboard.customer_management;

import com.inventory.controller.CustomerController;
import com.inventory.domain.Customer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.table.DefaultTableModel;
public class CustomerPanel extends JPanel {

    JTable custable;
    DefaultTableModel model;

    public CustomerPanel() {

        setLayout(new BorderLayout());

        JLabel header = new JLabel("Customer Management", SwingConstants.CENTER);
        header.setFont(new Font("Segoe UI", Font.BOLD, 20));
        header.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        add(header, BorderLayout.NORTH);

        String[] colums = {"Username", "Contact info"};

        List<Customer> all_customers = new CustomerController().getAllCustomers();

        Object[][] data = new Object[all_customers.size()][];

        for (int i = 0; i < all_customers.size(); i++) {
            Customer cus = all_customers.get(i);
            data[i] = new Object[] { cus.getCustomerName(), cus.getCustomerContactInfo() };
        }

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

                    new TransactionForm(all_customers.get(row));
                }
            }
        });
    }
}
