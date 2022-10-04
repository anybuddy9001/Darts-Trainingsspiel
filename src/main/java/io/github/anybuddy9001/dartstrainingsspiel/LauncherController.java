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

    @Getter
    private static int gameDuration = 0;
    @Getter
    private static Game.Type gameType;
    @FXML
    private Text activeLanguage;
    @FXML
    private TextField durationIn;

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
            LogController.println(resourceBundle.getString("fError.log.open") + "\n" + e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Opens log window in relation to the window it was given.
     *
     * @param window to open relative to
     */
    public void showLogWindow(Stage window) {
        logWindow.setX(window.getX() + window.getWidth());
        logWindow.setY(window.getY());
        logWindow.show();
        window.requestFocus();
    }

    @FXML
    public void startChallengeMode() {
        gameType = Game.Type.CHALLENGE;
        if (setGameDuration()) {
            startGame();
        }
    }

    /**
     * @return duration set successful
     */
    private boolean setGameDuration() {
        try {
            if (durationIn.getText().isBlank()) {
                gameDuration = 600;
            } else {
                gameDuration = Integer.parseInt(durationIn.getText()) * 60;
                if (gameDuration < 1 || gameDuration > 3600) throw new NumberFormatException();
            }
            return true;
        } catch (NumberFormatException e) {
            showLogWindow((Stage) activeLanguage.getScene().getWindow());
            LogController.println("E: " + resourceBundle.getString("sError.launcherWindow.duration")); //NON-NLS
            return false;
        }
    }

    @FXML
    public void startEndlessMode() {
        gameType = Game.Type.ENDLESS;
        startGame();
    }

    private void startGame() {
        Stage window = (Stage) activeLanguage.getScene().getWindow();
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("FXML/Game.fxml")), resourceBundle);
            Scene scene = new Scene(root);
            window.setTitle(resourceBundle.getString("projectName") + " - " + resourceBundle.getString("gameWindow.title." + gameType.toString().toLowerCase()));
            window.setScene(scene);
            window.setResizable(false);
            showLogWindow(window);
        } catch (IOException e) {
            LogController.println(resourceBundle.getString("fError.launcherWindow.startGame"));
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void setLanguageGerman() {
        Locale locale = new Locale("de"); //NON-NLS
        this.setLocale(locale);
    }

    @FXML
    public void setLanguageEnglish() {
        Locale locale = new Locale("en"); //NON-NLS
        this.setLocale(locale);
    }

    /**
     * Sets new locale and redraws the launcher window to represent locale change.
     *
     * @param locale to change to
     */
    private void setLocale(Locale locale) {
        resourceBundle = ResourceBundle.getBundle("lang", locale);
        Stage window = (Stage) activeLanguage.getScene().getWindow();
        try {
            Scene newScene = new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("FXML/Launcher.fxml")), resourceBundle));
            window.setScene(newScene);
            logWindow.close();
            createNewLogWindow();
        } catch (IOException e) {
            LogController.println(resourceBundle.getString("fError.launcherWindow.reset") + "\n" + e);
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void quit() {
        Platform.exit();
    }
}
