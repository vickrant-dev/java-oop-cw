package com.inventory.service;

import com.inventory.domain.Product;
import com.inventory.domain.Supplier;
import com.inventory.domain.Transaction;

import java.util.List;

public interface TransactionService {
    String createTransaction(Transaction transaction);

    List<Transaction> fetchAllTransactions();

    String deleteTransaction(Transaction transaction);

    boolean checkTransaction(Product product);
}
