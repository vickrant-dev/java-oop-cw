package com.inventory.ui.dashboard.product_management;

import com.inventory.utils.loadAllProducts;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ManageProducts extends JPanel {

    private JTable productTable; // used to display and edit regular two-dimensional tables of cells
    private DefaultTableModel tableModel; // to store the cell value objects.

    public ManageProducts() {
        // this represents the ManageProducts which is a JPanel as we have inherited.
        this.setLayout(new BorderLayout());

        // table name attached to the border
        this.setBorder(BorderFactory.createTitledBorder("All Products"));
        this.add(new createTableScrollPane(), BorderLayout.CENTER);

        // sending this (ManageProducts) to right panel so it can refresh the data using getter methods.
        this.add(new AddRightPanel(this), BorderLayout.EAST); // right panel with buttons.

        // change the appearance of the table
        productTable.setRowHeight(25);
        productTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        productTable.setFont(new Font("Segoe UI", Font.PLAIN, 12));


    }

    // Table
    // JScrollPane -> Provides a scrollable view of a lightweight component
    private class createTableScrollPane extends JScrollPane {
        private int hoveredRow;
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

            // the JScrollPane links the table's scroll bars to the movement of the data and
            // puts the table in a viewport under JScrollPane
            // JTable -> JScrollPane -> Viewport -> setViewportView
            setViewportView(productTable);

            List<Object[]> rows = new loadAllProducts().getAllProductRows();

            for (Object[] row: rows) {
                tableModel.addRow(row);
            }


            // add popup menu to the table
            PopupMenu popup = new PopupMenu();
            productTable.setComponentPopupMenu(popup.getPopupMenu());


        }
    }

    // getter
    public void refreshTableData() {
        tableModel.setRowCount(0);

        List<Object[]> rows = new loadAllProducts().getAllProductRows();

        for (Object[] row: rows) {
            tableModel.addRow(row);
        }
    }
}
