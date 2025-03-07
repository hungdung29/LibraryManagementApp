package org.app.DataBase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.app.Object.User;

public class UserData extends DataBaseAccessor {

    /**
	* Get user info from database.
	*
	* @param username username
	* @return user info
	*/
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

    /**
	* Get all users from database.
	*
	* @return list of users
	*/
    public static ObservableList<User> getAllUsers() {
	   ObservableList<User> users = FXCollections.observableArrayList();
	   users.clear();

	   String query = "SELECT * FROM users WHERE accountName != 'admin'";
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
