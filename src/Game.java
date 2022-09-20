import lombok.Getter;
import lombok.Setter;

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
        if (Score < 0) {
            System.out.println("Du hast verloren! Highscore: " + Highscore);
        } else {
            checkScore();
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
    }

}