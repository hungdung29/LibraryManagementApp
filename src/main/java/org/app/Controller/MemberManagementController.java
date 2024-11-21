package org.app.Controller;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import org.app.DataBase.UserData;
import org.app.Object.User;

import java.net.URL;
import java.util.ResourceBundle;

public class MemberManagementController implements Initializable {

    public TextField searchTextField;
    public Button searchButton;
    
    public TableView memberTable;    
    public TableColumn nameColumn;
    public TableColumn phoneColumn;
    public TableColumn addressColumn;
    public TableColumn numBorrowedBookColumn;
    
    public VBox infoMemberVBox;
    public Label gmailLabel;
    public Label birthdayLabel;
    public Label numCommentsLabel;
    public ListView bookList;

    public Button deleteButton;

    ObservableList<User> users;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // set up column for table
        configTable();
        infoMemberVBox.setVisible(false);

        users = UserData.getAllUsers();
        memberTable.setItems(users);

        memberTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                infoMemberVBox.setVisible(true);

                User selectedUser = (User) newValue;
                handleMemberSelection(selectedUser);
            } else {
                infoMemberVBox.setVisible(false);
            }
        });


    }

    private void handleMemberSelection(User selectedUser) {
//        gmailLabel.setText("Gmail: " + selectedUser.getEmail());
//        birthdayLabel.setText("Birthday: " + selectedUser.getBirthday());
//        numCommentsLabel.setText("Number of comments: " + selectedUser.getNumComments());
//        bookList.setItems(selectedUser.getComments());
    }

    private void configTable() {
        memberTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        numBorrowedBookColumn.setCellValueFactory(new PropertyValueFactory<>("numBorrowedBook"));
    }

    public void onSearchButtonClicked(ActionEvent actionEvent) {
        String keyword = searchTextField.getText();

        if (keyword == null || keyword.isEmpty()) {
            return;
        }
        FilteredList<User> filteredUsers = new FilteredList<>(users, user ->
                user.getName().toLowerCase().contains(keyword.toLowerCase()) ||
                        user.getPhoneNumber().toLowerCase().contains(keyword.toLowerCase()) ||
                        user.getAddress().toLowerCase().contains(keyword.toLowerCase())
        );

        memberTable.setItems(filteredUsers);
    }

    public void onDeleteButtonClicked(ActionEvent actionEvent) {
    }


}
