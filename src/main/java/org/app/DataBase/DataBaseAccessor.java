package org.app.DataBase;

import java.sql.Connection;
import java.sql.SQLException;

abstract public class DataBaseAccessor {
    public Connection connection;

    /**
     * Connect to database
     */
    public void connect() {
        return;
    }

    public void disconnect() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
}
