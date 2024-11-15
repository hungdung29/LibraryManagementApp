package org.app.Controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import org.app.MainApp;

import java.io.IOException;

public class ChangePasswordController {
    public PasswordField oldPasswordTextField;
    public PasswordField newPasswordTextField;
    public PasswordField confirmPassWordTextField;

    public Button changeButton;
    public Button backButton;

    public void onChangeButtonClicked(ActionEvent actionEvent) {

    }

    public void onBackButtonClicked(ActionEvent actionEvent) {
        try {
            MainApp.navigateToScene("user-view.fxml");
        } catch (IOException e) {e.printStackTrace();}
    }
}
