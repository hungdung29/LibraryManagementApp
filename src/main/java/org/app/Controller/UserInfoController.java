package org.app.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.app.MainApp;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserInfoController implements Initializable {
    public Label nameLabel;
    public Label usernameLabel;
    public Label addressLabel;
    public Label phoneLabel;

    public Button changePassButton;
    public Button logOutButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        usernameLabel.setText("Username: " + SignInViewController.username);

    }

    public void onChangePassButtonClicked(ActionEvent actionEvent) {
        try {
            MainApp.navigateToScene("change-password-view.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onLogOutButtonClicked(ActionEvent actionEvent) {
        try {
            MainApp.navigateToScene("hello-view.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
