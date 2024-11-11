package org.app.Controller;

import com.sun.tools.javac.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.app.MainApp;

import java.io.IOException;

public class HelloController {
    public Button signInButton;
    public Button signUpButton;
    public Label messageLabel;

    public void onSignInButtonClick(ActionEvent actionEvent) {
        try {
            MainApp.navigateToScene("sign-in-view.fxml");
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void onSignUpButtonClicked(ActionEvent actionEvent) {
        try {
            MainApp.navigateToScene("sign-up-view.fxml");
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void setMessageLabel(String message) {
        messageLabel.setText(message);
    }
}