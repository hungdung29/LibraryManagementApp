package org.app.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import org.app.DataBase.BookData;
import org.app.DataBase.BorrowData;
import org.app.Object.Book;

import java.net.URL;
import java.util.ResourceBundle;

public class ReturnBookController extends BookController implements Initializable {
    public Label titleReturnBookLabel;
    public Label borrowDateLabel;

    public TextField commentTextField;

    public VBox infoBookVBox;

    public Button returnButton;

    private Book selectedBook;

    public void initialize(URL location, ResourceBundle resources) {
        // set up column for table
        configTable();
        infoBookVBox.setVisible(false);

        // pass data to books
        BorrowData.getDataBorrowedBook(SignInViewController.username, entireBooks);
        cloneListBook();

        // set content of book table
        bookTable.setItems(shownBooks);

        // Add a listener for row selection
        bookTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                infoBookVBox.setVisible(true);

                commentTextField.setText("");
                selectedBook = newValue;
                handleBookSelection(SignInViewController.username, selectedBook);
            } else {
                infoBookVBox.setVisible(false);
            }
        });
    }

    public void onReturnButtonClicked(ActionEvent actionEvent) {
        // add comment
        if ( !commentTextField.getText().isEmpty() ) {
            BookData.addCommentOfBook(SignInViewController.username, selectedBook.getIsbn(), commentTextField.getText());
        }

        // Remove book on borrowed list
        BorrowData.updateReturnDate(SignInViewController.username, selectedBook);
        BookData.updateRemainingBook(selectedBook.getIdBook(), 1);
        shownBooks.remove(selectedBook);

        // set content of book table
        bookTable.setItems(shownBooks);
        infoBookVBox.setVisible(false);
    }

    /**
     * Handle selection book.
     * @param username username
     * @param book book need to handle inform or more
     */
    public void handleBookSelection(String username, Book book) {
        titleReturnBookLabel.setText("Book title: " + book.getTitle());
        borrowDateLabel.setText("Borrow Date: " + BorrowData.getBorrowDate(username, book));
    }
}