package com.inventory.ui.dashboard.product_management;

import com.inventory.controller.ProductController;
import com.inventory.domain.Product;
import com.inventory.domain.Supplier;
import com.inventory.ui.dashboard.customer_management.CustomerLifecycleTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProductLifecycleTest {
    private ProductController productController = new ProductController();
    private Product curr_product;
    private Supplier supplier;

    public ProductLifecycleTest(Supplier supplier) {
        this.supplier = supplier;
    }

    public void testProduct() {
        // CREATING PRODUCT
        Product product = new Product("test-1000", "test-prod",
                "test-cat", 1000.50, 1, supplier);
        String res = productController.createProduct(product);
        curr_product = product;
        assertEquals("200", res, "Failed to create product");


        // READING PRODUCT
        String id = product.getId();
        String product_id = product.getProductId();
        String name = product.getProductName();
        String category = product.getProductCategory();
        double price = product.getProductPrice();
        int stock_quantity = product.getProductStockQuantity();

        assertNotNull(id);
        assertFalse(id.trim().isEmpty(), "Empty or invalid product id");
        assertEquals("test-1000", product_id, "Invalid product id");
        assertEquals("test-prod", name, "Invalid product name");
        assertEquals("test-cat", category, "Invalid product category");
        assertEquals(1000.50, price, "Invalid price");
        assertEquals(1, stock_quantity, "Invalid stock quantity");

        assertNotNull(supplier, "Invalid or empty supplier");


        // UPDATING PRODUCT
        String new_product_id = "test-2000";
        String new_product_name = "test-prod-1";
        String new_product_category = "test-cat-1";
        double new_product_price = 2000.00;
        int new_product_stock = 2;

        curr_product.updateDetails(new_product_id, new_product_name, new_product_category,
                new_product_price, new_product_stock);

        String res_2 = productController.updateProductDetails(curr_product);

        assertEquals("200", res_2, "Failed to update product");


        // CUSTOMER TESTING
        CustomerLifecycleTest customerLifecycleTest = new CustomerLifecycleTest();
        customerLifecycleTest.testCustomer(curr_product);


        // DELETING PRODUCT
        String res_3 = productController.deleteProduct(curr_product);
        assertEquals("200", res_3, "Failed to delete product");

    }
}
