package org.app.Controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.app.Object.Book;

abstract public class BookController {
    public TextField searchTextField;
    public Button searchButton;
    public TableView<Book> bookTable;

    public TableColumn<Book, String> titleColumn;
    public TableColumn<Book, String> authorColumn;
    public TableColumn<Book, String> isbnBorrowColumn;
    public TableColumn<Book, Integer> remainingColumn;

    public void onSearchButtonClicked(ActionEvent actionEvent) {
    }
}
