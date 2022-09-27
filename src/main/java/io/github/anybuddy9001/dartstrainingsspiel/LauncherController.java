package io.github.anybuddy9001.dartstrainingsspiel;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lombok.Getter;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * Controller for the launcher window
 *
 * @author anybuddy
 */
public class LauncherController implements Initializable {
    @Getter
    private static ResourceBundle resourceBundle;
    @Getter
    private static Stage logWindow;

    @FXML
    public Text activeLanguage;
    @FXML
    public TextField duration;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        resourceBundle = resources;
        switch (resources.getLocale().toString()) {
            case "en" -> activeLanguage.setText("English"); //NON-NLS
            case "de" -> activeLanguage.setText("German"); //NON-NLS
        }
        if (logWindow == null) {
            createNewLogWindow();
        }
    }

    private void createNewLogWindow() {
        logWindow = new Stage();
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("FXML/Log.fxml")), resourceBundle);
            Scene scene = new Scene(root);
            logWindow.setTitle(resourceBundle.getString("projectName") + resourceBundle.getString("logWindow.Title"));
            logWindow.setScene(scene);
            logWindow.setResizable(true);
        } catch (IOException e) {
            LogController.println(resourceBundle.getString("mError.log.open") + "\n" + e);
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void startChallengeMode() {
        LogController.println("Challenge Mode is not yet implemented"); //NON-NLS
        Stage window = (Stage) activeLanguage.getScene().getWindow();
        logWindow.setX(window.getX() + window.getWidth());
        logWindow.setY(window.getY());
        logWindow.show();
    }

    @FXML
    public void startEndlessMode() {
        Stage window = (Stage) activeLanguage.getScene().getWindow();
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("FXML/Game.fxml")), resourceBundle);
            Scene scene = new Scene(root);
            window.setTitle(resourceBundle.getString("projectName") + resourceBundle.getString("gameWindow.Title"));
            window.setScene(scene);
            window.setResizable(false);
            window.setOnCloseRequest(event -> Platform.exit());
            logWindow.setX(window.getX() + window.getWidth());
            logWindow.setY(window.getY());
            logWindow.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void quit() {
        Platform.exit();
    }

    @FXML
    public void setLanguageGerman() {
        Locale locale = new Locale("de"); //NON-NLS
        this.reset(locale);
    }

    @FXML
    public void setLanguageEnglish() {
        Locale locale = new Locale("en"); //NON-NLS
        this.reset(locale);
    }

    private void reset(Locale locale) {
        resourceBundle = ResourceBundle.getBundle("lang", locale);
        Stage window = (Stage) activeLanguage.getScene().getWindow();
        try {
            Scene newScene = new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("FXML/Launcher.fxml")), resourceBundle));
            window.setScene(newScene);
            logWindow.close();
            createNewLogWindow();
        } catch (IOException e) {
            LogController.println(resourceBundle.getString("fError.launcherWindow.resetFailed") + "\n" + e);
            this.quit();
        }
    }
}
