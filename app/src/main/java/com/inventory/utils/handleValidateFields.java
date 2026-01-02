package com.inventory.utils;

import com.inventory.domain.*;

import javax.swing.*;
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
        if (transaction.getCustomerId().trim().isEmpty()
                || transaction.getTransactionDate().trim().isEmpty()
                || transaction.getTotalAmount() < 0
                || transaction.getPaymentMethod().trim().isEmpty()
                || transaction.getCreatedBy().trim().isEmpty()
                || transaction.getCreatedAt().trim().isEmpty()
                || transaction.getTransactionDetails().isEmpty()) {
            return "401a";
        }
        return null;
    }
    public String validateFields(String product_id, int quantity, double price)
    {
        if (product_id.trim().isEmpty() || quantity <= 0 || price < 0) {
            return "401a";
        }
        return null;
    }
    public String validateFields(List<TransactionDetails> transactionDetails)
    {
        for (TransactionDetails transaction : transactionDetails) {
            if (validateFields(transaction.getProductId(), transaction.getQuantity(),
                    transaction.getPrice()) != null) {
                return "401a";
            }
        }
        return null;
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
