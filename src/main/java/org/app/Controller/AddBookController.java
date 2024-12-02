package org.app.Controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.concurrent.Task;
import javafx.application.Application;
import org.app.BookAPI.GoogleBookSearch;
import org.app.DataBase.BookData;
import org.app.MainApp;
import org.app.Object.Book;

import java.net.URL;
import java.util.ResourceBundle;

public class AddBookController extends BookController implements Initializable {
    public TextField searchTextField;
    public Button searchButton;
    public Button addBookButton;
    public Button backButton;

    public TableView bookTable;
    public TableColumn titleColumn;
    public TableColumn authorColumn;
    public TableColumn categoryColumn;
    public TableColumn publisherColumn;

    public Button importButton;

    public TextField titleField;
    public TextField authorField;
    public TextField publisherField;
    public TextField isbnField;
    public TextField quantityField;
    public TextField descriptionField;

    public VBox infoBookVBox;
    public ImageView bookImage;
    public Label isbnBookDetailLabel;
    public Label languageDetailLabel;
    public Label descriptionDetailLabel;

    public Label messageLabel;

    private Book addBook = null;
    private Book choosingBook = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configTable();
        infoBookVBox.setVisible(false);

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
    }

    public void onBackButtonClicked(ActionEvent actionEvent) {
        try {
            MainApp.navigateToScene("admin-view.fxml#adminTabPane#book_management");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onSearchButtonClicked(ActionEvent actionEvent) {
        getDataEntireBook();
        bookTable.setItems(entireBooks);
    }

    public void configTable() {
        bookTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        titleColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("catalog"));
        publisherColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("publisher"));
    }

    public void onAddBookButtonClicked(ActionEvent actionEvent) {
        if (titleField.getText().isEmpty() || authorField.getText().isEmpty()
                || publisherField.getText().isEmpty() || isbnField.getText().isEmpty()
                || descriptionField.getText().isEmpty() || quantityField.getText().isEmpty()) {
            messageLabel.setText("Please fill in all fields");
        }
        else {
            // Check book from API or not
            if (addBook == null || (!addBook.getTitle().equals(titleField.getText())
                    || !addBook.getAuthor().equals(authorField.getText())
                    || !addBook.getPublisher().equals(publisherField.getText())
                    || !addBook.getIsbn().equals(isbnField.getText())
                    || !addBook.getDescription().equals(descriptionField.getText()))) {
                Book newBook = new Book.Builder()
                        .setTitle(titleField.getText())
                        .setAuthor(authorField.getText())
                        .setPublisher(publisherField.getText())
                        .setIsbn(isbnField.getText())
                        .setDescription(descriptionField.getText())
                        .setRemaining(Integer.parseInt(quantityField.getText()))
                        .build();
                BookData.addBook(newBook);
                try {
                    MainApp.navigateToScene("admin-view.fxml#adminTabPane#book_management");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                addBook.setRemaining(Integer.parseInt(quantityField.getText()));
                BookData.addBook(addBook);
                try {
                    MainApp.navigateToScene("admin-view.fxml#adminTabPane#book_management");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void onImportButtonClicked(ActionEvent actionEvent) {
        addBook = choosingBook;
        titleField.setText(addBook.getTitle());
        authorField.setText(addBook.getAuthor());
        publisherField.setText(addBook.getPublisher());
        isbnField.setText(addBook.getIsbn());
        descriptionField.setText(addBook.getDescription());
    }

    @Override
    public void handleBookSelection(String username, Book book) {
        choosingBook = book;
        if (book.getImagePath() != null) {
            bookImage.setImage(new Image(book.getImagePath()));
        }
        else {
            bookImage.setImage(new Image("file:image/NoAvailable.jpg"));
        }
        isbnBookDetailLabel.setText("ISBN: " + book.getIsbn());
        languageDetailLabel.setText("Language: " + book.getContent());
        descriptionDetailLabel.setText("Description: " + book.getDescription());
    }

    @Override
    public void getDataEntireBook() {
        String query = convertStringHaveSpacing(searchTextField.getText());
        if (query.isEmpty()) {
            messageLabel.setText("Please type in search field");
            return;
        }
        Task<ObservableList<Book>> getBookTask = new Task<ObservableList<Book>>() {
            @Override
            protected ObservableList<Book> call() throws Exception {
                return GoogleBookSearch.getBookFromAPI(query);
            }
        };
        getBookTask.setOnSucceeded(event -> {
            entireBooks.clear();
            entireBooks = getBookTask.getValue();
            bookTable.setItems(entireBooks);
            if (entireBooks != null) messageLabel.setText("Number of books found: " + entireBooks.size());
        });

        getBookTask.setOnFailed(event -> {
            Throwable exception = getBookTask.getException();
            exception.printStackTrace();;
            messageLabel.setText("Error occurred during searching");
        });

        Thread getBookFromAPIThread = new Thread(getBookTask);
        getBookFromAPIThread.setDaemon(true);
        getBookFromAPIThread.start();

        cloneListBook();
    }

    public String convertStringHaveSpacing(String str) {
        return str.replace(" ", "+");
    }
}