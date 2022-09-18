import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Objects;

public class UI3 {

    public void open() {
        Stage stage = new Stage();
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("UI3.fxml")));
            Scene scene = new Scene(root);
            stage.setTitle("Trainingsspiel");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
        } catch (Exception e) {
            System.out.println("UI3-couldn't be initialized: \n" + e);
        }
        try {
            stage.show();
        } catch (Exception e) {
            System.out.println("UI3-couldn't be opened: \n" + e);
        }
    }
}
