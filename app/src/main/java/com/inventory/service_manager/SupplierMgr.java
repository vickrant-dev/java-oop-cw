package com.inventory.service_manager;

import com.inventory.domain.Product;
import com.inventory.domain.Supplier;
import com.inventory.repositories.ProductRepository;
import com.inventory.repositories.SupplierRepository;
import com.inventory.service.SupplierService;
import com.inventory.utils.handleValidateFields;

import java.util.ArrayList;
import java.util.List;

public class SupplierMgr implements SupplierService {

    private final SupplierRepository supplierRepo;

    public SupplierMgr() {
        this.supplierRepo = new SupplierRepository();
    }

    public String createSupplier(Supplier supplier)
    {
        int update_res = supplierRepo.createSupplier(supplier);
        if (update_res == 200) {
            System.out.println("created supplier successfully for: " + supplier.getSupplierName());
            return "200";
        }
        else {
            System.out.println("Product updating failed for: " + supplier.getSupplierName() + ". Error: " + update_res);
            return "401a";
        }
    }

    public List<Supplier> fetchAllSuppliers() {
        return supplierRepo.fetchAllSuppliers();
    }

    public String updateSupplierDetails(Supplier supplier)
    {
        int update_res = supplierRepo.updateSupplierDetails(supplier);
        if (update_res == 200) {
            System.out.println("Supplier updated successfully for: " + supplier.getSupplierId());
            return "200";
        }
        else {
            System.out.println("Supplier updated failed for: " + supplier.getSupplierId() + ". Error: " + update_res);
            return "401a";
        }
    }

    public String deleteSupplier(Supplier supplier)
    {
        int update_res = supplierRepo.deleteSupplier(supplier);
        if (update_res == 200) {
            System.out.println("Deleted supplier successfully for: " + supplier.getSupplierId());
            return "200";
        }
        else {
            System.out.println("Supplier deletion failed for: " + supplier.getSupplierId() + ". Error: " + update_res);
            return "401a";
        }
    }

    public List<Product> getSupplierProducts(Supplier supplier) {
        return supplierRepo.getSupplierProducts(supplier);
    }

    public boolean checkSupplierProds(Supplier supplier) {
        return supplierRepo.checkSupplierProds(supplier);
    }

}
