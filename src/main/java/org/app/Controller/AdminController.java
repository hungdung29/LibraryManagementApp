package org.app.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.app.DataBase.MemberData;
import org.app.Object.Member;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    public Tab memberTab;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        memberTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        MemberData memberData = new MemberData();

        nameColumn.setCellValueFactory(new PropertyValueFactory<Member, String>("username"));
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<Member, String>("phoneNumber"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<Member, String>("address"));
        nBorrowedColumn.setCellValueFactory(new PropertyValueFactory<Member, Integer>("numberOfBorrowedBooks"));

        memberTable.setItems(memberData.getMembers());
    }
}