package com.inventory.ui.dashboard.product_management;

import com.inventory.controller.ProductController;
import com.inventory.controller.TransactionController;
import com.inventory.domain.Product;
import com.inventory.domain.Transaction;
import com.inventory.utils.handleValidateFields;
import com.inventory.utils.loadAllProducts;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ManageProducts extends JPanel {

    private JTable productTable; // used to display and edit regular two-dimensional tables of cells
    private DefaultTableModel tableModel; // to store the cell value objects.
    // Shared private variable to store Product objects
    private List<Product> productRows = new ArrayList<>();

    public ManageProducts() {
        // this represents the ManageProducts which is a JPanel as we have inherited.
        this.setLayout(new BorderLayout());

        // table name attached to the border
        this.setBorder(BorderFactory.createTitledBorder("All Products"));

        // initializing table first
        setupTable();
        this.add(new JScrollPane(productTable), BorderLayout.CENTER);

        // sending this (ManageProducts) to right panel so it can refresh the data using getter methods.
        this.add(new AddRightPanel(this), BorderLayout.EAST); // right panel with buttons.

        // initial data load
        refreshTableData();

        // change the appearance of the table
        productTable.setRowHeight(25);
        productTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        productTable.setFont(new Font("Segoe UI", Font.PLAIN, 12));
    }

    private void setupTable() {
        String[] columns = {"ID", "Name", "Category", "Price", "Stock", "Supplier name"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // table is read-only
            }
        };

        productTable = new JTable(tableModel);
        productTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Popup menu
        PopupMenu popup = new PopupMenu();
        JPopupMenu popupMenu = popup.getPopupMenu();
        productTable.setComponentPopupMenu(popupMenu);

        // UPDATE prod
        JMenuItem updateItem = (JMenuItem) popupMenu.getComponent(0);
        updateItem.addActionListener(e -> {
            int row = productTable.getSelectedRow();
            if (row >= 0) {
                // product from the productRows based on row index
                Product selectedProduct = productRows.get(row);
                Frame parentFrame = (Frame) SwingUtilities.getWindowAncestor(this);
                new UpdateProduct(parentFrame, selectedProduct, this);
            }
        });

        // DELETE prod
        JMenuItem deleteItem = (JMenuItem) popupMenu.getComponent(1);
        deleteItem.addActionListener(e -> {
            int row = productTable.getSelectedRow();
            if (row >= 0) {
                int confirm = JOptionPane.showConfirmDialog(this, "Delete this product?", "Confirm", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    Product productToDelete = productRows.get(row);
                    TransactionController prod_transaction = new TransactionController();
                    boolean prod_check_res = prod_transaction.checkTransaction(productToDelete);
                    if (prod_check_res) {
                        // unable to delete as of existing transactions
                        JOptionPane.showMessageDialog(
                                null,
                                "Unable to delete this product. \nIt has existing transactions associated with it.",
                                "Delete failed",
                                JOptionPane.WARNING_MESSAGE
                        );
                    }
                    else {
                        // Check product fields before deleting
                        String errorMsg = new handleValidateFields().validateFields(productToDelete);

                        if (errorMsg != null) {
                            JOptionPane.showMessageDialog(this,
                                    "Please correct the following: \n"
                                            + errorMsg, "Invalid fields", JOptionPane.WARNING_MESSAGE);
                        }
                        else {
                            // proceed to delete product.
                            ProductController del_prod_controller = new ProductController();
                            del_prod_controller.deleteProduct(productToDelete);
                            refreshTableData();
                        }

                    }
                }
            }
        });

        // selecting the row on right click
        productTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent e) { selectRow(e); }
            public void mouseReleased(java.awt.event.MouseEvent e) { selectRow(e); }
            private void selectRow(java.awt.event.MouseEvent e) {
                int row = productTable.rowAtPoint(e.getPoint());
                if (row >= 0) {
                    productTable.setRowSelectionInterval(row, row);
                }
            }
        });
    }

    // GETTER
    public void refreshTableData() {
        tableModel.setRowCount(0);
        productRows = new ProductController().fetchAllProducts();

        for (Product p : productRows) {
            Object[] rowData = {
                    p.getProductId(),
                    p.getProductName(),
                    p.getProductCategory(),
                    p.getProductPrice(),
                    p.getProductStockQuantity(),
                    p.getSupplierName()
            };
            tableModel.addRow(rowData);
        }
    }
}