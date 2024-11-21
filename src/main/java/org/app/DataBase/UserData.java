package org.app.DataBase;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.app.Object.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserData extends DataBaseAccessor {
    public static User getUserInfo(String username) {
        User user = new User();

        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM users WHERE accountName = '" + username + "'";
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                user.setName(resultSet.getString("Name"));
                user.setUsername(resultSet.getString("accountName"));
                user.setPassword(resultSet.getString("password"));
                user.setAddress(resultSet.getString("address"));
                user.setPhoneNumber(resultSet.getString("phoneNumber"));
            }

            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ObservableList<User> getAllUsers() {
        ObservableList<User> users = FXCollections.observableArrayList();
        users.clear();

        String query = "SELECT * FROM users";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                User user = new User(
                        resultSet.getString("accountName"),
                        resultSet.getString("password"),
                        resultSet.getString("Name"),
                        resultSet.getString("phoneNumber"),
                        resultSet.getString("address")
                );
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
