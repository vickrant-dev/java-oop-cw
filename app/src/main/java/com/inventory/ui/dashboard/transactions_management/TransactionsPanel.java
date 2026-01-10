package com.inventory.ui.dashboard.transactions_management;

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

        Color googleBlue = Color.decode("#4285f4");

        JButton createBtn = new JButton("Create transaction");
        createBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        createBtn.setMaximumSize(new Dimension(160, 36));

        createBtn.setBackground(googleBlue);
        createBtn.setForeground(Color.WHITE);
        createBtn.setOpaque(true);
        createBtn.setBorderPainted(false);

        JButton refreshBtn = new JButton("Refresh");
        refreshBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        refreshBtn.setMaximumSize(new Dimension(160, 36));

        refreshBtn.setBackground(googleBlue);
        refreshBtn.setForeground(Color.WHITE);
        refreshBtn.setOpaque(true);
        refreshBtn.setBorderPainted(false);

        rightPanel.add(createBtn);
        rightPanel.add(Box.createRigidArea(new Dimension(0,10)));
        rightPanel.add(refreshBtn);

        add(rightPanel, BorderLayout.EAST);

        // Load initial data
        loadTransactions();

        // setting up the right click menu thing
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem deleteItem = new JMenuItem("Delete");
        popupMenu.add(deleteItem);

        deleteItem.addActionListener(e -> {
            int selectedRow = transtble.getSelectedRow();
            if (selectedRow != -1) {
                Transaction transactionToDelete = transactions.get(selectedRow);

                int confirm = JOptionPane.showConfirmDialog(this,
                        "Are you sure you want to delete this?", "Confirm", JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    transactionController.deleteTransaction(transactionToDelete);
                    loadTransactions(); // refresh the view
                }
            }
        });

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

            // show menu when right clicked
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    showPopup(e);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    showPopup(e);
                }
            }

            private void showPopup(MouseEvent e) {
                int row = transtble.rowAtPoint(e.getPoint());
                if (row >= 0 && row < transtble.getRowCount()) {
                    transtble.setRowSelectionInterval(row, row);
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
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
        transactions = transactionController.getAllTransactions();
        model.setRowCount(0);
        if (transactions == null) return;
        for (Transaction tx : transactions) {
            String date = tx.getTransactionDate();
            String total = String.format("Rs.%.2f", tx.getTotalAmount());
            String payment = tx.getPaymentMethod();
            String createdBy = tx.getCreatedByName();
            String createdAt = tx.getCreatedAt();
            model.addRow(new Object[]{date, total, payment, createdBy, createdAt});
        }
    }
}