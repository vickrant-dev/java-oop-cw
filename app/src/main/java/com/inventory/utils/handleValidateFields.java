package com.inventory.utils;

import com.inventory.domain.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class handleValidateFields {

    public String validateFields(String input) {
        if (input.trim().isEmpty()){
            return "401a";
        }
        return null;
    }

    // THE TRANSACTION VALIDATION
    // used for validating TRANSACTION fields
    public String validateFields(Transaction transaction)
    {
        if (transaction == null) return "401_null";

        if (isInvalid(transaction.getCustomerId())
                || isInvalid(transaction.getTransactionDate())
                || transaction.getTotalAmount() < 0
                || isInvalid(transaction.getPaymentMethod())
                || transaction.getTransactionDetails() == null
                || transaction.getTransactionDetails().isEmpty()) {
            return "401a";
        }
        return null;
    }

    private boolean isInvalid(String str) {
        return str == null || str.trim().isEmpty();
    }

    public String validateFields(String product_id, int quantity, double price)
    {
        if (product_id == null || product_id.trim().isEmpty() || quantity <= 0 || price < 0) {
            return "401a";
        }
        return null;
    }

    public String validateFields(List<TransactionDetails> transactionDetails)
    {
        if (transactionDetails == null || transactionDetails.isEmpty()) {
            return "401_empty_list";
        }

        for (TransactionDetails detail : transactionDetails) {
            if (validateFields(detail.getProductId(), detail.getQuantity(), detail.getPrice()) != null) {
                return "401a";
            }
        }
        return null;
    }
    public String validateFields(JTextField txtCustomerName, JTextField txtContactInfo,
                                 DefaultTableModel detailsModel, JComboBox<String> cmbPayment)
    {
        StringBuilder errors = new StringBuilder();
        if (txtCustomerName == null || txtCustomerName.getText().trim().isEmpty()) {
            errors.append("- Customer name is required.\n");
        }
        if (txtContactInfo == null || txtContactInfo.getText().trim().isEmpty()) {
            errors.append("- Contact info is required.\n");
        }
        if (detailsModel == null || detailsModel.getRowCount() == 0) {
            errors.append("- At least one product must be added to the transaction.\n");
        } else {
            // validate each row has valid product id/qty/price
            for (int i = 0; i < detailsModel.getRowCount(); i++) {
                Object productIdObj = detailsModel.getValueAt(i, 0);
                Object qtyObj = detailsModel.getValueAt(i, 2);
                Object priceObj = detailsModel.getValueAt(i, 3);
                String productId = productIdObj == null ? "" : productIdObj.toString().trim();
                int qty = 0;
                double price = -1;
                try { qty = Integer.parseInt(qtyObj.toString()); } catch (Exception ignored) {}
                try { price = Double.parseDouble(priceObj.toString()); } catch (Exception ignored) {}
                if (productId.isEmpty() || qty <= 0 || price < 0) {
                    errors.append(String.format("- Invalid product entry on row %d.\n", i+1));
                }
            }
        }
        if (cmbPayment == null || cmbPayment.getSelectedItem() == null) {
            errors.append("- Please select a payment method.\n");
        }

        return errors.length() == 0 ? null : errors.toString();
    }


    // THE SUPPLIER VALIDATION
    // used for validating SUPPLIER fields
    public String validateFields(Supplier supplier)
    {
        if (supplier.getSupplierName().trim().isEmpty() ||
                supplier.getSupplierContactInfo().trim().isEmpty()) {
            return "401a";
        }
        return null;
    }


    // THE PRODUCTS VALIDATION
    // used for validating PRODUCT field when deleting
    public String validateFields(Product product)
    {

        if (product.getProductId().trim().isEmpty()) {
            return "401a";
        }
        if (product.getProductName().trim().isEmpty()) {
            return "401a"; // invalid name
        }

        if (product.getProductCategory().trim().isEmpty()) {
            return "401a"; // invalid category
        }

        if (product.getProductPrice() < 0) {
            return "401a"; // invalid price
        }

        if (product.getProductStockQuantity() < 0) {
            return "401a"; // invalid stock quantity
        }

        if (product.getSupplierName().trim().isEmpty()) {
            return "401a";
        }

        return null; // valid
    }

    // used for validating when creating PRODUCT
    public String validateFields(JTextField txtProductId, JTextField txtProductName,
                                 JTextField txtCategory, JFormattedTextField txtPrice,
                                 JSpinner spnStock, JComboBox<Supplier> cmbSupplier)
    {
        StringBuilder errors = new StringBuilder(); // allows insert and append fn

        if (txtProductId.getText().trim().isEmpty()) errors.append("- Product ID is required.\n");
        if (txtProductName.getText().trim().isEmpty()) errors.append("- Product Name is required.\n");
        if (txtCategory.getText().trim().isEmpty()) errors.append("- Category is required.\n");

        if (txtPrice.getValue() == null || ((Number) txtPrice.getValue()).doubleValue() <= 0) {
            errors.append("- Price must be greater than 0.\n");
        }

        if (spnStock.getValue() == null || (int) spnStock.getValue() <= 0) {
            errors.append("- Stock cannot be negative.\n");
        }

        if (cmbSupplier.getSelectedItem() == null) {
            errors.append("- Please select a valid Supplier.\n");
        }

        return errors.toString();
    }

    // used for validating when updating PRODUCT
    public String validateFields(JTextField product_id, JTextField product_name,
                                 JTextField product_category, JTextField price_field,
                                 JTextField stock_field)
    {
        StringBuilder errors = new StringBuilder(); // allows insert and append fn

        if (product_id.getText().trim().isEmpty()) errors.append("- Product ID is required.\n");
        if (product_name.getText().trim().isEmpty()) errors.append("- Product Name is required.\n");
        if (product_category.getText().trim().isEmpty()) errors.append("- Category is required.\n");

        if (price_field.getText().trim().isEmpty() || Double.parseDouble(price_field.getText()) <= 0 ) {
            errors.append("- Price must be greater than 0.\n");
        }

        if (stock_field.getText().trim().isEmpty() || Integer.parseInt(stock_field.getText()) <= 0) {
            errors.append("- Stock cannot be negative or empty.\n");
        }

        return errors.toString();

    }



    // THE CUSTOMER VALIDATION
    // used for validating CUSTOMER fields
    public String validateFields(Customer customer) {
        if (customer.getCustomerId().trim().isEmpty()) {
            return "401a";
        }
        if (customer.getCustomerName().trim().isEmpty()) {
            return "401a";
        }
        if (customer.getCustomerContactInfo().trim().isEmpty()) {
            return "401a";
        }
        return null;
    }


    // THE AUTH VALIDATION
    // used for validating LOGIN/SIGNUP
    public String validateFields(JTextField field1, JPasswordField field2)
    {
        if (field1.getText().trim().isEmpty() || field2.getPassword().length == 0) {
            return "401a";
        }
        return null;
    }

    public String validateFields(JTextField field1, JTextField field2, JPasswordField field3)
    {
        if (field1.getText().trim().isEmpty() || field2.getText().trim().isEmpty() || field3.getPassword().length == 0) {
            return "401a";
        }
        return null;
    }
}
