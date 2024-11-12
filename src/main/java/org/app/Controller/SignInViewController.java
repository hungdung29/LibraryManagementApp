package org.app.Controller;

import com.sun.tools.javac.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.app.DataBase.HandleUserAccount;
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

        HandleUserAccount handleUserAccount = new HandleUserAccount();
        int check = handleUserAccount.checkLogIn(username, password);

        navigate(check);
    }

    public void onBackButtonClicked(ActionEvent actionEvent) {
        try {
            MainApp.navigateToScene("hello-view.fxml");
        } catch (IOException e) { e.printStackTrace(); }
    }

    private void navigate(int check) {
        if (check == HandleUserAccount.ACCOUNT_NOT_FOUND) {
            messageLabel.setText("User not found");
            usernameTextField.setText("");
            passwordTextField.setText("");
        } else if (check == HandleUserAccount.WRONG_PASSWORD) {
            messageLabel.setText("Wrong password");
            passwordTextField.setText("");
        } else if (check == HandleUserAccount.LOG_IN_SUCCESS){
            // login success
            messageLabel.setText("Login successful!");
            try {
                switchToUserPanel();
            } catch (IOException e) { e.printStackTrace(); }
        } else if (check == HandleUserAccount.ADMIN) {
            messageLabel.setText("Admin logged in");
            try {
                MainApp.navigateToScene("admin-view.fxml");
            } catch (IOException e) { e.printStackTrace(); }
        }
    }

    private void switchToUserPanel() throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("user-view.fxml"));
//        Parent root = fxmlLoader.load();
//
//        HelloUserController controller = fxmlLoader.getController();
//        controller.setLabel(username);
//
//        MainApp.setScene(root);

        MainApp.navigateToScene("admin-view.fxml");
        //MainApp.navigateToScene("user-view.fxml");
    }

    private void extractData() {
        username = usernameTextField.getText();
        password = passwordTextField.getText();
    }

    public void setMessageLabel(String message) {
        messageLabel.setText(message);
    }
}
