package org.app.Controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.app.Object.Book;

public class ReturnBookController extends BookController {
    public Label titleReturnBookLabel;
    public Label borrowDateLabel;
    public TextField commentTextField;

    public Button returnButton;

    public void onReturnButtonClicked(ActionEvent actionEvent) {

    }

    @Override
    public void handleBookSelection(String username, Book book) {

    }
}