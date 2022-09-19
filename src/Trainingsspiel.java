public class Trainingsspiel {
    protected int Highscore;
    protected String HighscoreUebertrag;

    private int Punktestand;

    public int numCache;

    public Trainingsspiel() {
        Punktestand = 0;
        Highscore = 0;
        HighscoreUebertrag = Integer.toString(Highscore);
    }

    public void keinTreffer() {
        Punktestand--;
        if (Punktestand < 0) {
            System.out.println("Du hast verloren! Highscore: " + Highscore);
        } else {
            System.out.println("Punktestand: " + Punktestand);
            System.out.println("Highscore: " + Highscore);
        }
    }

    public void NurEinTrefferImTriple() {
        if (Punktestand < 0) {
            System.out.println("Du hast verloren! Highscore: " + Highscore);
        } else {
            Punktestand++;
            if (Highscore < Punktestand) {
                Highscore = Punktestand;
            }
            System.out.println("Punktestand: " + Punktestand);
            System.out.println("Highscore: " + Highscore);
        }
    }

    public void ZweiTreffer(int Triple) {
        if (Punktestand < 0) {
            System.out.println("Du hast verloren! Highscore: " + Highscore);
        } else {
            switch (Triple) {
                case 0 -> Punktestand++;
                case 1 -> Punktestand += 1 + Triple * 2;
                case 2 -> Punktestand += Triple * 2;
                default -> {
                    System.out.println("Diese Anzahl an Triple Treffern ist nicht möglich!");
                    System.out.println("Punktestand:" + Punktestand);
                    System.out.println("Highscore:" + Highscore);
                }
            }
            if (Highscore < Punktestand) {
                Highscore = Punktestand;

                System.out.println("Punktestand:" + Punktestand);
                System.out.println("Highscore:" + Highscore);
            }
        }
    }

    public void DreiTreffer(int Triple) {
        if (Punktestand < 0) {
            System.out.println("Du hast verloren! Highscore:" + Highscore);
        } else {
            switch (Triple) {
                case 0 -> Punktestand += 2;
                case 1 -> Punktestand += 2 + Triple * 2;
                case 2 -> Punktestand += 1 + Triple * 2;
                case 3 -> Punktestand += Triple * 2;
                default -> {
                    System.out.println("Diese Anzahl an Triple Treffern ist nicht möglich!");
                    System.out.println("Punktestand:" + Punktestand);
                    System.out.println("Highscore:" + Highscore);
                }
            }
            if (Highscore < Punktestand) {
                Highscore = Punktestand;
            }
            System.out.println("Punktestand:" + Punktestand);
            System.out.println("Highscore:" + Highscore);
        }
    }

    public int getScore() {
        return Punktestand;
    }

    public int getHighscore() {
        return Highscore;
    }
}