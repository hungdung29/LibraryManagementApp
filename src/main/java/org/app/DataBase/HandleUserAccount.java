package org.app.DataBase;

import org.app.Object.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HandleUserAccount extends DataBaseAccessor {
    public static final int ACCOUNT_NOT_FOUND = -1;
    public static final int WRONG_PASSWORD = 0;
    public static final int NORM_USER_LOG_IN_SUCCESS = 1;
    public static final int ADMIN_LOG_IN_SUCCESS = 2;

    /**
     * Check whether username is existed in database
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
     * @param username username
     * @param password pw
     * @return type of user
     */
    public static int checkLogIn(String username, String password) {
//        if (!isUsernameExist(username)) { return ACCOUNT_NOT_FOUND; }
        if (username.equals("admin")) {
            return ADMIN_LOG_IN_SUCCESS;
        }

        PreparedStatement preparedStatement;
        String query = "SELECT * FROM users WHERE accountName = ? and password = ?";
        try {
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return  NORM_USER_LOG_IN_SUCCESS;
            } else {
                return  ACCOUNT_NOT_FOUND;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return ACCOUNT_NOT_FOUND;
        }
    }

    public static void addAccount(User user) {
        // add account to database
        PreparedStatement preparedStatement;
        String query = "insert into users (Name, accountName, password, phoneNumber, address) VALUES (?,?,?,?,?)";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getPhoneNumber());
            preparedStatement.setString(5, user.getAddress());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * check username account is exist and strong
     * @param username new username. Make sure that username is not exist in database
     * @param password new password. Make sure that it strong and not include space " "
     * @param confirmpasswordText make sure = password
     * @return true if account is valid. Otherwise, return false
     */
    public static boolean checkValidAccount(String username, String password,
                                            String confirmpasswordText) {
        if (isUsernameExist(username)) {
            return false;
        }

        if (password == null || password.isEmpty()) {
            return false;
        }

        if ( !password.equals(confirmpasswordText) ) {
            return false;
        }

        // check valid and strong password
        if (password.length() < 8 || password.contains(" ")) {
            return false;
        }

        return true;
    }

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