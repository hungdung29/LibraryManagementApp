package org.app.Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.app.DataBase.BookData;
import org.app.DataBase.BorrowData;
import org.app.DataBase.RequestData;
import org.app.Object.BorrowRequest;

public class BorrowRequestController implements Initializable {
    public TableView<BorrowRequest> borrowRequestTable;

    public CheckBox selectAllCheckBox;

    public TableColumn<BorrowRequest, CheckBox> selectColumn;
    public TableColumn<BorrowRequest, String> usernameColumn;
    public TableColumn<BorrowRequest, String> bookTitleColumn;
    public TableColumn<BorrowRequest, String> dateRequestColumn;

    public Button acceptButton;
    public Button RejectButton;

    ObservableList<BorrowRequest> borrowRequests = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
	   configTable();

	   RequestData.getDataAllRequest(borrowRequests);

	   borrowRequestTable.setItems(borrowRequests);
    }

    /**
	* Handle accept button clicked.
	*
	* @param actionEvent event
	*/
    public void onAcceptButtonClicked(ActionEvent actionEvent) {
	   List<BorrowRequest> toRemove = new ArrayList<>();
	   for (BorrowRequest borrowRequest : borrowRequests) {
		  if (borrowRequest.getCheckBox().isSelected()) {
			 //System.out.println(borrowRequest.getBookTitle());

			 BorrowData.addBorrowInfo(borrowRequest.getUserName(), borrowRequest.getBookID());
			 BookData.updateRemainingBook(borrowRequest.getBookID(), -1);

			 RequestData.deleteRequestInfo(borrowRequest.getUserName(),
				    borrowRequest.getBookID());
			 toRemove.add(borrowRequest);
		  }
	   }
	   borrowRequests.removeAll(toRemove);
	   borrowRequestTable.setItems(borrowRequests);
    }

    /**
	* Handle reject button clicked.
	*
	* @param actionEvent event when reject button clicked
	*/
    public void onRejectButtonClicked(ActionEvent actionEvent) {
	   List<BorrowRequest> toRemove = new ArrayList<>();
	   for (BorrowRequest borrowRequest : borrowRequests) {
		  if (borrowRequest.getCheckBox().isSelected()) {
			 RequestData.deleteRequestInfo(SignInViewController.username,
				    borrowRequest.getBookID());

			 toRemove.add(borrowRequest);
		  }
	   }
	   borrowRequests.removeAll(toRemove);
	   borrowRequestTable.setItems(borrowRequests);
    }

    /**
	* Config table view.
	*/
    private void configTable() {
	   borrowRequestTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

	   selectColumn.setCellValueFactory(new PropertyValueFactory<BorrowRequest, CheckBox>(
			 "checkBox"));
	   usernameColumn.setCellValueFactory(new PropertyValueFactory<BorrowRequest, String>(
			 "userName"));
	   bookTitleColumn.setCellValueFactory(new PropertyValueFactory<BorrowRequest, String>(
			 "bookTitle"));
	   dateRequestColumn.setCellValueFactory(new PropertyValueFactory<BorrowRequest, String>(
			 "dateRequested"));
    }

    /**
	* Handle select all checkbox clicked.
	*
	* @param actionEvent event when select all checkbox clicked
	*/
    public void onAllCheckBoxClicked(ActionEvent actionEvent) {
	   boolean checked = selectAllCheckBox.isSelected();

	   for (BorrowRequest borrowRequest : borrowRequests) {
		  borrowRequest.getCheckBox().setSelected(checked);
	   }
    }
}
