package com.inventory.ui.dashboard.transactions;

import com.inventory.domain.Transaction;
import com.inventory.domain.TransactionDetails;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.awt.Window;

public class TransactionDetailsForm extends JDialog {

    JTable prdtbl;
    DefaultTableModel prdmdl;

    public TransactionDetailsForm(Window owner, Transaction transaction) {
        super(owner, "Transaction Details", ModalityType.APPLICATION_MODAL);
        setTitle("Transaction Details");
        setSize(600, 420);
        setLocationRelativeTo(owner);
        setModal(true);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JLabel header = new JLabel("Transaction Details", SwingConstants.CENTER);
        header.setFont(new Font("Segoe UI", Font.BOLD, 18));
        header.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(header, BorderLayout.NORTH);

        JPanel upperpanel = new JPanel(new GridLayout(2, 2, 10, 10));
        upperpanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel p1 = new JPanel(new BorderLayout());
        p1.add(new JLabel("Date:"), BorderLayout.NORTH);
        p1.add(new JLabel(transaction.getTransactionDate()), BorderLayout.CENTER);

        JPanel p2 = new JPanel(new BorderLayout());
        p2.add(new JLabel("Total:"), BorderLayout.NORTH);
        p2.add(new JLabel(String.format("Rs.%.2f", transaction.getTotalAmount())), BorderLayout.CENTER);

        JPanel p3 = new JPanel(new BorderLayout());
        p3.add(new JLabel("Payment:"), BorderLayout.NORTH);
        p3.add(new JLabel(transaction.getPaymentMethod()), BorderLayout.CENTER);

        JPanel p4 = new JPanel(new BorderLayout());
        p4.add(new JLabel("Created By:"), BorderLayout.NORTH);
        p4.add(new JLabel(transaction.getCreatedBy()), BorderLayout.CENTER);

        upperpanel.add(p1);
        upperpanel.add(p2);
        upperpanel.add(p3);
        upperpanel.add(p4);

        add(upperpanel, BorderLayout.NORTH);

        String[] columns = {"Product Id", "Quantity", "Price"};

        List<TransactionDetails> details = transaction.getTransactionDetails();
        Object[][] data = new Object[details.size()][3];
        for (int i = 0; i < details.size(); i++) {
            TransactionDetails d = details.get(i);
            data[i][0] = d.getProductId();
            data[i][1] = d.getQuantity();
            data[i][2] = String.format("Rs.%.2f", d.getPrice());
        }

        prdmdl = new DefaultTableModel(data, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        prdtbl = new JTable(prdmdl);
        prdtbl.setRowHeight(28);
        prdtbl.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        prdtbl.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        prdtbl.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scroll = new JScrollPane(prdtbl);
        scroll.setBorder(BorderFactory.createTitledBorder("Products in this Transaction"));
        add(scroll, BorderLayout.CENTER);

        JPanel bottom = new JPanel();
        bottom.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        bottom.setLayout(new BorderLayout());

        JLabel createdAtLabel = new JLabel("Created At: " + transaction.getCreatedAt());
        bottom.add(createdAtLabel, BorderLayout.WEST);

        JButton btn = new JButton("Close");
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setOpaque(true);
        btn.setBackground(new Color(33, 153, 243));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Arial",Font.BOLD,14));
        btn.setPreferredSize(new Dimension(100, 35));
        btn.addActionListener(e -> dispose());

        JPanel btnPanel = new JPanel();
        btnPanel.add(btn);
        bottom.add(btnPanel, BorderLayout.EAST);

        add(bottom, BorderLayout.SOUTH);
    }
}