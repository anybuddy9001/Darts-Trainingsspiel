package io.github.anybuddy9001.dartstrainingsspiel;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * @author anybuddy
 * @author Specktulatius
 * @version 3.0pre1.3
 */
public class NumberPrompt {
    private static final ResourceBundle resourceBundle = LauncherController.getResourceBundle();  //NON-NLS;

    public void open(int number) {
        Stage stage = new Stage();
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("FXML/NumberPrompt.fxml")), resourceBundle);
            Scene scene = new Scene(root);
            stage.setTitle(resourceBundle.getString("projectName") + resourceBundle.getString("numberPromptWindow.Title") + " (" + number + ")");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            scene.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.ESCAPE) {
                    stage.close();
                }
            });
        } catch (IOException e) {
            System.out.println(resourceBundle.getString("mError.numberPrompt.open") + "\n" + e);
            throw new RuntimeException(e);
        }
        stage.show();
    }
}
