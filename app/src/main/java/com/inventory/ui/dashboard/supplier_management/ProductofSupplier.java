package com.inventory.ui.dashboard.supplier_management;

import com.inventory.controller.SupplierController;
import com.inventory.domain.Product;
import com.inventory.domain.Supplier;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.net.URL;
import java.util.List;

public class ProductofSupplier extends JFrame {

    JTable table;
    DefaultTableModel model;
    private Image image;
    private Supplier selectedSupplier;
    private SupplierController supplierController;

    public ProductofSupplier(Supplier supplier)
    {

        this.supplierController = new SupplierController();
        this.selectedSupplier = supplier;
        setTitle("Product(s) of Supplier");
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



        JLabel title = new JLabel("Product(s) of Supplier");
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setForeground(new Color(0, 70, 0));
        title.setBorder(BorderFactory.createEmptyBorder(10, 0, 12, 0));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        add(title, BorderLayout.NORTH);


        // set column names
        String[] columns = {"Product Name","Category", "Price", "Stock quantity"};

        // create table
        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);

        List<Product> supplierProducts = supplierController.getSupplierProducts(selectedSupplier);

        for (Product supplier_prod : supplierProducts) {
            Object[] rowData = {
                    supplier_prod.getProductName(),
                    supplier_prod.getProductCategory(),
                    supplier_prod.getProductPrice(),
                    supplier_prod.getProductStockQuantity()
            };
            model.addRow(rowData);
        }

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
