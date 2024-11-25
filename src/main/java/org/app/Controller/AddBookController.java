package org.app.Controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
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

    Book bookSearched = new Book.Builder().build();

    public void onAddBookButtonClicked(ActionEvent actionEvent) {

    }

    public void onBackButtonClicked(ActionEvent actionEvent) {
        try {
            MainApp.navigateToScene("admin-view.fxml#book_management");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onSearchButtonClicked(ActionEvent actionEvent) {
        String search = searchTextField.getText();
        bookSearched = GoogleBookSearch.searchBook(search);
        if (bookSearched != null) {
            bookNameField.setText(bookSearched.getTitle());
            authorField.setText(bookSearched.getAuthor());
            publisherField.setText(bookSearched.getPublisher());
            isbnField.setText(bookSearched.getIsbn());
        }
    }

    public void onImportButtonClicked(ActionEvent actionEvent) {
        
        BookData.addBook(bookSearched);
    }
}