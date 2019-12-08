import java.util.concurrent.ThreadLocalRandom;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class LoadingScreenController {

    @FXML
    private Button go;

    @FXML
    private Label message;

    private String[] messageOfTheDay = {"30,000 websites are hacked every day.",
            "00000000, this was the password for the computer controls of nuclear missiles of the US for total 8 years.",
            "Tetris, the Nintendo Game Boy classic, might cure insomnia!","Kitchen Sink"};

    /**
     * Gets a randomly generated number between 0 and the length of messageOfTheDay array
     * initializes the text of the message label to the string at specific index
     */
    @FXML
    void initialize() {
        int randomNum = ThreadLocalRandom.current().nextInt(0, 4);
        message.setText(messageOfTheDay[randomNum]);
    }

    /**
     * This method starts the game
     * @param event
     */
    public void handleGoButton(ActionEvent event) {

        Stage primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Launch.getGame().start(primaryStage);
    }

}