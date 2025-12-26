package com.inventory.controller;

import com.inventory.repositories.AuthRepository;

public class AuthController {
    private final AuthRepository authRepo;

    public AuthController() {
        this.authRepo = new AuthRepository();
    }

    public int LoginUser(String username, String password) {
        int loginRes = authRepo.Login(username, password);
        if (loginRes == 200) {
            System.out.println("Login successfully");
            return 200;
        }
        else {
            System.out.println("Login failed");
            return 401;
        }
    }

    public int SignupUser(String username, String email, String password) {
        int signupRes = authRepo.Signup(username, email, password);
        if (signupRes == 200) {
            System.out.println("Signup successfully");
            return 200;
        }
        else {
            System.out.println("Signup failed");
            return 401;
        }
    }
}
