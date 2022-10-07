package io.github.anybuddy9001.dartstrainingsspiel;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * @author anybuddy
 */
public class SettingsController implements Initializable {
    private static ResourceBundle resourceBundle;
    private static Locale activeLocale;
    private boolean saveLocale;
    @FXML
    private Text selectedLanguage;
    @FXML
    private TextField durationIn;
    @FXML
    private CheckBox showLogOnStartup;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        resourceBundle = resources;
        activeLocale = resources.getLocale();
        selectedLanguage.setText(Objects.equals(activeLocale, new Locale("de")) ? "Deutsch" : "English"); //NON-NLS
        durationIn.setPromptText(String.valueOf(JSONInterface.getDefaultGameDuration()));
        showLogOnStartup.setSelected(JSONInterface.getDoOpenLog());
    }

    @FXML
    private void setLanguageGerman() {
        selectedLanguage.setText("Deutsch"); //NON-NLS
        activeLocale = new Locale("de"); //NON-NLS
        checkIfSetAlready(activeLocale);
    }

    @FXML
    private void setLanguageEnglish() {
        selectedLanguage.setText("English"); //NON-NLS
        activeLocale = new Locale("en"); //NON-NLS
        checkIfSetAlready(activeLocale);
    }

    @FXML
    private void setLanguageNone() {
        selectedLanguage.setText(Locale.getDefault().getDisplayLanguage());
        activeLocale = null;
        checkIfSetAlready(activeLocale);
    }

    private void checkIfSetAlready(Locale locale) {
        if (!(locale == JSONInterface.getCustomLanguage())) {
            saveLocale = true;
        }
    }

    @FXML
    private void save() {
        // Save window so it can be closed for sure
        Stage window = (Stage) durationIn.getScene().getWindow();
        // Save showLog check-box state
        JSONInterface.setDoOpenLog(showLogOnStartup.isSelected());
        // Check, save duration and update duration prompt
        if (!durationIn.getText().isBlank()) {
            try {
                int duration = Integer.parseInt(durationIn.getText());
                if (duration < 1 || duration > 60) throw new NumberFormatException();
                JSONInterface.setDefaultGameDuration(duration);
                LauncherController.getLauncherController().setDurationInPromptToDefault();
            } catch (NumberFormatException e) {
                LogController.showLog(Main.getPrimaryStage());
                LogController.println("E: " + resourceBundle.getString("sError.duration")); //NON-NLS
            }
        }
        // Save Locale and reset Launcher window
        if (saveLocale) {
            JSONInterface.setCustomLanguage(activeLocale);
            LauncherController.getLauncherController().setLocale(activeLocale);
        }
        JSONInterface.saveSettings();
        closeWindow(window);
    }

    private void closeWindow(Stage window) {
        window.close();
    }

    @FXML
    private void closeWindow() {
        Stage window = (Stage) durationIn.getScene().getWindow();
        this.closeWindow(window);
    }
}
