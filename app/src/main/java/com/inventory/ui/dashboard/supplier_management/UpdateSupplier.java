package com.inventory.ui.dashboard.supplier_management;

import com.inventory.controller.SupplierController;
import com.inventory.domain.Supplier;
import com.inventory.utils.handleValidateFields;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.List;

public class UpdateSupplier extends JDialog {

    private JTextField supplieridField;
    private JTextField supplierNameField;
    private JTextField contactInfoField; // Combined Field

    private Supplier supplier;
    private SupplierPanel parentPanel;

    public UpdateSupplier(Frame owner, Supplier supplier, SupplierPanel parentPanel) {
        super(owner, "Update Supplier", true);
        this.supplier = supplier;
        this.parentPanel = parentPanel;

        setTitle("Update Supplier");
        URL iconURL = getClass().getResource("/shop.png");
        if (iconURL != null) setIconImage(new ImageIcon(iconURL).getImage());

        setSize(400, 300);
        setLocationRelativeTo(owner);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel updateLabel = new JLabel("Update Supplier", SwingConstants.CENTER);
        updateLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        updateLabel.setForeground(new Color(0, 70, 0));
        updateLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(updateLabel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // name field
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Name:"), gbc);
        supplierNameField = new JTextField(supplier.getSupplierName(), 15);
        gbc.gridx = 1;
        formPanel.add(supplierNameField, gbc);

        // contact info field
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("Contact Info:"), gbc);
        contactInfoField = new JTextField(supplier.getSupplierContactInfo(), 15);
        gbc.gridx = 1;
        formPanel.add(contactInfoField, gbc);

        add(formPanel, BorderLayout.CENTER);

        // btns
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 15));
        JButton updateButton = new JButton("Update");
        JButton cancelButton = new JButton("Cancel");

        updateButton.addActionListener(e -> handleUpdate());
        cancelButton.addActionListener(e -> dispose());

        buttonPanel.add(updateButton);
        buttonPanel.add(cancelButton);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void handleUpdate() {
        supplier.updateSupplierDetails(
                supplierNameField.getText(),
                contactInfoField.getText()
        );

        SupplierController supplier_controller = new SupplierController();
        List<String> errMsg = new handleValidateFields().validateFields(supplier);

        if (!errMsg.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Invalid supplier fields," +
                            "Please fill in all the blanks. " + "Error: " + errMsg,
                    "Invalid Supplier Fields: ", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String update_res = supplier_controller.updateSupplierDetails(supplier);

        if ("200".equals(update_res)) {
            parentPanel.refreshTableData();
            dispose();
        }
         else {
            System.out.println("Failed updating suppliers");
        }
    }
}