package com.inventory.controller;

import com.inventory.repositories.AuthRepository;

public class AuthController {
    private final AuthRepository authRepo;

    public AuthController() {
        this.authRepo = new AuthRepository();
    }

    public int LoginUser(String username, String password) {
        int loginRes = authRepo.Login(username, password);
        return loginRes;
    }

    public int SignupUser(String username, String email, String password) {
        int signupRes = authRepo.Signup(username, email, password);
        return signupRes;
    }
}
