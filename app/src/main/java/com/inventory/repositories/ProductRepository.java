package com.inventory.repositories;

import com.inventory.server.Server;
import com.inventory.domain.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository {

    public List<Product> fetchAllProducts()
    {
        List<Product> all_products = new ArrayList<>();
        String fetch_products_query = """
                    SELECT p.id, p.product_id, p.name, p.category, p.price,\s
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
                                res.getString("id"),
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

    public int updateProductDetails(String id, int product_id, String name, String category, double price,
                                    int stock_quantity)
    {
        String update_product_stock_query =
                """
                    UPDATE products SET product_id=?, name=?, category=?, price=?, stock_quantity=? 
                    WHERE id=?
                """;

        try (Connection conn = Server.getConnection()) {
            if (conn != null) {
                PreparedStatement updateStatement = conn.prepareStatement(update_product_stock_query);
                updateStatement.setInt(1, product_id);
                updateStatement.setString(2, name);
                updateStatement.setString(3, category);
                updateStatement.setDouble(4, price);
                updateStatement.setInt(5, stock_quantity);
                updateStatement.setString(6, id);

                ResultSet res = updateStatement.executeQuery();

                if (res.next()) {
                    System.out.println("res update products: " + res.next());
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

    public int createProduct(int product_id, String name, String category,
                             double price, int stock_quantity)
    {
        String create_product_query =
                """
                    INSERT INTO products (product_id, name, category, price, stock_category)
                    VALUES (?, ?, ?, ?, ?)
                """;

        try (Connection conn = Server.getConnection()) {
            if (conn != null) {
                PreparedStatement createProdStatement = conn.prepareStatement(create_product_query);
                createProdStatement.setInt(1, product_id);
                createProdStatement.setString(2, name);
                createProdStatement.setString(3, category);
                createProdStatement.setDouble(4, price);
                createProdStatement.setInt(5, stock_quantity);

                ResultSet res = createProdStatement.executeQuery();

                if (res.next()) {
                    System.out.println("res create product: " + res.next());
                    createProdStatement.close();
                    res.close();
                    return 200;
                }
                else {
                    createProdStatement.close();
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

    public int deleteProduct(String id) {
        String delete_product_query =
                """
                    DELETE FROM products WHERE id=?
                """;

        try (Connection conn = Server.getConnection()) {
            if (conn != null) {
                PreparedStatement deleteProdStatement = conn.prepareStatement(delete_product_query);
                deleteProdStatement.setString(1, id);

                ResultSet res = deleteProdStatement.executeQuery();

                if (res.next()) {
                    System.out.println("res create product: " + res.next());
                    deleteProdStatement.close();
                    res.close();
                    return 200;
                }
                else {
                    deleteProdStatement.close();
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
