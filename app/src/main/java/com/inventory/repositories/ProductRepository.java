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

    public ProductRepository() {};

    public List<Product> fetchAllProducts()
    {
        List<Product> all_products = new ArrayList<>();
        String fetch_products_query = """
                    SELECT p.id, p.product_id, p.name, p.category, p.price,\s
                    p.stock_quantity, s.name AS supplier_name
                    FROM products p
                    LEFT JOIN suppliers s ON p.supplier_id = s.id
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

    public int updateProductDetails(Product product)
    {
        String update_product_stock_query =
                """
                    UPDATE products SET product_id=?, name=?, category=?, price=?,
                    stock_quantity=? WHERE id=?
                """;

        try (Connection conn = Server.getConnection()) {
            if (conn != null) {
                PreparedStatement updateStatement = conn.prepareStatement(update_product_stock_query);
                updateStatement.setInt(1, product.getProductId());
                updateStatement.setString(2, product.getProductName());
                updateStatement.setString(3, product.getProductCategory());
                updateStatement.setDouble(4, product.getProductPrice());
                updateStatement.setInt(5, product.getProductStockQuantity());
                updateStatement.setString(6, product.getId());

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

    public int createProduct(Product product)
    {
        String create_product_query =
                """
                    INSERT INTO products (product_id, name, category, price, stock_quantity)
                    VALUES (?, ?, ?, ?, ?)
                """;

        try (Connection conn = Server.getConnection()) {
            if (conn != null) {
                PreparedStatement createProdStatement = conn.prepareStatement(create_product_query);
                createProdStatement.setInt(1, product.getProductId());
                createProdStatement.setString(2, product.getProductName());
                createProdStatement.setString(3, product.getProductCategory());
                createProdStatement.setDouble(4, product.getProductPrice());
                createProdStatement.setInt(5, product.getProductStockQuantity());

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

    public int deleteProduct(Product product)
    {
        String delete_product_query =
                """
                    DELETE FROM products WHERE id=?
                """;

        try (Connection conn = Server.getConnection()) {
            if (conn != null) {
                PreparedStatement deleteProdStatement = conn.prepareStatement(delete_product_query);
                deleteProdStatement.setString(1, product.getId());

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
