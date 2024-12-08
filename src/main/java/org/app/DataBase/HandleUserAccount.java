package org.app.DataBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.app.Object.User;

public class HandleUserAccount extends DataBaseAccessor {
    public static final int ACCOUNT_NOT_FOUND = -1;
    public static final int WRONG_PASSWORD = 0;
    public static final int NORM_USER_LOG_IN_SUCCESS = 1;
    public static final int ADMIN_LOG_IN_SUCCESS = 2;

	public static final int ACCOUNT_ALREADY_EXISTS = 3;
	public static final int INVALID_PASSWORD = 4;
	public static final int INVALID_EMAIL = 5;
	public static final int VALID_ACCOUNT = 6;

    /**
	* Check if username is existing in database.
	*
	* @param username username
	* @return true if username is existing. Otherwise, return false
	*/
    private static boolean isUsernameExist(String username) {
	   PreparedStatement preparedStatement;
	   String query = "SELECT 1 FROM users WHERE accountName = ?";
	   try {
		  preparedStatement = connection.prepareStatement(query);
		  preparedStatement.setString(1, username);
		  ResultSet resultSet = preparedStatement.executeQuery();
		  return resultSet.next();
	   } catch (SQLException e) {
		  System.out.println(e.getMessage());
		  return false;
	   }
    }

    /**
	* Check account information and type of user.
	*
	* @param username username
	* @param password pw
	* @return type of user
	*/
    public static int checkLogIn(String username, String password) {
	   if (username.equals("admin")) {
		  return ADMIN_LOG_IN_SUCCESS;
	   }

	   if (!isUsernameExist(username)) {
		  return ACCOUNT_NOT_FOUND;
	   }


	   PreparedStatement preparedStatement;
	   String query = "SELECT * FROM users WHERE accountName = ? and password = ?";
	   try {
		  preparedStatement = connection.prepareStatement(query);

		  preparedStatement.setString(1, username);
		  preparedStatement.setString(2, password);
		  ResultSet resultSet = preparedStatement.executeQuery();

		  if (resultSet.next()) {
			 return NORM_USER_LOG_IN_SUCCESS;
		  } else {
			 return WRONG_PASSWORD;
		  }
	   } catch (SQLException e) {
		  System.out.println(e.getMessage());
		  return ACCOUNT_NOT_FOUND;
	   }
    }

    /**
	* Add account to database.
	*
	* @param user user
	*/
    public static void addAccount(User user) {
	   // add account to database
	   PreparedStatement preparedStatement;
	   String query = "insert into users (name, accountname, password, phonenumber, address, gmail, birthday) values (?, ?, ?, ?, ?, ?, ?)";
	   try {
		  preparedStatement = connection.prepareStatement(query);
		  preparedStatement.setString(1, user.getName());
		  preparedStatement.setString(2, user.getUsername());
		  preparedStatement.setString(3, user.getPassword());
		  preparedStatement.setString(4, user.getPhoneNumber());
		  preparedStatement.setString(5, user.getAddress());
		  preparedStatement.setString(6, user.getEmail());
		  preparedStatement.setString(7, user.getBirthday());

		  preparedStatement.executeUpdate();
	   } catch (SQLException e) {
		  System.out.println(e.getMessage());
	   }
    }

    /**
	* check username account is exist and strong.
	*
	* @param username            new username. Make sure that username is not exist in database
	* @param password            new password. Make sure that it strong and not include space " "
	* @param confirmpasswordText make sure = password
	* @return true if account is valid. Otherwise, return false
	*/
    public static int checkValidAccount(String username, String password,
								    String confirmpasswordText, String mail) {
	   if (isUsernameExist(username)) {
		  return ACCOUNT_ALREADY_EXISTS;
	   }

	   if (!checkValidPassword(password, confirmpasswordText)) {
		   return INVALID_PASSWORD;
	   }

	   if (!checkValidMail(mail)) {
		   	return INVALID_EMAIL;
	   }

	   return VALID_ACCOUNT;
    }

    /**
	* check valid password.
	*
	* @param password            password
	* @param confirmpasswordText confirm password
	* @return true if password is valid. Otherwise, return false
	*/
    public static boolean checkValidPassword(String password, String confirmpasswordText) {
	   if (password==null || password.isEmpty()) {
		  return false;
	   }

	   if (!password.equals(confirmpasswordText)) {
		  return false;
	   }

	   // check valid and strong password
	   if (password.length() < 8 || password.contains(" ")) {
		  return false;
	   }

	   return true;
    }

	public static boolean checkValidMail(String mail) {
		String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

		// Check if the email matches the regex
		return mail.matches(emailRegex);
	}

    /**
	* Update password of user.
	*
	* @param username    username
	* @param newPassword new password
	*/
    public static void changePassword(String username, String newPassword) {
	   PreparedStatement preparedStatement;
	   String query = "UPDATE users SET password = ? WHERE accountName = ?";
	   try {
		  preparedStatement = connection.prepareStatement(query);
		  preparedStatement.setString(1, newPassword);
		  preparedStatement.setString(2, username);

		  preparedStatement.executeUpdate();
	   } catch (SQLException e) {
		  System.out.println(e.getMessage());
	   }
    }
}