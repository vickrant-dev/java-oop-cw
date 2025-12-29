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
        String update_customers_query = "UPDATE customers SET name=?, contact_info=? WHERE id=?";

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
        String delete_customer_query = "DELETE FROM customers WHERE id=?";

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
}
