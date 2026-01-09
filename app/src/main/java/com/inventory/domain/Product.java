package com.inventory.domain;

import com.inventory.controller.ProductController;
import com.inventory.utils.handleValidateFields;

public class Product {
    private String id;
    private String product_id;
    private String name;
    private String category;
    private double price;
    private int stock_quantity;
    private Supplier supplier;

    // used to create new products.
    public Product(String product_id, String name, String category, double price,
                   int stock_quantity, Supplier supplier)
    {
        this.product_id = product_id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.stock_quantity = stock_quantity;
        this.supplier = supplier;
    }

    // used to retrieve products from the db.
    public Product(String id, String product_id, String name, String category, double price,
                   int stock_quantity, Supplier supplier)
    {
        this.id = id;
        this.product_id = product_id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.stock_quantity = stock_quantity;
        this.supplier = supplier;
    }

    // GETTERS
    public String getId() {
        return id;
    }
    public String getProductId() { return product_id; }
    public String getProductName() { return name; }
    public String getProductCategory() { return category; }
    public double getProductPrice() { return price; }
    public int getProductStockQuantity() { return stock_quantity; }
    public String getSupplierName() { return supplier.getSupplierName(); }
    public String getSupplierId() { return supplier.getSupplierId(); }
    public String getSupplierContactInfo() { return supplier.getSupplierContactInfo(); }

    // SETTERS
    // used when updating an existing product
    public void updateDetails(String product_id, String name, String category,
                                    double price, int stock_quantity)
    {
        this.product_id = product_id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.stock_quantity = stock_quantity;
    }

    public void setId(String id) {
        this.id = id;
    }
}
