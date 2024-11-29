package org.app.DataBase;

import javafx.collections.ObservableList;
import org.app.Object.BorrowRequest;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RequestData extends DataBaseAccessor {
    public static void getDataAllRequest(ObservableList<BorrowRequest> borrowRequests) {
        borrowRequests.clear();

        String query = "select user_username as username, title, book_idBook, date_request from borrows_request join books on borrows_request.book_idBook = books.idBook";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                BorrowRequest borrowRequest = new BorrowRequest(
                        resultSet.getString("username"),
                        resultSet.getInt("book_idBook"),
                        resultSet.getString("title"),
                        resultSet.getString("date_request")
                );

                borrowRequests.add(borrowRequest);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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

    // delete borrow request info
    public static void deleteRequestInfo(String username, int idBook) {
        PreparedStatement preparedStatement;
        String query = "delete from borrows_request where book_idBook = ? and user_username = ?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, idBook);
            preparedStatement.setString(2, username);

            preparedStatement.executeUpdate();

            System.out.println("delete " + idBook);
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
