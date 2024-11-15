package org.app.DataBase;

import java.sql.*;

import org.sqlite.SQLiteConfig;

abstract public class DataBaseAccessor {
    protected static Connection connection;
    protected static final String url = "jdbc:sqlite:Library.db";

    // Method to connect to the SQLite database
    public static void connect() {
        try {
            connection = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Method to disconnect from the SQLite database
    public static void disconnect() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Connection to SQLite has been closed.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
