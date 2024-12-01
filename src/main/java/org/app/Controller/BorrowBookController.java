package org.app.Controller;

import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.app.DataBase.BookData;
import org.app.DataBase.BorrowData;
import org.app.DataBase.RequestData;
import org.app.Object.Book;

import java.net.URL;
import java.util.ResourceBundle;

public class BorrowBookController extends BookController implements Initializable {
    public TableColumn remainingColumn;

    public Label titleBookDetailLabel;
    public Label publisherDetailLabel;
    public Label descriptionDetailLabel;
    public ImageView bookImage;

    public ListView<String> commentList;

    public Button borrowButton;

    private Book selectedBook;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // set up column for table
        configTable();
        remainingColumn.setCellValueFactory(new PropertyValueFactory<>("remaining"));
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
        Task<Void> getAllBookDataTask = new Task<Void>() {
            @Override
            protected Void call() {
                BookData.getDataAllBook(entireBooks);
                return null;
            }
        };

        getAllBookDataTask.setOnSucceeded(event -> {
            cloneListBook();
            System.out.println("Get all book data success");
        });

        getAllBookDataTask.setOnFailed(event -> {
            System.out.println("Get all book data failed");
        });

        new Thread(getAllBookDataTask).start();
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
        if (book.getImagePath() != null) {
            bookImage.setImage(new Image(book.getImagePath()));
        } else {
            bookImage.setImage(new Image("file:image/NoAvailable.jpg"));
        }

        ObservableList<String> comments = BookData.getCommentOfBook(book.getIsbn());
        commentList.setItems(comments);
        commentList.setPrefHeight( commentList.getFixedCellSize() * Math.min(comments.size() + 2, 10) );

        // check whether this book is borrowed by user
        if (BorrowData.isBorrowingBook(username, book.getIdBook())) {
            // book is borrowed by this user
            borrowButton.setText("Borrowed");
            borrowButton.setDisable(true);
            return;
        }

        // check whether this book already request to borrow
        if (RequestData.isPending(username, book.getIdBook())) {
            borrowButton.setText("Pending");
            borrowButton.setDisable(true);
            return;
        }

        // check valid quantity book
        if (book.getRemaining() == 0) {
            borrowButton.setText("All have been borrowed");
            borrowButton.setDisable(true);
        } else {
            borrowButton.setText("Borrow");
            borrowButton.setDisable(false);
        }
    }

    public void onBorrowButtonClicked(ActionEvent actionEvent) {
        borrowButton.setText("Pending");
        borrowButton.setDisable(true);

        // add borrow inform to database
//        BorrowData.addBorrowInfo(SignInViewController.username, selectedBook.getIdBook());
//
//        BookData.updateRemainingBook(selectedBook.getIdBook(), -1);
        RequestData.addRequestInfo(SignInViewController.username, selectedBook.getIdBook());

        // pass data to books
        getDataEntireBook();

        // set content of book table
        bookTable.setItems(shownBooks);
    }
}