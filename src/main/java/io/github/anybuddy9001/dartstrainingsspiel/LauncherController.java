package io.github.anybuddy9001.dartstrainingsspiel;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Modality;
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
    private static LauncherController launcherController;
    private static Stage settingsWindow;
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
        launcherController = this;
        switch (resources.getLocale().toString()) {
            case "en" -> activeLanguage.setText("English"); //NON-NLS
            case "de" -> activeLanguage.setText("Deutsch"); //NON-NLS
        }
        setDurationInPromptToDefault();
        if (logWindow == null) {
            createNewLogWindow();
        }
        if (settingsWindow == null) {
            createNewSettingsWindow();
        }
    }

    /**
     * Sets the TextField prompt of durationIn to the default from settings file
     */
    public void setDurationInPromptToDefault() {
        durationIn.setPromptText(String.valueOf(JSONInterface.getDefaultGameDuration()));
    }

    private void createNewSettingsWindow() {
        if (settingsWindow == null) {
            settingsWindow = new Stage();
            settingsWindow.initModality(Modality.APPLICATION_MODAL);
            settingsWindow.setResizable(false);
        }
        try {
            Scene scene = new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("FXML/Settings.fxml")), resourceBundle));
            settingsWindow.setTitle(resourceBundle.getString("projectName") + " - " + resourceBundle.getString("settingsWindow.title"));
            settingsWindow.setScene(scene);
        } catch (IOException e) {
            System.out.println(resourceBundle.getString("fError.numberPrompt.open") + "\n" + e);
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void openSettings() {
        settingsWindow.show();
    }

    private void createNewLogWindow() {
        if (logWindow == null) {
            logWindow = new Stage();
            logWindow.setResizable(true);
        }
        try {
            Scene scene = new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("FXML/Log.fxml")), resourceBundle));
            logWindow.setTitle(resourceBundle.getString("projectName") + resourceBundle.getString("logWindow.Title"));
            logWindow.setScene(scene);
        } catch (IOException e) {
            LogController.println(resourceBundle.getString("fError.log.open") + "\n" + e);
            throw new RuntimeException(e);
        }
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
                gameDuration = JSONInterface.getDefaultGameDuration() * 60;
            } else {
                gameDuration = Integer.parseInt(durationIn.getText()) * 60;
                if (gameDuration < 1 || gameDuration > 3600) throw new NumberFormatException();
            }
            return true;
        } catch (NumberFormatException e) {
            LogController.showLog((Stage) activeLanguage.getScene().getWindow());
            LogController.println("E: " + resourceBundle.getString("sError.duration")); //NON-NLS
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
            Scene scene = new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("FXML/Game.fxml")), resourceBundle));
            window.setTitle(resourceBundle.getString("projectName") + " - " + resourceBundle.getString("gameWindow.title." + gameType.toString().toLowerCase()));
            window.setScene(scene);
            window.setResizable(false);
            if (JSONInterface.getDoOpenLog()) {
                LogController.showLog(window);
            }
        } catch (IOException e) {
            LogController.println(resourceBundle.getString("fError.launcherWindow.startGame"));
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void setLanguageGerman() {
        Locale locale = new Locale("de"); //NON-NLS
        this.setLocale(locale);
    }

    @FXML
    private void setLanguageEnglish() {
        Locale locale = new Locale("en"); //NON-NLS
        this.setLocale(locale);
    }

    /**
     * Sets new locale and redraws the launcher window to represent locale change.
     *
     * @param locale to change to
     */
    public void setLocale(Locale locale) {
        resourceBundle = ResourceBundle.getBundle("lang", locale == null ? Locale.getDefault() : locale);
        Stage window = (Stage) activeLanguage.getScene().getWindow();
        try {
            logWindow.close();
            createNewLogWindow();
            createNewSettingsWindow();
            Scene newScene = new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("FXML/Launcher.fxml")), resourceBundle));
            window.setScene(newScene);
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
