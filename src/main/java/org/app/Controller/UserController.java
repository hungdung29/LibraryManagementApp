package org.app.Controller;

import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.net.URL;
import java.util.ResourceBundle;

public class UserController implements Initializable {
    public static String username = "default";
    public static String tabType = "borrow";

    public TabPane userTabPane;

    public Tab user_info;
    public Tab borrow_book;
    public Tab return_book;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userTabPane.getSelectionModel().selectedItemProperty().addListener((obs, oldTab, newTab) -> {
            if (newTab == borrow_book) {
                UserController.tabType = "borrow";
            } else if (newTab == return_book) {
                UserController.tabType = "return";
            }
        });
    }

    public void setUsername(String username) {
        UserController.username = username;
    }


}