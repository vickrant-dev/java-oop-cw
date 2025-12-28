package com.inventory.repositories;

import com.inventory.domain.Customer;
import com.inventory.server.Server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepository {
    public List<Customer> getAllCustomers() {
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

    public int updateCustomerDetails(String id, String name, String contact_info) {
        String update_customers_query = "UPDATE customers SET name=?, contact_info=? WHERE id=?";

        try (Connection conn = Server.getConnection()) {
            if (conn != null) {
                PreparedStatement updateCustomersStatement = conn.prepareStatement(update_customers_query);
                updateCustomersStatement.setString(1, name);
                updateCustomersStatement.setString(2, contact_info);
                updateCustomersStatement.setString(3, id);
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

    public int createNewCustomer(String name, String contact_info) {
        String create_customers_query = "INSERT INTO customers (name, contact_info) VALUES (?, ?)";

        try (Connection conn = Server.getConnection()) {
            if (conn != null) {
                PreparedStatement createCustomerStatement = conn.prepareStatement(create_customers_query);
                createCustomerStatement.setString(1, name);
                createCustomerStatement.setString(2, contact_info);
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

    public int deleteCustomer(String id) {
        String delete_customer_query = "DELETE FROM customers WHERE id=?";

        try (Connection conn = Server.getConnection()) {
            if (conn != null) {
                PreparedStatement deleteCustomersStatement = conn.prepareStatement(delete_customer_query);
                deleteCustomersStatement.setString(1, id);
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
}
