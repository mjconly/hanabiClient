package sample.View;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import sample.Controller.ClientController;
import sample.Model.AfterModel;
import sample.Model.Model;


/**
 * EndGame -- this view will display the users score and rank from the
 * game that has just ended. It will also allow the user to quit or return
 * to the main menu
 */
public class EndGameView extends ClientView {
    /** controller to handle click events **/
    ClientController controller;
    /** model to update view onChange **/
    Model model;
    /** the title for the view **/
    Label title;
    /** the container for the title **/
    VBox titleBox;
    /** generic score message **/
    Label scoreMessage;
    /** user score from game **/
    Label score;
    /** container for score info **/
    HBox scoreBox;
    /** generic rank message **/
    Label rankMessage;
    /** rank from game **/
    Label rank;
    /** container for rank info **/
    HBox rankBox;
    /** button to return to main **/
    Button playAgain;
    /** button to quit **/
    Button quit;
    /** container for buttons */
    HBox buttonBox;
    /** Button font object **/
    private Font buttonText;
    /** css string to update buttons when hovered **/
    final String HOVER;
    /** css string to update buttons when idle **/
    final String IDLE;
    /** object for end game image **/
    private ImageView endView;
    /** object to write image stream to **/
    private Image endImage;
    /** container for end game image **/
    private VBox imageBox;


    /**
     * creates a new instance of EndGameView with all UI laid out accordingly
     * @param id ViewID END
     */
    public EndGameView(ViewId id){
        super(id);

        VBox.setVgrow(this, Priority.ALWAYS);


        buttonText = Font.font("Segoe Print", FontWeight.BOLD, FontPosture.REGULAR, 27);
        HOVER = "-fx-text-fill: white; -fx-background-color: transparent; -fx-border-color: white; " +
                "-fx-border-width: 0 4 0 4; -fx-border-radius: 20";
        IDLE = "-fx-text-fill: #ffa411; -fx-background-color: transparent";
        this.setAlignment(Pos.CENTER);

        initTitle();
        initScoreLabels();
        initRankLabels();
        initQuitBtn();
        initPlayAgainBtn();
        initButtonBox();
        initImage();

        this.getChildren().addAll(titleBox, scoreBox, rankBox, buttonBox, imageBox);

    }


    /**
     * Initializes the titleBox and adds a text label to it with appropriate
     * layout
     */
    private void initTitle(){
        this.titleBox = new VBox();
        this.title = new Label("Game Over");
        this.title.setFont(Font.font("Segoe Print", FontWeight.NORMAL, FontPosture.REGULAR, 125));
        this.title.setTextFill(Color.valueOf("#ffa411"));
        this.titleBox.setAlignment(Pos.CENTER);
        this.titleBox.setStyle("-fx-background-color: #13102f");
        this.titleBox.getChildren().add(title);
        this.titleBox.prefHeightProperty().bind(this.heightProperty().multiply(.2));
        VBox.setVgrow(titleBox, Priority.ALWAYS);
    }

    /**
     * Initializes the score message and score label, the score label will dynamically change
     * to reflect score for the previous game. This method also initalizes the score box and adds
     * the labels to it
     */
    private void initScoreLabels(){
        this.scoreMessage = new Label("Score:");
        this.score = new Label("XXX");
        this.scoreBox = new HBox();
        this.scoreMessage.setFont(Font.font("Segoe Print", FontWeight.NORMAL, FontPosture.REGULAR, 30));
        this.scoreMessage.setTextFill(Color.valueOf("#ffa411"));
        this.score.setFont(Font.font("Segoe Print", FontWeight.NORMAL, FontPosture.REGULAR, 30));
        this.score.setTextFill(Color.valueOf("#ffa411"));
        this.scoreBox.getChildren().addAll(scoreMessage, score);
        this.scoreBox.setStyle("-fx-background-color: #13102f");
        this.scoreBox.setSpacing(10);
        this.scoreBox.setAlignment(Pos.CENTER);
        this.scoreBox.prefHeightProperty().multiply(this.heightProperty().multiply(.2));
    }


    /**
     * Initializes the rank message and rank label, the rank label will dynamically change
     * to reflect score for the previous game. This method also initalizes the rank box and adds
     * the labels to it
     */
    private void initRankLabels(){
        this.rankMessage = new Label("Rank:");
        this.rank = new Label("XXX");
        this.rankBox = new HBox();
        this.rankMessage.setFont(Font.font("Segoe Print", FontWeight.NORMAL, FontPosture.REGULAR, 30));
        this.rankMessage.setTextFill(Color.valueOf("#ffa411"));
        this.rank.setFont(Font.font("Segoe Print", FontWeight.NORMAL, FontPosture.REGULAR, 30));
        this.rank.setTextFill(Color.valueOf("#ffa411"));
        this.rankBox.getChildren().addAll(rankMessage, rank);
        this.rankBox.setStyle("-fx-background-color: #13102f");
        this.rankBox.setSpacing(10);
        this.rankBox.setAlignment(Pos.CENTER);
        this.rankBox.prefHeightProperty().multiply(this.heightProperty().multiply(.2));
    }

    /**
     * creates a button that will return the user to the main menu, also
     * adds entered and exited mouse events to style the button
     */
    private void initPlayAgainBtn(){
        this.playAgain = new Button("Play Again");
        this.playAgain.setFont(buttonText);
        this.playAgain.setTextFill(Color.valueOf("#ffa411"));
        this.playAgain.setStyle(IDLE);
        this.playAgain.setOnMouseEntered(e->this.playAgain.setStyle(HOVER));
        this.playAgain.setOnMouseExited(e->this.playAgain.setStyle(IDLE));
        this.playAgain.setPrefWidth(250);
    }

    /**
     * creates a button that will quit the application, also adds mouse
     * entered and exited events to style the button
     */
    private void initQuitBtn(){
        this.quit = new Button("Quit");
        this.quit.setFont(buttonText);
        this.quit.setTextFill(Color.valueOf("#ffa411"));
        this.quit.setStyle(IDLE);
        this.quit.setOnMouseEntered(e->this.quit.setStyle(HOVER));
        this.quit.setOnMouseExited(e->this.quit.setStyle(IDLE));
        this.quit.setPrefWidth(250);
    }

    /**
     * creates a container to hold the buttons in the view
     */
    private void initButtonBox(){
        this.buttonBox = new HBox();
        this.buttonBox.setAlignment(Pos.CENTER);
        this.buttonBox.setSpacing(10);
        this.buttonBox.getChildren().addAll(this.quit, this.playAgain);
        this.buttonBox.setStyle("-fx-background-color: #13102f");
        this.buttonBox.prefHeightProperty().bind(this.heightProperty().multiply(.2));
    }

    /**
     * adds the end game image to the view
     */
    private void initImage(){
        this.imageBox = new VBox();
        this.endView = new ImageView();
        this.endImage = new Image(this.getClass().getResourceAsStream("Assets/EndGame.png"));
        this.endView.setImage(endImage);
        this.imageBox.setAlignment(Pos.CENTER);
        this.imageBox.setStyle("-fx-background-color: #13102f");
        this.imageBox.getChildren().add(endView);
        this.imageBox.prefHeightProperty().bind(this.heightProperty().multiply(.4));
        this.imageBox.prefWidthProperty().bind(this.widthProperty());
        VBox.setVgrow(imageBox, Priority.ALWAYS);
    }

    /**
     * sets the controller attribute also sets on click events
     * for playAgain and quit
     * @param control an instance of ClientController
     */
    @Override
    public void setController(ClientController control) {
        this.controller = control;
        this.quit.setOnMouseClicked(e->this.controller.handleQuit(e));
        this.playAgain.setOnMouseClicked(e->this.controller.handleQuit(e));
    }

    /**
     * sets the model attribute
     * @param model an instance of Model (After)
     */
    @Override
    public void setModel(Model model) {
        this.model = model;
    }

    /**
     * adds the score and rank achieved by the user from the model
     * to display in the view
     */
    @Override
    public void onChange() {
        String scoreFromGame = String.valueOf(((AfterModel) model).getScore());
        String rankFromGame = ((AfterModel) model).getRank();

        this.score.setText(scoreFromGame);
        this.rank.setText(rankFromGame);
    }

}


