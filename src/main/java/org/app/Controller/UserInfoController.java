package org.app.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.app.DataBase.UserData;
import org.app.MainApp;
import org.app.Object.User;

public class UserInfoController implements Initializable {
    public Label nameLabel;
    public Label usernameLabel;
    public Label addressLabel;
    public Label phoneLabel;
    public Label emailLabel;
    public Label birthdayLabel;

    public Button changePassButton;
    public Button logOutButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
	   User user = UserData.getUserInfo(SignInViewController.username);

	   usernameLabel.setText("Username: " + user.getUsername());
	   addressLabel.setText("Address: " + user.getAddress());
	   nameLabel.setText("Name: " + user.getName());
	   phoneLabel.setText("Phone Number: " + user.getPhoneNumber());
	   emailLabel.setText("Email: " + user.getEmail());
	   birthdayLabel.setText("Birthday: " + user.getBirthday());
    }

    /**
	* Handle when change password button is clicked.
	*
	* @param actionEvent event
	*/
    public void onChangePassButtonClicked(ActionEvent actionEvent) {
	   try {
		  MainApp.navigateToScene("change-password-view.fxml");
	   } catch (Exception e) {
		  e.printStackTrace();
	   }
    }

    /**
	* Handle when log out button is clicked.
	*
	* @param actionEvent event
	*/
    public void onLogOutButtonClicked(ActionEvent actionEvent) {
	   try {
		  MainApp.navigateToScene("hello-view.fxml");
	   } catch (Exception e) {
		  e.printStackTrace();
	   }
    }
}
