package com.inventory.controller;

import com.inventory.repositories.SupplierRepository;
import com.inventory.domain.Supplier;

import java.util.List;

public class SupplierController {
    private final SupplierRepository supplierRepo;

    public SupplierController() {
        this.supplierRepo = new SupplierRepository();
    }

    public List<Supplier> getAllSuppliers() {
        return supplierRepo.getAllSuppliers();
    }

    public int updateSupplierDetails(int id, String name, String contact_info) {
        return supplierRepo.updateSupplierDetails(id, name, contact_info);
    }
}
