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
        mainController.updateTimer(String.format("%02d:%02d", second / 60, second % 60)); //NON-NLS
        initializeTimer();
    }

    public void keinTreffer() {
        score--;
        this.updateScore();
    }

    public boolean handleNumberPromptOutput(int triples) {
        if (triples <= numCache && triples >= 0) {
            switch (numCache) {
                case 1 -> this.einTreffer(triples);
                case 2 -> this.ZweiTreffer(triples);
                case 3 -> this.DreiTreffer(triples);
            }
            this.setNumCache(-1);
            return true;
        } else {
            return false;
        }
    }

    public void einTreffer(int Triple) {
        switch (Triple) {
            case 0 -> {
            }
            case 1 -> score += 1;
        }
        this.updateScore();
    }

    public void ZweiTreffer(int Triple) {
        switch (Triple) {
            case 0 -> score++;
            case 1 -> score += 2;
            case 2 -> score += 3;
        }
        this.updateScore();
    }

    public void DreiTreffer(int Triple) {
        switch (Triple) {
            case 0 -> score += 2;
            case 1 -> score += 3;
            case 2 -> score += 4;
            case 3 -> score += 5;
        }
        this.updateScore();
    }

    private void updateScore() {
        if (highscore < score) {
            highscore = score;
//             LogController.println(resourceBundle.getString("kw.highscore") + ": " + highscore);
        }
        LogController.println(resourceBundle.getString("kw.score") + ": " + score);
        mainController.updateScore();
    }

    private void initializeTimer() {
        switch (LauncherController.getGameType()) {
            case ENDLESS -> timeline = new Timeline(new KeyFrame(Duration.seconds(1), (ActionEvent event) -> {
                second++;
                mainController.updateTimer(String.format("%02d:%02d", (second % 3600) / 60, second % 60)); //NON-NLS
            }));
            case CHALLENGE -> timeline = new Timeline(new KeyFrame(Duration.seconds(1), (ActionEvent event) -> {
                if (second-- > 0) {
                    mainController.updateTimer(String.format("%02d:%02d", (second % 3600) / 60, second % 60)); //NON-NLS
                } else {
                    timerExpired();
                }
            }));
        }
        timeline.setCycleCount(Timeline.INDEFINITE);
    }

    private void timerExpired() {
        this.stopTimer();
        mainController.timerExpired();
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
