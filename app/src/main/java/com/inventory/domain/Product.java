package com.inventory.domain;

import com.inventory.controller.ProductController;
import com.inventory.utils.handleValidateFields;

public class Product {
    private String id;
    private int product_id;
    private String name;
    private String category;
    private double price;
    private int stock_quantity;
    private String supplier_name;

    // used to create new products.
    public Product(int product_id, String name, String category, double price,
                   int stock_quantity, String supplier_name)
    {
        this.product_id = product_id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.stock_quantity = stock_quantity;
        this.supplier_name = supplier_name;
    }

    // used to retrieve products from the db.
    public Product(String id, int product_id, String name, String category, double price,
                   int stock_quantity, String supplier_name)
    {
        this.id = id;
        this.product_id = product_id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.stock_quantity = stock_quantity;
        this.supplier_name = supplier_name;
    }

    // GETTERS
    public String getId() {
        return id;
    }
    public int getProductId() { return product_id; }
    public String getProductName() { return name; }
    public String getProductCategory() { return category; }
    public double getProductPrice() { return price; }
    public int getProductStockQuantity() { return stock_quantity; }
    public String getSupplierName() { return supplier_name; }

    // SETTERS
    public void updateDetails(int product_id, String name, String category,
                                    double price, int stock_quantity)
    {
        this.product_id = product_id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.stock_quantity = stock_quantity;
    }
}
