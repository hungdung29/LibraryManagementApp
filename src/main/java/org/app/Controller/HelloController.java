package org.app.Controller;

import com.sun.tools.javac.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.app.MainApp;

public class HelloController {
    public Button signInButton;

    public void onSignInButtonClick(ActionEvent actionEvent) {
        try {
            MainApp.navigateToScene("sign-in-view.fxml");
        } catch (Exception e) { e.printStackTrace(); }
    }
}