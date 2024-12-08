package org.app.Controller;

import com.sun.tools.javac.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.app.DataBase.HandleUserAccount;
import org.app.MainApp;
import org.app.Object.User;

public class SignUpViewController {
    public Label messageLabel;

    public TextField usernameTextField;
    public PasswordField passwordTextField;
    public PasswordField confirmPasswordTextField;
    public TextField nameTextField;
    public TextField addressTextField;
    public TextField phoneNumberTextField;
    public TextField emailTextField;
    public DatePicker birthdayPicker;

    public Button signUpButton;
    public Button backButton;

    private String username;
    private String password;
    private String confirmPassword;
    private String name;
    private String address;
    private String phoneNumber;
    private String email;
    private String birthday;

    /**
	* Handle when button sign up is clicked.
	*
	* @param actionEvent event
	*/
    public void onSignUpButtonClicked(ActionEvent actionEvent) {
	   if (!extractData()) {
		  messageLabel.setText("Please type in all cell");
	   }

	   int checkValidAccount = HandleUserAccount.checkValidAccount(username, password, confirmPassword, email);
	   if (checkValidAccount == HandleUserAccount.VALID_ACCOUNT) {
		  User user = new User.Builder()
				.setUsername(username)
				.setPassword(password)
				.setName(name)
				.setAddress(address)
				.setPhoneNumber(phoneNumber)
				.setBirthday(birthday)
				.setEmail(email)
				.build();

		  HandleUserAccount.addAccount(user);

		  try {
			 FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("sign-in-view.fxml"));
			 Parent root = fxmlLoader.load();

			 SignInViewController controller = fxmlLoader.getController();
			 controller.setMessageLabel("Sign up Successful. Sign in now");

			 MainApp.setScene(root, MainApp.SMALL_WIDTH, MainApp.SMALL_HEIGHT);
		  } catch (Exception e) {
			 e.printStackTrace();
		  }
	   } else if (checkValidAccount == HandleUserAccount.USERNAME_HAS_SPACE) {
		   messageLabel.setText("Username must not contain spaces");
		   usernameTextField.setText("");
	   } else if (checkValidAccount == HandleUserAccount.USERNAME_ALREADY_EXISTS) {
		  messageLabel.setText("Username already exists");
		  usernameTextField.setText("");
	   } else if (checkValidAccount == HandleUserAccount.INVALID_PASSWORD) {
		   messageLabel.setText("Invalid password");
		   passwordTextField.setText("");
		   confirmPasswordTextField.setText("");
	   } else if (checkValidAccount == HandleUserAccount.INVALID_EMAIL) {
		   messageLabel.setText("Invalid email");
		   emailTextField.setText("");
	   }
	}

    /**
	* Handle when button back is clicked.
	*
	* @param actionEvent event
	*/
    public void onBackButtonClicked(ActionEvent actionEvent) {
	   try {
		  MainApp.navigateToScene("hello-view.fxml", MainApp.SMALL_WIDTH, MainApp.SMALL_HEIGHT);
	   } catch (Exception e) {
		  e.printStackTrace();
	   }
    }

    /**
	* extract user data
	*
	* @return true if user have typed in enough data. Otherwise, return false
	*/
    private boolean extractData() {
	   username = usernameTextField.getText();
	   if (username==null || username.isEmpty()) {
		  return false;
	   }

	   password = passwordTextField.getText();
	   if (password==null || password.isEmpty()) {
		  return false;
	   }

	   confirmPassword = confirmPasswordTextField.getText();
	   if (confirmPassword==null || confirmPassword.isEmpty()) {
		  return false;
	   }

	   name = nameTextField.getText();
	   if (name==null || name.isEmpty()) {
		  return false;
	   }

	   address = addressTextField.getText();
	   if (address==null || address.isEmpty()) {
		  return false;
	   }

	   phoneNumber = phoneNumberTextField.getText();
	   if (phoneNumber==null || phoneNumber.isEmpty()) {
		  return false;
	   }

	   email = emailTextField.getText();
	   if (email==null || email.isEmpty()) {
		  return false;
	   }

	   birthday = birthdayPicker.getValue().toString();
	   if (birthday.isEmpty()) {
		  return false;
	   }

	   return true;
    }
}
