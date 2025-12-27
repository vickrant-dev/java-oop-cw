package com.inventory.controller;

import com.inventory.repositories.ProductRepository;
import com.inventory.ui.dashboard.product_management.Product;

import java.util.List;

public class ProductController {
    private final ProductRepository productRepo;

    public ProductController() {
        this.productRepo = new ProductRepository();
    }

    public List<Product> fetchAllProducts() {
        return productRepo.fetchAllProducts();
    }

    public int updateProductStock(int product_id, int new_quantity) {
        return productRepo.updateProductStock(product_id, new_quantity);
    }

    public int updateProductName(int product_id, String new_name) {
        return productRepo.updateProductName(product_id, new_name);
    }

    public int updateProductCategory(int product_id, String category) {
        return productRepo.updateProductCategory(product_id, category);
    }
}
