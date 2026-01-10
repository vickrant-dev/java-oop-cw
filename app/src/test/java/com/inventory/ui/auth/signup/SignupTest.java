package com.inventory.ui.auth.signup;

import com.inventory.utils.handleAuth;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SignupTest {

    public void testSignup(String username, String email, String password) {
        handleAuth signup = new handleAuth (username, email, password.toCharArray());
        int signup_status = signup.signupUser();
        assertEquals(200, signup_status, "Failed to signup");
    }

}
