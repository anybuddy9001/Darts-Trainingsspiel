package io.github.anybuddy9001.dartstrainingsspiel;

import lombok.Getter;
import lombok.Setter;

/**
 * @author anybuddy
 * @author Specktulatius
 * @version 2.0
 */
public class Game {
    private final Controller mainController;
    @Setter
    @Getter
    private int Highscore = 0;
    @Setter
    @Getter
    private int Score = 0;
    @Setter
    @Getter
    private int numCache;

    public Game(Controller mainController) {
        this.mainController = mainController;
    }

    public void keinTreffer() {
        Score--;
        checkScore();
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
            case 1 -> Score += 1;
            default -> System.out.println("Diese Anzahl an Triple Treffern ist nicht möglich!");
        }
        checkScore();
    }

    public void ZweiTreffer(int Triple) {
        switch (Triple) {
            case 0 -> Score++;
            case 1 -> Score += 2;
            case 2 -> Score += 3;
            default -> System.out.println("Diese Anzahl an Triple Treffern ist nicht möglich!");
        }
        checkScore();
    }

    public void DreiTreffer(int Triple) {
        switch (Triple) {
            case 0 -> Score += 2;
            case 1 -> Score += 3;
            case 2 -> Score += 4;
            case 3 -> Score += 5;
            default -> System.out.println("Diese Anzahl an Triple Treffern ist nicht möglich!");
        }
        checkScore();
    }

    private void checkScore() {
        if (Highscore < Score) {
            Highscore = Score;
            // System.out.println("Highscore: " + Highscore);
        }
        System.out.println("Punktestand: " + Score);
        mainController.setScore();
    }
}
