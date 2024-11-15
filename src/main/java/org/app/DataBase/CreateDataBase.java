package org.app.DataBase;

// statement to create database in this file.
// Call 1 time, but it is necessary for put it here.

import org.app.Object.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateDataBase extends DataBaseAccessor {
    public static void createDatabase() {
        String sql = """
            CREATE TABLE IF NOT EXISTS example_table (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT NOT NULL,
                age INTEGER
            );
        """;

        try (Statement stmt = connection.createStatement()) {
            // Execute the SQL statement
            stmt.execute(sql);
            System.out.println("Table 'example_table' has been created.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void addSample() {
        String sql = """
            insert into example_table (name, age) values 
                ('tho', 18), ('phan', 19);
        """;

        try (Statement stmt = connection.createStatement()) {
            // Execute the SQL statement
            stmt.execute(sql);
            System.out.println("Insert successful.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void showSample() {
        String sql = """
            select * from example_table;
        """;

        try (Statement stmt = connection.createStatement()) {
            // system out all content in table
            try (ResultSet rs = stmt.executeQuery(sql)) {
                while (rs.next()) {
                    System.out.println(rs.getString("name"));
                }
            }


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
