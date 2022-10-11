package io.github.anybuddy9001.dartstrainingsspiel;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.util.Duration;
import lombok.Getter;
import lombok.Setter;

import java.util.ResourceBundle;

/**
 * @author anybuddy
 * @author Specktulatius
 */
public class Game {
    private static final ResourceBundle resourceBundle = LauncherController.getResourceBundle();  //NON-NLS;

    private final GameController mainController;
    private final Type gameType;
    private final boolean gameOverOnZero;
    private Timeline timeline;
    private int second = LauncherController.getGameDuration();
    @Setter
    @Getter
    private int highscore = 0;
    @Setter
    @Getter
    private int score = 0;
    @Setter
    @Getter
    private int numCache;

    public Game(GameController mainController) {
        this.mainController = mainController;
        this.gameType = LauncherController.getGameType();
        this.gameOverOnZero = (gameType == Type.CHALLENGE ? JSONInterface.getGameOverChallenge() : JSONInterface.getGameOverEndless());
        mainController.updateTimer(String.format("%02d:%02d", second / 60, second % 60)); //NON-NLS
        initializeTimer();
    }

    /**
     * Called by the game controller if an input was made to a number prompt.
     * Responsible for checking if the value is in range.
     *
     * @param in amount of triple hits inserted into number prompt
     * @return number prompt should be closed
     */
    public boolean handleNumberPromptInput(String in) {
        try {
            int triples = Integer.parseInt(in);
            if (triples <= numCache && triples >= 0) {
                switch (numCache) {
                    case 1 -> this.noHit(triples);
                    case 2 -> this.twoHits(triples);
                    case 3 -> this.threeHits(triples);
                }
                this.setNumCache(-1);
                return true;
            }
        } catch (NumberFormatException ignored) {
        }
        return false;
    }

    /**
     * Called if one dart hit inside the ring.
     *
     * @param Triple amount of triple hits
     */
    public void noHit(int Triple) {
        switch (Triple) {
            case 0 -> {
            }
            case 1 -> score += 1;
        }
        this.updateScore();
    }

    /**
     * Called if two darts hit inside the ring.
     *
     * @param Triple amount of triple hits
     */
    public void twoHits(int Triple) {
        switch (Triple) {
            case 0 -> score++;
            case 1 -> score += 2;
            case 2 -> score += 3;
        }
        this.updateScore();
    }

    /**
     * Called if three darts hit inside the ring.
     *
     * @param Triple amount of triple hits
     */
    public void threeHits(int Triple) {
        switch (Triple) {
            case 0 -> score += 2;
            case 1 -> score += 3;
            case 2 -> score += 4;
            case 3 -> score += 5;
        }
        this.updateScore();
    }

    /**
     * Called if no dart hit inside the ring.
     */
    public void noHit() {
        score--;
        this.updateScore();
    }

    /**
     * Sets new highscore value if needed.
     * Print scores to Log.
     * Instructs the controller to update the score displays.
     */
    private void updateScore() {
        if (score < 0) {
            score = 0;
            LogController.println(resourceBundle.getString("kw.score") + ": " + score);
            if (gameOverOnZero) {
                this.gameOver();
            }
        } else {
            if (highscore < score) {
                highscore = score;
//                LogController.println(resourceBundle.getString("kw.highscore") + ": " + highscore);
            }
            LogController.println(resourceBundle.getString("kw.score") + ": " + score);
            mainController.updateScore();
        }
    }

    private void initializeTimer() {
        switch (gameType) {
            case ENDLESS -> timeline = new Timeline(new KeyFrame(Duration.seconds(1), (ActionEvent event) -> {
                second++;
                if (second % 3600 == 0) {
                    LogController.println(resourceBundle.getString("mError.mainWindow.timerOverflow")); //NON-NLS
                }
                mainController.updateTimer(String.format("%02d:%02d", (second % 3600) / 60, second % 60)); //NON-NLS
            }));
            case CHALLENGE -> timeline = new Timeline(new KeyFrame(Duration.seconds(1), (ActionEvent event) -> {
                if (second-- > 0) {
                    mainController.updateTimer(String.format("%02d:%02d", (second % 3600) / 60, second % 60)); //NON-NLS
                } else {
                    LogController.println(resourceBundle.getString("stdOut.timesUp")); //NON-NLS
                    gameOver();
                }
            }));
        }
        timeline.setCycleCount(Timeline.INDEFINITE);
    }

    private void gameOver() {
        this.stopTimer();
        mainController.gameOver();
    }

    public void startTimer() {
        timeline.play();
    }

    public void pauseTimer() {
        timeline.pause();
    }

    public void stopTimer() {
        timeline.stop();
    }

    enum Type {
        ENDLESS, CHALLENGE
    }
}
