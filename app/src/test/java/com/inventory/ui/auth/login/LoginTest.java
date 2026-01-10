package com.inventory.ui.auth.login;

import com.inventory.utils.handleAuth;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginTest {

    public void testLogin(String username, String password) {
        handleAuth login = new handleAuth(username, password.toCharArray());
        int login_status = login.loginUser();
        assertEquals(200, login_status, "Failed to login");
    }


}
