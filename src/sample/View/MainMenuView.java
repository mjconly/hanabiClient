package sample.View;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import sample.Controller.ClientController;
import sample.Model.Model;


/**
 * MainMenuView - Allows the user to create a game, join a game,
 * view the rules or quit.
 *
 * @version 1.0
 * @author Group B3
 */
public class MainMenuView extends ClientView {
    /** controller to handle click events **/
    private ClientController controller;
    /** model to update view onChange **/
    private Model model;
    /** button to create a game **/
    private Button createGame;
    /** button to join a game **/
    private Button joinGame;
    /** button to view the rules **/
    private Button viewRules;
    /** button to quit the application **/
    private Button quit;
    /** button container **/
    private VBox buttonBox;
    /** title container **/
    private VBox titleBox;
    /** object for fire works image **/
    private ImageView fireWorksView;
    /** object to write image stream to **/
    private Image fireWorksImage;
    /** container for fire works image **/
    private VBox imageBox;
    /** Button font object **/
    private Font buttonText;
    /** css string to update buttons when hovered **/
    final String HOVER;
    /** css string to update buttons when idle **/
    final String IDLE;

    /**
     * Creates a new instance of MainMenuView with all UI components
     * laid out accordingly
     * @param id ViewId MAIN
     */
    public MainMenuView(ViewId id){
        super(id);

        VBox.setVgrow(this, Priority.ALWAYS);

        //attributes used for styling buttons
        buttonText = Font.font("Segoe Print", FontWeight.BOLD, FontPosture.REGULAR, 27);
        HOVER = "-fx-text-fill: white; -fx-background-color: transparent; -fx-border-color: white; " +
                "-fx-border-width: 0 4 0 4; -fx-border-radius: 20";
        IDLE = "-fx-text-fill: #ffa411; -fx-background-color: transparent";

        //initialize view layout
        this.initTitle();
        this.initCreateGameBtn();
        this.initJoinGameBtn();
        this.initViewRules();
        this.initQuitBtn();
        this.initImage();
        this.initButtonBox();
        this.getChildren().addAll(titleBox, buttonBox, imageBox);
        this.setAlignment(Pos.CENTER);

    }


    /**
     * Initializes the titleBox and adds a text label to it with appropriate
     * layout
     */
    private void initTitle(){
        this.titleBox = new VBox();
        Label title = new Label("HANABI");
        title.setFont(Font.font("Segoe Print", FontWeight.NORMAL, FontPosture.REGULAR, 125));
        title.setTextFill(Color.valueOf("#ffa411"));
        this.titleBox.setAlignment(Pos.CENTER);
        this.titleBox.setStyle("-fx-background-color: #13102f");
        this.titleBox.getChildren().add(title);
        this.titleBox.prefHeightProperty().bind(this.heightProperty().multiply(.15));
        VBox.setVgrow(titleBox, Priority.ALWAYS);
    }


    /**
     * Initializes the createGame button with appropriate layout,
     * also sets mouse entered and move exited events on button
     * to style these events
     */
    private void initCreateGameBtn(){
        this.createGame = new Button("Create Game");
        this.createGame.setFont(buttonText);
        this.createGame.setTextFill(Color.valueOf("#ffa411"));
        this.createGame.setStyle(IDLE);
        this.createGame.setOnMouseEntered(e->this.createGame.setStyle(HOVER));
        this.createGame.setOnMouseExited(e->this.createGame.setStyle(IDLE));
        this.createGame.setPrefWidth(250);
    }


    /**
     * Initializes the joinGame button with appropriate layout,
     * also sets mouse entered and move exited events on button
     * to style these events
     */
    private void initJoinGameBtn(){
        this.joinGame = new Button("Join Game");
        this.joinGame.setFont(buttonText);
        this.joinGame.setTextFill(Color.valueOf("#ffa411"));
        this.joinGame.setStyle(IDLE);
        this.joinGame.setOnMouseEntered(e->this.joinGame.setStyle(HOVER));
        this.joinGame.setOnMouseExited(e->this.joinGame.setStyle(IDLE));
        this.joinGame.setPrefWidth(250);
    }


    /**
     * Initializes the viewRules button with appropriate layout,
     * also sets mouse entered and move exited events on button
     * to style these events
     */
    private void initViewRules(){
        this.viewRules = new Button("View Rules");
        this.viewRules.setFont(buttonText);
        this.viewRules.setTextFill(Color.valueOf("#ffa411"));
        this.viewRules.setStyle(IDLE);
        this.viewRules.setOnMouseEntered(e->this.viewRules.setStyle(HOVER));
        this.viewRules.setOnMouseExited(e->this.viewRules.setStyle(IDLE));
        this.viewRules.setPrefWidth(250);
    }


    /**
     * Initializes the quit button with appropriate layout,
     * also sets mouse entered and move exited events on button
     * to style these events
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
     * Initializes the imageBox, fireWorksView and fireWorksImage. The
     * image is read in from the Assests package
     */
    private void initImage(){
        this.imageBox = new VBox();
        this.fireWorksView = new ImageView();

        this.fireWorksImage = new Image(this.getClass().getResourceAsStream("Assets/MainMenuPic.png"));
        this.fireWorksView.setImage(fireWorksImage);
        this.imageBox.setAlignment(Pos.BOTTOM_CENTER);
        this.imageBox.setStyle("-fx-background-color: #13102f");
        this.imageBox.getChildren().add(fireWorksView);
        this.imageBox.prefHeightProperty().bind(this.heightProperty().multiply(.3));
        VBox.setVgrow(imageBox, Priority.ALWAYS);
    }


    /**
     * Initializes the buttonBox, adding all view button to the buttonBox
     */
    private void initButtonBox(){
        this.buttonBox = new VBox();
        this.buttonBox.setAlignment(Pos.CENTER);
        this.buttonBox.setSpacing(10);
        this.buttonBox.getChildren().addAll(this.createGame, this.joinGame, this.viewRules, this.quit);
        this.buttonBox.setStyle("-fx-background-color: #13102f");
        this.buttonBox.prefHeightProperty().bind(this.heightProperty().multiply(.55));
        VBox.setVgrow(buttonBox, Priority.ALWAYS);
    }


    /**
     * Sets the controller attribute and adds onClickEvent handlers to each
     * button in the View
     * @param control an instance of ClientController
     */
    @Override
    public void setController(ClientController control) {
        this.controller = control;
        //TODO: set event handlers in controller
        this.createGame.setOnMouseClicked(e->this.controller.handleCreateGame(e));
        this.joinGame.setOnMouseClicked(e->this.controller.handleJoinGame(e));
        this.viewRules.setOnMouseClicked(e->this.controller.handleRules(e));
        this.quit.setOnMouseClicked(e->this.controller.handleQuit(e));
    }


    /**
     * Sets the model attribute
     * @param model an instance of Model (Before)
     */
    @Override
    public void setModel(Model model) {
        this.model = model;
    }


    /**
     * Updates the view when data in the model changes
     */
    @Override
    public void onChange() {
        //No data in the view depends on model (silent)
    }
}
