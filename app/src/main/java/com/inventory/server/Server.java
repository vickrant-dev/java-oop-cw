package com.inventory.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Server {
    // using final to prevent overriding

    // session pooler (preferred as of ipv6 conflicts)
    private static final String pooler_url = "jdbc:postgresql://aws-1-ap-south-1.pooler.supabase.com:5432/postgres?sslmode=require";
    private static final String pooler_user = "postgres.mtsbkpmwirijtppkhvqn";

    // direct connection
    private static final String direct_url = "jdbc:postgresql://db.mtsbkpmwirijtppkhvqn.supabase.co:5432/postgres?sslmode=require";
    private static final String direct_user = "postgres";
    private static final String password = "2MuHbK02kr7X6GO4";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(pooler_url, pooler_user, password); // this throws a sql exception if anything goes wrong
    }

}
