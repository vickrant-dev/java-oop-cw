package com.inventory.controller;

import com.inventory.domain.Supplier;
import com.inventory.service.SupplierService;
import com.inventory.service_manager.SupplierMgr;

import java.util.List;

public class SupplierController {
    private final SupplierService supplierService;

    public SupplierController() {
        this.supplierService = new SupplierMgr();
    }

    // CRUD ops
    public List<Supplier> getAllSuppliers() {
        return supplierService.fetchAllSuppliers();
    }

    public String updateSupplierDetails(Supplier supplier)
    {
        return supplierService.updateSupplierDetails(supplier);
    }

    public String createSupplier(Supplier supplier) {
        return supplierService.createSupplier(supplier);
    }

    public String deleteSupplier(Supplier supplier) {
        return supplierService.deleteSupplier(supplier);
    }

}
