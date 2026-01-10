package com.inventory.utils;

import com.inventory.controller.AuthController;

public class handleAuth {

    AuthController authController = new AuthController();

    String username;
    String password;
    String email;

    // For signup
    public handleAuth(String username, String email, char[] password) {
        this.username = username;
        this.password = new String(password); // converting char arr to a string
        this.email = email;
    }

    // For login
    public handleAuth(String username, char[] password) {
        this.username = username;
        this.password = new String(password); // converting char arr to a string
    }

    // Call controller
    public int loginUser () {
        return authController.LoginUser(username, password);
    }

    public int signupUser() {
        return authController.SignupUser(username, email, password);
    }

    public int deleteUser() {
        return authController.DeleteUser(email, password);
    }

}
