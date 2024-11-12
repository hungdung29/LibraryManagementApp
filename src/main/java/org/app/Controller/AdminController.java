package org.app.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.app.DataBase.MemberData;
import org.app.Object.Member;

public class AdminController {
    @FXML
    private TableView<Member> memberTable;
    @FXML
    private TableColumn<Member, String> nameColumn;
    @FXML
    private TableColumn<Member, String> phoneNumberColumn;
    @FXML
    private TableColumn<Member, String> addressColumn;
    @FXML
    private TableColumn<Member, Integer> nBorrowedColumn;

    @FXML
    private void initialize() {
//        MemberData memberData = new MemberData();

        nameColumn.setCellValueFactory(new PropertyValueFactory<Member, String>("username"));
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<Member, String>("phoneNumber"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<Member, String>("address"));
        nBorrowedColumn.setCellValueFactory(new PropertyValueFactory<Member, Integer>("numberOfBorrowedBooks"));

        ObservableList<Member> members = FXCollections.observableArrayList(
                new Member("t", "11", "tt", 1),
                new Member("t", "11", "tt", 1)
                );

        memberTable.setItems(members);
//        memberTable.setItems(memberData.getMembers());
    }
}