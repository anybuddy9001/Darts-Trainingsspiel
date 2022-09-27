package io.github.anybuddy9001.dartstrainingsspiel;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller for the game windows - Game and NumberPrompt
 *
 * @author anybuddy
 * @author Specktulatius
 */
public class GameController implements Initializable {
    private static final ResourceBundle resourceBundle = LauncherController.getResourceBundle();  //NON-NLS;

    private static final NumberPrompt numberPrompt = new NumberPrompt();
    private static Game game;

    // Game
    @FXML
    public Text timeDisplay;
    @FXML
    private TextField score;
    @FXML
    private TextField highscore;
    @FXML
    private TextField loose;
    @FXML
    private Button closeButton;
    @FXML
    private TextField error;
    // Number Prompt
    @FXML
    private TextField numberPromptIn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (game == null) {
            game = new Game(this);
            this.setScore();
        }
    }

    @FXML
    public void startStopTimer() {
    }

    @FXML
    public void keinTreffer() {
        game.keinTreffer();
    }

    @FXML
    public void einTreffer() {
        game.setNumCache(1);
        numberPrompt.open(1);
    }

    @FXML
    public void zweiTreffer() {
        game.setNumCache(2);
        numberPrompt.open(2);
    }

    @FXML
    public void dreiTreffer() {
        game.setNumCache(3);
        numberPrompt.open(3);
    }

    @FXML
    public void closeNumberPrompt() {
        Stage window = (Stage) closeButton.getScene().getWindow();
        try {
            boolean success = game.handleNumberPromptOutput(Integer.parseInt(numberPromptIn.getText()));
            if (success) {
                window.close();
                game.setNumCache(-1);
            } else {
                error.setText(resourceBundle.getString("sError.numberPrompt.amount"));
            }
        } catch (NumberFormatException e) {
            error.setText(resourceBundle.getString("sError.numberPrompt.amount"));
        }
    }

    @FXML
    public void newGame() {
        LogController.println(resourceBundle.getString("stdOut.reset"));
        game = new Game(this);
        loose.setText("");
        this.setScore();
    }

    public void setScore() {
        if (game.getScore() >= 0) {
            score.setText(resourceBundle.getString("kw.score") + ": " + game.getScore());
        } else {
            loose.setText(resourceBundle.getString("mainWindow.textField.lost"));
        }
        highscore.setText(resourceBundle.getString("kw.highscore") + ": " + game.getHighscore());
    }

    /**
     * Handler for key-presses on the game window
     *
     * @param keyEvent of the key that was pressed
     */
    public void keyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case DIGIT0, NUMPAD0 -> this.keinTreffer();
            case DIGIT1, NUMPAD1 -> this.einTreffer();
            case DIGIT2, NUMPAD2 -> this.zweiTreffer();
            case DIGIT3, NUMPAD3 -> this.dreiTreffer();
            case R, DECIMAL -> this.newGame();
            case Q -> Platform.exit();
            // Debug option for finding keycodes
//            default -> LogController.println("E: Key not known: " + keyEvent.getCode());
        }
    }
}
