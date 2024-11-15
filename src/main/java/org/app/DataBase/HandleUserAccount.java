package org.app.DataBase;

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
        Connection connection;
        PreparedStatement preparedStatement;
        String query = "SELECT 1 FROM users WHERE accountName = ?";
        try {
            connection = DataBaseAccessor.connect();
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
//        connect();
        if (!isUsernameExist(username)) { return ACCOUNT_NOT_FOUND; }

        Connection connection;
        PreparedStatement preparedStatement;
        String query = "SELECT * FROM users WHERE accountName = ? and password = ?";
        try {
            connection = DataBaseAccessor.connect();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return  NORM_USER_LOG_IN_SUCCESS;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return ACCOUNT_NOT_FOUND;
        }

        // check user account info in database

        return NORM_USER_LOG_IN_SUCCESS;
    }

    public static void addAccount(String username, String password, String name) {
        // add account to database
        Connection connection;
        PreparedStatement preparedStatement;
        String query = "INSERT INTO users (accountName,Password, Name) VALUES (?,?)";
        try {
            connection = DataBaseAccessor.connect();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username.trim());
            preparedStatement.setString(2, password);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

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
        if (password.length() < 8 || confirmpasswordText.length() < 8) {
            //Message;
            return false;
        }
        return true;
    }
}