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
}