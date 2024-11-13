package org.app.Controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.app.MainApp;

import java.io.IOException;

public class UserInfoController {
    public Label nameLabel;
    public Label usernameLabel;
    public Label addressLabel;
    public Label phoneLabel;

    public Button changePassButton;
    public Button logOutButton;

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
