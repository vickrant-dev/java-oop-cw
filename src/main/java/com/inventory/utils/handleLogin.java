package com.inventory.utils;

public class handleLogin {
    
    String username;
    String password;

    public handleLogin(String username, char[] password) {
        this.username = username;
        this.password = new String(password); // converting char arr to a string
    }

    public boolean loginUser () {
        if (username.equals("abc") & password.equals("123")) {
            return true;
        }
        else {
            return false;
        }
    }

}
