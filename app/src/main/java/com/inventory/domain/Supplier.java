package com.inventory.domain;

import com.inventory.controller.SupplierController;
import com.inventory.utils.handleValidateFields;

public class Supplier {
    private int id;
    private String name;
    private String contact_info;

    // use this to create new suppliers from dashboard.
    public Supplier(String name, String contact_info) {
        this.name = name;
        this.contact_info = contact_info;
    }

    // used when retrieving or updating existing suppliers
    public Supplier(int id, String name, String contact_info){
        this.id = id;
        this.name = name;
        this.contact_info = contact_info;
    }

    // getters
    public int getSupplierId() { return id; }
    public String getSupplierName() { return name; }
    public String getSupplierContactInfo() { return contact_info; }

    // setters (updaters basically)
    public String updateSupplier(String name, String contact_info) {
        this.name = name;
        this.contact_info = contact_info;
        return updateSupplierDetails();
    }
    public String addSupplier() {
        return createSupplier();
    }

    // overriding the default toString method of an Object.
    // Typically, the default Object comes with toString that returns like this:
    // -> return getClass().getName() + "@" + Integer.toHexString(hashCode());
    // Since that is not readable, we override to return it as a string that is readable and usable.
    public String toString() {
        return name;
    }

    private String updateSupplierDetails() {
        if(new handleValidateFields().validateString(this.name, this.contact_info) == null) {
            int update_res = new SupplierController().updateSupplierDetails(this.id, this.name,
                    this.contact_info);
            if (update_res == 200) {
                System.out.println("Updated Supplier successfully for: " + this.id);
            }
            else {
                System.out.println("Supplier updating failed for: " + this.id + ". Error: " + update_res);
            }
            return "200";
        }
        else {
            return "401a";
        }
    }

    private String createSupplier() {
        if(new handleValidateFields().validateString(this.name, this.contact_info) == null) {
            int update_res = new SupplierController().updateSupplierDetails(this.id, this.name,
                    this.contact_info);
            if (update_res == 200) {
                System.out.println("created a new Supplier successfully for: " + this.id);
            }
            else {
                System.out.println("Supplier updating failed for: " + this.id + ". Error: " + update_res);
            }
            return "200";
        }
        else {
            return "401a";
        }
    }
}
