package org.app.Controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import org.app.Object.Book;

public class BorrowBookController extends BookController {
    public Label titleBookDetailLabel;
    public Label publisherDetailLabel;
    public Label descripDetailLabel;

    public ListView<String> commentList;

    public Button borrowButton;
    public TableView<Book> bookTable;

    public void onBorrowButtonClicked(ActionEvent actionEvent) {
    }
}
