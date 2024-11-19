package org.app.DataBase;

// TODO:
// add borrow inform when user borrow book

// method
// addBorrowInfo

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BorrowData extends DataBaseAccessor {

    /**
     * add borrow inform to borrow table
     * @param username user borrow
     * @param idBok isbn of borrowed book
     */
    public static void addBorrowInfo(String username, int idBok) {
        PreparedStatement preparedStatement;
        String query = "insert into borrows(date_borrow, book_idBook, user_username) values (date('now'), ?, ?)";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, idBok);
            preparedStatement.setString(2, username);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Check whether username has borrowed specified book
     * @param username username
     * @param idBook id book
     * @return true if borrowed
     */
    public static boolean isBorrowedBook(String username, int idBook) {
        PreparedStatement preparedStatement;
        String query = "select * from borrows where user_username = ? and book_idBook = ?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setInt(2, idBook);

            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet.next();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}