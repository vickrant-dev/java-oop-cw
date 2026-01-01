package com.inventory.ui.dashboard.supplier_management;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.net.URL;

public class ProductofSupplier extends JFrame {

    JTable table;
    DefaultTableModel model;
    private Image image;

    public ProductofSupplier()
    {

        setTitle("Product of Supplier");
        // Set icon
        URL iconURL = getClass().getResource("/shop.png");
        if (iconURL != null) {
            Image icon = new ImageIcon(iconURL).getImage();
            setIconImage(icon);
        }
        setSize(600,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());



        JLabel title = new JLabel("Product of Supplier");
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setForeground(new Color(0, 70, 0));
        title.setBorder(BorderFactory.createEmptyBorder(10, 0, 12, 0));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        add(title, BorderLayout.NORTH);


        // set column names
        String[] columns = {"Product Name","Category", "Price"};

        Object[][] data = {
                {"bomb","Terrorist", "100000"},
                {"lap","Electronics", "100" },
                {"software","Scamming", "10000000"}

        };

        // create table
        model = new DefaultTableModel(data, columns);
        table = new JTable(model);

        // change the appearance of the table
        table.setRowHeight(25);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        table.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        // adding scroll support to the table
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);


        setVisible(true);

    }

}
