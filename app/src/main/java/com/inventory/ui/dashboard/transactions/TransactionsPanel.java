package com.inventory.ui.dashboard.transactions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.table.DefaultTableModel;


public class TransactionsPanel extends JPanel {

    JTable transtble;
    DefaultTableModel model;

    public TransactionsPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        //add(new JLabel("Transactions", SwingConstants.CENTER), BorderLayout.CENTER);

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

        Object[][] data = {
                {"2025-01-20", "Rs.250.00", "Cash", "Vikrant", "2025-02-20 14:32"},
                {"2025-02-18", "Rs.120.00", "Cash", "Gihan", "2025-02-18 11:05"},
                {"2025-02-15", "Rs.75.00", "Card", "Junjju", "2025-02-15 09:40"},
                {"2025-02-10", "Rs.60.00", "Cash", "Rahul", "2025-02-10 16:20"},
                {"2025-02-01", "Rs.90.00", "Card", "Vikirant", "2025-02-01 10:10"}
        };

        model = new DefaultTableModel(data, clms){

            @Override
            public boolean isCellEditable(int row, int column){
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

        transtble.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                if(e.getClickCount() == 2){
                    int row = transtble.getSelectedRow();

                    String transacationdate = model.getValueAt(row, 0).toString();
                    String totalamount = model.getValueAt(row, 1).toString();
                    String paymentmethod = model.getValueAt(row, 2).toString();
                    String createdby = model.getValueAt(row, 3).toString();
                    String createdat = model.getValueAt(row, 4).toString();

                    new TransactionDetailsForm(
                            transacationdate,
                            totalamount,
                            paymentmethod,
                            createdby,
                            createdat
                    );
                }
            }
        });

    }
}
