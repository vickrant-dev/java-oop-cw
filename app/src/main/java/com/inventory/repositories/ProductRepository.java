package com.inventory.repositories;

import com.inventory.server.Server;
import com.inventory.ui.dashboard.product_management.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository {

    public List<Product> fetchAllProducts() {
        List<Product> all_products = new ArrayList<>();
        String fetch_products_query = """
                    SELECT p.product_id, p.name, p.category, p.price,\s
                    p.stock_quantity, s.name AS supplier_name
                    FROM products p
                    LEFT JOIN suppliers s ON p.supplier_id = s.supplier_id
                """;

        try (Connection conn = Server.getConnection()) {
            if (conn != null) {
                PreparedStatement fetchProductsStatement = conn.prepareStatement(fetch_products_query);
                ResultSet res = fetchProductsStatement.executeQuery();
                    while (res.next()) {
                        Product prod = new Product(
                                res.getInt("product_id"),
                                res.getString("name"),
                                res.getString("category"),
                                res.getDouble("price"),
                                res.getInt("stock_quantity"),
                                res.getString("supplier_name")
                        );
                        all_products.add(prod);
                    }
                    fetchProductsStatement.close();
                    res.close();
                    return all_products;
            }
            else {
                return all_products; // connection err.
            }
        }
        catch (SQLException e) {
            System.err.println("Database connection err: " + e.getMessage());
            e.printStackTrace();
            return all_products;
        }
    }

    public int updateProductStock(int product_id, int new_stock) {
        String update_product_stock_query = "UPDATE products SET stock_quantity=? WHERE product_id=?";

        try (Connection conn = Server.getConnection()) {
            if (conn != null) {
                PreparedStatement updateStatement = conn.prepareStatement(update_product_stock_query);
                updateStatement.setInt(1, new_stock);
                updateStatement.setInt(2, product_id);

                ResultSet res = updateStatement.executeQuery();

                if (res.next()) {
                    System.out.println("res update stock: " + res.next());
                    updateStatement.close();
                    res.close();
                    return 200;
                }
                else {
                    updateStatement.close();
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

    public int updateProductName(int product_id, String product_name) {
        String update_product_stock_query = "UPDATE products SET name=? WHERE product_id=?";

        try (Connection conn = Server.getConnection()) {
            if (conn != null) {
                PreparedStatement updateStatement = conn.prepareStatement(update_product_stock_query);
                updateStatement.setString(1, product_name);
                updateStatement.setInt(2, product_id);

                ResultSet res = updateStatement.executeQuery();

                if (res.next()) {
                    System.out.println("res update stock: " + res.next());
                    updateStatement.close();
                    res.close();
                    return 200;
                }
                else {
                    updateStatement.close();
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

    public int updateProductCategory(int product_id, String product_category) {
        String update_product_stock_query = "UPDATE products SET category=? WHERE product_id=?";

        try (Connection conn = Server.getConnection()) {
            if (conn != null) {
                PreparedStatement updateStatement = conn.prepareStatement(update_product_stock_query);
                updateStatement.setString(1, product_category);
                updateStatement.setInt(2, product_id);

                ResultSet res = updateStatement.executeQuery();

                if (res.next()) {
                    System.out.println("res update category: " + res.next());
                    updateStatement.close();
                    res.close();
                    return 200;
                }
                else {
                    updateStatement.close();
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
