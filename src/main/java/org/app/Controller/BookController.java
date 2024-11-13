package org.app.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import javafx.scene.control.cell.PropertyValueFactory;
import org.app.Object.Book;

import java.net.URL;
import java.util.ResourceBundle;

public class BookController implements Initializable {
    public TextField searchTextField;
    public Button searchButton;

    public TableView<Book> bookTable;

    public TableColumn<Book, String> titleColumn;
    public TableColumn<Book, String> authorColumn;
    public TableColumn<Book, String> isbnBorrowColumn;
    public TableColumn<Book, Integer> remainingColumn;

    private final ObservableList<Book> books = FXCollections.observableArrayList();

    public void onSearchButtonClicked(ActionEvent actionEvent) {

    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        bookTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        setUpColumns();

        showBooks(UserController.tabType, UserController.username);
    }

    private void setUpColumns() {
        titleColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
        isbnBorrowColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("isbn"));
        remainingColumn.setCellValueFactory(new PropertyValueFactory<Book, Integer>("remaining"));
    }

    public void showBooks(String tabType, String username) {
        books.clear();
        if (tabType.equals("borrow")) {
            books.add(new Book("muon", "muon", "muon", "muon", 5));
        } else if (tabType.equals("return")) {
            books.add(new Book("tra", "tra", "tra", "tra", 5));
        }
        bookTable.setItems(books);
    }
}
