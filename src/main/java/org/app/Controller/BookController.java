package org.app.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import org.app.DataBase.BookData;
import org.app.Object.Book;

import java.util.function.Predicate;

abstract public class BookController {
    public TextField searchTextField;
    public Button searchButton;

    protected ObservableList<Book> books = FXCollections.observableArrayList();

    public TableView<Book> bookTable;
    public TableColumn<Book, String> titleColumn;
    public TableColumn<Book, String> authorColumn;
    public TableColumn<Book, String> isbnBorrowColumn;
    public TableColumn<Book, Integer> remainingColumn;

    public void onSearchButtonClicked(ActionEvent actionEvent) {
        String keyword = searchTextField.getText();
        BookData.getDataAllBook(books);
        if (keyword == null || keyword.isEmpty()) {
            return;
        }
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
                continue;
            } else if (book.getAuthor().toLowerCase().contains(keyword.toLowerCase())) {
                continue;
            } else if (book.getIsbn().toLowerCase().contains(keyword.toLowerCase())) {
                continue;
            } else {
                books.remove(book);
            }
        }
    }

    protected void configTable() {
        bookTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        titleColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
        isbnBorrowColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("isbn"));
        remainingColumn.setCellValueFactory(new PropertyValueFactory<Book, Integer>("remaining"));
    }


}
