package com.inventory.controller;

import com.inventory.domain.Transaction;
import com.inventory.domain.TransactionDetails;
import com.inventory.repositories.TransactionRepository;

import java.util.List;

public class TransactionController {
    private final TransactionRepository transactionRepo;

    public TransactionController() {
        this.transactionRepo = new TransactionRepository();
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepo.fetchAllTransactions();
    }

    public int createTransaction(String customer_id, String transaction_date, double total_amount,
                                    String created_by, List<TransactionDetails> transaction_details)
    {
        return transactionRepo.createTransaction(customer_id, transaction_date, total_amount,
                created_by, transaction_details);
    }

    public int deleteTransaction(String id) {
        return transactionRepo.deleteTransaction(id);
    }
}
