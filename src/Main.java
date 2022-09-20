import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * @author Specktulatius
 * @author anybuddy
 * @version 1.3.1
 */
public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void init() {
    }

    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Main.fxml")));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Trainingsspiel - Main");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);
    }

    public void stop() {
    }
}
