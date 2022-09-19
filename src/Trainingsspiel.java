import lombok.Getter;
import lombok.Setter;

public class Trainingsspiel {
    @Setter @Getter
    private int Highscore = 0;
    @Setter @Getter
    private int Score = 0;
    @Setter @Getter
    private int numCache;

    public void keinTreffer() {
        Score--;
        if (Score < 0) {
            System.out.println("Du hast verloren! Highscore: " + Highscore);
        } else {
            System.out.println("Punktestand: " + Score);
        }
    }

    public void NurEinTrefferImTriple() {
        if (Score < 0) {
            System.out.println("Du hast verloren! Highscore: " + Highscore);
        } else {
            Score++;
            checkHighscore();
        }
    }

    public void ZweiTreffer(int Triple) {
        if (Score < 0) {
            System.out.println("Du hast verloren! Highscore: " + Highscore);
        } else {
            switch (Triple) {
                case 0 -> Score++;
                case 1 -> Score += 1 + Triple * 2;
                case 2 -> Score += Triple * 2;
                default -> System.out.println("Diese Anzahl an Triple Treffern ist nicht möglich!");
            }
            checkHighscore();
        }
    }

    public void DreiTreffer(int Triple) {
        if (Score < 0) {
            System.out.println("Du hast verloren! Highscore:" + Highscore);
        } else {
            switch (Triple) {
                case 0 -> Score += 2;
                case 1 -> Score += 2 + Triple * 2;
                case 2 -> Score += 1 + Triple * 2;
                case 3 -> Score += Triple * 2;
                default -> System.out.println("Diese Anzahl an Triple Treffern ist nicht möglich!");
            }
            checkHighscore();
        }
    }

    private void checkHighscore() {
        if (Highscore < Score) {
            Highscore = Score;
            System.out.println("Highscore: " + Highscore);
        }
        System.out.println("Punktestand: " + Score);
    }
}