package com.inventory.ui.dashboard.customer_management;

import com.inventory.controller.CustomerController;
import com.inventory.domain.Customer;
import com.inventory.utils.handleValidateFields;

import javax.swing.*;
import java.awt.*;

public class UpdateCustomerForm extends BaseCustomerForm {
    private final CustomerController controller = new CustomerController();
    private Customer customer;
    private final handleValidateFields handleValidateFields = new handleValidateFields();

    public UpdateCustomerForm(Window owner, Customer customer)
    {
        super(owner, "Update Customer Info");
        this.customer = customer;

        btnAction.setText("Update");

        // set existing data
        nameField.setText(customer.getCustomerName());
        contactField.setText(customer.getCustomerContactInfo());

        btnAction.addActionListener(e -> {
            String errMsg = handleValidateFields.validateFields(nameField.getText(),
                    contactField.getText());

            if (errMsg != null) {
                JOptionPane.showMessageDialog(this, "Invalid details");
                return;
            }

            customer.updateCustomer(nameField.getText(), contactField.getText());
            String res = controller.updateCustomerDetails(customer);
            if (res.equals("200")) {
                dispose();
                return;
            }
            JOptionPane.showMessageDialog(this,
                    "Failed to update customer: ",
                    "Failed to update customer",
                    JOptionPane.WARNING_MESSAGE);
        });
    }
}