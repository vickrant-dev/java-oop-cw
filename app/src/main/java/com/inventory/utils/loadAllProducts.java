package com.inventory.utils;

import com.inventory.controller.ProductController;
import com.inventory.ui.dashboard.product_management.Product;

import java.util.ArrayList;
import java.util.List;

public class loadAllProducts {
    public List<Object[]> getAllProductRows() {
        List<Product> all_products = new ProductController().fetchAllProducts();
        List<Object[]> table_data = new ArrayList<>();
        for (Product prod: all_products) {
            // What are anonymous objects
            // An anonymous object is an instance created without assigning it to a reference variable.
            // They are typically used for "one-off" tasks where you don't need to refer
            // to that object ever again.

            // addRow requires an Object array (Object[]) where each index represents a column
            // we use an anonymous array initialization by doing -> new Object[] that has no name,
            // created and destroyed almost instantly.
            // the object is built with the exact no of columns mentioned inside the "{}"
            // values are pulled from the db on runtime and is dynamically inserted.
            table_data.add(new Object[] {
                    prod.getProductId(),
                    prod.getProductName(),
                    prod.getProductCategory(),
                    prod.getProductPrice(),
                    prod.getProductStockQuantity(),
                    prod.getProductSupplierId()
            });
        }
        return table_data;
    }
}
