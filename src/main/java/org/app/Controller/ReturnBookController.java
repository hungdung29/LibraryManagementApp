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

        // pass data to books
        BorrowData.getDataBorrowedBook(SignInViewController.username, entireBooks);
        cloneListBook();

        // set content of book table
        bookTable.setItems(shownBooks);

        // Add a listener for row selection
        bookTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                infoBookVBox.setVisible(true);

                selectedBook = newValue;
                handleBookSelection(SignInViewController.username, selectedBook);
            } else {
                infoBookVBox.setVisible(false);
            }
        });
    }

    public void onReturnButtonClicked(ActionEvent actionEvent) {
        // Remove book on borrowed list
        BorrowData.removeBorrowedBook(SignInViewController.username, selectedBook);
        shownBooks.remove(selectedBook);

        // pass data to books
        BorrowData.getDataBorrowedBook(SignInViewController.username, entireBooks);
        cloneListBook();

        // set content of book table
        bookTable.setItems(shownBooks);
    }

    @Override
    /**
     * Handle selection book
     * @param username username
     * @param book book need to handle inform or more
     */
    public void handleBookSelection(String username, Book book) {
        titleReturnBookLabel.setText(book.getTitle());
        borrowDateLabel.setText(BorrowData.getBorrowDate(username, book));

    }
}