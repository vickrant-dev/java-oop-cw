package com.inventory.ui.dashboard.customer_management;

import com.inventory.controller.CustomerController;
import com.inventory.domain.Customer;
import com.inventory.domain.Transaction;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;


public class TransactionForm extends JFrame{
    public  TransactionForm(Customer customer){
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

        JLabel userlbl = new JLabel("Username: " + customer.getCustomerName());
        JLabel contactlbl = new JLabel("Contact info: " + customer.getCustomerContactInfo());

        info.add(userlbl);
        info.add(contactlbl);
        add(info, BorderLayout.SOUTH);

        String[] colms = {"Transaction ID", "Created at", "Total amount", "Payment method",
                "Created by"};

        List<Transaction> cus_transactions = new CustomerController().fetchCustomerTransactions(customer);

        Object[][] data = new Object[cus_transactions.size()][];

        for (int i = 0; i < cus_transactions.size(); i++) {
            Transaction cus_transaction = cus_transactions.get(i);
            data[i] =  new Object[] { cus_transaction.getTransactionId(),
                    cus_transaction.getCreatedAt(), cus_transaction.getTotalAmount(),
                    cus_transaction.getPaymentMethod(), cus_transaction.getCreatedBy()
            };
        }

        DefaultTableModel model = new DefaultTableModel(data, colms) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable tbl = new JTable(model);
        tbl.setRowHeight(28);

        add(new JScrollPane(tbl), BorderLayout.CENTER);
        setVisible(true);
    }
}
