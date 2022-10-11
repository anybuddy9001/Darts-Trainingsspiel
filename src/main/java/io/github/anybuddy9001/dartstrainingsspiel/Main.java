package io.github.anybuddy9001.dartstrainingsspiel;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.Getter;

import java.io.IOException;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * @author anybuddy
 * @author Specktulatius
 */
public class Main extends Application {
    private static ResourceBundle resourceBundle;
    @Getter
    private static Stage primaryStage;

    public static void main(String[] args) {
        Locale locale = JSONInterface.getCustomLanguage();
        if (locale == null) {
            resourceBundle = ResourceBundle.getBundle("lang");  //NON-NLS
        } else {
            resourceBundle = ResourceBundle.getBundle("lang", locale);  //NON-NLS
        }
        launch(args);
    }

    @Override
    public void init() {
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Main.primaryStage = primaryStage;
        Scene scene = new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("FXML/Launcher.fxml")), resourceBundle));
        primaryStage.setTitle(resourceBundle.getString("projectName") + resourceBundle.getString("launcherWindow.Title"));
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setOnCloseRequest(event -> Platform.exit());
        primaryStage.show();
    }

    @Override
    public void stop() {
        LogController.println(resourceBundle.getString("stdOut.exit")); //NON-NLS
    }
}
