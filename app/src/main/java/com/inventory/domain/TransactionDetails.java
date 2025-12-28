package com.inventory.domain;

public class TransactionDetails {
    private String transaction_id; // gen by db.
    private String product_id;
    private int quantity;
    private double price;

    // retrieving transactions
    public TransactionDetails(String transaction_id, String product_id, int quantity,
                              double price) {
        this.transaction_id = transaction_id;
        this.product_id = product_id;
        this.quantity = quantity;
        this.price = price;
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

}
