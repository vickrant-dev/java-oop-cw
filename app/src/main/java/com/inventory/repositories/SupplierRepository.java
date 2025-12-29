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
    public List<Supplier> fetchAllSuppliers()
    {
        List<Supplier> all_suppliers = new ArrayList<>();
        String update_suppliers_query = "SELECT * FROM suppliers";

        try (Connection conn = Server.getConnection()) {
            if (conn != null) {
                PreparedStatement updateSupplierStatement = conn.prepareStatement(update_suppliers_query);
                ResultSet res = updateSupplierStatement.executeQuery();
                while (res.next()) {
                    Supplier supplier = new Supplier(
                            res.getString("id"),
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

    public int updateSupplierDetails(Supplier supplier)
    {
        String update_suppliers_query = "UPDATE suppliers SET name=?, contact_info=? WHERE id=?";

        try (Connection conn = Server.getConnection()) {
            if (conn != null) {
                PreparedStatement updateSupplierStatement = conn.prepareStatement(update_suppliers_query);
                updateSupplierStatement.setString(1, supplier.getSupplierName());
                updateSupplierStatement.setString(2, supplier.getSupplierContactInfo());
                updateSupplierStatement.setString(3, supplier.getSupplierId());
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

    public int createSupplier(Supplier supplier)
    {
        String create_suppliers_query = "INSERT INTO suppliers (name, contact_info) VALUES (?, ?)";

        try (Connection conn = Server.getConnection()) {
            if (conn != null) {
                PreparedStatement createSupplierStatement = conn.prepareStatement(create_suppliers_query);
                createSupplierStatement.setString(1, supplier.getSupplierName());
                createSupplierStatement.setString(2, supplier.getSupplierContactInfo());
                ResultSet res = createSupplierStatement.executeQuery();

                if (res.next()) {
                    System.out.println("res create supplier: " + res.next());
                    createSupplierStatement.close();
                    res.close();
                    return 200;
                }
                else {
                    createSupplierStatement.close();
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

    public int deleteSupplier(Supplier supplier)
    {
        String delete_suppliers_query = "DELETE FROM suppliers WHERE id=?";

        try (Connection conn = Server.getConnection()) {
            if (conn != null) {
                PreparedStatement deleteSupplierStatement = conn.prepareStatement(delete_suppliers_query);
                deleteSupplierStatement.setString(1, supplier.getSupplierId());
                ResultSet res = deleteSupplierStatement.executeQuery();

                if (res.next()) {
                    System.out.println("res delete supplier: " + res.next());
                    deleteSupplierStatement.close();
                    res.close();
                    return 200;
                }
                else {
                    deleteSupplierStatement.close();
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
