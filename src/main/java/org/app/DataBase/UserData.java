package org.app.DataBase;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.app.Object.BorrowInfo;
import org.app.Object.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserData extends DataBaseAccessor {
    public static User getUserInfo(String username) {
        User user = new User.Builder().build();

        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM users WHERE accountName = '" + username + "'";
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                user = new User.Builder()
                        .setUsername(resultSet.getString("accountName"))
                        .setPassword(resultSet.getString("password"))
                        .setName(resultSet.getString("Name"))
                        .setPhoneNumber(resultSet.getString("phoneNumber"))
                        .setAddress(resultSet.getString("address"))
                        .setBirthday(resultSet.getString("birthday"))
                        .setEmail(resultSet.getString("gmail"))
                        .build();
            }

            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ObservableList<User> getAllUsers() {
        ObservableList<User> users = FXCollections.observableArrayList();
        users.clear();

        String query = "SELECT * FROM users";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                User user = new User.Builder()
                        .setUsername(resultSet.getString("accountName"))
                        .setPassword(resultSet.getString("password"))
                        .setName(resultSet.getString("Name"))
                        .setPhoneNumber(resultSet.getString("phoneNumber"))
                        .setAddress(resultSet.getString("address"))
                        .setBirthday(resultSet.getString("birthday"))
                        .setEmail(resultSet.getString("gmail"))
                        .build();
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
