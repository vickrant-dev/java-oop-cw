package com.inventory.ui.dashboard.supplier_management;

import com.inventory.controller.ProductController;
import com.inventory.utils.handleValidateFields;

public class Supplier {
    private int id;
    private String name;
    private String contact_info;

    public Supplier(int id, String name, String contact_info){
        this.id = id;
        this.name = name;
        this.contact_info = contact_info;
    }

    // getters
    public int getSupplierId() { return id; }
    public String getSupplierName() { return name; }
    public String getSupplierContactInfo() { return contact_info; }

    // setters
    public void setSupplierName(String name) {
        this.name = name;
    }
    public void setSupplierContactInfo(String contact_info) {
        this.contact_info = contact_info;
    }

    // overriding the default toString method of an Object.
    // Typically, the default Object comes with toString that returns like this:
    // -> return getClass().getName() + "@" + Integer.toHexString(hashCode());
    // Since that is not readable, we override to return it as a string that is readable and usable.
    public String toString() {
        return name;
    }

//    public String updateSupplierName(String name) {
//        if(new handleValidateFields().validateString(name) == null) {
//            int update_res = new ProductController().updateProductName(this.id, this.name);
//            if (update_res == 200) {
//                System.out.println("Updated Supplier successfully for: " + this.id);
//            }
//            else {
//                System.out.println("Supplier updating failed for: " + this.id + ". Error: " + update_res);
//            }
//            return "200";
//        }
//        else {
//            return "401a";
//        }
//    }

//    public String updateSupplierContact(String contact_info) {
//        if(new handleValidateFields().validateString(contact_info) == null) {
//            int update_res = new ProductController().updateSupplierContact(this.id, this.contact_info);
//            if (update_res == 200) {
//                System.out.println("Updated Supplier successfully for: " + this.id);
//            }
//            else {
//                System.out.println("Supplier updating failed for: " + this.id + ". Error: " + update_res);
//            }
//            return "200";
//        }
//        else {
//            return "401a";
//        }
//    }
}
