import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private static Game game;
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
        if (game == null) {
            game = new Game(this);
        }
        numberPrompt = new NumberPrompt();
    }

    @FXML
    public void keinTreffer() {
        game.keinTreffer();
        scoreTMP = score;
        highscoreTMP = highscore;
        looseTMP = loose;
        setScore();
    }

    @FXML
    public void einTreffer() {
        scoreTMP = score;
        highscoreTMP = highscore;
        looseTMP = loose;
        game.setNumCache(1);
        numberPrompt.open(1);
    }

    @FXML
    public void zweiTreffer() {
        scoreTMP = score;
        highscoreTMP = highscore;
        looseTMP = loose;
        game.setNumCache(2);
        numberPrompt.open(2);
    }

    @FXML
    public void dreiTreffer() {
        scoreTMP = score;
        highscoreTMP = highscore;
        looseTMP = loose;
        game.setNumCache(3);
        numberPrompt.open(3);
    }

    @FXML
    public void closeNumberPrompt() {
        int numCache = game.getNumCache();
        int triples;
        Stage stage = (Stage) closeButton.getScene().getWindow();
        try {
            triples = Integer.parseInt(numberPromptIn.getText());
            if (triples <= numCache && triples >= 0) {
                switch (numCache) {
                    case 1 -> game.einTreffer(triples);
                    case 2 -> game.ZweiTreffer(triples);
                    case 3 -> game.DreiTreffer(triples);
                }
                stage.close();
                game.setNumCache(-1);
                setScore();
            } else {
                error.setText("Anzahl an Triple-Treffern überprüfen!");
            }
        } catch (NumberFormatException e) {
            error.setText("Anzahl an Triple-Treffern überprüfen!");
        }
    }

    @FXML
    public void newGame() {
        System.out.println("I: Resetting Game!");
        game = new Game(this);
        score.setText("Score: 0");
        highscore.setText("Highscore: 0");
        loose.setText("");
    }

    public void setScore() {
        if (game.getScore() >= 0) {
            scoreTMP.setText("Score: " + game.getScore());
        } else {
            looseTMP.setText("Verloren!");
        }
        highscoreTMP.setText("Highscore: " + game.getHighscore());
    }

    public void keyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case DIGIT0, NUMPAD0 -> keinTreffer();
            case DIGIT1, NUMPAD1 -> einTreffer();
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