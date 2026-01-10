package com.inventory.repositories;

import com.inventory.server.Server;
import com.inventory.utils.Encryptor;
import com.inventory.utils.sessionManager;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthRepository {
    public int Login(String username, String password) {
        String user_check_create_user_query = "SELECT * FROM users where username = ?";
        String user_login_create_user_query = "SELECT * FROM users where username = ? AND password = ?";

        String encryptedPassword = "";
        // We put this in a try catch to prevent "NoSuchAlgorithmException" errors
        try {
            Encryptor encryptor = new Encryptor();
            encryptedPassword = encryptor.encrypt(password);
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return 503;
        }

        try (Connection loginConn = Server.getConnection()) {
            if (loginConn != null) {
                // check if username exists
                PreparedStatement check_username_exists = loginConn.prepareStatement(user_check_create_user_query);
                check_username_exists.setString(1, username);
                ResultSet isUser = check_username_exists.executeQuery(); // checking if username exists

                if (isUser.next()) {
                    PreparedStatement user_login = loginConn.prepareStatement(user_login_create_user_query);
                    user_login.setString(1, username);
                    user_login.setString(2, encryptedPassword);

                    ResultSet res = user_login.executeQuery();

                    // login success
                    if (res.next()) {
                        String role = res.getString("role");
                        String user_id = res.getString("id");
                        new sessionManager(role, user_id);
                        res.close();
                        user_login.close();
                        return 200;
                    }
                    else {
                        res.close();
                        user_login.close();
                        return 401; // login failed. invalid credentials.
                    }
                }
                else {
                    isUser.close();
                    check_username_exists.close();
                    return 4201; // not yet a user. popup a signup form.
                }


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
        String check_user_exists_query = "SELECT * FROM users WHERE username = ? OR email = ?";
        String create_user_query = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";

        String encryptedPassword = "";
        try {
            Encryptor encryptor = new Encryptor();
            encryptedPassword = encryptor.encrypt(password);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return 503;
        }

        try (Connection signupConn = Server.getConnection()) {
            if (signupConn != null) {
                // Check if username or email already exists
                PreparedStatement check_user = signupConn.prepareStatement(check_user_exists_query);
                check_user.setString(1, username);
                check_user.setString(2, email);

                ResultSet user_exists = check_user.executeQuery();

                if (user_exists.next()) {
                    user_exists.close();
                    check_user.close();
                    return 409; // Username or email already exists
                }
                // we close result set and prepared statement as they hold database resources
                // if you don't close, ur risking leaking resources.
                user_exists.close();
                check_user.close();

                // Create new user
                PreparedStatement create_user = signupConn.prepareStatement(create_user_query);
                create_user.setString(1, username);
                create_user.setString(2, email);
                create_user.setString(3, encryptedPassword);

                // returns the no of rows affected (therefore nothing to close)
                // used for INSERT, UPDATE AND DELETE.
                int no_of_rows_affected = create_user.executeUpdate();
                create_user.close();

                if (no_of_rows_affected > 0) {
                    return 200; // signup success
                } else {
                    return 401; // signup failed
                }

            } else {
                System.out.println("Connection failed!");
                return 503;
            }
        } catch (SQLException e) {
            System.err.println("Database connection err: " + e.getMessage());
            return 503;
        }
    }

    public int DeleteUser(String email, String password) {
        String check_user_exists_query = "SELECT * FROM users WHERE email = ?";
        String delete_user_query = """
                    DELETE FROM users WHERE id = ?::uuid AND email = ? AND password = ?
                """;

        String encryptedPassword = "";
        try {
            Encryptor encryptor = new Encryptor();
            encryptedPassword = encryptor.encrypt(password);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return 503;
        }

        try (Connection deleteConn = Server.getConnection()) {
            if (deleteConn != null) {
                // Check if username or email already exists
                PreparedStatement check_user = deleteConn.prepareStatement(check_user_exists_query);
                check_user.setString(1, email);

                ResultSet user_exists = check_user.executeQuery();

                if (!user_exists.next()) {
                    user_exists.close();
                    check_user.close();
                    return 550; // email doesn't exist
                }
                user_exists.close();
                check_user.close();

                // Delete user
                PreparedStatement delete_user = deleteConn.prepareStatement(delete_user_query);
                delete_user.setString(1, new sessionManager().getUserId());
                delete_user.setString(2, email);
                delete_user.setString(3, encryptedPassword);

                int no_of_rows_affected = delete_user.executeUpdate();
                delete_user.close();

                if (no_of_rows_affected > 0) {
                    return 200; // delete success
                } else {
                    return 401; // delete failed
                }

            } else {
                System.out.println("Connection failed!");
                return 503;
            }
        } catch (SQLException e) {
            System.err.println("Database connection err: " + e.getMessage());
            return 503;
        }

    }

}
