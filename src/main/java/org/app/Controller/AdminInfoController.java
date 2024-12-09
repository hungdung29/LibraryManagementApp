package org.app.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.app.DataBase.BookData;
import org.app.DataBase.BorrowData;
import org.app.MainApp;

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

    /**
	* Handle log out button clicked.
	*
	* @param actionEvent event
	*/
    public void onLogOutButton(ActionEvent actionEvent) {
	   try {
		  MainApp.navigateToScene("hello-view.fxml", MainApp.SMALL_WIDTH, MainApp.SMALL_HEIGHT);
	   } catch (Exception e) {
		  e.printStackTrace();
	   }
    }
}
