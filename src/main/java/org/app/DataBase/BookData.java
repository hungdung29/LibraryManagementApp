package org.app.DataBase;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.app.Object.Book;
import org.app.Object.BorrowInfo;

import java.sql.PreparedStatement;
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
// addCommentOfBook

public class BookData extends DataBaseAccessor {
    public static void getDataAllBook(ObservableList<Book> books) {
        books.clear();

        String query = "SELECT * FROM books";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Book book = new Book.Builder()
                        .setIdBook(resultSet.getInt("idBook"))
                        .setTitle(resultSet.getString("title"))
                        .setAuthor(resultSet.getString("author"))
                        .setDescription(resultSet.getString("description"))
                        .setPrice(resultSet.getDouble("price"))
                        .setContent(resultSet.getString("content"))
                        .setCatalog(resultSet.getString("catalog"))
                        .setIsbn(resultSet.getString("ISBN"))
                        .setPublisher(resultSet.getString("publisher"))
                        .setRemaining(resultSet.getInt("remaining"))
                        .setImagePath(resultSet.getString("image_path"))
                        .build();
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

    /**
     * add comment to specific book by user
     * @param username username
     * @param isbn isbn of book
     * @param comment comment
     */
    public static void addCommentOfBook(String username, String isbn, String comment) {
        String query = "insert into comments (user_username, book_ISBN, comment, created_at) " +
                "VALUES (?, ?, ?, date('now'))";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, isbn);
            preparedStatement.setString(3, comment);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void setCommentOfBook(String username, String isbn, String comment) {
        String query = "update comments set comment = ? where book_ISBN = ? and user_username= ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, comment);
            preparedStatement.setString(2, isbn);
            preparedStatement.setString(3, username);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
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

    public static int getTotalRemainingBooks() {
        String query = "SELECT SUM(remaining) AS totalRemainingBooks FROM books";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.next();
            return resultSet.getInt("totalRemainingBooks");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static void addBook (Book book) {
        String query = "INSERT INTO books (title, author, description, price, content, " +
                "catalog, ISBN, publisher, remaining, image_path) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setString(3, book.getDescription());
            preparedStatement.setDouble(4, book.getPrice());
            preparedStatement.setString(5, book.getContent());
            preparedStatement.setString(6, book.getCatalog());
            preparedStatement.setString(7, book.getIsbn());
            preparedStatement.setString(8, book.getPublisher());
            preparedStatement.setInt(9, book.getRemaining());
            preparedStatement.setString(10, book.getImagePath());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}