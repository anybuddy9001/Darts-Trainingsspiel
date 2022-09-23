package io.github.anybuddy9001.dartstrainingsspiel;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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
 * @version 2.1
 */
public class Main extends Application {
    //    private static Locale fallBackLocale = new Locale("de");
    @Getter
    private static ResourceBundle resourceBundle;

    public static void main(String[] args) {
        Locale locale = new Locale("de");
        resourceBundle = ResourceBundle.getBundle("lang", locale);  //NON-NLS
        launch(args);
    }

    public void init() {
    }

    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("FXML/Main.fxml")), resourceBundle);
        Scene scene = new Scene(root);
        primaryStage.setTitle(resourceBundle.getString("mainWindow.Title"));
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);
    }

    public void stop() {
    }
}
