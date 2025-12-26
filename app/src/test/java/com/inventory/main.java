// Initializing connection with the local sqlite db

package com.inventory;

import com.inventory.utils.*;

import java.sql.Connection; // used to execute sql queries.
import java.sql.DriverManager; // establishes db connections.
import java.sql.SQLException; // an exception that is thrown when smthin goes wrong when accessing db

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class main {

    @Test
    void sampleTest() {
        assertEquals(2, 1 + 1);
    }

    public static void main(String[] args) {
        try (Connection conn = DBConnection.getConnection()) {
            if (conn != null) {
                System.out.println("Connection successful!");
            } else {
                System.out.println("Connection failed!");
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to the database:");
            e.printStackTrace();
        }
    }

}