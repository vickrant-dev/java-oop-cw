package com.inventory.repositories;

import com.inventory.server.Server;
import com.inventory.domain.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierRepository {
    public List<Supplier> getAllSuppliers() {
        List<Supplier> all_suppliers = new ArrayList<>();
        String update_suppliers_query = "SELECT * FROM suppliers";

        try (Connection conn = Server.getConnection()) {
            if (conn != null) {
                PreparedStatement updateSupplierStatement = conn.prepareStatement(update_suppliers_query);
                ResultSet res = updateSupplierStatement.executeQuery();
                while (res.next()) {
                    Supplier supplier = new Supplier(
                            res.getInt("supplier_id"),
                            res.getString("name"),
                            res.getString("contact_info")
                    );
                    all_suppliers.add(supplier);
                }
                updateSupplierStatement.close();
                res.close();
                return all_suppliers;
            }
            else {
                return all_suppliers; // connection err.
            }
        }
        catch (SQLException e) {
            System.err.println("Database connection err: " + e.getMessage());
            e.printStackTrace();
            return all_suppliers;
        }
    }

    public int updateSupplierName(int id, String new_name) {
        String update_suppliers_query = "UPDATE suppliers SET name=? WHERE supplier_id=?";

        try (Connection conn = Server.getConnection()) {
            if (conn != null) {
                PreparedStatement updateSupplierStatement = conn.prepareStatement(update_suppliers_query);
                updateSupplierStatement.setString(1, new_name);
                updateSupplierStatement.setInt(2, id);
                ResultSet res = updateSupplierStatement.executeQuery();

                if (res.next()) {
                    System.out.println("res update supplier: " + res.next());
                    updateSupplierStatement.close();
                    res.close();
                    return 200;
                }
                else {
                    updateSupplierStatement.close();
                    res.close();
                    return 401; // invalid product_id maybe...
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

    public int updateSupplierContact(int id, String contact_info) {
        String update_suppliers_query = "UPDATE suppliers SET contact_info=? WHERE supplier_id=?";

        try (Connection conn = Server.getConnection()) {
            if (conn != null) {
                PreparedStatement updateSupplierStatement = conn.prepareStatement(update_suppliers_query);
                updateSupplierStatement.setString(1, contact_info);
                updateSupplierStatement.setInt(2, id);
                ResultSet res = updateSupplierStatement.executeQuery();

                if (res.next()) {
                    System.out.println("res update supplier: " + res.next());
                    updateSupplierStatement.close();
                    res.close();
                    return 200;
                }
                else {
                    updateSupplierStatement.close();
                    res.close();
                    return 401; // invalid product_id maybe...
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
