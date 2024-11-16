package org.app.DataBase;

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
                user.setAddress(resultSet.getString("address"));
                user.setPhoneNumber(resultSet.getString("phoneNumber"));
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

//        user.setName("Tho");
//        user.setUsername(username);
//        user.setAddress("Vinh Phuc");
//        user.setPhoneNumber("124123");
    }
}
