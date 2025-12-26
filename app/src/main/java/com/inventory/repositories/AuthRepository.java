package com.inventory.repositories;

import com.inventory.server.Server;
import com.inventory.utils.Encryptor;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthRepository {
    public int Login(String username, String password) {
        String user_check_query = "SELECT * FROM users where username = ?";
        String user_login_query = "SELECT * FROM users where username = ? AND password = ?";

        String encryptedPassword = "";
        // We put this in a try catch to prevent "NoSuchAlgorithmException" errors
        try {
            Encryptor encryptor = new Encryptor();
            encryptedPassword = encryptor.encrypt(password);
            System.out.println("encrypted password: " + encryptedPassword);
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        try (Connection loginConn = Server.getConnection()) {
            if (loginConn != null) {
                // check if username exists
                PreparedStatement statement1 = loginConn.prepareStatement(user_check_query);
                statement1.setString(1, username);
                ResultSet isUser = statement1.executeQuery();

                if (!sUser) {
                    return 4201; // not yet a user. popup a signup form.
                }
                else {
                    PreparedStatement statement2 = loginConn.prepareStatement(user_login_query);
                    statement2.setString(1, username);
                    statement2.setString(2, encryptedPassword);

                    ResultSet res = statement2.executeQuery();

                    if (res.next()) {
                        System.out.println("Success: " + res.getString("username"));
                        return 200;
                    }
                    else {
                        return 401; // login failed. invalid credentials.
                    }
                }


                // System.out.println("Connection successful!");
            } else {
                System.out.println("Connection failed!");
                return 503;
            }
        }
        catch (SQLException e) {
            System.err.println("Database connection err: " + e.getMessage());
            return 503;
        }

    }
    public int Signup(String username, String email, String password) {
        String query = "INSERT INTO users (username, email, password) VALUES ( ?, ?, ?)";

        String encryptedPassword = "";
        // We put this in a try catch to prevent "NoSuchAlgorithmException" errors
        try {
            Encryptor encryptor = new Encryptor();
            encryptedPassword = encryptor.encrypt(password);
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        try (Connection signupConn = Server.getConnection()) {
            if (signupConn != null) {
                PreparedStatement statement = signupConn.prepareStatement(query);
                statement.setString(1, username);
                statement.setString(1, email);
                statement.setString(2, encryptedPassword);

                // System.out.println("Connection successful!");
                return 200;
            } else {
                System.out.println("Connection failed!");
                return 503;
            }
        }
        catch (SQLException e) {
            System.err.println("Database connection err: " + e.getMessage());
            return 503;
        }
    }
}
