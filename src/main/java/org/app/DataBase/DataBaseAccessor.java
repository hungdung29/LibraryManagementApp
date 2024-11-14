package org.app.DataBase;

import java.sql.*;

import org.sqlite.SQLiteConfig;

abstract public class DataBaseAccessor {
    public static Connection Connect(){
        try {
            Class.forName("org.sqlite.JDBC");
            SQLiteConfig configuration = new SQLiteConfig();
            configuration.enforceForeignKeys(true);
            configuration.setBusyTimeout(Integer.parseInt(String.valueOf(900000)));
            Connection connect = DriverManager.getConnection("jdbc:sqlite:Library.db",
                    configuration.toProperties());
            return connect;
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println(e);
            return null;
        }
    }

    public static void checkTable(String tableName,String query) {
        Statement statement = null;
        Connection connect = null;
        try {
            connect = Connect();
            DatabaseMetaData metaData = connect.getMetaData();
            ResultSet rs = metaData.getTables(null, null, tableName, null);
            if (rs.next()) {
            } else {
                statement = connect.createStatement();
                statement.execute(query);
            }
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connect != null) {
                    connect.close();
                }
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }

    public void disconnect() throws SQLException {
        if (Connect() != null) {
            Connect().close();
        }
    }
}
