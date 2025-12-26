// Initializing connection with the local sqlite db

package com.inventory.utils;

import java.sql.Connection; // used to execute sql queries.
import java.sql.DriverManager; // establishes db connections.
import java.sql.SQLException; // an exception that is thrown when something goes wrong when accessing db

public class DBConnection {

    // using final to prevent overriding
    private static final String URL = "jdbc:postgresql://aws-1-ap-south-1.pooler.supabase.com:5432/postgres?sslmode=require";

    public static Connection getConnection() throws SQLException {
        String user = "postgres.mtsbkpmwirijtppkhvqn";
        String password = "2MuHbK02kr7X6GO4";
        return DriverManager.getConnection(URL, user, password); // this throws a sql exception if anything goes wrong
    }

}