package org.app.Controller;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import org.app.MainApp;
import org.app.Object.Book;

public class UserController {
    public Tab user_info;
    public Label nameLabel;
    public Label usernameLabel;
    public Label addressLabel;
    public Label phoneLabel;
    public Button changePassButton;
    public Button logOutButton;

    public Tab borrow_book;
    public TextField borrowSearchTextField;
    public Button borrowSearchButton;
    public TableView<Book> borrowBookTable;
    public TableColumn<Book, String> bookColumn;
    public TableColumn<Book, String> authorColumn;
    public TableColumn<Book, String> isbnBorrowColumn;
    public TableColumn<Book, Integer> remainingColumn;
    public Label nameBorrowBookDetailLabel;
    public Label publisherBorrowLabel;
    public Label descripDetailLabel;
    public ListView<String> commentList;
    public Button borrowButton;

    public Tab return_book;
    public TextField returnSearchTextField;
    public Button returnSearchButton;
    public TableView<Book> borrowedBookTable;
    public TableColumn<Book, String> bookBorrowedColumn;
    public TableColumn<Book, String> isbnBorrowedColumn;
    public Label nameReturnBookLabel;
    public Label borrowDateLabel;
    public Label commentLabel;
    public TextField commentTextField;
    public Button returnButton;


    public void onChangePassButtonClicked(ActionEvent actionEvent) {
        try {
            MainApp.navigateToScene("change-password-view.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onLogOutButtonClicked(ActionEvent actionEvent) {
        try {
            MainApp.navigateToScene("hello-view.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onBorrowSearchButtonClicked(ActionEvent actionEvent) {
    }

    public void onBorrowButtonClicked(ActionEvent actionEvent) {
    }

    public void onReturnsSearchClicked(ActionEvent actionEvent) {
    }

    public void onReturnButtonClicked(ActionEvent actionEvent) {
    }

    public static class AdminController {
    }
}
