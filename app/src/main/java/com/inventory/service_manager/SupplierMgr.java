package com.inventory.service_manager;

import com.inventory.domain.Product;
import com.inventory.domain.Supplier;
import com.inventory.repositories.ProductRepository;
import com.inventory.repositories.SupplierRepository;
import com.inventory.service.SupplierService;
import com.inventory.utils.handleValidateFields;

import java.util.List;

public class SupplierMgr implements SupplierService {

    private final SupplierRepository supplierRepo;

    public SupplierMgr() {
        this.supplierRepo = new SupplierRepository();
    }

    public String createSupplier(Supplier supplier)
    {
        if(new handleValidateFields().validateFields(supplier) == null)
        {
            int update_res = supplierRepo.createSupplier(supplier);
            if (update_res == 200) {
                System.out.println("created supplier successfully for: " + supplier.getSupplierName());
            }
            else {
                System.out.println("Product updating failed for: " + supplier.getSupplierName() + ". Error: " + update_res);
            }
            return "200";
        }
        else {
            return "401a";
        }
    }

    public List<Supplier> fetchAllSuppliers() {
        return supplierRepo.fetchAllSuppliers();
    }

    public String updateSupplierDetails(Supplier supplier)
    {
        if(new handleValidateFields().validateFields(supplier) == null) {
            int update_res = supplierRepo.updateSupplierDetails(supplier);
            if (update_res == 200) {
                System.out.println("Supplier updated successfully for: " + supplier.getSupplierId());
            }
            else {
                System.out.println("Supplier updated failed for: " + supplier.getSupplierId() + ". Error: " + update_res);
            }
            return "200";
        }
        else {
            return "401a";
        }
    }

    public String deleteSupplier(Supplier supplier)
    {
        if(new handleValidateFields().validateFields(supplier) == null) {
            int update_res = supplierRepo.deleteSupplier(supplier);
            if (update_res == 200) {
                System.out.println("Deleted Product successfully for: " + supplier.getSupplierId());
            }
            else {
                System.out.println("Product deletion failed for: " + supplier.getSupplierId() + ". Error: " + update_res);
            }
            return "200";
        }
        else {
            return "401a";
        }
    }

}
