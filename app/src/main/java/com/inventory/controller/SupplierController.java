package com.inventory.controller;

import com.inventory.repositories.ProductRepository;
import com.inventory.repositories.SupplierRepository;
import com.inventory.ui.dashboard.product_management.Product;
import com.inventory.ui.dashboard.supplier_management.Supplier;

import java.util.List;

public class SupplierController {
    private final SupplierRepository supplierRepo;

    public SupplierController() {
        this.supplierRepo = new SupplierRepository();
    }

    public List<Supplier> getAllSuppliers() {
        return supplierRepo.getAllSuppliers();
    }
}
