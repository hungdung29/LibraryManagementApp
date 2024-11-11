package org.app.Controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.app.MainApp;

public class HelloUserController {
    public Label helloLabel;
    public Button logOutButton;

    public void onLogOutButtonClicked(ActionEvent actionEvent) {
        try {
            MainApp.navigateToScene("hello-view.fxml");
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void setLabel(String username) {
        helloLabel.setText("Hello " + username);
    }
}
