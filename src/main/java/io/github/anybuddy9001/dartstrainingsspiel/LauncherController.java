package io.github.anybuddy9001.dartstrainingsspiel;

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
 * @version 3.0pre1
 */
public class LauncherController implements Initializable {
    @Getter
    private static ResourceBundle resourceBundle;

    @FXML
    public Text selectedLanguage;
    @FXML
    public TextField duration;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        resourceBundle = resources;
    }

    @FXML
    public void startChallengeMode() {
        System.out.println("Challenge Mode is not yet implemented"); //NON-NLS
    }

    @FXML
    public void startEndlessMode() {
        Stage window = (Stage) selectedLanguage.getScene().getWindow();
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("FXML/Main.fxml")), resourceBundle);
            Scene scene = new Scene(root);
            window.setTitle(resourceBundle.getString("mainWindow.Title"));
            window.setScene(scene);
            window.setResizable(false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void quit() {
        Stage window = (Stage) selectedLanguage.getScene().getWindow();
        System.out.println(resourceBundle.getString("stdOut.exit"));
        window.close();
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
        Stage window = (Stage) selectedLanguage.getScene().getWindow();
        try {
            Scene newScene = new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("FXML/Launcher.fxml")), resourceBundle));
            window.setScene(newScene);
        } catch (IOException e) {
            System.out.println(resourceBundle.getString("fError.launcherWindow.resetFailed") + "\n" + e);
            this.quit();
        }
    }
}
