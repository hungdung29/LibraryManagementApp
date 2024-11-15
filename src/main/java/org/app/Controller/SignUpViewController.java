package org.app.Controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import org.app.DataBase.HandleUserAccount;
import org.app.MainApp;

public class SignUpViewController {
    @FXML
    public TextField usernameText;
    public PasswordField pwText;
    public PasswordField cfPwText;
    public TextField nameText;
    public Button signUpButton;
    public Label messageLabel;
    public Button backButton;

    private String username;
    private String password;
    private String confirmPassword;
    private String name;

    private void requestFocus(TextField field) {
        field.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {

            }
        });
    }

    public void onSignUpButtonClicked(ActionEvent actionEvent) {
        HandleUserAccount handleUserAccount = new HandleUserAccount();

        extract();

        if (handleUserAccount.checkValidAccount(username, password, confirmPassword)) {
            handleUserAccount.addAccount(username, password, name);

            try {
//                MainApp.navigateToScene("hello-view.fxml");
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
            usernameText.setText("");
            pwText.setText("");
            cfPwText.setText("");
        }
    }

    public void onBackButtonClicked(ActionEvent actionEvent) {
        try {
            MainApp.navigateToScene("hello-view.fxml");
        } catch (Exception e) {e.printStackTrace();}
    }

    private void extract() {
        username = usernameText.getText();
        password = pwText.getText();
        confirmPassword = cfPwText.getText();
        name = nameText.getText();
    }
}
