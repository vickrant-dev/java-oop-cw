package com.inventory.repositories;

import com.inventory.server.Server;
import com.inventory.ui.dashboard.product_management.Product;
import com.inventory.ui.dashboard.supplier_management.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierRepository {
    public List<Supplier> getAllSuppliers() {
        List<Supplier> all_suppliers = new ArrayList<>();
        String fetch_suppliers_query = "SELECT * FROM suppliers";

        try (Connection conn = Server.getConnection()) {
            if (conn != null) {
                PreparedStatement fetchSuppliersStatement = conn.prepareStatement(fetch_suppliers_query);
                ResultSet res = fetchSuppliersStatement.executeQuery();
                while (res.next()) {
                    Supplier supplier = new Supplier(
                            res.getInt("supplier_id"),
                            res.getString("name"),
                            res.getString("contact_info")
                    );
                    all_suppliers.add(supplier);
                }
                fetchSuppliersStatement.close();
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
}
