package com.inventory.domain;

import com.inventory.controller.SupplierController;
import com.inventory.utils.handleValidateFields;

public class Supplier extends Person {

    // use this to create new suppliers from dashboard.
    public Supplier(String name, String contact_info) {
        this.name = name;
        this.contact_info = contact_info;
    }

    // used when retrieving or updating existing suppliers
    public Supplier(String id, String name, String contact_info){
        this.id = id;
        this.name = name;
        this.contact_info = contact_info;
    }


    // GETTERS
    public String getSupplierId() {
        return id;
    }
    public String getSupplierName() {
        return name;
    }
    public String getSupplierContactInfo() {
        return contact_info;
    }

    // overriding the default toString method of an Object.
    // Typically, the default Object comes with toString that returns like this:
    // -> return getClass().getName() + "@" + Integer.toHexString(hashCode());
    // Since that is not readable, we override to return it as a string that is readable and usable.
    public String toString() {
        return name;
    }
}
