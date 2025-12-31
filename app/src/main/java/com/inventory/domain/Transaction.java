package com.inventory.domain;

import com.inventory.controller.TransactionController;
import com.inventory.utils.handleValidateFields;
import com.inventory.utils.sessionManager;

import java.util.List;

public class Transaction {
    private String id;
    private String customer_id;
    private String transaction_date;
    private double total_amount;
    private String payment_method;
    private String created_by; // taken from sessionManager
    private String created_at;
    private List<TransactionDetails> transaction_details;


    // retrieving a transaction
    public Transaction(String id, String customer_id, String transaction_date, double total_amount,
                       String payment_method, String created_by, String created_at,
                       List<TransactionDetails> transaction_details)
    {
        this.id = id;
        this.customer_id = customer_id;
        this.transaction_date = transaction_date;
        this.total_amount = total_amount;
        this.payment_method = payment_method;
        this.created_by = created_by;
        this.created_at = created_at;
        this.transaction_details = transaction_details;
    }

    // create a new transaction
    public Transaction(String customer_id, String transaction_date, double total_amount,
                       String payment_method, String created_at,
                       List<TransactionDetails> transaction_details)
    {
        this.customer_id = customer_id;
        this.transaction_date = transaction_date;
        this.total_amount = total_amount;
        this.created_by = getSessionUserId();
        this.created_at = created_at;
        this.transaction_details = transaction_details;
        this.payment_method = payment_method;
    }


    // GETTERS
    public String getTransactionId() {
        return id;
    }
    public String getCustomerId() {
        return customer_id;
    }
    public String getTransactionDate() {
        return  transaction_date;
    }
    public double getTotalAmount() {
        return total_amount;
    }
    public String getPaymentMethod() {
        return payment_method;
    }
    public String getCreatedBy() {
        return created_by;
    }
    public String getCreatedAt() {
        return created_at;
    }
    public List<TransactionDetails> getTransactionDetails() {
        return transaction_details;
    }
    private String getSessionUserId() {
        return new sessionManager().getUserId();
    }


    // SETTERS
    public void setTransactionDetails(List<TransactionDetails> transaction_details) {
        this.transaction_details = transaction_details;
    }

}
