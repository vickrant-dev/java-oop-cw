package com.inventory.service_manager;

import com.inventory.domain.Product;
import com.inventory.domain.Transaction;
import com.inventory.repositories.TransactionRepository;
import com.inventory.service.TransactionService;
import com.inventory.utils.handleValidateFields;

import java.util.List;

public class TransactionMgr implements TransactionService {

    private final TransactionRepository transactionRepo;

    public TransactionMgr() {
        this.transactionRepo = new TransactionRepository();
    }

    public String createTransaction(Transaction transaction)
    {
        if(new handleValidateFields().validateFields(transaction) == null
                && new handleValidateFields().validateFields
                (transaction.getTransactionDetails()) == null)
        {
            int create_res = transactionRepo.createTransaction(transaction);
            if (create_res == 200) {
                System.out.println("Created new transaction successfully for: " + transaction.getCustomerId());
                return "200";
            }
            else {
                System.out.println("Product updating failed for: " + transaction.getCustomerId() + ". Error: " + create_res);
                return "401a";
            }
        }
        else {
            return "401a";
        }
    }

    public List<Transaction> fetchAllTransactions() {
        return transactionRepo.fetchAllTransactions();
    }

    public String deleteTransaction(Transaction transaction)
    {
        if(new handleValidateFields().validateFields(transaction) == null)
        {
            int delete_res = transactionRepo.deleteTransaction(transaction);
            if (delete_res == 200) {
                System.out.println("deleted transaction successfully for: " + transaction.getTransactionId());
                return "200";
            }
            else {
                System.out.println("deleting transaction updating failed for: " + transaction.getTransactionId() + ". Error: " + delete_res);
                return "401a";
            }
        }
        else {
            return "401a";
        }
    }

    public boolean checkTransaction(Product product)
    {
        if(new handleValidateFields().validateFields(product) == null)
        {
            return transactionRepo.checkTransaction(product);
        }
        else {
            return true;
        }
    }
}
