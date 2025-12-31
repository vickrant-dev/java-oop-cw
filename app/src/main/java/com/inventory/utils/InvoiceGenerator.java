package com.inventory.utils;

import com.inventory.domain.Customer;
import com.inventory.domain.Invoice;
import com.inventory.domain.Transaction;

import java.time.LocalDate;
import java.util.List;

public class InvoiceGenerator {
    public Invoice generateInvoice(Customer customer, Transaction transaction) {
        return new Invoice(
                new IDGenerator().generateUUID(),
                LocalDate.now().toString(),
                customer,
                transaction.getPaymentMethod(),
                transaction
        );
    }
    public String printInvoice() {
        return null;
    }
}
