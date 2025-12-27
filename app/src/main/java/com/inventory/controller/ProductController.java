package com.inventory.controller;

import com.inventory.repositories.ProductRepository;
import com.inventory.domain.Product;

import java.util.List;

public class ProductController {
    private final ProductRepository productRepo;

    public ProductController() {
        this.productRepo = new ProductRepository();
    }

    public List<Product> fetchAllProducts() {
        return productRepo.fetchAllProducts();
    }

    public int updateProductDetails(String id, int product_id, String name, String category,
                                    double price, int stock_quantity) {
        return productRepo.updateProductDetails(id, product_id, name, category, price,
                stock_quantity);
    }
}
