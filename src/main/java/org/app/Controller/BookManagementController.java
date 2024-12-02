package org.app.Controller;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import javafx.util.converter.IntegerStringConverter;
import org.app.DataBase.BookData;
import org.app.DataBase.BorrowData;
import org.app.MainApp;
import org.app.Object.Book;

import java.net.URL;
import java.util.ResourceBundle;

public class BookManagementController extends BookController implements Initializable {
    public TextField searchTextField;
    public SplitPane bookForm;

    public TableView bookTable;
    public TableColumn titleColumn;
    public TableColumn authorColumn;
    public TableColumn isbnBorrowColumn;
    public TableColumn remainingColumn;
    public TableColumn quantityColumn;

    public VBox infoBookVBox;
    public ImageView bookImage;
    public Label titleBookDetailLabel;
    public Label publisherDetailLabel;
    public Label descriptionDetailLabel;
    public ListView commentList;

    public Button inboxButton;
    public Button addButton;
    public Button modifyButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // set up column for table
        configBookManagementTable();
        infoBookVBox.setVisible(false);

        // pass data to books
        getDataEntireBook();

        // set content of book table
        bookTable.setItems(shownBooks);

        // Add a listener for row selection
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
        bookTable.setEditable(false);
        inboxButton.setDisable(true);
    }

    @Override
    public void handleBookSelection(String username, Book selectedBook) {
        if (selectedBook.getImagePath() != null) {
            bookImage.setImage(new Image(selectedBook.getImagePath()));
        } else {
            bookImage.setImage(new Image("file:image/NoAvailable.jpg"));
        }

        titleBookDetailLabel.setText("Title: " + selectedBook.getTitle());
        publisherDetailLabel.setText("Publisher: " + selectedBook.getPublisher());
        descriptionDetailLabel.setText("Description: " + selectedBook.getDescription());
        commentList.setItems(BookData.getCommentOfBook(selectedBook.getIsbn()));
    }

    @Override
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

    public void onDeleteButtonClicked(ActionEvent actionEvent) {
        Book selectedBook = (Book) bookTable.getSelectionModel().getSelectedItem();
        if (selectedBook != null) {
            BorrowData.deleteBook(selectedBook);
            entireBooks.remove(selectedBook);
            getDataEntireBook();
            bookTable.setItems(shownBooks);
        }
    }

    private void configBookManagementTable() {
        super.configTable();
        remainingColumn.setCellValueFactory(new PropertyValueFactory<Book, Integer>("remaining"));
        quantityColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures,
                ObservableValue>() {
            @Override
            public ObservableValue call(TableColumn.CellDataFeatures cellDataFeatures) {
                return new SimpleIntegerProperty(((Book)
                        cellDataFeatures.getValue()).getQuantity()).asObject();
            }
        });
        quantityColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        quantityColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Book, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Book, Integer> cellEditEvent) {
                Book book = cellEditEvent.getRowValue();
                BookData.updateRemainingBook(book.getIdBook(),
                        cellEditEvent.getNewValue() - cellEditEvent.getOldValue());
            }
        });
    }

    public void onAddButtonClicked(ActionEvent actionEvent) {
        try {
            MainApp.navigateToScene("add-book-view.fxml");
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void onModifyButtonClicked(ActionEvent actionEvent) {
        if (modifyButton.getText().equals("Modify")) {
            bookTable.setEditable(true);
            modifyButton.setText("Save");
            inboxButton.setDisable(false);
        } else {
            bookTable.setEditable(false);
            modifyButton.setText("Modify");
            inboxButton.setDisable(true);

            //refresh table
            getDataEntireBook();
            bookTable.setItems(shownBooks);
        }
    }
}
