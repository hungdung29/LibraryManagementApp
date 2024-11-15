package org.app.DataBase;

import org.app.Object.User;

import java.sql.SQLException;
import java.sql.Statement;

public class UserData extends DataBaseAccessor {
    public static User getUserInfo(String username) {
        User user = new User();

//        String sql = """
//            CREATE TABLE IF NOT EXISTS example_table (
//                id INTEGER PRIMARY KEY AUTOINCREMENT,
//                name TEXT NOT NULL,
//                age INTEGER
//            );
//        """;
//
//        try (Statement stmt = connection.createStatement()) {
//            // Execute the SQL statement
//            stmt.execute(sql);
//            System.out.println("Table 'example_table' has been created.");
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }

        user.setName("Tho");
        user.setUsername(username);
        user.setAddress("Vinh Phuc");
        user.setPhoneNumber("124123");

        return user;
    }
}
