package org.app.Controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
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

    public Button signUpButton;
    public Button backButton;

    private String username;
    private String password;
    private String confirmPassword;
    private String name;
    private String address;
    private String phoneNumber;

    public void onSignUpButtonClicked(ActionEvent actionEvent) {
        if ( !extractData() ) {
            messageLabel.setText("Please type in all cell");
        }

        if (HandleUserAccount.checkValidAccount(username, password, confirmPassword)) {
            User user = new User.Builder()
                    .setUsername(username)
                    .setPassword(password)
                    .setName(name)
                    .setAddress(address)
                    .setPhoneNumber(phoneNumber)
                    .build();

            HandleUserAccount.addAccount(user);

            try {
                FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("sign-in-view.fxml"));
                Parent root = fxmlLoader.load();

                SignInViewController controller = fxmlLoader.getController();
                controller.setMessageLabel("Sign up Successful");

                MainApp.setScene(root);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            messageLabel.setText("Invalid username or password");
            usernameTextField.setText("");
            passwordTextField.setText("");
            confirmPasswordTextField.setText("");
        }
    }

    public void onBackButtonClicked(ActionEvent actionEvent) {
        try {
            MainApp.navigateToScene("hello-view.fxml");
        } catch (Exception e) {e.printStackTrace();}
    }

    /**
     * extract user data
     * @return true if user have typed in enough data. Otherwise, return false
     */
    private boolean extractData() {
        username = usernameTextField.getText();
        password = passwordTextField.getText();
        confirmPassword = confirmPasswordTextField.getText();
        name = nameTextField.getText();
        address = addressTextField.getText();
        phoneNumber = phoneNumberTextField.getText();

        if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() ||
                name.isEmpty() || address.isEmpty() || phoneNumber.isEmpty()) {
            return false;
        }
        return true;
    }
}
