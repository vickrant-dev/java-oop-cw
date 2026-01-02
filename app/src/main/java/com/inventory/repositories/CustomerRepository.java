package com.inventory.repositories;

import com.inventory.domain.Customer;
import com.inventory.domain.Transaction;
import com.inventory.domain.TransactionDetails;
import com.inventory.server.Server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepository {
    public List<Customer> getAllCustomers()
    {
        List<Customer> all_customers = new ArrayList<>();
        String get_customers_query = "SELECT * FROM customers";

        try (Connection conn = Server.getConnection()) {
            if (conn != null) {
                PreparedStatement getCustomersStatement = conn.prepareStatement(get_customers_query);
                ResultSet res = getCustomersStatement.executeQuery();
                while (res.next()) {
                    Customer supplier = new Customer(
                            res.getString("id"),
                            res.getString("name"),
                            res.getString("contact_info")
                    );
                    all_customers.add(supplier);
                }
                getCustomersStatement.close();
                res.close();
                return all_customers;
            }
            else {
                return all_customers; // connection err.
            }
        }
        catch (SQLException e) {
            System.err.println("Database connection err: " + e.getMessage());
            e.printStackTrace();
            return all_customers;
        }
    }

    public int updateCustomerDetails(Customer customer)
    {
        String update_customers_query = "UPDATE customers SET name=?, contact_info=? WHERE id=?::uuid";

        try (Connection conn = Server.getConnection()) {
            if (conn != null) {
                PreparedStatement updateCustomersStatement = conn.prepareStatement(update_customers_query);
                updateCustomersStatement.setString(1, customer.getCustomerName());
                updateCustomersStatement.setString(2, customer.getCustomerContactInfo());
                updateCustomersStatement.setString(3, customer.getCustomerId());
                ResultSet res = updateCustomersStatement.executeQuery();

                if (res.next()) {
                    System.out.println("res update supplier: " + res.next());
                    updateCustomersStatement.close();
                    res.close();
                    return 200;
                }
                else {
                    updateCustomersStatement.close();
                    res.close();
                    return 401; //  invalid...
                }
            }
            else {
                return 503; //connection error
            }
        }
        catch (SQLException e) {
            System.err.println("Database connection err: " + e.getMessage());
            e.printStackTrace();
            return 503;
        }
    }

    public int createCustomer(Customer customer)
    {
        String create_customers_query = "INSERT INTO customers (name, contact_info) VALUES (?, ?)";

        try (Connection conn = Server.getConnection()) {
            if (conn != null) {
                PreparedStatement createCustomerStatement = conn.prepareStatement(create_customers_query);
                createCustomerStatement.setString(1, customer.getCustomerName());
                createCustomerStatement.setString(2, customer.getCustomerContactInfo());
                ResultSet res = createCustomerStatement.executeQuery();

                if (res.next()) {
                    System.out.println("res create customer: " + res.next());
                    createCustomerStatement.close();
                    res.close();
                    return 200;
                }
                else {
                    createCustomerStatement.close();
                    res.close();
                    return 401; // invalid...
                }
            }
            else {
                return 503; //connection error
            }
        }
        catch (SQLException e) {
            System.err.println("Database connection err: " + e.getMessage());
            e.printStackTrace();
            return 503;
        }
    }

    public int deleteCustomer(Customer customer)
    {
        String delete_customer_query = "DELETE FROM customers WHERE id=?::uuid";

        try (Connection conn = Server.getConnection()) {
            if (conn != null) {
                PreparedStatement deleteCustomersStatement = conn.prepareStatement(delete_customer_query);
                deleteCustomersStatement.setString(1, customer.getCustomerId());
                ResultSet res = deleteCustomersStatement.executeQuery();

                if (res.next()) {
                    System.out.println("res delete customer: " + res.next());
                    deleteCustomersStatement.close();
                    res.close();
                    return 200;
                }
                else {
                    deleteCustomersStatement.close();
                    res.close();
                    return 401; // invalid...
                }
            }
            else {
                return 503; //connection error
            }
        }
        catch (SQLException e) {
            System.err.println("Database connection err: " + e.getMessage());
            e.printStackTrace();
            return 503;
        }
    }

    public List<Transaction> fetchCustomerTransactions(Customer customer)
    {
        List<Transaction> all_cus_transactions = new ArrayList<>();

        String get_cus_transactions_query = """
                SELECT t.*, u.username AS created_by_name
                FROM transactions t
                JOIN users u ON t.created_by = u.id
                WHERE t.customer_id = ?::uuid;
            """;

        // joining products table here to get the actual name
        String get_transaction_details_query = """
                SELECT td.*, p.name as product_name
                FROM transaction_details td
                JOIN products p ON td.product_id = p.id
                WHERE td.transaction_id = ?::uuid;
            """;

        try (Connection conn = Server.getConnection()) {
            if (conn == null) return all_cus_transactions;

            // prepare both statements up front
            try (PreparedStatement getCusTxStmt = conn.prepareStatement(get_cus_transactions_query);
                 PreparedStatement getDetailsStmt = conn.prepareStatement(get_transaction_details_query)) {

                getCusTxStmt.setString(1, customer.getCustomerId());

                try (ResultSet res = getCusTxStmt.executeQuery()) {
                    while (res.next()) {
                        Transaction cus_transaction = new Transaction(
                                res.getString("id"),
                                res.getString("customer_id"),
                                res.getString("transaction_date"),
                                res.getDouble("total_amount"),
                                res.getDouble("discount_percentage"),
                                res.getDouble("discount_amount"),
                                res.getString("payment_method"),
                                res.getString("created_by"),
                                res.getString("created_by_name"),
                                res.getString("created_at"),
                                new ArrayList<>()
                        );

                        // reuse the same prepared statement for efficiency
                        getDetailsStmt.setString(1, cus_transaction.getTransactionId());

                        try (ResultSet details_res = getDetailsStmt.executeQuery()) {
                            List<TransactionDetails> transaction_details = new ArrayList<>();
                            while(details_res.next()) {
                                TransactionDetails details = new TransactionDetails(
                                        details_res.getString("transaction_id"),
                                        details_res.getString("product_id"),
                                        details_res.getString("product_name"), // from joined table
                                        details_res.getInt("quantity"),
                                        details_res.getDouble("price")
                                );
                                transaction_details.add(details);
                            }
                            cus_transaction.setTransactionDetails(transaction_details);
                        }

                        all_cus_transactions.add(cus_transaction);
                    }
                }
            }
            return all_cus_transactions;

        } catch (SQLException e) {
            System.err.println("Database connection err: " + e.getMessage());
            e.printStackTrace();
            return all_cus_transactions;
        }
    }
}
