package org.app.Controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.app.MainApp;

public class HelloController {
    public Button signInButton;
    public Button signUpButton;
    public Label messageLabel;

    /**
	* Handle sign in button clicked.
	*
	* @param actionEvent event
	*/
    public void onSignInButtonClick(ActionEvent actionEvent) {
	   try {
		  MainApp.navigateToScene2("sign-in-view.fxml", 473, 549);
	   } catch (Exception e) {
		  e.printStackTrace();
	   }
    }

    /**
	* Handle sign up button clicked.
	*
	* @param actionEvent event
	*/
    public void onSignUpButtonClicked(ActionEvent actionEvent) {
	   try {
		  MainApp.navigateToScene("sign-up-view.fxml");
	   } catch (Exception e) {
		  e.printStackTrace();
	   }
    }

    /**
	* Set message label.
	*
	* @param message message
	*/
    public void setMessageLabel(String message) {
	   messageLabel.setText(message);
    }
}