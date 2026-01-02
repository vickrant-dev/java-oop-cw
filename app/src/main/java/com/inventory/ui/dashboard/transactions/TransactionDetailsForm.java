package com.inventory.ui.dashboard.transactions;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TransactionDetailsForm extends JDialog {

    JTable prdtbl;
    DefaultTableModel prdmdl;

    public TransactionDetailsForm(
            String transactiondate,
            String totalamount,
            String paymentmethod,
            String createdby,
            String createdat
    ) {
        setTitle("Transaction Details");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setModal(true);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JLabel header = new JLabel("Transaction Details", SwingConstants.CENTER);
        header.setFont(new Font("Segoe UI", Font.BOLD, 18));
        header.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(header, BorderLayout.NORTH);

        JPanel upperpanel = new JPanel(new GridLayout(2, 5, 10, 10));
        upperpanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        upperpanel.add(new JLabel("Date: "));
        upperpanel.add(new JLabel(transactiondate));

        upperpanel.add(new JLabel("Total:"));
        upperpanel.add(new JLabel(totalamount));

        upperpanel.add(new JLabel("Payment:"));
        upperpanel.add(new JLabel(paymentmethod));

        upperpanel.add(new JLabel("Created By:"));
        upperpanel.add(new JLabel(createdby));

        upperpanel.add(new JLabel("Created At:"));
        upperpanel.add(new JLabel(createdat));

        add(upperpanel, BorderLayout.NORTH);

        String[] colums = {
                "Product Id",
                "Quantity",
                "Price"
        };

        Object[][] data = {
                {"Ab556", 2, "Rs.5052.00"},
                {"Ac514", 1, "Rs.1200.00"},
                {"Ad985", 8, "Rs.30000.00"}
        };

        prdmdl = new DefaultTableModel(data, colums){
            @Override
            public boolean isCellEditable(int row, int column){
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


        JButton btn = new JButton("Close");
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setOpaque(true);
        btn.setBackground(new Color(33, 153, 243));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Arial",Font.BOLD,14));
        btn.setPreferredSize(new Dimension(100, 35));

        Color defaultcolor = new Color(33,153,243);
        Color aftercolor = new Color(169,169,169);

        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e){
                btn.setBackground(aftercolor);
                btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public  void mouseExited(MouseEvent e){
                btn.setBackground(defaultcolor);
                btn.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });

        btn.addActionListener(e -> dispose());

        JPanel btnPanel = new JPanel();
        btnPanel.add(btn);
        add(btnPanel, BorderLayout.SOUTH);

        setVisible(true);

    }
}







