import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * @author Specktulatius
 * @author anybuddy
 * @version 1.3
 */
public class NumberPrompt {
    public void open(int number) {
        Stage stage = new Stage();
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("NumberPrompt.fxml")));
            Scene scene = new Scene(root);
            stage.setTitle("Trainingsspiel - Number Prompt (" + number + ")");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            scene.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.ESCAPE) {
                    stage.close();
                }
            });
        } catch (IOException e) {
            System.out.println("E: NumberPrompt couldn't be initialized: \n" + e);
        }
        try {
            stage.show();
        } catch (IllegalStateException e) {
            System.out.println("E: NumberPrompt couldn't be opened: \n" + e);
        }
    }
}
