package com.inventory.ui.dashboard.product_management;

import com.inventory.controller.ProductController;
import com.inventory.utils.handleValidateFields;

public class Product {
    private int id;
    private String name;
    private String category;
    private double price;
    private int stock_quantity;
    private String supplier_name;

    public Product(int id, String name, String category, double price, int stock_quantity, String supplier_name){
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.stock_quantity = stock_quantity;
        this.supplier_name = supplier_name;
    }

    // getters
    public int getProductId() { return id; }
    public String getProductName() { return name; }
    public String getProductCategory() { return category; }
    public double getProductPrice() { return price; }
    public int getProductStockQuantity() { return stock_quantity; }
    public String getProductSupplierId() { return supplier_name; }

    // setters
    public void setProductName(String name) {
        this.name = name;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public void setStockQuantity(int stock_quantity) {
        this.stock_quantity = stock_quantity;
    }
    public void setSupplierId(String supplier_name) {
        this.supplier_name = supplier_name;
    }

    // reduce stock when customer purchases
    public int reduceStock(int quantity) {
        if (quantity < 0 || Math.abs(quantity) > stock_quantity) {
            return 6001; // can not be negative or greater than available stock
        }
        this.stock_quantity -= quantity;

        // Update Database
        int update_res = new ProductController().updateProductStock(this.id, this.stock_quantity);
        if (update_res == 200) {
            System.out.println("Updated Product successfully for: " + this.id);
        }
        else {
            System.out.println("Product updating failed for: " + this.id + ". Error: " + update_res);
        }
        return 200;
    }

    // increment stock when supplier supplies new stock
    public int incrementStock(int quantity) {
        if (quantity < 0) {
            return 6002; // can not be less than 0
        }
        this.stock_quantity += quantity;

        // Update Database
        int update_res = new ProductController().updateProductStock(this.id, this.stock_quantity);
        if (update_res == 200) {
            System.out.println("Updated Product successfully for: " + this.id);
        }
        else {
            System.out.println("Product updating failed for: " + this.id + ". Error: " + update_res);
        }

        return 200;
    }

    public String updateProductName(String name) {
        if(new handleValidateFields().validateString(name) == null) {
            int update_res = new ProductController().updateProductName(this.id, this.name);
            if (update_res == 200) {
                System.out.println("Updated Product successfully for: " + this.id);
            }
            else {
                System.out.println("Product updating failed for: " + this.id + ". Error: " + update_res);
            }
            return "200";
        }
        else {
            return "401a";
        }
    }

    public String updateProductCategory(String category) {
        if(new handleValidateFields().validateString(category) == null) {
            int update_res = new ProductController().updateProductCategory(this.id, this.category);
            if (update_res == 200) {
                System.out.println("Updated Product successfully for: " + this.id);
            }
            else {
                System.out.println("Product updating failed for: " + this.id + ". Error: " + update_res);
            }
            return "200";
        }
        else {
            return "401a";
        }
    }

    // can be overridden if needed when recording transactions for customers
    public double applyProductDiscount() {
        return price * 0.15;
    }

}
