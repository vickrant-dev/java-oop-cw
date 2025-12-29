package com.inventory.controller;

import com.inventory.repositories.ProductRepository;
import com.inventory.domain.Product;
import com.inventory.service.ProductService;
import com.inventory.service_manager.ProductMgr;

import java.util.List;

// call the service
public class ProductController {
    private final ProductService productService;

    public ProductController() {
        this.productService = new ProductMgr();
    }

    // CRUD ops
    public List<Product> fetchAllProducts() {
        return productService.fetchAllProducts();
    }

    public String updateProductDetails(Product product) {
        return productService.updateProductDetails(product);
    }

    public String createProduct(Product product) {
        return productService.createProduct(product);
    }

    public String deleteProduct(Product product) {
        return productService.deleteProduct(product);
    }

}
