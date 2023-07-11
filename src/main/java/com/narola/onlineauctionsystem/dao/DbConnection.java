package com.narola.onlineauctionsystem.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private Connection connection = null;
    private static DbConnection dbConnection = new DbConnection();
    private final String url = "jdbc:mysql://localhost:3306/online_auction";
    private final String username = "root";
    private final String password = "";

    private DbConnection() {
    }

    public static DbConnection getInstance() {
        return dbConnection;
    }

    public Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(url, username, password);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}
