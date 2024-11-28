package org.app.Controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.app.BookAPI.GoogleBookSearch;
import org.app.DataBase.BookData;
import org.app.MainApp;
import org.app.Object.Book;

public class AddBookController {
    public TextField bookNameField;
    public TextField authorField;
    public TextField publisherField;
    public TextField isbnField;
    public TextField quantityField;

    public Button addBookButton;
    public Button backButton;

    public TextField searchTextField;
    public Button searchButton;
    public Button importButton;
    public Label messageLabel;

    Book searchedBook = new Book.Builder().build();

    public void onAddBookButtonClicked(ActionEvent actionEvent) {

    }

    public void onBackButtonClicked(ActionEvent actionEvent) {
        try {
            MainApp.navigateToScene("admin-view.fxml#adminTabPane#book_management");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onSearchButtonClicked(ActionEvent actionEvent) {
        String search = searchTextField.getText();
        searchedBook = GoogleBookSearch.searchBook(search);
        if (searchedBook != null) {
            bookNameField.setText(searchedBook.getTitle());
            authorField.setText(searchedBook.getAuthor());
            publisherField.setText(searchedBook.getPublisher());
            isbnField.setText(searchedBook.getIsbn());
            messageLabel.setText("Book found in Books Google API");
        }
        else {
            messageLabel.setText("Book not found in Books Google API");
        }
    }

    public void onImportButtonClicked(ActionEvent actionEvent) {
        if (searchedBook.getIsbn() != null) {
            BookData.addBook(searchedBook);
            try {
                MainApp.navigateToScene("admin-view.fxml#adminTabPane#book_management");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            messageLabel.setText("Import book failed");
        }
    }
}