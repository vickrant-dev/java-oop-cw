package com.inventory.ui.dashboard.supplier_management;

import com.inventory.controller.SupplierController;
import com.inventory.domain.Supplier;
import com.inventory.ui.dashboard.product_management.PopupMenu;
import com.inventory.utils.handleValidateFields;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class SupplierPanel extends JPanel {

    private JTable table;
    private DefaultTableModel model;
    private List<Supplier> supplierRows = new ArrayList<>();

    public SupplierPanel() {
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Supplier Management");
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setForeground(new Color(0, 70, 0));
        title.setBorder(BorderFactory.createEmptyBorder(10, 0, 12, 0));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        add(title, BorderLayout.NORTH);

        setupTable();
        add(new JScrollPane(table), BorderLayout.CENTER);

        setupRightPanel();
        refreshTableData();
    }

    private void setupTable() {
        String[] columns = {"ID", "Name", "Contact Info"};
        model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(model);
        table.setRowHeight(25);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        table.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        PopupMenu popup = new PopupMenu();
        JPopupMenu popupMenu = popup.getPopupMenu();
        table.setComponentPopupMenu(popupMenu);

        JMenuItem updateItem = (JMenuItem) popupMenu.getComponent(0);
        updateItem.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                Supplier selected = supplierRows.get(row);
                Frame parentFrame = (Frame) SwingUtilities.getWindowAncestor(this);
                new UpdateSupplier(parentFrame, selected, this);
            }
        });

        // delete supplier
        JMenuItem deleteItem = (JMenuItem) popupMenu.getComponent(1);
        deleteItem.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                Supplier selectedSupplier = supplierRows.get(row);

                // confirm
                int confirm = JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to delete supplier: " + selectedSupplier.getSupplierName() + "?",
                        "Confirm Deletion",
                        JOptionPane.YES_NO_OPTION
                );

                if (confirm == JOptionPane.YES_OPTION) {
                    // check supplier fields before checking
                    List<String> errMsg = new handleValidateFields().validateFields(selectedSupplier);
                    if (!errMsg.isEmpty()) {
                        JOptionPane.showMessageDialog(this, "Invalid supplier fields," +
                                        "Supplier is missing important fields. " + "Error: " + errMsg,
                                "Invalid Supplier Fields: ", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    // check if supplier has products
                    SupplierController supplierController = new SupplierController();
                    boolean checkSupplierProds = supplierController.checkSupplierProds(selectedSupplier);

                    if (checkSupplierProds) {
                        JOptionPane.showMessageDialog(this,
                                "Unable to delete suppler. " +
                                        "\nSupplier is currently providing products",
                                "Dependency Error",
                                JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    else {
                        supplierController.deleteSupplier(selectedSupplier);
                        refreshTableData();
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select a supplier to delete.");
            }
        });

        Frame parentFrame = (Frame) SwingUtilities.getWindowAncestor(this);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && table.getSelectedRow() != -1) {
                    int row = table.getSelectedRow();
                        new ProductofSupplier(parentFrame, supplierRows.get(row));
                }
            }
            @Override
            public void mousePressed(MouseEvent e) { selectRow(e); }
            @Override
            public void mouseReleased(MouseEvent e) { selectRow(e); }
            private void selectRow(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                if (row >= 0) table.setRowSelectionInterval(row, row);
            }
        });
    }

    private void setupRightPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        JButton addButton = new JButton("Add new Supplier");
        JButton refreshButton = new JButton("Refresh");

        Color googleBlue = Color.decode("#4285f4");
        addButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        refreshButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        addButton.setBackground(googleBlue);
        addButton.setForeground(Color.WHITE);
        addButton.setOpaque(true);
        addButton.setBorderPainted(false);

        refreshButton.setBackground(googleBlue);
        refreshButton.setForeground(Color.WHITE);
        refreshButton.setOpaque(true);
        refreshButton.setBorderPainted(false);


        buttonPanel.add(Box.createVerticalStrut(20));
        buttonPanel.add(addButton);
        buttonPanel.add(Box.createVerticalStrut(20));
        buttonPanel.add(refreshButton);

        add(buttonPanel, BorderLayout.EAST);

        Frame parentFrame = (Frame) SwingUtilities.getWindowAncestor(this);
        addButton.addActionListener(e -> new AddNewSupplier(parentFrame, this));
        refreshButton.addActionListener(e -> refreshTableData());
    }

    public void refreshTableData() {
        model.setRowCount(0);
        supplierRows = new SupplierController().fetchAllSuppliers();

        for (Supplier s : supplierRows) {
            Object[] rowData = {
                    s.getSupplierId(),
                    s.getSupplierName(),
                    s.getSupplierContactInfo()
            };
            model.addRow(rowData);
        }
    }
}