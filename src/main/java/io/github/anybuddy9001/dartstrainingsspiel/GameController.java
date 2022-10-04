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

    /**
     * Prepares the number prompt window and stores it for later use.
     */
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

    /**
     * Called if the user made an input to the number prompt.
     * Tries to parse an integer from the input and hands it to game.
     * On success it will close the number prompt.
     * On failure a message will be shown inside the number prompt.
     */
    @FXML
    public void handleNumberPromptInput() {
        Stage window = (Stage) numberPromptCloseButton.getScene().getWindow();
        try {
            boolean success = game.handleNumberPromptInput(Integer.parseInt(numberPromptIn.getText()));
            if (success) {
                window.close();
                numberPromptIn.clear();
                game.setNumCache(-1);
            } else {
                throw new NumberFormatException("Number not in range");
            }
        } catch (NumberFormatException e) {
            numberPromptErrOut.setText(resourceBundle.getString("sError.numberPrompt.amount"));
        }
    }

    /**
     * @param number amount of hits
     */
    public void openNumberPrompt(int number) {
        numberPrompt.setTitle(resourceBundle.getString("projectName") + resourceBundle.getString("numberPromptWindow.Title") + " (" + number + ")");
        numberPrompt.show();
    }

    @FXML
    public void noHit() {
        game.noHit();
    }

    @FXML
    public void oneHit() {
        game.setNumCache(1);
        this.openNumberPrompt(1);
    }

    @FXML
    public void twoHits() {
        game.setNumCache(2);
        this.openNumberPrompt(2);
    }

    @FXML
    public void threeHits() {
        game.setNumCache(3);
        this.openNumberPrompt(3);
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
        game.stopTimer(); // Stop currents game timer to avoid clashing
        game = new Game(this);
        timerIsRunning = false;
        statusDisplay.setText(resourceBundle.getString("mainWindow.textField.status.init"));
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
                if (!locked) this.noHit();
            }
            case DIGIT1, NUMPAD1 -> {
                if (!locked) this.oneHit();
            }
            case DIGIT2, NUMPAD2 -> {
                if (!locked) this.twoHits();
            }
            case DIGIT3, NUMPAD3 -> {
                if (!locked) this.threeHits();
            }
            case R, DECIMAL -> this.newGame();
            case Q -> Platform.exit();
            // Debug option for finding keycodes
//            default -> LogController.println("E: Key not known: " + keyEvent.getCode());
        }
    }

    /**
     * Called by game if the timer hit zero
     */
    public void timerExpired() {
        lock(true);
        numberPrompt.close();
        statusDisplay.setText(resourceBundle.getString("mainWindow.textField.status.gameOver"));
        LogController.println("I: " + resourceBundle.getString("mainWindow.textField.status.gameOver")); //NON-NLS
    }

    /**
     * @param time to be displayed
     */
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


    /**
     * Disables all game functionality buttons
     *
     * @param gameOver whether the timer should be disabled
     */
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

    /**
     * Enables all game functionality buttons
     */
    private void unlock() {
        locked = false;

        buttonStartStopTimer.setDisable(false);
        buttonNoHit.setDisable(false);
        buttonOneHit.setDisable(false);
        buttonTwoHits.setDisable(false);
        buttonThreeHits.setDisable(false);
    }
}
