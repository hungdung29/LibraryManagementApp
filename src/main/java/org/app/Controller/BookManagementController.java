package org.app.Controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import org.app.DataBase.BookData;
import org.app.Object.Book;

import java.net.URL;
import java.util.ResourceBundle;

public class BookManagementController implements Initializable {
    public TextField searchTextField;
    public Button searchButton;

    public TableView bookTable;
    public TableColumn titleColumn;
    public TableColumn authorColumn;
    public TableColumn isbnBorrowColumn;
    public TableColumn quantityColumn;

    public VBox infoBookVBox;
    public Label titleBookDetailLabel;
    public Label publisherDetailLabel;
    public Label descriptionDetailLabel;
    public ListView commentList;

    public Button deleteButton;

    ObservableList<Book> books;

    public void onSearchButtonClicked(ActionEvent actionEvent) {
    }

    public void onDeleteButtonClicked(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    private void handleBookSelection(Book selectedBook) {
    }

    private void configTable() {
        bookTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        isbnBorrowColumn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
    }

}
