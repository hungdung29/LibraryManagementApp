package org.app.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.app.DataBase.BookData;
import org.app.Object.Book;

import java.net.URL;
import java.util.ResourceBundle;

public class BorrowBookController extends BookController implements Initializable {
    public Label titleBookDetailLabel;
    public Label publisherDetailLabel;
    public Label descripDetailLabel;

    public ListView<String> commentList;

    public Button borrowButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configTable();

        BookData.getDataAllBook(books);
        
        bookTable.setItems(books);
    }

    public void onBorrowButtonClicked(ActionEvent actionEvent) {

    }


}
