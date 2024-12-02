package org.app.Controller;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.app.DataBase.HandleUserAccount;
import org.app.MainApp;

public class SignInViewController {
    public TextField usernameTextField;
    public PasswordField passwordTextField;
    public Button logInButton;
    public Label messageLabel;
    public Button backButton;
    public Label messageLabel1;

    public static String username = "admin";
    private String password;

    /**
	* Handle when button log in is clicked.
	*
	* @param actionEvent event
	*                    extract username, password
	*                    check account info in database
	*                    display message
	*                    navigate to next scene
	*/
    public void onLogInButtonClicked(ActionEvent actionEvent) {
	   extractData();

	   int check = HandleUserAccount.checkLogIn(SignInViewController.username, password);

	   navigate(check);
    }

    /**
	* Handle when button back is clicked.
	*
	* @param actionEvent event
	*/
    public void onBackButtonClicked(ActionEvent actionEvent) {
	   try {
		  MainApp.navigateToScene("hello-view.fxml");
	   } catch (IOException e) {
		  e.printStackTrace();
	   }
    }

    /**
	* Navigate to next scene.
	*
	* @param check check
	*/
    private void navigate(int check) {
	   if (check==HandleUserAccount.ACCOUNT_NOT_FOUND) {
		  messageLabel.setText("User not found. Sign up or try again");
		  usernameTextField.setText("");
		  passwordTextField.setText("");
	   } else if (check==HandleUserAccount.WRONG_PASSWORD) {
		  messageLabel.setText("Wrong password. Try a different password");
		  passwordTextField.setText("");
	   } else if (check==HandleUserAccount.NORM_USER_LOG_IN_SUCCESS) {
		  // login success
		  messageLabel.setText("Login successful!");
		  try {
			 switchToUserPanel();
		  } catch (IOException e) {
			 e.printStackTrace();
		  }
	   } else if (check==HandleUserAccount.ADMIN_LOG_IN_SUCCESS) {
		  messageLabel.setText("Admin logged in");
		  try {
			 MainApp.navigateToScene("admin-view.fxml");
		  } catch (IOException e) {
			 e.printStackTrace();
		  }
	   }
    }

    /**
	* Switch to user panel.
	*
	* @throws IOException exception
	*/
    private void switchToUserPanel() throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("user-view.fxml"));
//        Parent root = fxmlLoader.load();
//
//        UserController controller = fxmlLoader.getController();
//        controller.setUsername(username);
//
//        MainApp.setScene(root);
	   MainApp.navigateToScene("user-view.fxml");
    }

    private void extractData() {
	   SignInViewController.username = usernameTextField.getText();
	   password = passwordTextField.getText();
    }

    public void setMessageLabel(String message) {
	   messageLabel.setText(message);
    }
}
