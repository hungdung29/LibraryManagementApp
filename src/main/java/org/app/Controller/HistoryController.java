package org.app.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.app.DataBase.BookData;
import org.app.DataBase.BorrowData;
import org.app.DataBase.RequestData;
import org.app.Object.Book;
import org.app.Object.BorrowInfo;

public class HistoryController extends BookController implements Initializable {
    public TableColumn statusColumn;

    public Label titleBookLabel;
    public Label borrowDateLabel;
    public Label returnDateLabel;
    public TextField commentTextField;

    public Button changeCommentButton;
    // if isChange is true. Allow user change comment
    private boolean isChange;

    private Book selectedBook;

    public void initialize(URL location, ResourceBundle resources) {
	   // set up column for table
	   configTable();
	   statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
	   infoBookVBox.setVisible(false);
	   isChange = false;

	   // pass data to books
	   getDataEntireBook();

	   // set content of book table
	   bookTable.setItems(shownBooks);

	   // Add a listener for row selection
	   bookTable.getSelectionModel().selectedItemProperty().addListener(
			 (observable, oldValue, newValue) -> {
				if (newValue!=null) {
				    infoBookVBox.setVisible(true);
				    commentTextField.setDisable(true);

				    selectedBook = newValue;
				    handleBookSelection(SignInViewController.username, selectedBook);
				} else {
				    infoBookVBox.setVisible(false);
				}
			 });
    }

    @Override
    public void onSearchButtonClicked(ActionEvent actionEvent) {
	   getDataEntireBook();
	   super.onSearchButtonClicked(actionEvent);
    }

    /**
	* Get data from database.
	*/
    public void getDataEntireBook() {
	   entireBooks.clear();
	   BorrowData.getDataReturnedBook(SignInViewController.username, shownBooks);
	   entireBooks.addAll(shownBooks);
	   BorrowData.getDataBorrowingBook(SignInViewController.username, shownBooks);
	   entireBooks.addAll(shownBooks);
	   RequestData.getBookisPending(SignInViewController.username, shownBooks);
	   entireBooks.addAll(shownBooks);
	   cloneListBook();
    }

    /**
	* Handle change comment button clicked.
	*
	* @param actionEvent event
	*/
    public void onChangeCommentButtonClicked(ActionEvent actionEvent) {
	   // Save changed comment
	   if (isChange) {
		  changeCommentButton.setText("Change Comment");
		  commentTextField.setDisable(true);
		  // save new comment to database

		  BookData.setCommentOfBook(SignInViewController.username, selectedBook.getIsbn(),
				commentTextField.getText());
	   } else {
		  changeCommentButton.setText("Save");
		  commentTextField.setDisable(false);
	   }

	   isChange = !isChange;
    }

    /**
	* Handle selection book.
	*
	* @param username username
	* @param book     book need to handle inform or more
	*/
    public void handleBookSelection(String username, Book book) {
	   titleBookLabel.setText("Book title: " + book.getTitle());
	   BorrowInfo borrowInfo;
	   if (book.getStatus().equals("Borrowing")) {
		  borrowInfo = BorrowData.getBorrowingInfo(username, book);
	   } else if (book.getStatus().equals("Returned")) {
		  borrowInfo = BorrowData.getBorrowedInfo(username, book);
	   } else {
		  borrowInfo = new BorrowInfo(null, null, null);
	   }

	   if (borrowInfo==null) {
		  infoBookVBox.setVisible(false);
		  return;
	   }

	   borrowDateLabel.setText("Borrowed Date: " + borrowInfo.getBorrowDate());
	   returnDateLabel.setText("Returned Date: " + borrowInfo.getReturnDate());
	   commentTextField.setText(borrowInfo.getComment());
    }
}