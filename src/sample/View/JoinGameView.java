package sample.View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
import sample.Model.Model;


/**
 * JoinGameView - Allows the user to join a game by indicating the game name
 * and submitting it to the server
 *
 * @version 1.0
 * @author Group B3
 */
public class JoinGameView extends ClientView {
    /** controller to handle click events **/
    private ClientController controller;
    /** model to update view onChange **/
    private Model model;
    /** label indicating game name field */
    private Label gameKey;
    /** text field for user input **/
    private TextField gameKeyText;
    /** container from game key UI **/
    private HBox gameKeyBox;
    /** button to go back to main **/
    private Button backButton;
    /** button to submit game name to server **/
    private Button submitButton;
    /** container for buttons **/
    private HBox buttonBox;
    /** container for all view UI **/
    private VBox UIbox;
    /** label for title **/
    private Label title;
    /** container for title **/
    private VBox titleBox;
    /** nsid text field **/
    private TextField nsidText;
    /** nsid label **/
    private Label nsidLabel;
    /** nsid Box **/
    private HBox nsidBox;
    /** nsid text field **/
    private TextField tokenText;
    /** nsid label **/
    private Label tokenLabel;
    /** nsid Box **/
    private HBox tokenBox;
    /** button font object **/
    private Font buttonText;
    /** object for bombs image **/
    private ImageView bombsView;
    /** object to write image stream to **/
    private Image bombsImage;
    /** container for fire works image **/
    private VBox imageBox;
    /** css string to update buttons when hovered **/
    final String HOVER;
    /** css string to update buttons when idle **/
    final String IDLE;
    /** css string to style UI box **/
    final String UI;
    /** css string to style text field **/
    final String TEXT_FIELD;


    /**
     * Create a new instance of JoinGameView with all UI components laid
     * out accordingly
     * @param id
     */
    public JoinGameView(ViewId id){
        super(id);

        VBox.setVgrow(this, Priority.ALWAYS);

        buttonText = Font.font("Segoe Print", FontWeight.BOLD, FontPosture.REGULAR, 27);
        HOVER = "-fx-text-fill: white; -fx-background-color: transparent; -fx-border-color: white; " +
                "-fx-border-width: 0 4 0 4; -fx-border-radius: 20";
        IDLE = "-fx-text-fill: #ffa411; -fx-background-color: transparent";
        UI = "-fx-background-color: #13102f; -fx-border-color: white; -fx-border-width: 3 0 3 0;" +
            "-fx-border-style:solid; -fx-border-insets: 5";
        TEXT_FIELD = "-fx-text-fill: black; -fx-font-size:20; -fx-control-inner-background: white;" +
                "-fx-border-color: white;";

        initTitle();
        initGameKey();
        initGameKeyText();
        initGameKeyBox();
        initBackBtn();
        initSubmitBtn();
        initButtonBox();
        initNsid();
        initToken();
        initUIbox();
        initImage();
        this.setAlignment(Pos.CENTER);
        this.setStyle("-fx-background-color: #13102f");
        this.getChildren().addAll(this.titleBox, this.UIbox, this.imageBox);
    }


    /**
     * Initialize title for view with appropriate layout
     */
    private void initTitle(){
        this.title = new Label("Join Game");
        this.titleBox = new VBox();
        this.title.setFont(Font.font("Segoe Print", FontWeight.NORMAL, FontPosture.REGULAR, 100));
        this.title.setTextFill(Color.valueOf("#ffa411"));
        this.titleBox.setAlignment(Pos.CENTER);
        this.titleBox.setStyle("-fx-background-color: #13102f");
        this.titleBox.getChildren().add(title);
        this.titleBox.prefHeightProperty().bind(this.heightProperty().multiply(.2));
        VBox.setVgrow(titleBox, Priority.ALWAYS);
    }


    /**
     * Initialize label to indicate where to input game name with appropriate layout
     */
    private void initGameKey(){
        this.gameKey = new Label("Game ID");
        this.gameKey.setFont(Font.font("Segoe Print", FontWeight.BOLD, FontPosture.REGULAR, 35));
        this.gameKey.setTextFill(Color.valueOf("#ffa411"));
    }


    /**
     * Initialize text field for user input with appropriate layout
     */
    private void initGameKeyText(){
        this.gameKeyText = new TextField();
        this.gameKeyText.setStyle(TEXT_FIELD);
    }


    /**
     * Initialize gameKeyBox to hold gameKey label and gameKeyText field
     */
    private void initGameKeyBox(){
        this.gameKeyBox = new HBox();
        this.gameKeyBox.setAlignment(Pos.CENTER);
        this.gameKeyBox.setStyle("-fx-background-color: #13102f");
        this.gameKeyBox.setPadding(new Insets(50, 0, 0, 0));
        this.gameKeyBox.setSpacing(70);
        this.gameKeyBox.getChildren().addAll(this.gameKey, this.gameKeyText);
    }

    private void initNsid(){
        this.nsidLabel = new Label("Enter NSID");
        this.nsidLabel.setFont(Font.font("Segoe Print", FontWeight.BOLD, FontPosture.REGULAR, 35));
        this.nsidLabel.setTextFill(Color.valueOf("#ffa411"));
        this.nsidText = new TextField();
        this.nsidText.setStyle(TEXT_FIELD);
        this.nsidBox = new HBox();
        this.nsidBox.getChildren().addAll(nsidLabel, nsidText);
        this.nsidBox.setSpacing(30);
        this.nsidBox.setAlignment(Pos.CENTER);
    }


    private void initToken(){
        this.tokenLabel = new Label("Enter Token");
        this.tokenLabel.setFont(Font.font("Segoe Print", FontWeight.BOLD, FontPosture.REGULAR, 35));
        this.tokenLabel.setTextFill(Color.valueOf("#ffa411"));
        this.tokenText = new TextField();
        this.tokenText.setStyle(TEXT_FIELD);
        this.tokenBox = new HBox();
        this.tokenBox.getChildren().addAll(tokenLabel, tokenText);
        this.tokenBox.setSpacing(20);
        this.tokenBox.setAlignment(Pos.CENTER);
    }


    /**
     * Initializes the backButton with appropriate layout, also sets
     * mouse entered and mouse exited events to style button
     */
    private void initBackBtn(){
        this.backButton = new Button("Back");
        this.backButton.setFont(buttonText);
        this.backButton.setStyle(IDLE);
        this.backButton.setOnMouseEntered(e->this.backButton.setStyle(HOVER));
        this.backButton.setOnMouseExited(e->this.backButton.setStyle(IDLE));
        this.backButton.setPrefWidth(150);
    }


    /**
     * Initializes the submitButton with appropriate layout, also sets
     * mouse entered and mouse exited events to style button
     */
    private void initSubmitBtn(){
        this.submitButton = new Button("Submit");
        this.submitButton.setFont(buttonText);
        this.submitButton.setStyle(IDLE);
        this.submitButton.setOnMouseEntered(e->this.submitButton.setStyle(HOVER));
        this.submitButton.setOnMouseExited(e->this.submitButton.setStyle(IDLE));
        this.submitButton.setPrefWidth(150);
    }


    /**
     * Initializes the buttonBox, adding all view button to the buttonBox
     */
    private void initButtonBox(){
        this.buttonBox = new HBox();
        this.buttonBox.setAlignment(Pos.CENTER);
        this.buttonBox.setSpacing(30);
        this.buttonBox.getChildren().addAll(this.backButton, this.submitButton);
        this.buttonBox.setStyle("-fx-background-color: #13102f");
        this.buttonBox.setPadding(new Insets(0, 0, 50, 0));
    }


    /**
     * Initializes the UIBox, adding both the gameKeyBox and buttonBox to itself
     */
    private void initUIbox(){
        this.UIbox = new VBox();
        this.UIbox.setSpacing(20);
        this.UIbox.getChildren().addAll(this.gameKeyBox, this.nsidBox, this.tokenBox, this.buttonBox);
        this.UIbox.setMaxWidth(750);
        this.UIbox.setStyle(UI);
    }


    /**
     * Initializes the imageBox, bombsView and bombsImage. The
     * image is read in from the Assests package
     */
    private void initImage(){
        this.imageBox = new VBox();
        this.bombsView = new ImageView();

        //TODO: Check os to make sure file path's can get resource!!!


        this.bombsImage = new Image(this.getClass().getResourceAsStream("Assets/JoinGamePic.png"));
        this.bombsView.setImage(bombsImage);
        this.imageBox.setAlignment(Pos.CENTER);
        this.imageBox.setStyle("-fx-background-color: #13102f");
        this.imageBox.getChildren().add(bombsView);
        this.imageBox.prefHeightProperty().bind(this.heightProperty().multiply(.4));
        VBox.setVgrow(imageBox, Priority.ALWAYS);
    }



    /**
     * Sets the controller attribute and adds onClickEvent handlers to each
     * button in the View. Submit will send user input to controller
     * @param control an instance of ClientController
     */
    @Override
    public void setController(ClientController control) {
        this.controller = control;

        //TODO: add event handlers in controller
        this.backButton.setOnMouseClicked(e->this.controller.handleBack(e));
        this.submitButton.setOnMouseClicked(e->this.controller.handleJoinSubmit(e, gameKeyText.getText(),
                nsidText.getText(), tokenText.getText()));

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
        //TODO: Add update on change functionality
        this.gameKeyText.setText("");
        this.nsidText.setText("");
        this.tokenText.setText("");
    }
}