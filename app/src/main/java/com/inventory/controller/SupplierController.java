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

    public int updateSupplierName(int id, String new_name) {
        return supplierRepo.updateSupplierName(id, new_name);
    }

    public int updateSupplierContact(int id, String contact_info) {
        return supplierRepo.updateSupplierContact(id, contact_info);
    }
}
