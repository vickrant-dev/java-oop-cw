package com.inventory.domain;

import java.util.List;

public class Invoice {
    private String invoice_id;
    private String invoice_date;
    private Customer customer_snapshot; // prevents from losing auditability
    private String payment_method;
    private Transaction transaction_snapshot;

    public Invoice(String invoice_id, String invoice_date, Customer customer_snapshot,
                   String payment_method, Transaction transaction_snapshot) {
        this.invoice_id = invoice_id;
        this.invoice_date = invoice_date;
        this.customer_snapshot = customer_snapshot;
        this.payment_method = payment_method;
        this.transaction_snapshot = transaction_snapshot;
    }

    public String getInvoiceId() {
        return invoice_id;
    }
    public String getInvoiceDate() {
        return invoice_date;
    }
    public Customer getInvoiceCustomerSnapshot() {
        return customer_snapshot;
    }
    public String getPaymentMethod() {
        return payment_method;
    }
    public Transaction getTransactionSnapshot() {
        return transaction_snapshot;
    }
}
