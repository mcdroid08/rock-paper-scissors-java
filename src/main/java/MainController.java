import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.Random;

public class MainController {

    @FXML private Label userScore;
    @FXML private Label compScore;
    @FXML private Label textToShow;
    @FXML private ImageView rock;
    @FXML private ImageView paper;
    @FXML private ImageView scissors;
    @FXML private Label greet;

    String userMove, compMove;
    Random random;
    FadeTransition ftText, ftUS, ftCS;
    int fTDur = 350;

    public void initialize() {
        random = new Random();
        ftText = new FadeTransition(Duration.millis(fTDur), textToShow);
        ftText.setToValue(0);
        ftText.setAutoReverse(true);
        ftText.setCycleCount(2);

        ftUS = new FadeTransition(Duration.millis(fTDur), userScore);
        ftUS.setToValue(0);
        ftUS.setAutoReverse(true);
        ftUS.setCycleCount(2);

        ftCS = new FadeTransition(Duration.millis(fTDur), compScore);
        ftCS.setToValue(0);
        ftCS.setAutoReverse(true);
        ftCS.setCycleCount(2);

        rock.setOnMouseClicked(event -> {
            userMove = "Rock";
            ftText.playFrom(Duration.millis(fTDur));
            makeMove();
        });

        paper.setOnMouseClicked(event -> {
            userMove = "Paper";
            ftText.playFrom(Duration.millis(fTDur));
            makeMove();
        });

        scissors.setOnMouseClicked(event -> {
            userMove = "Scissors";
            ftText.playFrom(Duration.millis(fTDur));
            makeMove();
        });

    }

    private void makeMove() {
        int move = random.nextInt(3);

        switch (move) {
            case 0:
                compMove = "Rock";
                break;
            case 1:
                compMove = "Paper";
                break;
            case 2:
                compMove = "Scissors";
                break;
        }

        if (userMove.equals("Rock")){
            if (compMove.equals("Paper")) {
                setWinner('l');
            }
            else if (compMove.equals("Scissors")){
                setWinner('w');
            }
            else {
                setWinner('d');
            }
        }

        else if (userMove.equals("Paper")){
            if (compMove.equals("Scissors")) {
                setWinner('l');
            }
            else if (compMove.equals("Rock")){
                setWinner('w');
            }
            else {
                setWinner('d');
            }
        }

        else {
            if (compMove.equals("Rock")) {
                setWinner('l');
            }
            else if (compMove.equals("Paper")){
                setWinner('w');
            }
            else {
                setWinner('d');
            }
        }
    }

    private void setWinner(char winStatus) { //win w, lose l, draw d
        switch (winStatus) {
            case 'w':
                ftUS.playFrom(Duration.millis(fTDur));
                userScore.setText(String.valueOf(Integer.parseInt(userScore.getText())+1));
                textToShow.setText("You won: Your " + userMove + " defeated Comp " + userMove + ".");
                greet.setText("You Won :)");
                break;
            case 'l':
                ftCS.playFrom(Duration.millis(fTDur));
                compScore.setText(String.valueOf(Integer.parseInt(compScore.getText())+1));
                textToShow.setText("You lost: Your " + userMove + " lost to Comp " + compMove + ".");
                greet.setText("You Lost :(");
                break;
            case 'd':
                textToShow.setText("Try Again: Both You & Comp picked " + compMove);
                greet.setText("Its Draw :|");
                break;
        }
    }


    @FXML
    private void restart() {
        userScore.setText("0");
        compScore.setText("0");
        textToShow.setText("Choose a move to begin.");
        greet.setText("Enjoy :)");
    }
}
