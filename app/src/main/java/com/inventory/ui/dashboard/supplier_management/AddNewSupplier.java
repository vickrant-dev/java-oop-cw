package com.inventory.ui.dashboard.supplier_management;

import com.inventory.domain.Supplier;
import com.inventory.controller.SupplierController;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class AddNewSupplier extends JDialog {

    private JLabel titleLabel, supplierIdLabel, supplierNameLabel, contactInfoLabel;
    private JTextField supplierIdField, supplierNameField, contactInfoField;
    private SupplierPanel parentPanel;

    public AddNewSupplier(Frame owner, SupplierPanel parent) {
        super(owner, "Add New Supplier", true);
        this.parentPanel = parent;

        setTitle("Add New Supplier");
        setSize(450, 350); // Slightly reduced height
        setLocationRelativeTo(owner);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLayout(null);

        // Set icon
        URL iconURL = getClass().getResource("/shop.png");
        if (iconURL != null) {
            setIconImage(new ImageIcon(iconURL).getImage());
        }

        titleLabel = new JLabel("Add New Supplier");
        titleLabel.setBounds(140, 5, 200, 40);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titleLabel.setForeground(new Color(0, 70, 0));
        add(titleLabel);

        // name
        supplierNameLabel = new JLabel("Name:");
        supplierNameLabel.setBounds(30, 110, 100, 25);
        supplierNameField = new JTextField();
        supplierNameField.setBounds(140, 110, 240, 25);
        add(supplierNameLabel);
        add(supplierNameField);

        // contact info
        contactInfoLabel = new JLabel("Contact Info:");
        contactInfoLabel.setBounds(30, 160, 100, 25);
        contactInfoField = new JTextField();
        contactInfoField.setBounds(140, 160, 240, 25);
        add(contactInfoLabel);
        add(contactInfoField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setBounds(0, 240, 450, 50);

        JButton addBtn = new JButton("Add");
        JButton cancelBtn = new JButton("Cancel");

        buttonPanel.add(addBtn);
        buttonPanel.add(cancelBtn);
        add(buttonPanel);

        cancelBtn.addActionListener(e -> dispose());

        addBtn.addActionListener(e -> {
            Supplier newSupplier = new Supplier(supplierNameField.getText(),
                    contactInfoField.getText());

            SupplierController supplierController = new SupplierController();
            String create_supplier_res = supplierController.createSupplier(newSupplier);
            if (create_supplier_res.equals("200")) {
                parentPanel.refreshTableData();
                dispose();
            }
            else if (create_supplier_res.equals("401a")) {
                JOptionPane.showMessageDialog(this, "Please fill in all the blanks!");
            }
            else {
                JOptionPane.showMessageDialog(this, "Failed to create supplier");
            }

        });

        setVisible(true);
    }
}