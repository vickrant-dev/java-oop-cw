package com.inventory.utils;

import com.inventory.controller.SupplierController;
import com.inventory.domain.Supplier;

import java.util.List;

public class loadAllSuppliers {
    public List<Supplier> getSupplierList() {
        List<Supplier> all_suppliers = new SupplierController().getAllSuppliers();
        return all_suppliers;
    }
}
