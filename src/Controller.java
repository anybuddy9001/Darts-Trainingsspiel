import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private static Trainingsspiel spiel = new Trainingsspiel();
    private static NumberPrompt numberPrompt;

    private static TextField scoreTMP;
    private static TextField highscoreTMP;
    private static TextField looseTMP;

    @FXML
    private TextField score;
    @FXML
    private TextField highscore;
    @FXML
    private TextField loose;
    @FXML
    private Button closeButton;
    @FXML
    private TextField error;
    @FXML
    private TextField numberPromptIn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        numberPrompt = new NumberPrompt();
    }

    @FXML
    public void keinTreffer() {
        spiel.keinTreffer();
        scoreTMP = score;
        highscoreTMP = highscore;
        looseTMP = loose;
        setScore();
    }

    @FXML
    public void nurEinDartImTriple() {
        spiel.NurEinTrefferImTriple();
        scoreTMP = score;
        highscoreTMP = highscore;
        looseTMP = loose;
        setScore();
    }

    @FXML
    public void zweiTreffer() {
        scoreTMP = score;
        highscoreTMP = highscore;
        looseTMP = loose;
        spiel.setNumCache(2);
        numberPrompt.open(2);
    }

    @FXML
    public void dreiTreffer() {
        scoreTMP = score;
        highscoreTMP = highscore;
        looseTMP = loose;
        spiel.setNumCache(3);
        numberPrompt.open(3);
    }

    @FXML
    public void closeNumberPrompt() {
        int numCache = spiel.getNumCache();
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
                spiel.setNumCache(-1);
            } else {
                error.setText("E: Anzahl an Triple-Treffern überprüfen!");
            }
        } catch (NumberFormatException e) {
            error.setText("E: cleAnzahl an Triple-Treffern überprüfen!");
        }
        setScore();
    }

    @FXML
    public void newGame() {
        System.out.println("I: Resetting Game!");
        spiel = new Trainingsspiel();
        score.setText("Score: 0");
        highscore.setText("Highscore: 0");
        loose.setText("");
    }

    public void setScore() {
        if (spiel.getScore() >= 0) {
            scoreTMP.setText("Score: " + spiel.getScore());
        } else {
            looseTMP.setText("Verloren!");
        }
        highscoreTMP.setText("Highscore: " + spiel.getHighscore());
    }

    public void keyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case DIGIT0, NUMPAD0 -> keinTreffer();
            case DIGIT1, NUMPAD1 -> nurEinDartImTriple();
            case DIGIT2, NUMPAD2 -> zweiTreffer();
            case DIGIT3, NUMPAD3 -> dreiTreffer();
            case R -> newGame();
            case Q -> {
                System.out.println("I: Exiting!");
                System.exit(0);
            }
            default -> {
                //System.out.println("E: Key not known!");
            }
        }
    }
}