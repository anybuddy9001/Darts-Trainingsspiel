package io.github.anybuddy9001.dartstrainingsspiel;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author anybuddy
 */
public class LogController implements Initializable {
    private static TextArea staticLogOut;

    @FXML
    public TextArea logOut;

    public static void println(String s) {
        staticLogOut.appendText(s + "\n");
        System.out.println(s);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        staticLogOut = logOut;
    }

    public void clearLogScreen() {
        logOut.clear();
    }

    public void hideLog() {
        Stage window = (Stage) logOut.getScene().getWindow();
        window.hide();
    }
}
