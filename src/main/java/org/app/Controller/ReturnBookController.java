package org.app.Controller;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import org.app.DataBase.BookData;
import org.app.DataBase.BorrowData;
import org.app.Object.Book;

import java.net.URL;
import java.util.ResourceBundle;

public class ReturnBookController extends BookController implements Initializable {
    public TableColumn borrowedDateColumn;

    public ImageView bookImage;
    public Label titleReturnBookLabel;
    public Label borrowDateLabel;

    public TextField commentTextField;

    public VBox infoBookVBox;

    public Button returnButton;

    private Book selectedBook;

    public void initialize(URL location, ResourceBundle resources) {
        // set up column for table
        configTable();
        borrowedDateColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures, ObservableValue>() {
            @Override
            public ObservableValue call(TableColumn.CellDataFeatures cellDataFeatures) {
                return new SimpleObjectProperty<>(
                        BorrowData.getBorrowDate(SignInViewController.username,
                        (Book) cellDataFeatures.getValue()));
            }
        });
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
                BorrowData.getDataBorrowingBook(SignInViewController.username, entireBooks);
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
        cloneListBook();
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
        if (book.getImagePath() != null) {
            bookImage.setImage(new Image(book.getImagePath()));
        } else {
            bookImage.setImage(new Image("file:image/NoAvailable.jpg"));
        }

        commentTextField.setText("");
        titleReturnBookLabel.setText("Book title: " + book.getTitle());
        borrowDateLabel.setText("Borrow Date: " + BorrowData.getBorrowDate(username, book));
    }
}