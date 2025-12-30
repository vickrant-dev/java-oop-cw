package com.inventory.service;

import com.inventory.domain.Product;
import com.inventory.domain.Supplier;

import java.util.List;

public interface SupplierService {
    String createSupplier(Supplier supplier);

    List<Supplier> fetchAllSuppliers();

    String updateSupplierDetails(Supplier supplier);

    String deleteSupplier(Supplier supplier);

    List<Product> getSupplierProducts(Supplier supplier);
}
