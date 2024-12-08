package org.app.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import org.app.DataBase.HandleUserAccount;
import org.app.DataBase.UserData;
import org.app.MainApp;
import org.app.Object.User;

public class ChangePasswordController implements Initializable {
    public Label messageLabel;
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

    /**
	* Handle change button clicked.
	*
	* @param actionEvent event
	*/
    public void onChangeButtonClicked(ActionEvent actionEvent) {
	   // check data have been typed in
	   if (!extractData()) {
		  messageLabel.setText("Please type in all cell");
		  return;
	   }

	   // verify old password
	   if (!oldPassword.equals(getOldPassword)) {
		  messageLabel.setText("Old password is incorrect");
		  cleanTextFields();
		  return;
	   }

	   // check valid password
	   if (!HandleUserAccount.checkValidPassword(newPassword, confirmPassword)) {
		  messageLabel.setText("Invalid password");
		  cleanTextFields();
		  return;
	   }

	   // change password
	   HandleUserAccount.changePassword(SignInViewController.username, newPassword);
	   messageLabel.setText("Change password successfully");
	   cleanTextFields();
	   try {
		  MainApp.navigateToScene("user-view.fxml");
	   } catch (IOException e) {
		  e.printStackTrace();
	   }
    }

    /**
	* Clean text fields.
	*/
    private void cleanTextFields() {
	   oldPasswordTextField.setText("");
	   newPasswordTextField.setText("");
	   confirmPassWordTextField.setText("");
    }

    /**
	* Handle back button clicked.
	*
	* @param actionEvent event
	*/
    public void onBackButtonClicked(ActionEvent actionEvent) {
	   try {
		  MainApp.navigateToScene("user-view.fxml");
	   } catch (IOException e) {
		  e.printStackTrace();
	   }
    }

    /**
	* Extract data from text fields.
	*
	* @return true if data is extracted successfully, false otherwise
	*/
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
