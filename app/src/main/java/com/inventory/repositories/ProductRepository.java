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
