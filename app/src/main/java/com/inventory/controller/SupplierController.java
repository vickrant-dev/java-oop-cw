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

    public int updateSupplierDetails(String id, String name, String contact_info) {
        return supplierRepo.updateSupplierDetails(id, name, contact_info);
    }

    public int createSupplier(String name, String contact_info) {
        return supplierRepo.createSupplier(name, contact_info);
    }

    public int deleteSupplier(String id) {
        return supplierRepo.deleteSupplier(id);
    }
}
