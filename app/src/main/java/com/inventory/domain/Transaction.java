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
    private String created_by; // take it from sessionManager
    private List<TransactionDetails> transaction_details;

    // retrieving a transaction
    public Transaction(String id, String customer_id, String transaction_date, double total_amount,
                       String created_by, List<TransactionDetails> transaction_details)
    {
        this.id = id;
        this.customer_id = customer_id;
        this.transaction_date = transaction_date;
        this.total_amount = total_amount;
        this.created_by = created_by;
        this.transaction_details = transaction_details;
    }

    // create a new transaction
    public Transaction(String customer_id, String transaction_date, double total_amount,
                       List<TransactionDetails> transaction_details)
    {
        this.customer_id = customer_id;
        this.transaction_date = transaction_date;
        this.total_amount = total_amount;
        this.created_by = getSessionUserId();
        this.transaction_details = transaction_details;
    }

    // GETTERS
    public String getTransactionId() {
        return this.id;
    }


    // SETTER
    public String createNewTransaction() {
        return createTransaction();
    }

    public String delTransaction() {
        return deleteTransaction();
    }

    public void setTransactionDetails(List<TransactionDetails> transaction_details) {
        this.transaction_details = transaction_details;
    }

    // services
    private String createTransaction() {
        if(new handleValidateFields().validateFields(this.customer_id, this.transaction_date,
                this.total_amount, this.created_by) == null
                && new handleValidateFields().validateListFields(this.transaction_details) == null)
        {
            int create_res = new TransactionController().createTransaction(this.customer_id,
                    this.transaction_date, this.total_amount, this.created_by,
                    this.transaction_details);
            if (create_res == 200) {
                System.out.println("Created new transaction successfully for: " + this.customer_id);
            }
            else {
                System.out.println("Product updating failed for: " + this.customer_id + ". Error: " + create_res);
            }
            return "200";
        }
        else {
            return "401a";
        }
    }

    private String deleteTransaction() {
        if(new handleValidateFields().validateFields(this.id) == null)
        {
            int delete_res = new TransactionController().deleteTransaction(this.id);
            if (delete_res == 200) {
                System.out.println("deleted transaction successfully for: " + this.id);
            }
            else {
                System.out.println("deleting transaction updating failed for: " + this.id + ". Error: " + delete_res);
            }
            return "200";
        }
        else {
            return "401a";
        }
    }

    private String getSessionUserId() {
        return new sessionManager().getUserId();
    }

}
