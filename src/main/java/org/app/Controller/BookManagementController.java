package org.app.Controller;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import org.app.DataBase.BookData;
import org.app.DataBase.BorrowData;
import org.app.MainApp;
import org.app.Object.Book;

import java.net.URL;
import java.util.ResourceBundle;

public class BookManagementController extends BookController implements Initializable {
    public TextField searchTextField;

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
    public Button addButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // set up column for table
        configBookManagementTable();
        infoBookVBox.setVisible(false);

        // pass data to books
        getDataEntireBook();

        // set content of book table
        bookTable.setItems(shownBooks);

        bookTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
            if (newValue != null) {
                infoBookVBox.setVisible(true);

                Book selectedBook = (Book) newValue;
                handleBookSelection("admin", selectedBook);
            } else {
                infoBookVBox.setVisible(false);
            }
        });
    }

    @Override
    public void handleBookSelection(String username, Book selectedBook) {
        titleBookDetailLabel.setText("Title: " + selectedBook.getTitle());
        publisherDetailLabel.setText("Publisher: " + selectedBook.getPublisher());
        descriptionDetailLabel.setText("Description: " + selectedBook.getDescription());
        commentList.setItems(BookData.getCommentOfBook(selectedBook.getIsbn()));
    }

    @Override
    public void getDataEntireBook() {
        BookData.getDataAllBook(entireBooks);
        cloneListBook();
    }

    public void onDeleteButtonClicked(ActionEvent actionEvent) {
    }

    private void configBookManagementTable() {
        bookTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        super.configTable();
        quantityColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures,
                ObservableValue>() {
            @Override
            public ObservableValue call(TableColumn.CellDataFeatures cellDataFeatures) {
                return new SimpleIntegerProperty(((Book)
                        cellDataFeatures.getValue()).getQuantity()).asObject();
            }
        });
    }

    public void onAddButtonClicked(ActionEvent actionEvent) {
        try {
            MainApp.navigateToScene("add-book-view.fxml");
        } catch (Exception e) { e.printStackTrace(); }
    }
}
