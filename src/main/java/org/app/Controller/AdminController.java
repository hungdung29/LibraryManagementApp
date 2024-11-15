package org.app.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.app.Object.User;

public class AdminController
//        implements Initializable
{
    public Tab memberTab;
    @FXML
    private TableView<User> memberTable;
    @FXML
    private TableColumn<User, String> nameColumn;
    @FXML
    private TableColumn<User, String> phoneNumberColumn;
    @FXML
    private TableColumn<User, String> addressColumn;
    @FXML
    private TableColumn<User, Integer> nBorrowedColumn;

//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//        memberTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
//        MemberData memberData = new MemberData();
//
//        nameColumn.setCellValueFactory(new PropertyValueFactory<Member, String>("username"));
//        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<Member, String>("phoneNumber"));
//        addressColumn.setCellValueFactory(new PropertyValueFactory<Member, String>("address"));
//        nBorrowedColumn.setCellValueFactory(new PropertyValueFactory<Member, Integer>("numberOfBorrowedBooks"));
//
//        memberTable.setItems(memberData.getMembers());
//    }
}