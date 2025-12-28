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
                   int stock_quantity, String supplier_name) {
        this.product_id = product_id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.stock_quantity = stock_quantity;
        this.supplier_name = supplier_name;
    }

    // used to retrieve and update existing products in the db.
    public Product(String id, int product_id, String name, String category, double price,
                   int stock_quantity, String supplier_name){
        this.id = id;
        this.product_id = product_id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.stock_quantity = stock_quantity;
        this.supplier_name = supplier_name;
    }

    // GETTERS
    public int getProductId() { return product_id; }
    public String getProductName() { return name; }
    public String getProductCategory() { return category; }
    public double getProductPrice() { return price; }
    public int getProductStockQuantity() { return stock_quantity; }
    public String getProductSupplierId() { return supplier_name; }


    // SETTERS (with updaters)
    public String updateProduct(int product_id, String name, String category,
                                    double price, int stock_quantity)
    {
        this.product_id = product_id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.stock_quantity = stock_quantity;
        return updateProductDetails();
    }

    public String addProduct() {
        return createProduct();
    }

    public String delProduct() {
        return deleteProduct();
    }

    // default discount 15%
    public double applyProductDiscount() {
        return this.price - (this.price * 0.15);
    }

    // overridden when needed with custom discount
    public double applyProductDiscount(int custom_discount) {
        return this.price - (this.price * custom_discount);
    }

    private String createProduct()
    {
        if(new handleValidateFields().validateString(this.product_id, this.name, this.category,
                this.price, this.stock_quantity) == null) {
            int update_res = new ProductController().createProduct(this.product_id,
                    this.name, this.category, this.price, this.stock_quantity);
            if (update_res == 200) {
                System.out.println("Updated Product successfully for: " + this.product_id);
            }
            else {
                System.out.println("Product updating failed for: " + this.product_id + ". Error: " + update_res);
            }
            return "200";
        }
        else {
            return "401a";
        }
    }

    private String updateProductDetails()
    {
        if(new handleValidateFields().validateString(this.product_id, this.name, this.category,
                this.price, this.stock_quantity) == null) {
            int update_res = new ProductController().updateProductDetails(this.id, this.product_id,
                    this.name, this.category, this.price, this.stock_quantity);
            if (update_res == 200) {
                System.out.println("Updated Product successfully for: " + this.product_id);
            }
            else {
                System.out.println("Product updating failed for: " + this.product_id + ". Error: " + update_res);
            }
            return "200";
        }
        else {
            return "401a";
        }
    }

    private String deleteProduct()
    {
        if(new handleValidateFields().validateString(this.id) == null) {
            int update_res = new ProductController().deleteProduct(this.id);
            if (update_res == 200) {
                System.out.println("Deleted Product successfully for: " + this.product_id);
            }
            else {
                System.out.println("Product deletion failed for: " + this.product_id + ". Error: " + update_res);
            }
            return "200";
        }
        else {
            return "401a";
        }
    }

}
