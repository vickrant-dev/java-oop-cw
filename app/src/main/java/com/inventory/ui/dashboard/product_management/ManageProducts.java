package com.inventory.ui.dashboard.product_management;

import com.inventory.controller.ProductController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class ManageProducts extends JPanel {

    private JTable productTable; // used to display and edit regular two-dimensional tables of cells
    private DefaultTableModel tableModel; // to store the cell value objects.

    public ManageProducts() {
        setLayout(new BorderLayout());

        // table name attached to the border
        setBorder(BorderFactory.createTitledBorder("All Products"));
        add(new createTableScrollPane(), BorderLayout.CENTER);
        add(new createRightPanel(), BorderLayout.EAST);
    }

    // Table
    // JScrollPane -> Provides a scrollable view of a lightweight component
    public class createTableScrollPane extends JScrollPane {
        public createTableScrollPane() {
            String[] columns = {"ID", "Name", "Category", "Price", "Stock", "Supplier name"};

            // Constructs a DefaultTableModel with as many columns as there are elements in columnNames
            // and rowCount of null object values.
            tableModel = new DefaultTableModel(columns, 0) {
                // overriding
                public boolean isCellEditable(int row, int column) {
                    return false; // table is read-only
                }
            };

            productTable = new JTable(tableModel);

            // Sets the table's selection mode to allow only single selections,
            // a single contiguous interval, or multiple intervals
            productTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

            setViewportView(productTable);

            loadAllProducts();
        }
    }

    // Separate panel on the right for adding products button
    public class createRightPanel extends JPanel {
        public createRightPanel() {
            // Creates a layout manager that will lay out components along the given axis
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

            // Creates an empty border that takes up space but which does no drawing,
            // specifying the width of the top,left, bottom, and right sides.
            setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            JButton addProductButton = new JButton("Add New Product");
            addProductButton.setAlignmentX(Component.CENTER_ALIGNMENT);

            addProductButton.addActionListener(e -> addNewProduct());

            add(addProductButton);
        }

        private void addNewProduct() {
            // add new product by calling controller
        }
    }

    private void loadAllProducts() {
        List<Product> all_products = new ProductController().fetchAllProducts();
        for (Product prod: all_products) {
            tableModel.addRow(new Object[] {
                    prod.getProductId(),
                    prod.getProductName(),
                    prod.getProductCategory(),
                    prod.getProductPrice(),
                    prod.getProductStockQuantity(),
                    prod.getProductSupplierId()
            });
        }
    }

    public JTable getProductTable() {
        return productTable;
    }
}
