package com.inventory.ui.dashboard.transactions;

import com.inventory.controller.TransactionController;
import com.inventory.domain.Transaction;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.Window;

public class TransactionsPanel extends JPanel {

    JTable transtble;
    DefaultTableModel model;
    private final TransactionController transactionController;
    private List<Transaction> transactions = new ArrayList<>();

    public TransactionsPanel() {
        transactionController = new TransactionController();

        setLayout(new BorderLayout());
        // removed explicit white background to follow TODO

        JLabel header = new JLabel("Transactions", SwingConstants.CENTER);
        header.setFont(new Font("Segoe UI", Font.BOLD, 20));
        header.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        add(header, BorderLayout.NORTH);

        String[] clms = {
                "Transaction Date",
                "Total Amount",
                "Payment Method",
                "Created by",
                "Created at"
        };

        model = new DefaultTableModel(clms, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        transtble = new JTable(model);
        transtble.setRowHeight(30);
        transtble.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        transtble.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        transtble.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollpanel = new JScrollPane(transtble);
        add(scrollpanel, BorderLayout.CENTER);

        // Right side panel with actions
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JButton createBtn = new JButton("Create transaction");
        createBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        createBtn.setMaximumSize(new Dimension(160, 36));

        JButton refreshBtn = new JButton("Refresh");
        refreshBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        refreshBtn.setMaximumSize(new Dimension(160, 36));

        rightPanel.add(createBtn);
        rightPanel.add(Box.createRigidArea(new Dimension(0,10)));
        rightPanel.add(refreshBtn);

        add(rightPanel, BorderLayout.EAST);

        // Load initial data
        loadTransactions();

        transtble.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = transtble.getSelectedRow();
                    if (row >= 0 && row < transactions.size()) {
                        Transaction tx = transactions.get(row);
                        // open details dialog relative to ancestor window
                        Window owner = SwingUtilities.getWindowAncestor(TransactionsPanel.this);
                        TransactionDetailsForm dlg = new TransactionDetailsForm(owner, tx);
                        dlg.setVisible(true);
                    }
                }
            }
        });

        refreshBtn.addActionListener(e -> loadTransactions());

        createBtn.addActionListener(e -> {
            Window owner = SwingUtilities.getWindowAncestor(TransactionsPanel.this);
            CreateTransactionForm form = new CreateTransactionForm(owner);
            form.setVisible(true);
            // after form closes, refresh transactions
            loadTransactions();
        });
    }

    private void loadTransactions() {
        // fetch from controller and populate table model
        transactions = transactionController.getAllTransactions();
        // clear model
        model.setRowCount(0);
        if (transactions == null) return;
        for (Transaction tx : transactions) {
            String date = tx.getTransactionDate();
            String total = String.format("Rs.%.2f", tx.getTotalAmount());
            String payment = tx.getPaymentMethod();
            String createdBy = tx.getCreatedBy();
            String createdAt = tx.getCreatedAt();
            model.addRow(new Object[]{date, total, payment, createdBy, createdAt});
        }
    }
}