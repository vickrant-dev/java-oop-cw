package com.inventory.ui.dashboard.customer_management;

import com.inventory.controller.CustomerController;
import com.inventory.domain.Customer;
import com.inventory.domain.Transaction;
import com.inventory.ui.dashboard.transactions_management.TransactionDetailsForm;
import com.inventory.utils.handleValidateFields;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class TransactionForm extends JDialog{
    private JTable tbl;
    private List<Transaction> cusTransactions;

    public  TransactionForm(Frame owner, Customer customer){
        super(owner, "Customer Transaction", true);
        setTitle("Customer Transaction");
        setSize(600,400);
        setLocationRelativeTo(owner);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
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

        List<String> errMsg = new handleValidateFields().validateFields(customer);
        if (!errMsg.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No transactions found. " +
                            "Error: " + errMsg,
                    "Transactions not found for customer: " + customer.getCustomerName(),
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        cusTransactions = new CustomerController().fetchCustomerTransactions(customer);

        Object[][] data = new Object[cusTransactions.size()][];

        for (int i = 0; i < cusTransactions.size(); i++) {
            Transaction cus_transaction = cusTransactions.get(i);
            data[i] =  new Object[] { cus_transaction.getTransactionId(),
                    cus_transaction.getCreatedAt(), cus_transaction.getTotalAmount(),
                    cus_transaction.getPaymentMethod(), cus_transaction.getCreatedByName()
            };
        }

        DefaultTableModel model = new DefaultTableModel(data, colms) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tbl = new JTable(model);
        tbl.setRowHeight(28);

        // double click on a transaction row -> open TransactionDetailsForm from transactions package
        tbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && SwingUtilities.isLeftMouseButton(e)) {
                    int row = tbl.getSelectedRow();
                    if (row >= 0 && row < cusTransactions.size()) {
                        Transaction tx = cusTransactions.get(row);
                        Window ownerWindow = SwingUtilities.getWindowAncestor(TransactionForm.this);
                        TransactionDetailsForm details = new TransactionDetailsForm(ownerWindow, tx);
                        details.setVisible(true);
                    }
                }
            }
        });

        add(new JScrollPane(tbl), BorderLayout.CENTER);
        setVisible(true);
    }
}