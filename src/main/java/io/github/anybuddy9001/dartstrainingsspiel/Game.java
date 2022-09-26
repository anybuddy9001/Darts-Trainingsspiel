package io.github.anybuddy9001.dartstrainingsspiel;

import lombok.Getter;
import lombok.Setter;

import java.util.ResourceBundle;

/**
 * @author anybuddy
 * @author Specktulatius
 * @version 3.0pre2
 */
public class Game {
    private static final ResourceBundle resourceBundle = LauncherController.getResourceBundle();  //NON-NLS;

    private final GameController mainController;
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
    }

    public void keinTreffer() {
        score--;
        this.checkScore();
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
        this.checkScore();
    }

    public void ZweiTreffer(int Triple) {
        switch (Triple) {
            case 0 -> score++;
            case 1 -> score += 2;
            case 2 -> score += 3;
        }
        this.checkScore();
    }

    public void DreiTreffer(int Triple) {
        switch (Triple) {
            case 0 -> score += 2;
            case 1 -> score += 3;
            case 2 -> score += 4;
            case 3 -> score += 5;
        }
        this.checkScore();
    }

    private void checkScore() {
        if (highscore < score) {
            highscore = score;
//             LogController.println(resourceBundle.getString("kw.highscore") + ": " + highscore);
        }
        LogController.println(resourceBundle.getString("kw.score") + ": " + score);
        mainController.setScore();
    }
}
