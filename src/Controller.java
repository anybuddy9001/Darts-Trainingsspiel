import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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
    private TextField tripleTreffer;
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
        numberPrompt.open(2);
    }

    @FXML
    public void dreiTreffer() {
        scoreS = score;
        highscoreS = highscore;
        looseS = loose;
        numberPrompt.open(3);
    }

    @FXML
    private void closeNumberPrompt() {
        int triples;
        // get a handle to the stage
        Stage stage = (Stage) closeButton.getScene().getWindow();
        // do what you have to do
        try {
            triples = Integer.parseInt(tripleTreffer.getText());
            // System.out.println(triples);
            if (triples <= 2 && triples >= 0) {
                spiel.ZweiTreffer(triples);
                stage.close();
            } else {
                error.setText("Anzahl an Triple-Treffern überprüfen!");
            }
        } catch (NumberFormatException e) {
            error.setText("Anzahl an Triple-Treffern überprüfen!");
        }
        setScore();
    }

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
}