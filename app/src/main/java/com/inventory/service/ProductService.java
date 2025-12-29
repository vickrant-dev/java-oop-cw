package com.inventory.service;

import com.inventory.domain.Product;

import java.util.List;

// implemented by the manager
public interface ProductService {
    String createProduct(Product product);

    List<Product> fetchAllProducts();

    String updateProductDetails(Product product);

    String deleteProduct(Product product);
}
