package org.app.DataBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RequestData extends DataBaseAccessor {

    // add borrow request info
    public static void addRequestInfo(String username, int idBok) {
        PreparedStatement preparedStatement;
        String query = "insert into borrows_request(date_request, book_idBook, user_username) values (datetime('now'), ?, ?)";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, idBok);
            preparedStatement.setString(2, username);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // check whether this book already request to borrow
    public static boolean isPending(String username, int idBook) {
        PreparedStatement preparedStatement;
        String query = "select * from borrows_request where user_username = ? and book_idBook = ?";
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
