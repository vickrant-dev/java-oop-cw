package com.inventory.repositories;

import com.google.gson.Gson;
import com.inventory.domain.Transaction;
import com.inventory.domain.TransactionDetails;
import com.inventory.server.Server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransactionRepository {
    public List<Transaction> fetchAllTransactions()
    {
        List<Transaction> all_transactions = new ArrayList<>();
        String fetch_transactions_query = """
                    SELECT * FROM transactions
                """;
        String fetch_transaction_details_query = """
                    SELECT * FROM transaction_details WHERE transaction_id = ?
                """;

        try (Connection conn = Server.getConnection()) {
            if (conn != null) {
                PreparedStatement fetchTransactionsStatement = conn.prepareStatement(fetch_transactions_query);
                ResultSet res = fetchTransactionsStatement.executeQuery();

                // First we take the transactions
                while (res.next()) {

                    // for each transaction we create an obj and fetch its transaction details
                    // and set it to its transaction details
                    Transaction transaction = new Transaction(
                            res.getString("id"),
                            res.getString("customer_id"),
                            res.getString("transaction_date"),
                            res.getDouble("total_amount"),
                            res.getDouble("discount_percentage"),
                            res.getDouble("discount_amount"),
                            res.getString("payment_method"),
                            res.getString("created_by"),
                            res.getString("created_at"),
                            new ArrayList<>()
                    );

                    PreparedStatement fetchTransactionsDetailsStatement = conn.prepareStatement(fetch_transaction_details_query);
                    fetchTransactionsDetailsStatement.setString(1, transaction.getTransactionId());
                    ResultSet details_res = fetchTransactionsDetailsStatement.executeQuery();

                    List<TransactionDetails> transaction_details = new ArrayList<>();
                    while(details_res.next()) {
                        TransactionDetails details = new TransactionDetails(
                                details_res.getString("transaction_id"),
                                details_res.getString("product_id"),
                                details_res.getInt("quantity"),
                                details_res.getDouble("price")
                        );
                        // for every transaction details row we add it to a List
                        transaction_details.add(details);
                    }

                    // once we get all transaction details for a given transaction
                    // we add it to transaction's transaction details variable
                    // and then finally add it to the main transactions list
                    transaction.setTransactionDetails(transaction_details);
                    all_transactions.add(transaction);

                    fetchTransactionsDetailsStatement.close();
                    details_res.close();
                }
                fetchTransactionsStatement.close();
                res.close();
                return all_transactions;
            }
            else {
                return all_transactions; // connection err.
            }
        }
        catch (SQLException e) {
            System.err.println("Database connection err: " + e.getMessage());
            e.printStackTrace();
            return all_transactions;
        }
    }

    public int createTransaction(Transaction transaction)
    {
        String create_transaction_query =
                """
                    SELECT create_transaction(?, ?, ?, ?, ?::jsonb);
                """;

        try (Connection conn = Server.getConnection()) {
            if (conn != null) {

                // we are using a Gson library to convert java obj to json format
                String transaction_details_json = new Gson().toJson(transaction.getTransactionDetails());

                PreparedStatement createTransactionStatement = conn.prepareStatement(create_transaction_query);
                createTransactionStatement.setString(1, transaction.getCustomerId());
                createTransactionStatement.setString(2, transaction.getTransactionDate());
                createTransactionStatement.setDouble(3, transaction.getTotalAmount());
                createTransactionStatement.setDouble(3, transaction.getDiscountAmount());
                createTransactionStatement.setDouble(3, transaction.getDiscountPercentage());
                createTransactionStatement.setString(4, transaction.getCreatedBy());
                createTransactionStatement.setString(5, transaction_details_json);

                ResultSet res = createTransactionStatement.executeQuery();

                if (res.next()) {
                    System.out.println("res create transaction: " + res.next());
                    createTransactionStatement.close();
                    res.close();
                    return 200;
                }
                else {
                    createTransactionStatement.close();
                    res.close();
                    return 401; // invalid product_id maybe...
                }
            }
            else {
                return 503;
            }
        }
        catch (SQLException e) {
            System.err.println("Database connection err: " + e.getMessage());
            e.printStackTrace();
            return 503;
        }
    }

    public int deleteTransaction(Transaction transaction)
    {
        String create_transaction_query =
                """
                    SELECT delete_transaction(?);
                """;

        try (Connection conn = Server.getConnection()) {
            if (conn != null) {
                PreparedStatement deleteTransactionStatement = conn.prepareStatement(create_transaction_query);
                deleteTransactionStatement.setString(1, transaction.getTransactionId());

                ResultSet res = deleteTransactionStatement.executeQuery();

                if (res.next()) {
                    System.out.println("res delete transaction: " + res.next());
                    deleteTransactionStatement.close();
                    res.close();
                    return 200;
                }
                else {
                    deleteTransactionStatement.close();
                    res.close();
                    return 401; // invalid product_id maybe...
                }
            }
            else {
                return 503;
            }
        }
        catch (SQLException e) {
            System.err.println("Database connection err: " + e.getMessage());
            e.printStackTrace();
            return 503;
        }
    }

}
