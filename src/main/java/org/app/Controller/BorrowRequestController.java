package org.app.Controller;

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

import java.net.URL;
import java.util.ResourceBundle;

public class BorrowRequestController  implements Initializable {
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

    // delete selected request and add to borrow
    public void onAcceptButtonClicked(ActionEvent actionEvent) {
        for (BorrowRequest borrowRequest : borrowRequests) {
            if (borrowRequest.getCheckBox().isSelected()) {
                System.out.println(borrowRequest.getBookTitle());

                BorrowData.addBorrowInfo(borrowRequest.getUserName(), borrowRequest.getBookID());
                BookData.updateRemainingBook(borrowRequest.getBookID(), -1);

                RequestData.deleteRequestInfo(borrowRequest.getUserName(), borrowRequest.getBookID());
                borrowRequests.remove(borrowRequest);
            }
        }

        borrowRequestTable.setItems(borrowRequests);
    }

    // delete selected request
    public void onRejectButtonClicked(ActionEvent actionEvent) {
        for (BorrowRequest borrowRequest : borrowRequests) {
            if (borrowRequest.getCheckBox().isSelected()) {
                RequestData.deleteRequestInfo(SignInViewController.username, borrowRequest.getBookID());

                borrowRequests.remove(borrowRequest);
            }
        }
        borrowRequestTable.setItems(borrowRequests);
    }

    private void configTable() {
        borrowRequestTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        selectColumn.setCellValueFactory(new PropertyValueFactory<BorrowRequest, CheckBox>("checkBox"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<BorrowRequest, String>("userName"));
        bookTitleColumn.setCellValueFactory(new PropertyValueFactory<BorrowRequest, String>("bookTitle"));
        dateRequestColumn.setCellValueFactory(new PropertyValueFactory<BorrowRequest, String>("dateRequested"));
    }

    public void onAllCheckBoxClicked(ActionEvent actionEvent) {
        boolean checked = selectAllCheckBox.isSelected();

        for (BorrowRequest borrowRequest : borrowRequests) {
            borrowRequest.getCheckBox().setSelected(checked);
        }
    }
}
