package org.app.DataBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.ObservableList;
import org.app.Object.Book;
import org.app.Object.BorrowRequest;

public class RequestData extends DataBaseAccessor {
    /**
	* Get all request data from database.
	*
	* @param borrowRequests list of borrow request
	*/
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

    /**
	* Add request info to database.
	*
	* @param username username
	* @param idBook   id of book
	*/
    public static void addRequestInfo(String username, int idBook) {
	   PreparedStatement preparedStatement;
	   String query = "insert into borrows_request(date_request, book_idBook, user_username) values (datetime('now'), ?, ?)";
	   try {
		  preparedStatement = connection.prepareStatement(query);
		  preparedStatement.setInt(1, idBook);
		  preparedStatement.setString(2, username);

		  preparedStatement.executeUpdate();
	   } catch (SQLException e) {
		  System.out.println(e.getMessage());
	   }
    }

    /**
	* Delete request info from database.
	*
	* @param username username
	* @param idBook   id of book
	*/
    public static void deleteRequestInfo(String username, int idBook) {
	   PreparedStatement preparedStatement;
	   String query = "delete from borrows_request where book_idBook = ? and user_username = ?";
	   try {
		  preparedStatement = connection.prepareStatement(query);
		  preparedStatement.setInt(1, idBook);
		  preparedStatement.setString(2, username);

		  preparedStatement.executeUpdate();

		  //System.out.println("delete " + idBook);
	   } catch (SQLException e) {
		  System.out.println(e.getMessage());
	   }
    }

    /**
	* get book is pending.
	*
	* @param username username
	* @param books    list of book
	*/
    public static void getBookisPending(String username, ObservableList<Book> books) {
	   books.clear();
	   String query = "SELECT * FROM books JOIN borrows_request " +
			 "ON books.idBook = borrows_request.book_idBook where user_username = ?";
	   try {
		  PreparedStatement preparedStatement = connection.prepareStatement(query);
		  preparedStatement.setString(1, username);

		  ResultSet resultSet = preparedStatement.executeQuery();
		  while (resultSet.next()) {
			 Book book = new Book.Builder()
				    .setIdBook(resultSet.getInt("idBook"))
				    .setTitle(resultSet.getString("title"))
				    .setAuthor(resultSet.getString("author"))
				    .setIsbn(resultSet.getString("isbn"))
				    .setDescription(resultSet.getString("description"))
				    .setContent(resultSet.getString("content"))
				    .setCatalog(resultSet.getString("catalog"))
				    .setPrice(resultSet.getDouble("price"))
				    .setImagePath(resultSet.getString("image_path"))
				    .setRemaining(resultSet.getInt("remaining"))
				    .setPublisher(resultSet.getString("publisher"))
				    .setStatus("Pending")
				    .build();
			 books.add(book);
		  }
	   } catch (SQLException e) {
		  System.out.println(e.getMessage());
	   }
    }

    /**
	* check whether this book already request to borrow.
	*
	* @param username username
	* @param idBook   id of book
	* @return true if book is pending
	*/
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
