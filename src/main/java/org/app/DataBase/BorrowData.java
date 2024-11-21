package org.app.DataBase;

// TODO:
// add borrow inform when user borrow book

// method
// addBorrowInfo

import javafx.collections.ObservableList;
import org.app.Object.Book;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
        String query = "select * from borrows where user_username = ? and book_idBook = ? and date_give_back is null";
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
    public static void getDataBorrowedBook(String username, ObservableList<Book> books) {
        books.clear();

        String query = "SELECT * FROM books WHERE idBook IN (SELECT book_idBook FROM borrows " +
                "WHERE user_username = '" + username + "')";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Book book = new Book(
                        resultSet.getString("title"),
                        resultSet.getString("author"),
                        resultSet.getInt("idBook"),
                        resultSet.getString("ISBN"),
                        resultSet.getString("description"),
                        resultSet.getString("content"),
                        resultSet.getDouble("price"),
                        resultSet.getString("image_path"),
                        resultSet.getString("catalog"),
                        resultSet.getInt("remaining"),
                        resultSet.getString("publisher")
                );
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getBorrowDate(String username, Book book) {
        String query = "select date_borrow from borrows where user_username = ? and book_idBook = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setInt(2, book.getIdBook());

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("date_borrow");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static void updateReturnDate(String username, Book selectedBook) {
//        String query = "delete from borrows where user_username = ? and book_idBook = ?";
        String query = "update borrows set date_give_back = date('now') where user_username = ? and book_idBook = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setInt(2, selectedBook.getIdBook());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}