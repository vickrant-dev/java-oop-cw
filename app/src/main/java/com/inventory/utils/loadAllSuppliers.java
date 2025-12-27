package com.inventory.utils;

import com.inventory.controller.ProductController;
import com.inventory.controller.SupplierController;
import com.inventory.ui.dashboard.product_management.Product;
import com.inventory.ui.dashboard.supplier_management.Supplier;

import java.util.ArrayList;
import java.util.List;

public class loadAllSuppliers {
    public List<Supplier> getSupplierList() {
        List<Supplier> all_suppliers = new SupplierController().getAllSuppliers();
        return all_suppliers;
    }
}
