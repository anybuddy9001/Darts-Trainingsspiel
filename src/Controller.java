import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private static TextField scoreS;
    private static TextField highscoreS;
    private static TextField looseS;
    private static Trainingsspiel spiel = new Trainingsspiel();

    @FXML
    private TextField score;
    @FXML
    private TextField highscore;
    @FXML
    private TextField loose;
    @FXML
    private Button closeButton;
    @FXML
    private TextField numberPromptIn;
    @FXML
    private TextField error;

    private NumberPrompt numberPrompt;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        numberPrompt = new NumberPrompt();
    }

    @FXML
    public void keinTreffer() {
        spiel.keinTreffer();
        scoreS = score;
        highscoreS = highscore;
        looseS = loose;
        setScore();
    }

    @FXML
    public void nurEinDartImTriple() {
        spiel.NurEinTrefferImTriple();
        scoreS = score;
        highscoreS = highscore;
        looseS = loose;
        setScore();
    }

    @FXML
    public void zweiTreffer() {
        scoreS = score;
        highscoreS = highscore;
        looseS = loose;
        spiel.numCache = 2;
        numberPrompt.open(2);
    }

    @FXML
    public void dreiTreffer() {
        scoreS = score;
        highscoreS = highscore;
        looseS = loose;
        spiel.numCache = 3;
        numberPrompt.open(3);
    }

    @FXML
    public void closeNumberPrompt() {
        int numCache = spiel.numCache;
        int triples;
        // get a handle to the stage
        Stage stage = (Stage) closeButton.getScene().getWindow();
        // do what you have to do
        try {
            triples = Integer.parseInt(numberPromptIn.getText());
            if (triples <= numCache && triples >= 0) {
                switch (numCache) {
                    case 2 -> spiel.ZweiTreffer(triples);
                    case 3 -> spiel.DreiTreffer(triples);
                }
                stage.close();
                spiel.numCache = -1;
            } else {
                error.setText("Anzahl an Triple-Treffern überprüfen!");
            }
        } catch (NumberFormatException e) {
            error.setText("Anzahl an Triple-Treffern überprüfen!");
        }
        setScore();
    }

    @FXML
    public void newGame() {
        spiel = new Trainingsspiel();
        score.setText("Score: 0");
        highscore.setText("Highscore:" + spiel.getHighscore());
        loose.setText("");
    }

    public void setScore() {
        if (spiel.getScore() >= 0) {
            scoreS.setText("Score: " + spiel.getScore());
        } else {
            looseS.setText("Verloren!");
        }
        highscoreS.setText("Highscore: " + spiel.getHighscore());
    }

    public void keyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case DIGIT0, NUMPAD0 -> keinTreffer();
            case DIGIT1, NUMPAD1 -> nurEinDartImTriple();
            case DIGIT2, NUMPAD2 -> zweiTreffer();
            case DIGIT3, NUMPAD3 -> dreiTreffer();
            case R -> newGame();
            case Q -> {
                System.out.println("Exiting!");
                System.exit(0);
            }
            default -> System.out.println("Key not known!");
        }
    }
}