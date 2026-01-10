package com.inventory.ui.dashboard.supplier_management;

import com.inventory.controller.SupplierController;
import com.inventory.domain.Supplier;
import com.inventory.ui.dashboard.product_management.ProductLifecycleTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SupplierLifecycleTest {

    private SupplierController supplierController = new SupplierController();
    private Supplier curr_supplier;

    public void supplierTest() {
        // CREATING SUPPLIER
        Supplier supplier1 = new Supplier("supplier-test", "10000");
        String res = supplierController.createSupplier(supplier1);
        curr_supplier = supplier1;
        assertEquals("200", res, "Failed to create supplier");

        // READING SUPPLIER
        String id = curr_supplier.getSupplierId();
        String name = curr_supplier.getSupplierName();
        String contact_info = curr_supplier.getSupplierContactInfo();

        assertEquals("supplier-test", name, "Invalid supplier name");
        assertEquals("10000", contact_info, "Invalid supplier contact info");

        // checking if id is not null
        assertNotNull(id);
        assertFalse(id.trim().isEmpty(), "Invalid Supplier id");

        // UPDATING SUPPLIER
        String newName = "supplier-test-updated";
        String newContactInfo = "20000";

        curr_supplier.updateSupplierDetails(newName, newContactInfo);
        String res2 = supplierController.updateSupplierDetails(curr_supplier);
        assertEquals("200", res2, "Failed to update supplier");


        // PRODUCT CHECKING
        ProductLifecycleTest productLifecycleTest = new ProductLifecycleTest(curr_supplier);
        productLifecycleTest.testProduct();


        // DELETE SUPPLIER
        String res3 = supplierController.deleteSupplier(curr_supplier);
        assertEquals("200", res3, "Failed to delete supplier");
    }

}
