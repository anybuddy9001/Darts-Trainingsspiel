package io.github.anybuddy9001.dartstrainingsspiel;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * Controller for the game windows - Game and NumberPrompt
 *
 * @author anybuddy
 * @author Specktulatius
 */
public class GameController implements Initializable {
    private static ResourceBundle resourceBundle;  //NON-NLS

    private static Stage numberPrompt;
    private static Game game;
    private static boolean locked;

    // Game
    @FXML
    private TextField scoreDisplay;
    @FXML
    private TextField highscoreDisplay;
    @FXML
    private TextField statusDisplay;
    @FXML
    private Button buttonStartStopTimer;
    @FXML
    private Button buttonNoHit;
    @FXML
    private Button buttonOneHit;
    @FXML
    private Button buttonTwoHits;
    @FXML
    private Button buttonThreeHits;
    // Timer
    private boolean timerIsRunning = false;
    @FXML
    private Text timerDisplay;
    // Number Prompt
    @FXML
    private TextField numberPromptErrOut;
    @FXML
    private TextField numberPromptIn;
    @FXML
    private Button numberPromptCloseButton;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        resourceBundle = resources;
        if (game == null) {
            game = new Game(this);
            this.updateScore();
            this.lock(false);
        }
        if (numberPrompt == null) {
            createNumberPrompt();
        }
    }

    private void createNumberPrompt() {
        try {
            numberPrompt = new Stage();
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("FXML/NumberPrompt.fxml")), resourceBundle);
            Scene scene = new Scene(root);
            scene.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.ESCAPE) {
                    numberPrompt.close();
                }
            });
            numberPrompt.setScene(scene);
            numberPrompt.initModality(Modality.APPLICATION_MODAL);
            numberPrompt.setResizable(false);
        } catch (IOException e) {
            System.out.println(resourceBundle.getString("fError.numberPrompt.open") + "\n" + e);
            throw new RuntimeException(e);
        }
    }

    public void openNumberPrompt(int number) {
        numberPrompt.setTitle(resourceBundle.getString("projectName") + resourceBundle.getString("numberPromptWindow.Title") + " (" + number + ")");
        numberPrompt.show();
    }

    @FXML
    public void keinTreffer() {
        game.keinTreffer();
    }

    @FXML
    public void einTreffer() {
        game.setNumCache(1);
        this.openNumberPrompt(1);
    }

    @FXML
    public void zweiTreffer() {
        game.setNumCache(2);
        this.openNumberPrompt(2);
    }

    @FXML
    public void dreiTreffer() {
        game.setNumCache(3);
        this.openNumberPrompt(3);
    }

    @FXML
    public void closeNumberPrompt() {
        Stage window = (Stage) numberPromptCloseButton.getScene().getWindow();
        try {
            boolean success = game.handleNumberPromptOutput(Integer.parseInt(numberPromptIn.getText()));
            if (success) {
                window.close();
                numberPromptIn.clear();
                game.setNumCache(-1);
            } else {
                numberPromptErrOut.setText(resourceBundle.getString("sError.numberPrompt.amount"));
            }
        } catch (NumberFormatException e) {
            numberPromptErrOut.setText(resourceBundle.getString("sError.numberPrompt.amount"));
        }
    }

    @FXML
    public void startStopTimer() {
        if (!timerIsRunning) {
            game.startTimer();
            statusDisplay.clear();
            unlock();
        } else {
            game.pauseTimer();
            statusDisplay.setText(resourceBundle.getString("mainWindow.textField.status.paused"));
            lock(false);
        }
        timerIsRunning = !timerIsRunning;
    }

    @FXML
    public void newGame() {
        LogController.println(resourceBundle.getString("stdOut.reset"));
        game.stopTimer();
        timerIsRunning = false;
        game = new Game(this);
        statusDisplay.setText("");
        this.updateScore();
        lock(false);
        buttonStartStopTimer.setDisable(false);
    }

    /**
     * Handler for key-presses on the game window
     *
     * @param keyEvent of the key that was pressed
     */
    @FXML
    public void keyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case DIGIT0, NUMPAD0 -> {
                if (!locked) this.keinTreffer();
            }
            case DIGIT1, NUMPAD1 -> {
                if (!locked) this.einTreffer();
            }
            case DIGIT2, NUMPAD2 -> {
                if (!locked) this.zweiTreffer();
            }
            case DIGIT3, NUMPAD3 -> {
                if (!locked) this.dreiTreffer();
            }
            case R, DECIMAL -> this.newGame();
            case Q -> Platform.exit();
            // Debug option for finding keycodes
//            default -> LogController.println("E: Key not known: " + keyEvent.getCode());
        }
    }

    public void updateTimer(String time) {
        timerDisplay.setText(resourceBundle.getString("kw.timer") + " " + time);
    }

    public void updateScore() {
        if (game.getScore() >= 0) {
            scoreDisplay.setText(resourceBundle.getString("kw.score") + ": " + game.getScore());
            highscoreDisplay.setText(resourceBundle.getString("kw.highscore") + ": " + game.getHighscore());
        } else {
            this.startStopTimer();
            statusDisplay.setText(resourceBundle.getString("mainWindow.textField.status.gameOver"));
            LogController.println("I: " + resourceBundle.getString("mainWindow.textField.status.gameOver")); //NON-NLS
            lock(true);
        }
    }

    private void lock(boolean gameOver) {
        locked = true;

        if (gameOver) {
            buttonStartStopTimer.setDisable(true);
        }
        buttonNoHit.setDisable(true);
        buttonOneHit.setDisable(true);
        buttonTwoHits.setDisable(true);
        buttonThreeHits.setDisable(true);
    }

    private void unlock() {
        locked = false;

        buttonStartStopTimer.setDisable(false);
        buttonNoHit.setDisable(false);
        buttonOneHit.setDisable(false);
        buttonTwoHits.setDisable(false);
        buttonThreeHits.setDisable(false);
    }
}
