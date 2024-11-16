package org.app.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import org.app.DataBase.HandleUserAccount;
import org.app.DataBase.UserData;
import org.app.MainApp;
import org.app.Object.User;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ChangePasswordController implements Initializable {
    public PasswordField oldPasswordTextField;
    public PasswordField newPasswordTextField;
    public PasswordField confirmPassWordTextField;

    public Button changeButton;
    public Button backButton;

    private String oldPassword;
    private String newPassword;
    private String confirmPassword;
    private String getOldPassword;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        User user = UserData.getUserInfo(SignInViewController.username);
        getOldPassword = user.getPassword();
    }

    public void onChangeButtonClicked(ActionEvent actionEvent) {
        if ( !extractData() ) {
            System.out.println("Please type in all cell");
        }  else if (oldPassword.equals(getOldPassword)) {
            if (newPassword.equals(confirmPassword)) {
                HandleUserAccount.changePassword(SignInViewController.username, newPassword);
                try {
                    MainApp.navigateToScene("user-view.fxml");
                } catch (IOException e) {e.printStackTrace();}
            }
            else {
                System.out.println("New password and confirm password do not match");
            }
        }
        else {
            System.out.println("Old password is incorrect ");
        }
    }

    public void onBackButtonClicked(ActionEvent actionEvent) {
        try {
            MainApp.navigateToScene("user-view.fxml");
        } catch (IOException e) {e.printStackTrace();}
    }

    private boolean extractData() {
        oldPassword = oldPasswordTextField.getText();
        newPassword = newPasswordTextField.getText();
        confirmPassword = confirmPassWordTextField.getText();

        if (oldPassword.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
            return false;
        }

        return true;
    }
}
