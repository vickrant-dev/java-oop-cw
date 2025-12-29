package com.inventory.utils;

import com.inventory.controller.ProductController;
import com.inventory.controller.TransactionController;
import com.inventory.domain.Product;
import com.inventory.domain.Transaction;

import java.util.ArrayList;
import java.util.List;

public class loadAllTransactions {
    public List<Object[]> getAllTransactions() {
        List<Transaction> all_transactions = new TransactionController().getAllTransactions();
        List<Object[]> table_data = new ArrayList<>();
        for (Transaction transaction: all_transactions) {
            // An anonymous object is an instance created without assigning it to a reference variable.
            // They are typically used for "one-off" tasks where you don't need to refer
            // to that object ever again.

            // addRow requires an Object array (Object[]) where each index represents a column
            // we use an anonymous array initialization by doing -> new Object[] that has no name,
            // created and destroyed almost instantly.
            // the object is built with the exact no of size (columns) mentioned inside the "{}"
            // values are pulled from the db on runtime and is dynamically inserted.
            table_data.add(new Object[] {
                    transaction.getTransactionId(),
                    transaction.getCustomerId(),
                    transaction.getTransactionDate(),
                    transaction.getTotalAmount(),
                    transaction.getCreatedBy(),
                    transaction.getTransactionDetails()
            });
        }
        return table_data;
    }
}
