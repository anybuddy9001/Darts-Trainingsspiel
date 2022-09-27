package io.github.anybuddy9001.dartstrainingsspiel;

import javafx.application.Application;
import javafx.application.Platform;
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
 */
public class Main extends Application {
    private static ResourceBundle resourceBundle;

    public static void main(String[] args) {
        resourceBundle = ResourceBundle.getBundle("lang");  //NON-NLS
        launch(args);
    }

    @Override
    public void init() {
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("FXML/Launcher.fxml")), resourceBundle);
        Scene scene = new Scene(root);
        primaryStage.setTitle(resourceBundle.getString("projectName") + resourceBundle.getString("launcherWindow.Title"));
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setOnCloseRequest(event -> Platform.exit());
        primaryStage.show();
    }

    @Override
    public void stop() {
        LogController.println(resourceBundle.getString("stdOut.exit"));
    }
}
