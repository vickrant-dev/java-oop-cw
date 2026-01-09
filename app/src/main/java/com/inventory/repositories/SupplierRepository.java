package com.inventory.repositories;

import com.inventory.domain.Product;
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
        String get_suppliers_query = "SELECT * FROM suppliers";

        try (Connection conn = Server.getConnection()) {
            if (conn != null) {
                PreparedStatement getSupplierStatement = conn.prepareStatement(get_suppliers_query);
                ResultSet res = getSupplierStatement.executeQuery();
                while (res.next()) {
                    Supplier supplier = new Supplier(
                            res.getString("id"),
                            res.getString("name"),
                            res.getString("contact_info")
                    );
                    all_suppliers.add(supplier);
                }
                getSupplierStatement.close();
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
        String update_suppliers_query = "UPDATE suppliers SET name=?, contact_info=? WHERE id=?::uuid";

        try (Connection conn = Server.getConnection()) {
            if (conn != null) {
                PreparedStatement updateSupplierStatement = conn.prepareStatement(update_suppliers_query);
                updateSupplierStatement.setString(1, supplier.getSupplierName());
                updateSupplierStatement.setString(2, supplier.getSupplierContactInfo());
                updateSupplierStatement.setString(3, supplier.getSupplierId());
                int res = updateSupplierStatement.executeUpdate();

                if (res == 1) {
                    updateSupplierStatement.close();
                    return 200;
                }
                else {
                    updateSupplierStatement.close();
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
                PreparedStatement createSupplierStatement = conn.prepareStatement(create_suppliers_query,
                        new String[] { "id" });
                createSupplierStatement.setString(1, supplier.getSupplierName());
                createSupplierStatement.setString(2, supplier.getSupplierContactInfo());
                int res = createSupplierStatement.executeUpdate();

                if (res > 0) {
                    ResultSet res_keys = createSupplierStatement.getGeneratedKeys();
                    if (res_keys.next()) {
                        String gen_key = res_keys.getString(1);
                        supplier.setId(gen_key);
                    }
                    createSupplierStatement.close();
                    return 200;
                }
                else {
                    createSupplierStatement.close();
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
        String delete_suppliers_query = "DELETE FROM suppliers WHERE id=?::uuid";

        try (Connection conn = Server.getConnection()) {
            if (conn != null) {
                PreparedStatement deleteSupplierStatement = conn.prepareStatement(delete_suppliers_query);
                deleteSupplierStatement.setString(1, supplier.getSupplierId());
                int res = deleteSupplierStatement.executeUpdate();

                if (res == 1) {
                    deleteSupplierStatement.close();
                    return 200;
                }
                else {
                    deleteSupplierStatement.close();
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

    public List<Product> getSupplierProducts(Supplier supplier)
    {
        List<Product> supplier_products = new ArrayList<>();
        String get_supplier_products = """
                    SELECT p.*,
                    s.id AS supplier_name,
                    s.name as supplier_name,
                    s.contact_info AS supplier_contact_info
                    FROM products p
                    JOIN suppliers s ON p.supplier_id = s.id
                    WHERE s.id=?::uuid
                """;

        try (Connection conn = Server.getConnection()) {
            if (conn != null) {
                PreparedStatement getSupplierProducts = conn.prepareStatement(get_supplier_products);
                getSupplierProducts.setString(1, supplier.getSupplierId());
                ResultSet res = getSupplierProducts.executeQuery();
                while (res.next()) {

                    Supplier prod_supplier = new Supplier(
                            res.getString("supplier_id"),
                            res.getString("supplier_name"),
                            res.getString("supplier_contact_info")
                    );

                    Product supplier_prod = new Product(
                            res.getString("id"),
                            res.getString("product_id"),
                            res.getString("name"),
                            res.getString("category"),
                            res.getDouble("price"),
                            res.getInt("stock_quantity"),
                            prod_supplier
                    );
                    supplier_products.add(supplier_prod);
                }
                getSupplierProducts.close();
                res.close();
                return supplier_products;
            }
            else {
                return supplier_products; // connection err.
            }
        }
        catch (SQLException e) {
            System.err.println("Database connection err: " + e.getMessage());
            e.printStackTrace();
            return supplier_products;
        }
    }

    public boolean checkSupplierProds(Supplier supplier)
    {
        String check_products_query = """
                    SELECT EXISTS (
                        SELECT 1
                        FROM products
                        WHERE supplier_id = ?::uuid
                    );
                """;

        try (Connection conn = Server.getConnection()) {
            if (conn != null) {
                PreparedStatement checkProductStatement = conn.prepareStatement(check_products_query);
                checkProductStatement.setString(1, supplier.getSupplierId());
                ResultSet res = checkProductStatement.executeQuery();

                if (res.next()) {
                    Boolean bool = res.getBoolean(1);
                    System.out.println("bool: " + bool);
                    return bool; // result's column index where a boolean is the result.
                };
                return true;
            }
            else {
                return true;
            }
        }
        catch (SQLException e) {
            System.err.println("Database connection err: " + e.getMessage());
            e.printStackTrace();
            return true;
        }
    }
}
