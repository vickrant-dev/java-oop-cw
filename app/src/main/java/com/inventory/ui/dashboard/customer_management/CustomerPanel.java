package com.inventory.ui.dashboard.customer_management;

import com.inventory.controller.CustomerController;
import com.inventory.domain.Customer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class CustomerPanel extends JPanel {

    private JTable custable;
    private DefaultTableModel model;
    private List<Customer> allCustomers;
    private final CustomerController customerController = new CustomerController();

    public CustomerPanel() {
        setLayout(new BorderLayout());

        // load ui components
        initLayout();

        // fetch initial data
        loadCustomers();
    }

    private void initLayout() {
        JLabel header = new JLabel("Customer Management", SwingConstants.CENTER);
        header.setFont(new Font("Segoe UI", Font.BOLD, 20));
        header.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(header, BorderLayout.NORTH);

        String[] columns = {"Username", "Contact info"};
        model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        custable = new JTable(model);
        custable.setRowHeight(30);
        custable.getTableHeader().setReorderingAllowed(false);

        add(new JScrollPane(custable), BorderLayout.CENTER);

        // right side panel
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton addBtn = new JButton("Add Customer");
        JButton refreshBtn = new JButton("Refresh");

        // btns
        Dimension d = new Dimension(160, 38);
        for (JButton b : new JButton[]{addBtn, refreshBtn}) {
            b.setMaximumSize(d);
            b.setAlignmentX(CENTER_ALIGNMENT);
        }

        rightPanel.add(addBtn);
        rightPanel.add(Box.createVerticalStrut(10));
        rightPanel.add(refreshBtn);
        add(rightPanel, BorderLayout.EAST);

        // context menu for the table
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem updateItem = new JMenuItem("Update");
        JMenuItem deleteItem = new JMenuItem("Delete");
        popupMenu.add(updateItem);
        popupMenu.add(deleteItem);

        // table
        custable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e) && e.getClickCount() == 2) {
                    int row = custable.getSelectedRow();
                    if (row != -1) {
                        Window owner = SwingUtilities.getWindowAncestor(CustomerPanel.this);
                        new TransactionForm((Frame) owner, allCustomers.get(row));
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) { if (e.isPopupTrigger()) showPopup(e); }
            @Override
            public void mousePressed(MouseEvent e) { if (e.isPopupTrigger()) showPopup(e); }

            private void showPopup(MouseEvent e) {
                int row = custable.rowAtPoint(e.getPoint());
                if (row >= 0) {
                    custable.setRowSelectionInterval(row, row);
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });

        // update customer
        updateItem.addActionListener(e -> {
            int row = custable.getSelectedRow();
            if (row != -1) {
                Window win = SwingUtilities.getWindowAncestor(this);
                new UpdateCustomerForm(win, allCustomers.get(row)).setVisible(true);
                loadCustomers();
            }
        });

        // delete customer
        deleteItem.addActionListener(e -> {
            int row = custable.getSelectedRow();
            if (row != -1) {
                int confirm = JOptionPane.showConfirmDialog(this, "Delete selected customer?",
                        "Confirm", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    Customer selectedCus = allCustomers.get(row);

                    // check if any transactions for the customer exist
                    boolean transaction_exists = customerController.checkCusTransactions(selectedCus);

                    if (transaction_exists) {
                        JOptionPane.showMessageDialog(this,
                                "Can't delete customer. Customer is associated with existing transactions.",
                                "Failed to delete customer",
                                JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    String res = customerController.deleteCustomer(selectedCus);
                    if (res.equals("200")) {
                        loadCustomers();
                    }
                    else {
                        JOptionPane.showMessageDialog(this,
                                "Failed to delete customer",
                                "Failed to delete customer",
                                JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });

        // create customer
        addBtn.addActionListener(e -> {
            Window owner = SwingUtilities.getWindowAncestor(this);
            new CreateCustomerForm(owner).setVisible(true); // Don't forget setVisible!
            loadCustomers();
        });

        refreshBtn.addActionListener(e -> loadCustomers());
    }

    private void loadCustomers() {
        allCustomers = customerController.getAllCustomers();
        model.setRowCount(0);
        if (allCustomers == null) return;

        for (Customer cus : allCustomers) {
            model.addRow(new Object[] {
                    cus.getCustomerName(),
                    cus.getCustomerContactInfo()
            });
        }
    }
}