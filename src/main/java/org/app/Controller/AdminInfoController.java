package org.app.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.app.DataBase.BookData;
import org.app.DataBase.BorrowData;
import org.app.MainApp;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminInfoController implements Initializable {
    public Label numRemainingBooksLabel;
    public Label numBorrowedBooksLabel;
    public Label numTotalBooksLabel;
    
    public Button logOutButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Integer numBorrowedBooks = BorrowData.getTotalBorrowedBooks();
        Integer numRemainingBooks = BookData.getTotalRemainingBooks();
        Integer numTotalBooks = numRemainingBooks + numBorrowedBooks;
        numRemainingBooksLabel.setText("Remaining book(s): " + numRemainingBooks.toString());
        numBorrowedBooksLabel.setText("Borrowed book(s): " + numBorrowedBooks.toString());
        numTotalBooksLabel.setText("Total book(s): " + numTotalBooks.toString());
    }

    public void onLogOutButton(ActionEvent actionEvent) {
        try {
            MainApp.navigateToScene("hello-view.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
