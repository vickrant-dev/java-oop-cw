package com.inventory.ui.dashboard.transaction_management;

import com.inventory.controller.TransactionController;
import com.inventory.domain.Customer;
import com.inventory.domain.Product;
import com.inventory.domain.Transaction;
import com.inventory.domain.TransactionDetails;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransactionLifecycleTest {
    private TransactionController transactionController = new TransactionController();
    private Transaction curr_transaction;

    public void testTransaction(Product product, Customer customer) {
        // CREATING TRANSACTION
        List<TransactionDetails> transactionDetailsList = new ArrayList<>();
        TransactionDetails transactionDetails1 = new TransactionDetails(product.getId(),
                1, 1000);
        TransactionDetails transactionDetails2 = new TransactionDetails(product.getId(),
                1, 1000);
        transactionDetailsList.add(transactionDetails1);
        transactionDetailsList.add(transactionDetails2);

        Transaction transaction1 = new Transaction(customer.getCustomerId(),
                LocalDateTime.now().toString(), 1700, 10,
                300, "Card", LocalDateTime.now().toString(),
                transactionDetailsList);

        String res = transactionController.createTransaction(transaction1);
        assertEquals("200", res, "Failed to create transaction");


        // READING TRANSACTION


        // DELETING TRANSACTION
        String res_2 = transactionController.deleteTransaction(transaction1);
        assertEquals("200", res_2, "Failed to delete transaction");
    }
}
