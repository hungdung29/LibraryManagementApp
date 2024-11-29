package org.app.DataBase;

// TODO:
// add borrow inform when user borrow book

// method
// addBorrowInfo

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.app.Object.Book;
import org.app.Object.BorrowInfo;

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
     * Check whether username has borrowing specified book
     * @param username username
     * @param idBook id book
     * @return true if borrowed
     */
    public static boolean isBorrowingBook(String username, int idBook) {
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

    /**
     * pass all data of borrowing book of specific username to list books
     * @param username username
     * @param books list to store data
     */
    public static void getDataBorrowingBook(String username, ObservableList<Book> books) {
//        getDataBook(username, false, books);
        books.clear();
        String query = "SELECT * FROM books WHERE idBook IN " +
                "(SELECT book_idBook FROM borrows " +
                "WHERE user_username = ? and date_give_back is null)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Book book = new Book.Builder()
                        .setTitle(resultSet.getString("title"))
                        .setAuthor(resultSet.getString("author"))
                        .setIdBook(resultSet.getInt("idBook"))
                        .setIsbn(resultSet.getString("ISBN"))
                        .setDescription(resultSet.getString("description"))
                        .setContent(resultSet.getString("content"))
                        .setPrice(resultSet.getDouble("price"))
                        .setImagePath(resultSet.getString("image_path"))
                        .setCatalog(resultSet.getString("catalog"))
                        .setRemaining(resultSet.getInt("remaining"))
                        .setPublisher(resultSet.getString("publisher"))
                        .build();
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void getDataReturnedBook(String username, ObservableList<Book> books) {
//        getDataBook(username, true, books);
        books.clear();
        String query = "SELECT * FROM books WHERE idBook IN " +
                "(SELECT book_idBook FROM borrows " +
                "WHERE user_username = ? and date_give_back is not null)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Book book = new Book.Builder()
                        .setTitle(resultSet.getString("title"))
                        .setAuthor(resultSet.getString("author"))
                        .setIdBook(resultSet.getInt("idBook"))
                        .setIsbn(resultSet.getString("ISBN"))
                        .setDescription(resultSet.getString("description"))
                        .setContent(resultSet.getString("content"))
                        .setPrice(resultSet.getDouble("price"))
                        .setImagePath(resultSet.getString("image_path"))
                        .setCatalog(resultSet.getString("catalog"))
                        .setRemaining(resultSet.getInt("remaining"))
                        .setPublisher(resultSet.getString("publisher"))
                        .build();
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

    public static BorrowInfo getBorrowInfo(String username, Book book) {
        String query = "select date_borrow, date_give_back, " +
                "(select comment " +
                "from comments " +
                "where comments.user_username = ? " +
                "and comments.book_ISBN = ?) as comment " +
                "from borrows where user_username = ? and date_give_back is not null";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, book.getIsbn());
            preparedStatement.setString(3, username);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                BorrowInfo borrowInfo = new BorrowInfo(
                        resultSet.getString("date_borrow"),
                        resultSet.getString("date_give_back"),
                        resultSet.getString("comment")
                );

                return borrowInfo;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int getTotalBorrowedBooks() {
        String query = "SELECT COUNT(*) AS totalBorrowedBooks FROM borrows";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("totalBorrowedBooks");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }



    public static Integer getNumberOfComments(String username) {
        String query = "SELECT COUNT(*) FROM comments WHERE user_username = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static ObservableList<BorrowInfo> getBorrowingBooks(String username) {
        ObservableList<BorrowInfo> borrowedBooks = FXCollections.observableArrayList();
        String query = "SELECT books.title, borrows.date_borrow FROM borrows " +
                "JOIN books ON borrows.book_idBook = books.idBook " +
                "WHERE borrows.user_username = ? AND borrows.date_give_back IS NULL";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                BorrowInfo borrowedBook = new BorrowInfo(
                        resultSet.getString("title"),
                        resultSet.getString("date_borrow")
                );
                borrowedBooks.add(borrowedBook);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return borrowedBooks;
    }

    public static int getNumberOfBorrowingBooks(String username) {
        String query = "SELECT COUNT(id_borrow) FROM borrows WHERE user_username = ? and date_give_back is null";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int getQuantity(String isbn) {
        String query1 = "SELECT books.remaining as numRemaining FROM books WHERE books.ISBN = ?";
        String query2 = "SELECT COUNT(id_borrow) as countBooks FROM borrows " +
                "WHERE book_idBook = (SELECT idBook FROM books WHERE ISBN = ?) " +
                "AND date_give_back IS NULL";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query1);
            PreparedStatement preparedStatement2 = connection.prepareStatement(query2);
            preparedStatement.setString(1, isbn);
            preparedStatement2.setString(1, isbn);

            ResultSet resultSet = preparedStatement.executeQuery();
            ResultSet resultSet2 = preparedStatement2.executeQuery();

            if (resultSet.next() && resultSet2.next()) {
                return resultSet.getInt("numRemaining")
                        + resultSet2.getInt("countBooks");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static void deleteBook(Book selectedBook) {
        String query = "delete from books where ISBN = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, selectedBook.getIsbn());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}