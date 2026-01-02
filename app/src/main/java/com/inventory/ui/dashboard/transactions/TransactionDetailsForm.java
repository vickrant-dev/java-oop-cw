package com.inventory.ui.dashboard.transactions;

import com.inventory.domain.Transaction;
import com.inventory.domain.TransactionDetails;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TransactionDetailsForm extends JDialog {

    private JTable prdtbl;
    private DefaultTableModel prdmdl;

    public TransactionDetailsForm(Window owner, Transaction transaction) {
        super(owner, "Transaction Details", ModalityType.APPLICATION_MODAL);

        setTitle("Transaction Details");
        setSize(600, 450);
        setLocationRelativeTo(owner);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // header
        JLabel header = new JLabel("Transaction Details", SwingConstants.CENTER);
        header.setFont(new Font("Segoe UI", Font.BOLD, 18));
        header.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));
        add(header, BorderLayout.NORTH);

        // transaction info
        JPanel upperpanel = new JPanel(new GridLayout(2, 2, 15, 15));
        upperpanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

        upperpanel.add(createInfoSection("Date:", transaction.getTransactionDate()));
        upperpanel.add(createInfoSection("Total:", String.format("Rs.%.2f", transaction.getTotalAmount())));
        upperpanel.add(createInfoSection("Payment:", transaction.getPaymentMethod()));
        upperpanel.add(createInfoSection("Created By:", transaction.getCreatedByName()));

        JPanel topWrapper = new JPanel(new BorderLayout());
        topWrapper.add(header, BorderLayout.NORTH);
        topWrapper.add(upperpanel, BorderLayout.CENTER);
        add(topWrapper, BorderLayout.NORTH);

        // table
        String[] columns = {"Product name", "Quantity", "Price"};
        List<TransactionDetails> details = transaction.getTransactionDetails();
        Object[][] data = new Object[details.size()][3];

        for (int i = 0; i < details.size(); i++) {
            TransactionDetails d = details.get(i);
            data[i][0] = d.getProductName();
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
        prdtbl.setRowHeight(30);
        prdtbl.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        prdtbl.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        prdtbl.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scroll = new JScrollPane(prdtbl);
        scroll.setBorder(BorderFactory.createTitledBorder("Products in this Transaction"));
        add(scroll, BorderLayout.CENTER);

        // bottom panel
        JPanel bottom = new JPanel(new BorderLayout());
        bottom.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        JButton btnClose = new JButton("Close");
        btnClose.setFocusPainted(false);
        btnClose.setBackground(new Color(33, 153, 243));
        btnClose.setForeground(Color.WHITE);
        btnClose.setPreferredSize(new Dimension(100, 35));
        btnClose.addActionListener(e -> dispose());

        bottom.add(btnClose, BorderLayout.EAST);
        add(bottom, BorderLayout.SOUTH);
    }

    private JPanel createInfoSection(String title, String value) {
        JPanel p = new JPanel(new BorderLayout());
        JLabel lTitle = new JLabel(title);
        lTitle.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lTitle.setForeground(Color.GRAY);

        JLabel lValue = new JLabel(value);
        lValue.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        p.add(lTitle, BorderLayout.NORTH);
        p.add(lValue, BorderLayout.CENTER);
        return p;
    }
}