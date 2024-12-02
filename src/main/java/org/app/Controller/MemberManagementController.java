package org.app.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import org.app.DataBase.BorrowData;
import org.app.DataBase.UserData;
import org.app.MainApp;
import org.app.Object.User;

public class MemberManagementController implements Initializable {
    public TextField searchTextField;
    public Button searchButton;

    public TableView memberTable;
    public TableColumn nameColumn;
    public TableColumn phoneColumn;
    public TableColumn addressColumn;
    public TableColumn numBorrowedBookColumn;

    public VBox infoMemberVBox;
    public Label emailLabel;
    public Label birthdayLabel;
    public Label numCommentsLabel;

    public Button inboxButton;

    public TableView memberDetailTable;
    public TableColumn borrowedBookColumn;
    public TableColumn borrowedDateColumn;

    ObservableList<User> users;

    private User selectedUser;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
	   // set up column for table
	   configTable();
	   infoMemberVBox.setVisible(false);

	   users = UserData.getAllUsers();
	   memberTable.setItems(users);

	   memberTable.getSelectionModel().selectedItemProperty().addListener(
			 (observable, oldValue, newValue) -> {
				if (newValue!=null) {
				    infoMemberVBox.setVisible(true);

				    selectedUser = (User) newValue;
				    handleMemberSelection(selectedUser);
				} else {
				    infoMemberVBox.setVisible(false);
				}
			 });
    }

    /**
	* Handle member selection.
	*
	* @param selectedUser selected user
	*/
    private void handleMemberSelection(User selectedUser) {
	   configDetailTable();
	   memberDetailTable.setItems(BorrowData.getBorrowingBooks(selectedUser.getUsername()));

	   emailLabel.setText("Email: " + selectedUser.getEmail());
	   birthdayLabel.setText("Birthday: " + selectedUser.getBirthday());
	   numCommentsLabel.setText("Number of comments: " +
			 BorrowData.getNumberOfComments(selectedUser.getUsername()));
    }

    /**
	* Config detail table.
	*/
    private void configDetailTable() {
	   memberDetailTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

	   borrowedBookColumn.setCellValueFactory(new PropertyValueFactory<>("bookTitle"));
	   borrowedDateColumn.setCellValueFactory(new PropertyValueFactory<>("borrowDate"));
    }

    /**
	* Config table.
	*/
    private void configTable() {
	   memberTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

	   nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
	   phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
	   addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
	   numBorrowedBookColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures,
			 ObservableValue>() {
		  @Override
		  public ObservableValue call(TableColumn.CellDataFeatures cellDataFeatures) {
			 return new SimpleIntegerProperty(((User)
				    cellDataFeatures.getValue()).getNumBorrowingBooks());
		  }
	   });
    }

    /**
	* Handle search button clicked.
	*
	* @param actionEvent event
	*/
    public void onSearchButtonClicked(ActionEvent actionEvent) {
	   users = UserData.getAllUsers();
	   String keyword = searchTextField.getText();

	   if (keyword==null || keyword.isEmpty()) {
		  memberTable.setItems(users);
		  return;
	   }
	   FilteredList<User> filteredUsers = new FilteredList<>(users, user ->
			 user.getName().toLowerCase().contains(keyword.toLowerCase()) ||
				    user.getPhoneNumber().toLowerCase().contains(keyword.toLowerCase()) ||
				    user.getAddress().toLowerCase().contains(keyword.toLowerCase())
	   );

	   memberTable.setItems(filteredUsers);
    }

    /**
	* Handle inbox button clicked.
	*
	* @param actionEvent event.
	*/
    public void onInboxButtonClicked(ActionEvent actionEvent) {
	   try {
		  MainApp.navigateToScene("admin-view.fxml#adminTabPane#inbox");
	   } catch (Exception e) {
		  e.printStackTrace();
	   }
    }
}
