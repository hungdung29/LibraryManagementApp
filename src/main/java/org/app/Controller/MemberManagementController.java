package org.app.Controller;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import org.app.DataBase.BorrowData;
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
    public Label emailLabel;
    public Label birthdayLabel;
    public Label numCommentsLabel;

    public Button deleteButton;
    
    public TableView memberDetailTable;
    public TableColumn borrowedBookColumn;
    public TableColumn borrowedDateColumn;

    ObservableList<User> users;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // set up column for table
        configTable();
        infoMemberVBox.setVisible(false);

        users = UserData.getAllUsers();
        memberTable.setItems(users);

        memberTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
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
        configDetailTable();
        memberDetailTable.setItems(BorrowData.getBorrowedBooks(selectedUser.getUsername()));

        emailLabel.setText("Email: " + selectedUser.getEmail());
        birthdayLabel.setText("Birthday: " + selectedUser.getBirthday());
        numCommentsLabel.setText("Number of comments: " +
                 BorrowData.getNumberOfComments(selectedUser.getUsername()));
    }

    private void configDetailTable() {
        memberDetailTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        borrowedBookColumn.setCellValueFactory(new PropertyValueFactory<>("bookTitle"));
        borrowedDateColumn.setCellValueFactory(new PropertyValueFactory<>("borrowDate"));
    }

    private void configTable() {
        memberTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
//        numBorrowedBookColumn.setCellValueFactory();
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
