package org.app.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.app.DataBase.BookData;
import org.app.DataBase.BorrowData;
import org.app.Object.Book;
import org.app.Object.BorrowInfo;

import java.net.URL;
import java.util.ResourceBundle;

public class HistoryController extends BookController implements Initializable {
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
        infoBookVBox.setVisible(false);
        isChange = false;

        // pass data to books
        getDataEntireBook();

        // set content of book table
        bookTable.setItems(shownBooks);

        // Add a listener for row selection
        bookTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                infoBookVBox.setVisible(true);
                commentTextField.setDisable(true);

                selectedBook = newValue;
                handleBookSelection(SignInViewController.username, selectedBook);
            } else {
                infoBookVBox.setVisible(false);
            }
        });
    }

    public void getDataEntireBook() {
        BorrowData.getDataReturnedBook(SignInViewController.username, entireBooks);
        cloneListBook();
    }

    public void onChangeCommentButtonClicked(ActionEvent actionEvent) {
        // Save changed comment
        if (isChange) {
            changeCommentButton.setText("Change Comment");
            commentTextField.setDisable(true);
            // save new comment to database

            BookData.setCommentOfBook(SignInViewController.username, selectedBook.getIsbn(), commentTextField.getText());
        } else {
            changeCommentButton.setText("Save");
            commentTextField.setDisable(false);
        }

        isChange = !isChange;
    }

    /**
     * Handle selection book.
     * @param username username
     * @param book book need to handle inform or more
     */
    public void handleBookSelection(String username, Book book) {
        titleBookLabel.setText("Book title: " + book.getTitle());
        BorrowInfo borrowInfo = BorrowData.getBorrowInfo(username, book);
        if (borrowInfo == null) {
            infoBookVBox.setVisible(false);
            return;
        }

        borrowDateLabel.setText("Borrow Date: " + borrowInfo.getBorrowDate());
        returnDateLabel.setText("Return Date: " + borrowInfo.getReturnDate());
        commentTextField.setText(borrowInfo.getComment());
    }
}