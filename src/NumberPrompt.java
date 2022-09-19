import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class NumberPrompt {

    public void open(int number) {
        Stage stage = new Stage();
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("NumberPrompt.fxml")));
            Scene scene = new Scene(root);
            stage.setTitle("Trainingsspiel");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
        } catch (IllegalStateException | IOException e) {
            System.out.println("`NumberPrompt` couldn't be initialized: \n" + e);
        }
        try {
            stage.show();
        } catch (IllegalStateException e) {
            System.out.println("`NumberPrompt` couldn't be opened: \n" + e);
        }
    }
}
