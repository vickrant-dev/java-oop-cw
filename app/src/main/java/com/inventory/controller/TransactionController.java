package com.inventory.controller;

import com.inventory.domain.Product;
import com.inventory.domain.Transaction;
import com.inventory.service.TransactionService;
import com.inventory.service_manager.TransactionMgr;

import java.util.List;

public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController() {
        this.transactionService = new TransactionMgr();
    }

    // CRUD ops
    public List<Transaction> getAllTransactions() {
        return transactionService.fetchAllTransactions();
    }

    public String createTransaction(Transaction transaction) {
        return transactionService.createTransaction(transaction);
    }

    public String deleteTransaction(Transaction transaction) {
        return transactionService.deleteTransaction(transaction);
    }

    public boolean checkTransaction(Product product) {
        return transactionService.checkTransaction(product);
    }
}
