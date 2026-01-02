package com.inventory.domain;

public class TransactionDetails {
    private String transaction_id; // gen by db.
    private String product_id;
    private String product_name;
    private int quantity;
    private double price;

    // retrieving transactions
    public TransactionDetails(String transaction_id, String product_id, String product_name,
                              int quantity, double price) {
        this.transaction_id = transaction_id;
        this.product_id = product_id;
        this.quantity = quantity;
        this.price = price;
        this.product_name = product_name;
    }

    // creating transactions
    public TransactionDetails(String product_id, int quantity, double price) {
        this.product_id = product_id;
        this.quantity = quantity;
        this.price = price;
    }


    // GETTERS
    public String getTransactionId() {
        return transaction_id;
    }
    public String getProductId() {
        return product_id;
    }
    public int getQuantity() {
        return quantity;
    }
    public double getPrice() {
        return price;
    }
    public String getProductName() {
        return product_name;
    }
}
