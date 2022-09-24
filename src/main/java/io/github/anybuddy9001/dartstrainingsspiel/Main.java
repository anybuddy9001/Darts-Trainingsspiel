package io.github.anybuddy9001.dartstrainingsspiel;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * @author anybuddy
 * @author Specktulatius
 * @version 3.0pre1
 */
public class Main extends Application {
    private static ResourceBundle resourceBundle;

    public static void main(String[] args) {
        resourceBundle = ResourceBundle.getBundle("lang");  //NON-NLS
        launch(args);
    }

    public void init() {
    }

    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("FXML/Launcher.fxml")), resourceBundle);
        Scene scene = new Scene(root);
        primaryStage.setTitle(resourceBundle.getString("launcherWindow.Title"));
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);
    }

    public void stop() {
    }
}
