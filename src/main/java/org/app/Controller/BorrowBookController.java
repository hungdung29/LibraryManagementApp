package org.app.Controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import org.app.DataBase.BookData;
import org.app.DataBase.BorrowData;
import org.app.Object.Book;

import java.net.URL;
import java.util.ResourceBundle;

public class BorrowBookController extends BookController implements Initializable {
    public Label titleBookDetailLabel;
    public Label publisherDetailLabel;
    public Label descriptionDetailLabel;

    public ListView<String> commentList;

    public Button borrowButton;

    private Book selectedBook;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // set up column for table
        configTable();
        infoBookVBox.setVisible(false);

        // pass data to books
        getDataEntireBook();

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

    public void getDataEntireBook() {
        BookData.getDataAllBook(entireBooks);
        cloneListBook();
    }

    /**
     * Handle selection book
     * @param username username
     * @param book book need to handle inform or more
     */
    public void handleBookSelection(String username, Book book){
        titleBookDetailLabel.setText("Title: " + book.getTitle());
        publisherDetailLabel.setText("Publisher: " + book.getPublisher());
        descriptionDetailLabel.setText("Description: " + book.getDescription());

        ObservableList<String> comments = BookData.getCommentOfBook(book.getIsbn());
        commentList.setItems(comments);
        commentList.setPrefHeight( commentList.getFixedCellSize() * Math.min(comments.size() + 2, 10) );

        if (book.getRemaining() == 0) {
            borrowButton.setText("All been borrowed");
            borrowButton.setDisable(true);
        } else {
            borrowButton.setText("Borrow");
            borrowButton.setDisable(false);
        }
        if (BorrowData.isBorrowingBook(username, book.getIdBook())) {
            // book is borrowed by this user
            borrowButton.setText("Borrowed");
            borrowButton.setDisable(true);
        }
    }

    public void onBorrowButtonClicked(ActionEvent actionEvent) {
        borrowButton.setText("Borrowed");
        borrowButton.setDisable(true);

        // add borrow inform to database
        BorrowData.addBorrowInfo(SignInViewController.username, selectedBook.getIdBook());

        BookData.updateRemainingBook(selectedBook.getIdBook(), -1);

        // pass data to books
        BookData.getDataAllBook(entireBooks);
        cloneListBook();

        // set content of book table
        bookTable.setItems(shownBooks);
    }
}