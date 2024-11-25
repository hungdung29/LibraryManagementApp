package org.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import org.app.DataBase.CreateDataBase;
import org.app.DataBase.DataBaseAccessor;

import java.io.IOException;

public class MainApp extends Application {
    private static Stage currentStage;

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 800;

    @Override
    public void start(Stage stage) throws IOException {
        DataBaseAccessor.connect();
        CreateDataBase.createDatabase();
        CreateDataBase.addSample();

        currentStage = stage;
        navigateToScene("hello-view.fxml");
        stage.show();
    }

    public static void setScene(Parent root) {
        currentStage.setScene(new Scene(root, WIDTH, HEIGHT));
    }

    public static void navigateToScene(String fxmlFile) throws IOException {
        String[] parts = fxmlFile.split("#");
        String fxmlPath = parts[0];
        String fragment = parts.length > 1 ? parts[1] : null;

        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource(fxmlPath));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        currentStage.setScene(scene);

        if (fragment != null) {
            TabPane tabPane = (TabPane) scene.lookup("#adminTabPane");
            if (tabPane != null) {
                for (Tab tab : tabPane.getTabs()) {
                    if (tab.getId().equals(fragment)) {
                        tabPane.getSelectionModel().select(tab);
                        break;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        launch();
    }
}