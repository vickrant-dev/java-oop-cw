package com.inventory.ui.dashboard.customer_management;

import com.inventory.controller.CustomerController;
import com.inventory.domain.Customer;
import com.inventory.utils.handleValidateFields;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CreateCustomerForm extends BaseCustomerForm {
    private final CustomerController controller = new CustomerController();
    private final handleValidateFields handleValidateFields = new handleValidateFields();

    public CreateCustomerForm(Window owner) {
        super(owner, "Add New Customer");
        btnAction.setText("Create");

        btnAction.addActionListener(e -> {
            // validate fields
            List<String> errMsg = handleValidateFields.validateFields(nameField.getText(),
                    contactField.getText());
            if (!errMsg.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Invalid details: " + errMsg);
                return;
            }

            Customer newCus = new Customer(nameField.getText(), contactField.getText());
            String res = controller.createCustomer(newCus);
            if (res.equals("200")) {
                dispose();
            }
            else {
                JOptionPane.showMessageDialog(this,
                        "Failed to create customer",
                        "Failed to create customer",
                        JOptionPane.WARNING_MESSAGE);
            }
        });
    }
}