package com.inventory.controller;

import com.inventory.repositories.AuthRepository;

public class AuthController {
    private final AuthRepository authRepo;

    public AuthController() {
        this.authRepo = new AuthRepository();
    }

    public int LoginUser(String username, String password) {
        return authRepo.Login(username, password);
    }

    public int SignupUser(String username, String email, String password) {
        return authRepo.Signup(username, email, password);
    }
}
