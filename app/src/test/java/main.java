package com.inventory;

import com.inventory.ui.auth.login.LoginTest;
import com.inventory.ui.auth.signup.SignupTest;
import com.inventory.ui.dashboard.supplier_management.SupplierLifecycleTest;
import com.inventory.utils.*;

import java.sql.Connection; // used to execute sql queries.
import java.sql.DriverManager; // establishes db connections.
import java.sql.SQLException; // an exception that is thrown when smthin goes wrong when accessing db

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class main {

    @Test
    public void testAll() {
        // start signup
        SignupTest signupTest = new SignupTest();
        signupTest.testSignup("test-username", "test-email@test.com",
                "testing-signup");

        // login
        LoginTest loginTest = new LoginTest();
        loginTest.testLogin("test-username", "testing-signup");

        // supplier, product, customer, transaction - CRUD operations
        SupplierLifecycleTest supplierLifecycleTest = new SupplierLifecycleTest();
        supplierLifecycleTest.supplierTest();

        // Delete user
        handleAuth deleteTest = new handleAuth("test-username",
                "test-email@test.com", "testing-signup".toCharArray());
        int delete_status = deleteTest.deleteUser();
        assertEquals(200, delete_status, "Failed to delete user");
    }
}