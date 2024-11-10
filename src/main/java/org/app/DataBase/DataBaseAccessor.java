package org.app.DataBase;

import java.sql.Connection;
import java.sql.SQLException;

public class DataBaseAccessor {
    public static Connection connection;

    /**
     * Connect to database
     */
    public static void connect() {
        return;
    }

    public static void disconnect() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
}
