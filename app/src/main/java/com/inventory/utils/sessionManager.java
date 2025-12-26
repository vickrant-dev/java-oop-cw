package com.inventory.utils;

// We use this to check if the user can perform certain tasks (role based access)
public class sessionManager {
    private String user_role = "";

    public sessionManager(String user_role) {
        this.user_role = user_role;
    }
}
