package sample.View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
import sample.Model.BeforeModel;
import sample.Model.Model;



/**
 * WaitingView - This view is encountered by the user when they
 * are waiting to create or join a game. This view will display
 * the game name token to the user as well as indicate how many
 * players are still needed to start the game
 *
 * @version 1.0
 * @author Group B3
 */
public class WaitingView extends ClientView {
    /** controller to handle click events **/
    ClientController controller;
    /** model to update view onChange **/
    Model model;
    /** the view title **/
    Label title;
    /** container for title **/
    VBox titleBox;
    /** static load message text **/
    Label loadMsg;
    /** dynamic game name token **/
    Label gameToken;
    /** container for waitmsg and gametoken **/
    HBox gameMsgBox;
    /** static info message **/
    Label infoDisplay;
    /** dynamic message indicating players needed **/
    Label infoNeeded;
    /** container for info labels **/
    HBox infoBox;
    /** dyanmic game token label **/
    Label gameTokenActual;
    /** game token static message **/
    Label gameTokenStatic;
    /** container for game token inforamtion **/
    HBox tokenBox;
    /** container for gameMsgBox and infoBox **/
    VBox UIbox;
    /** object for bombs image **/
    private ImageView tokenView;
    /** object to write image stream to **/
    private Image tokenImage;
    /** container for fire works image **/
    private VBox imageBox;
    /** css string style UI box **/
    final String UI;



    /**
     * Create a new instance of WaitView with all UI components laid
     * out accordingly
     * @param id ViewId WAIT
     */
    public WaitingView(ViewId id){
        super(id);

        //lets the view grow vertically with window resize
        VBox.setVgrow(this, Priority.ALWAYS);

        //style UI box
        UI = "-fx-background-color: #13102f; -fx-border-color: white; -fx-border-width: 3 0 3 0;" +
                "-fx-border-style:solid; -fx-border-insets: 5";

        initTitle();
        initLoadtMsg();
        initGameToken();
        initGameMsgBox();
        initInfoDisplay();
        initInfoNeeded();
        initTokenLabelsAndBox();
        initInfoBox();
        initUIbox();
        initImage();

        this.setAlignment(Pos.TOP_CENTER);
        this.setStyle("-fx-background-color: #13102f");
        this.getChildren().addAll(this.titleBox, this.UIbox, this.imageBox);

    }


    /**
     * Initializes the titlebox and the view title, adds the title label
     * to the title box
     */
    public void initTitle(){
        this.titleBox = new VBox();
        this.title = new Label("Waiting ...");
        this.title.setFont(Font.font("Segoe Print", FontWeight.NORMAL, FontPosture.REGULAR, 100));
        this.title.setTextFill(Color.valueOf("#ffa411"));
        this.titleBox.setAlignment(Pos.CENTER);
        this.titleBox.getChildren().add(title);
        this.titleBox.setPadding(new Insets(25, 0, 0, 0));
        this.titleBox.setStyle("-fx-background-color: #13102f");
    }


    /**
     * Initializes the loadMsg, this message is static and will not change with model
     * data
     */
    public void initLoadtMsg(){
        this.loadMsg = new Label("Loading Game:");
        this.loadMsg.setFont(Font.font("Segoe Print", FontWeight.BOLD, FontPosture.REGULAR, 35));
        this.loadMsg.setTextFill(Color.valueOf("#ffa411"));
    }


    /**
     * Initializes the gameToken label, the text of this label will be
     * determined by a value in the model and will represent the game name
     */
    public void initGameToken(){
        this.gameToken = new Label("place holder");
        this.gameToken.setFont(Font.font("Segoe Print", FontWeight.BOLD, FontPosture.REGULAR, 35));
        this.gameToken.setTextFill(Color.valueOf("#ffa411"));
    }


    /**
     * Initializes the gameMsgBox, this box will contain the loadMsg and gameToken
     * labels
     */
    public void initGameMsgBox(){
        this.gameMsgBox = new HBox();
        this.gameMsgBox.setAlignment(Pos.CENTER);
        this.gameMsgBox.setStyle("-fx-background-color: #13102f");
        this.gameMsgBox.setPadding(new Insets(50, 0, 0, 0));
        this.gameMsgBox.setSpacing(20);
        this.gameMsgBox.getChildren().addAll(this.loadMsg, this.gameToken);
    }


    /**
     * Initializes the infoDisplay Label, this label is static and will not
     * change with model data
     */
    public void initInfoDisplay(){
        this.infoDisplay = new Label("Players Needed:");
        this.infoDisplay.setFont(Font.font("Segoe Print", FontWeight.BOLD, FontPosture.REGULAR, 35));
        this.infoDisplay.setTextFill(Color.valueOf("#ffa411"));
    }


    /**
     * Initializes the infoNeeded label, the text of this label is dynamic
     * and will change with model data. The label will indicate the
     * number of players needed to fill the game
     */
    public void initInfoNeeded(){
        this.infoNeeded = new Label("place holder");
        this.infoNeeded.setFont(Font.font("Segoe Print", FontWeight.BOLD, FontPosture.REGULAR, 35));
        this.infoNeeded.setTextFill(Color.valueOf("#ffa411"));
    }

    private void initTokenLabelsAndBox(){
        this.gameTokenStatic = new Label("Your Game Token:");
        this.gameTokenStatic.setFont(Font.font("Segoe Print", FontWeight.BOLD, FontPosture.REGULAR, 35));
        this.gameTokenStatic.setTextFill(Color.valueOf("#ffa411"));
        this.gameTokenActual = new Label("XXXXXXXXX");
        this.gameTokenActual.setFont(Font.font("Segoe Print", FontWeight.BOLD, FontPosture.REGULAR, 35));
        this.gameTokenActual.setTextFill(Color.valueOf("#ffa411"));
        this.tokenBox = new HBox();
        this.tokenBox.setSpacing(20);
        this.tokenBox.setAlignment(Pos.CENTER);
        this.tokenBox.getChildren().addAll(gameTokenStatic, gameTokenActual);
    }


    /**
     * Initializes the infoBox, this box will contain the infoDisplay and infoNeeded
     * labels
     */
    public void initInfoBox(){
        this.infoBox = new HBox();
        this.infoBox.setAlignment(Pos.CENTER);
        this.infoBox.setStyle("-fx-background-color: #13102f");
        this.infoBox.setPadding(new Insets(0, 0, 50, 0));
        this.infoBox.setSpacing(20);
        this.infoBox.getChildren().addAll(this.infoDisplay, this.infoNeeded);
    }


    /**
     * Initializes the UIbox, this box will hold the gameMsgBox and infoBox
     */
    private void initUIbox(){
        this.UIbox = new VBox();
        this.UIbox.setSpacing(20);
        this.UIbox.getChildren().addAll(this.gameMsgBox, this.tokenBox, this.infoBox);
        this.UIbox.setMaxWidth(750);
        this.UIbox.setStyle(UI);
    }


    /**
     * Initializes the imageBox, tokenView and tokenImage. The
     * image is read in from the Assests package
     */
    private void initImage(){
        this.imageBox = new VBox();
        this.tokenView = new ImageView();

        //TODO: Check os to make sure file path's can get resource!!!

        this.tokenImage = new Image(this.getClass().getResourceAsStream("Assets/waitPic.png"));
        this.tokenView.setImage(tokenImage);
        this.imageBox.setAlignment(Pos.CENTER);
        this.imageBox.setStyle("-fx-background-color: #13102f");
        this.imageBox.getChildren().add(tokenView);
        this.imageBox.prefHeightProperty().bind(this.heightProperty().multiply(.4));
        VBox.setVgrow(imageBox, Priority.ALWAYS);
    }


    /**
     * Sets the controller attribute
     * @param control an instance of ClientController
     */
    @Override
    public void setController(ClientController control) {
        this.controller = control;

        //TODO: Does this need a controller? No UI on this view?
    }


    /**
     * Sets the model attribute
     * @param model an instance of Model (Before, During, After)
     */
    @Override
    public void setModel(Model model) {
        this.model = model;
    }


    /**
     * updates the gameToken label and infoNeeded label when model data
     * changes, these values will indicate the name of the game the user
     * is waiting to load and the number of players needed to fill the game
     */
    @Override
    public void onChange() {
        this.gameToken.setText(((BeforeModel) model).getGameId());
        this.gameTokenActual.setText(((BeforeModel) model).getSecretToken());
        this.infoNeeded.setText(Integer.toString(((BeforeModel) model).getRemainingPlayers()));
    }
}
