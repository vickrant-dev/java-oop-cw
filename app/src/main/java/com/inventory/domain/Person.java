package com.inventory.domain;

abstract class Person {
    protected String id;
    protected String name;
    protected String contact_info;

    protected String getId() {
        return this.id;
    }
    protected String getName() {
        return this.name;
    }
    protected String getContactInfo() {
        return this.contact_info;
    }
}
