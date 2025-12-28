package com.inventory.utils;

// We use this to check if the user can perform certain tasks (role based access)
public class sessionManager {
    private String user_id = "";
    private String user_role = "";

    // retrieving session data
    public sessionManager() {}

    // creating sessions upon login
    public sessionManager(String user_role, String user_id) {
        this.user_role = user_role;
        this.user_id = user_id;
    }

    public String getUserId() {
        return user_id;
    }
    public String user_role() {
        return user_role;
    }
}
