package org.app.Controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import org.app.DataBase.BookData;
import org.app.DataBase.BorrowData;
import org.app.Object.Book;

import java.net.URL;
import java.util.ResourceBundle;

public class BorrowBookController extends BookController implements Initializable {
    public Label titleBookDetailLabel;
    public Label publisherDetailLabel;
    public Label descripDetailLabel;

    public ListView<String> commentList;

    public Button borrowButton;

    private Book selectedBook;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // set up column for table
        configTable();
        infoBookVBox.setVisible(false);

        // pass data to books
        BookData.getDataAllBook(entireBooks);
        cloneListBook();

        // set content of book table
        bookTable.setItems(shownBooks);

        // Add a listener for row selection
        bookTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                infoBookVBox.setVisible(true);

                borrowButton.setText("Borrow");
                borrowButton.setDisable(false);

                selectedBook = newValue;
                handleBookSelection(SignInViewController.username, selectedBook);
            } else {
                infoBookVBox.setVisible(false);
            }
        });
    }

    /**
     * Handle selection book
     * @param username username
     * @param book book need to handle inform or more
     */
    public void handleBookSelection(String username, Book book){
        titleBookDetailLabel.setText("Title: " + book.getTitle());
        publisherDetailLabel.setText("Publisher: " + book.getPublisher());
        descripDetailLabel.setText("Description: " + book.getDescription());

        ObservableList<String> comments = BookData.getCommentOfBook(book.getIsbn());
        commentList.setItems(comments);

        System.out.println(comments.size());
        commentList.setPrefHeight( commentList.getFixedCellSize() * Math.min(comments.size() + 2, 10) );
    }

    public void onBorrowButtonClicked(ActionEvent actionEvent) {
        borrowButton.setText("Borrow success");
        borrowButton.setDisable(true);

        // add borrow inform to database
        BorrowData.addBorrowInfo(SignInViewController.username, selectedBook.getIsbn());
    }
}
