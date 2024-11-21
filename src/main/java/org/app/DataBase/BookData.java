package org.app.DataBase;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.app.Object.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// TODO:
// get book data
// get all book in database
// get all book that specifier user have borrowed
// get comment of book

// method
// getDataAllBook(books) ==> insert directly list of books
// getDataBorrowedBook(username, books)
// getCommentOfBook(isbn)

public class BookData extends DataBaseAccessor {
    public static void getDataAllBook(ObservableList<Book> books) {
        books.clear();

        String query = "SELECT * FROM books";
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ObservableList<String> getCommentOfBook(String isbn) {
        ObservableList<String> comments = FXCollections.observableArrayList();
        comments.clear();

        String query = "select * from comments where book_ISBN = " + isbn;

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                comments.add(resultSet.getString("comment"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            }

        return comments;
    }

    public static void updateRemainingBook(int idBook, int quantity) {
        String query = "UPDATE books SET remaining = remaining + " + quantity + " WHERE idBook = " + idBook;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int getRemainingBook(int idBook) {
        String query = "SELECT remaining FROM books WHERE idBook = " + idBook;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.next();
            return resultSet.getInt("remaining");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}