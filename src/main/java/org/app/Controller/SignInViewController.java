package org.app.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import net.synedra.validatorfx.Check;
import org.app.DataBase.CheckUserAccountLogIn;
import org.app.MainApp;

import java.io.IOException;

public class SignInViewController {
    public TextField usernameTextField;
    public PasswordField passwordTextField;
    public Button logInButton;
    public Label messageLabel;
    public Button backButton;

    private String username;
    private String password;

    /**
     * Handle when button log in is clicked
     * @param actionEvent event
     * extract username, password
     * check account info in database
     * display message
     * navigate to next scene
     */
    public void onLogInButtonClicked(ActionEvent actionEvent) {
        extractData();

        int check = checkLogin();

        navigate(check);
    }

    public void onBackButtonClicked(ActionEvent actionEvent) {
        try {
            MainApp.navigateToScene("hello-view.fxml");
        } catch (IOException e) { e.printStackTrace(); }
    }

    private void navigate(int check) {
        if (check == CheckUserAccountLogIn.ACCOUNT_NOT_FOUND) {
            messageLabel.setText("User not found");
            usernameTextField.setText("");
            passwordTextField.setText("");
        } else if (check == CheckUserAccountLogIn.WRONG_PASSWORD) {
            messageLabel.setText("Wrong password");
            passwordTextField.setText("");
        } else {
            // login success
            messageLabel.setText("Login successful!");
            try {
                switchToUserPanel();
            } catch (IOException e) { e.printStackTrace(); }
        }
    }

    private void switchToUserPanel() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("hello-user-view.fxml"));
        Parent root = fxmlLoader.load();

        HelloUserController controller = fxmlLoader.getController();
        controller.setLabel(username);

        MainApp.setScene(root);
    }

    private int checkLogin() {
        return CheckUserAccountLogIn.checkLogIn(username, password);
    }

    private void extractData() {
        username = usernameTextField.getText();
        password = passwordTextField.getText();
    }
}
