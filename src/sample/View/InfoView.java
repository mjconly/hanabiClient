package sample.View;

import com.sun.glass.ui.View;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import sample.Controller.ClientController;
import sample.Model.DuringModel;
import sample.Model.Model;

/**
 * InfoView -- this view will be displayed in the right component of the gameView
 * when a user presses the info button from the actions view. This view will allow
 * the user to select a player at the table and send them information about
 * their current hand
 */
public class InfoView extends ClientView {
    /** controller to handle click events **/
    ClientController controller;
    /** model to update view on change **/
    Model model;
    /** view title **/
    Label title;
    /** container for view title **/
    VBox titleBox;
    /** info on how to select player **/
    Label selectPlayer;
    /** container for select player label**/
    VBox selectPlayerBox;
    /** info on how to select type **/
    Label selectType;
    /** container for type label **/
    VBox typeBox;
    /** toggle group for players **/
    ToggleGroup playerToggle;
    /** toggle group for info type **/
    ToggleGroup infoToggle;
    /** radio buttons for suit types **/
    RadioButton blue, yellow, red, green, white, rain;
    /** radio buttons for ranks **/
    RadioButton one, two, three, four, five;
    /** container for color radio buttons **/
    VBox colorBox;
    /** container for rank radio buttons **/
    VBox rankBox;
    /** container for color and rank box **/
    HBox infoBox;
    /** container for player radio buttons that will be made when game starts **/
    FlowPane playerPane;
    /** button to go back to Actions view **/
    private Button backButton;
    /** button to submit game name to server **/
    private Button submitButton;
    /** container for buttons **/
    private HBox buttonBox;
    /** Button font object **/
    private Font buttonText;
    /** css string to update buttons when hovered **/
    final String HOVER;
    /** css string to update buttons when idle **/
    final String IDLE;
    /** css string for radio style **/
    final String RADIO;


    /**
     * creates a new instance of InfoView with UI laid out accordingly
     * @param id ViewID INFO
     */
    public InfoView(ViewId id){
        super(id);

        VBox.setVgrow(this, Priority.ALWAYS);


        buttonText = Font.font("Segoe Print", FontWeight.BOLD, FontPosture.REGULAR, 27);
        HOVER = "-fx-text-fill: white; -fx-background-color: transparent; -fx-border-color: white; " +
                "-fx-border-width: 0 4 0 4; -fx-border-radius: 20";
        IDLE = "-fx-text-fill: #ffa411; -fx-background-color: transparent";
        RADIO = "-fx-text-fill: #ffa411; -fx-font-size: 18; -fx-font-family: 'Segoe Print'";

        this.setAlignment(Pos.CENTER);

        initTitle();
        initSelectPlayer();
        initRadioPlayer();
        initType();
        initRadioColor();
        initRadioRank();
        initInfoBox();
        initBackBtn();
        initSubmitBtn();
        initButtonBox();

        this.setSpacing(20);
        this.setAlignment(Pos.CENTER);

        this.getChildren().addAll(titleBox, selectPlayerBox, playerPane, typeBox, infoBox, buttonBox);
    }

    /**
     * Initializes the titleBox and adds a text label to it with appropriate
     * layout
     */
    private void initTitle(){
        this.titleBox = new VBox();
        this.title = new Label("Give Info");
        this.title.setFont(Font.font("Segoe Print", FontWeight.NORMAL, FontPosture.REGULAR, 50));
        this.title.setTextFill(Color.valueOf("#ffa411"));
        this.titleBox.setStyle("-fx-background-color: #13102f");
        this.titleBox.setAlignment(Pos.CENTER);
        this.titleBox.getChildren().add(title);
    }

    /**
     * creates a label with an info message to the user about what to do with player radio
     * buttons, also creates a container to store this label
     */
    private void initSelectPlayer(){
        this.selectPlayer = new Label("Select a player to inform");
        this.selectPlayerBox = new VBox();
        this.selectPlayer.setFont(Font.font("Segoe Print", FontWeight.NORMAL, FontPosture.REGULAR, 25));
        this.selectPlayer.setTextFill(Color.valueOf("#ffa411"));
        this.selectPlayerBox.setAlignment(Pos.CENTER);
        this.selectPlayerBox.getChildren().add(selectPlayer);
        this.selectPlayerBox.setStyle("-fx-background-color: #13102f");
    }


    /**
     * create toggle group and radio buttons. These buttons will represent
     * a player at the table and will refer to the players index into the
     * player array in the model
     */
    private void initRadioPlayer(){
        this.playerPane = new FlowPane();
        this.playerPane.setAlignment(Pos.CENTER);
        this.playerToggle = new ToggleGroup();
        RadioButton p1 = new RadioButton(" p1");
        p1.setSelected(true);
        RadioButton p2 = new RadioButton(" p2");
        RadioButton p3 = new RadioButton(" p3");
        RadioButton p4 = new RadioButton(" p4");
        p1.setStyle(RADIO);
        p2.setStyle(RADIO);
        p3.setStyle(RADIO);
        p4.setStyle(RADIO);
        p1.setToggleGroup(playerToggle);
        p2.setToggleGroup(playerToggle);
        p3.setToggleGroup(playerToggle);
        p4.setToggleGroup(playerToggle);
        this.playerPane.getChildren().addAll(p1, p2, p3, p4);
        this.playerPane.setHgap(10);
    }


    /**
     * creates a label with an info message to the user about what to do with info type
     * radio buttons, also creates a container to store this label
     */
    private void initType(){
        this.selectType = new Label("Select the information to send");
        this.typeBox = new VBox();
        this.selectType.setFont(Font.font("Segoe Print", FontWeight.NORMAL, FontPosture.REGULAR, 25));
        this.selectType.setTextFill(Color.valueOf("#ffa411"));
        this.typeBox.setAlignment(Pos.CENTER);
        this.typeBox.getChildren().add(selectType);
        this.typeBox.setStyle("-fx-background-color: #13102f");
    }


    /**
     * initialize the radio buttons that allow the user to select the SUIT or color info
     * that is to be sent to another player at the table
     */
    private void initRadioColor(){
        this.infoToggle = new ToggleGroup();
        this.blue = new RadioButton(" Blue");
        this.blue.setSelected(true);
        this.blue.setStyle(RADIO);

        this.red = new RadioButton(" Red");
        this.red.setStyle(RADIO);

        this.green = new RadioButton(" Green");
        this.green.setStyle(RADIO);

        this.yellow = new RadioButton(" Yellow");
        this.yellow.setStyle(RADIO);

        this.white = new RadioButton(" White");
        this.white.setStyle(RADIO);

        this.rain = new RadioButton(" Rainbow");
        this.rain.setStyle(RADIO);

        blue.setToggleGroup(infoToggle);
        red.setToggleGroup(infoToggle);
        green.setToggleGroup(infoToggle);
        yellow.setToggleGroup(infoToggle);
        white.setToggleGroup(infoToggle);
        rain.setToggleGroup(infoToggle);

        this.colorBox = new VBox();
        this.colorBox.getChildren().addAll(red, blue, green, yellow, white, rain);
        this.colorBox.setStyle("-fx-background-color: #13102f");
        this.colorBox.setSpacing(10);
        this.colorBox.setAlignment(Pos.TOP_LEFT);
    }

    /**
     * initialize the radio buttons that allow a user to send RANK info to another
     * player at the table
     */
    private void initRadioRank(){
        this.one = new RadioButton(" 1");
        this.one.setStyle(RADIO);

        this.two = new RadioButton(" 2");
        this.two.setStyle(RADIO);

        this.three = new RadioButton(" 3");
        this.three.setStyle(RADIO);

        this.four = new RadioButton(" 4");
        this.four.setStyle(RADIO);

        this.five = new RadioButton(" 5");
        this.five.setStyle(RADIO);

        one.setToggleGroup(infoToggle);
        two.setToggleGroup(infoToggle);
        three.setToggleGroup(infoToggle);
        four.setToggleGroup(infoToggle);
        five.setToggleGroup(infoToggle);

        this.rankBox = new VBox();
        this.rankBox.getChildren().addAll(one, two, three, four, five);
        this.rankBox.setStyle("-fx-background-color: #13102f");
        this.rankBox.setAlignment(Pos.TOP_LEFT);
        this.rankBox.setSpacing(10);
    }

    /**
     * create a container to store the info radio buttons [ SUIT | RANK ]
     */
    private void initInfoBox(){
        this.infoBox = new HBox();
        this.infoBox.setAlignment(Pos.TOP_CENTER);
        this.infoBox.setSpacing(10);
        this.infoBox.setStyle("-fx-background-color: #13102f");
        this.infoBox.getChildren().addAll(this.colorBox, this.rankBox);
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
     * set the controller attribute and add on click button events for the back and
     * submit buttons
     * @param control an instance of ClientController
     */
    @Override
    public void setController(ClientController control) {
        this.controller = control;
        this.backButton.setOnMouseClicked(e->controller.handleBack(e));
        this.submitButton.setOnMouseClicked(e->control.handleInfoSubmit(e, playerToggle, infoToggle));
    }

    /**
     * sets the model attribute
     * @param model an instance of Model (During)
     */
    @Override
    public void setModel(Model model) {
        this.model = model;
    }

    /**
     * loops over the number of players in the model, and creates a radio
     * button for each player except the user.
     */
    @Override
    public void onChange() {

        boolean isSet = false;
        this.playerPane.getChildren().clear();
        for(int i = 0; i < ((DuringModel)model).getNumOfPlayers(); i++) {
            if(!(i+1 == ((DuringModel)model).getUserPlayer())) {
                RadioButton p1 = new RadioButton(" p" + (i + 1));
                if (!isSet) {
                    p1.setSelected(true);
                    isSet = true;
                }
                p1.setStyle(RADIO);
                p1.setToggleGroup(playerToggle);
                this.playerPane.getChildren().add(p1);
            }
        }
        this.playerPane.setHgap(10);
    }
}
