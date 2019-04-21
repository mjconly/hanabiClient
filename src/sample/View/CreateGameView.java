package sample.View;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import sample.Controller.ClientController;
import sample.Model.Model;
import javafx.scene.text.Font;

/**
 * CreateGameView - allows the user to create a new game of Hanabi by
 * setting the number of players, timeout duration and game variant
 */
public class CreateGameView extends ClientView{
    /** controller to handle click events **/
    ClientController controller;
    /** model to update view on change **/
    Model model;
    /** back button to return to menu **/
    Button back;
    /** submit button to send create game info to server **/
    Button submit;
    /** container to hold/style buttons **/
    HBox buttonBox;
    /** label indicating where to input NSID **/
    Label nsid;
    /** text feild for user input (NSID) **/
    TextField nsidText;
    /** container for nsid info **/
    HBox nsidBox;
    /** label indicating characteristic of slider **/
    Label numPlayers;
    /** slider setting number of total players **/
    Slider numPlayersSlider ;
    /** label representing slider value for total player **/
    Label totalNumPlayers;
    /** label indicating characteristic of slider **/
    Label cpuPlayers;
    /** slider setting number of CPU players **/
    Slider cpuSlider;
    /** label representing slider value for CPU players **/
    Label totalCPUPlayers;
    /** label representing characteristic of slider **/
    Label timeOutLimit;
    /** label representing value of time slider **/
    Label timeVal;
    /** slider setting timeout value **/
    Slider timeSlider;
    /** view title **/
    Label title;
    /** container for view title **/
    VBox titleBox;
    /** contains the slider labels, sliders, slider value **/
    HBox UIbox;
    /** css string to style UI box **/
    final String UI;
    /** object for styling button **/
    private Font buttonText;
    /** css string to update buttons when hovered **/
    final String HOVER;
    /** css string to update buttons when idle **/
    final String IDLE;
    /** css string to style text field **/
    final String TEXT_FIELD;
    /** css String for radio button text **/
    final String RADIO;
    /** main container **/
    VBox mainBox;
    /** column left **/
    VBox leftBox;
    /** column mid **/
    VBox midBox;
    /** column right **/
    VBox rightBox;
    /** radio box **/
    HBox radioBox;
    /** radio label Box **/
    HBox radioLabelBox;
    /** radio standard **/
    RadioButton standard;
    /** radio fireWork **/
    RadioButton fireWork;
    /** radio wild **/
    RadioButton wild;
    /** toggle group for radio buttons **/
    ToggleGroup radioToggle;
    /** containers for radio button and label **/
    VBox col1, col2, col3;
    /** session Radio Button with Label **/
    RadioButton sessionRadio;



    /**
     * Creates a new instance of CreateGameView with all UI components laid out
     * accordingly
     * @param id ViewId.CREATE
     */
    public CreateGameView(ViewId id){
        super(id);

        VBox.setVgrow(this, Priority.ALWAYS);

        buttonText = Font.font("Segoe Print", FontWeight.BOLD, FontPosture.REGULAR, 27);
        HOVER = "-fx-text-fill: white; -fx-background-color: transparent; -fx-border-color: white; " +
                "-fx-border-width: 0 4 0 4; -fx-border-radius: 20";
        IDLE = "-fx-text-fill: #ffa411; -fx-background-color: transparent";
        TEXT_FIELD = "-fx-text-fill: black; -fx-font-size:20; -fx-control-inner-background: white;" +
                "-fx-border-color: white;";
        UI = "-fx-background-color: #13102f; -fx-border-color: white; -fx-border-width: 3 0 3 0;" +
                "-fx-border-style:solid; -fx-border-insets: 5";
        RADIO = "-fx-text-fill: #ffa411; -fx-font-size: 18; -fx-font-family: 'Segoe Print'";

        initViewLabels();
        initTextFields();
        initNumPlayersSlider();
        initNSIDBox();
        initCPUSlider();
        initTimeSlider();
        initTotalPlayerLabel();
        initCPULabel();
        initViewButtons();
        initButtonBox();
        initTitle();
        initLeftBox();
        initMidBox();
        initRightBox();
        initRadioButtons();
        initRadioSession();
        initUIbox();

        this.setAlignment(Pos.CENTER);

        this.mainBox = new VBox();
        this.mainBox.setAlignment(Pos.CENTER);
        this.mainBox.setSpacing(20);
        this.mainBox.getChildren().addAll(nsidBox, sessionRadio, UIbox, radioBox, buttonBox);
        this.setStyle("-fx-background-color: #13102f");
        this.getChildren().addAll(titleBox, mainBox);

    }

    /**
     * Initializes the title of the View
     */
    private void initTitle(){
        this.title = new Label("Create Game");
        this.titleBox = new VBox();
        this.title.setFont(Font.font("Segoe Print", FontWeight.NORMAL, FontPosture.REGULAR, 100));
        this.title.setTextFill(Color.valueOf("#ffa411"));
        this.titleBox.setAlignment(Pos.CENTER);
        this.titleBox.setStyle("-fx-background-color: #13102f");
        this.titleBox.getChildren().add(title);
        this.titleBox.prefHeightProperty().bind(this.heightProperty().multiply(.2));
        this.titleBox.setPadding(new Insets(-50, 0, 50, 0));
    }

    /**
     * Initializes the buttons for the view (back and submit). Also adds
     * events for mouse entered/exited to style the button during these
     * events
     */
    public void initViewButtons(){
        this.submit = new Button("Submit");
        this.submit.setFont(Font.font("Segoe Print", FontWeight.BOLD, FontPosture.REGULAR, 27));
        this.submit.setTextFill(Color.valueOf("#ffa411"));
        this.submit.setStyle(IDLE);
        this.submit.setOnMouseEntered(e->this.submit.setStyle(HOVER));
        this.submit.setOnMouseExited(e->this.submit.setStyle(IDLE));
        this.submit.setPrefWidth(250);

        this.back = new Button("Back");
        this.back.setFont(Font.font("Segoe Print", FontWeight.BOLD, FontPosture.REGULAR, 27));
        this.back.setTextFill(Color.valueOf("#ffa411"));
        this.back.setStyle(IDLE);
        this.back.setOnMouseEntered(e->this.back.setStyle(HOVER));
        this.back.setOnMouseExited(e->this.back.setStyle(IDLE));
        this.back.setPrefWidth(250);
    }


    /**
     * Initializes the text field for user to input nsid
     */
    public void initTextFields(){
        this.nsidText = new TextField();
        this.nsidText.setStyle(TEXT_FIELD);
    }

    /**
     * Initializes all of the labels for the view
     */
    public void initViewLabels(){
        this.nsid = new Label("Enter NSID:");
        this.numPlayers = new Label("Enter Number of Total Players:");
        this.timeOutLimit = new Label("Enter Time Out Limit:");
        this.timeVal = new Label("60");
        this.nsid.setFont(Font.font("Segoe Print", FontWeight.BOLD, FontPosture.REGULAR, 27));
        this.nsid.setTextFill(Color.valueOf("#ffa411"));
        this.numPlayers.setFont(Font.font("Segoe Print", FontWeight.BOLD, FontPosture.REGULAR, 20));
        this.numPlayers.setTextFill(Color.valueOf("#ffa411"));
        this.timeOutLimit.setFont(Font.font("Segoe Print", FontWeight.BOLD, FontPosture.REGULAR, 20));
        this.timeOutLimit.setTextFill(Color.valueOf("#ffa411"));
        this.timeVal.setFont(Font.font("Segoe Print", FontWeight.BOLD, FontPosture.REGULAR, 20));
        this.timeVal.setTextFill(Color.valueOf("#ffa411"));
    }


    /**
     * create a container to hold the buttons
     */
    private void initButtonBox(){
        this.buttonBox = new HBox();
        this.buttonBox.setAlignment(Pos.CENTER);
        this.buttonBox.setSpacing(30);
        this.buttonBox.getChildren().addAll(this.back, this.submit);
        this.buttonBox.setStyle("-fx-background-color: #13102f");
        this.buttonBox.setPadding(new Insets(50, 0, 50, 0));
    }

    /**
     * create a container to hold the nsid label and text field
     */
    public void initNSIDBox(){
        this.nsidBox = new HBox();
        this.nsidBox.getChildren().addAll(nsid,nsidText);
        this.nsidBox.setSpacing(20);
        this.nsidBox.setAlignment(Pos.CENTER);
    }

    /**
     * create a label to indicate the number of total players currently selected
     */
    public void initTotalPlayerLabel(){
        this.totalNumPlayers = new Label(String.valueOf((int)this.numPlayersSlider.getValue()));
        this.totalNumPlayers.setFont(Font.font("Segoe Print", FontWeight.BOLD, FontPosture.REGULAR, 20));
        this.totalNumPlayers.setTextFill(Color.valueOf("#ffa411"));
    }


    /**
     * initialize a slider to set the number of players required for the game. Values
     * will be updated and displayed with the totalNumPlayers Label
     */
    public void initNumPlayersSlider(){
        this.numPlayersSlider = new Slider();
        this.numPlayersSlider.setMinWidth(350);
        this.numPlayersSlider.setMin(2);
        this.numPlayersSlider.setMax(5);
        this.numPlayersSlider.setStyle("-fx-inner-control-background: #ffa411");
        this.numPlayersSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                totalNumPlayers.setText(String.valueOf(Math.round(numPlayersSlider.getValue())));
                cpuSlider.setMax(Math.round(numPlayersSlider.getValue())-1);
            }
        });
    }


    /**
     * create a label that will display the number of cpu players for the
     * game. This number will be updated by the cpu slider
     */
    public void initCPULabel(){
        this.cpuPlayers = new Label("Number of AI Players:");
        this.cpuPlayers.setFont(Font.font("Segoe Print", FontWeight.BOLD, FontPosture.REGULAR, 20));
        this.cpuPlayers.setTextFill(Color.valueOf("#ffa411"));
        this.totalCPUPlayers = new Label(String.valueOf((int)this.cpuSlider.getValue()));
        this.totalCPUPlayers.setFont(Font.font("Segoe Print", FontWeight.BOLD, FontPosture.REGULAR, 20));
        this.totalCPUPlayers.setTextFill(Color.valueOf("#ffa411"));
    }


    /**
     * initialize a slider to set the number of CPU players for the game
     */
    public void initCPUSlider(){
        this.cpuSlider = new Slider();
        cpuSlider.setMin(0);
        this.cpuSlider.setMinWidth(350);
        this.cpuSlider.setStyle("-fx-inner-control-background: #ffa411");
        cpuSlider.setMax(this.numPlayersSlider.getValue()-1);
        this.cpuSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                totalCPUPlayers.setText(String.valueOf(Math.round(cpuSlider.getValue())));
            }
        });
    }

    /**
     * initialize a slider that sets the time limit for each users turn in game
     */
    public void initTimeSlider(){
        this.timeSlider = new Slider();
        this.timeSlider.setMinWidth(350);
        this.timeSlider.setStyle("-fx-inner-control-background: #ffa411");
        this.timeSlider.setMin(60);
        this.timeSlider.setMax(120);
        this.timeSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                timeVal.setText(String.valueOf(Math.round(timeSlider.getValue())));
            }
        });
    }


    /**
     * create a box to hold the 3 columns of the main UI
     */
    private void initUIbox(){
        this.UIbox = new HBox();
        this.UIbox.setSpacing(20);
        this.UIbox.getChildren().addAll(leftBox, midBox, rightBox);
        this.UIbox.setMaxWidth(850);
        this.UIbox.setStyle(UI);
        this.UIbox.setAlignment(Pos.CENTER);
        this.UIbox.prefWidthProperty().bind(this.widthProperty());
        this.UIbox.prefHeightProperty().bind(this.heightProperty().multiply(.3));
    }

    /**
     * create a VBox to stack the labels that store the title for the slider
     */
    private void initLeftBox(){
        this.leftBox = new VBox();
        this.leftBox.setSpacing(20);
        this.leftBox.setAlignment(Pos.CENTER_LEFT);
        this.leftBox.setStyle(UI);
        this.leftBox.getChildren().addAll(numPlayers, cpuPlayers, timeOutLimit);
        this.leftBox.setStyle("-fx-background-color: #13102f");
    }

    /**
     * create a VBox to stack the sliders
     */
    private void initMidBox(){
        this.midBox = new VBox();
        this.midBox.setSpacing(30);
        this.midBox.setAlignment(Pos.CENTER_LEFT);
        this.midBox.setStyle(UI);
        this.midBox.getChildren().addAll(numPlayersSlider, cpuSlider, timeSlider);
        this.midBox.setStyle("-fx-background-color: #13102f");
    }

    /**
     * create a VBox to stack the labels that display the slider values
     */
    private void initRightBox(){
        this.rightBox = new VBox();
        this.rightBox.setSpacing(20);
        this.rightBox.setAlignment(Pos.CENTER_LEFT);
        this.rightBox.setStyle(UI);
        this.rightBox.getChildren().addAll(totalNumPlayers, totalCPUPlayers, timeVal);
        this.rightBox.setStyle("-fx-background-color: #13102f");
        this.rightBox.setMinWidth(100);
    }

    /**
     * create toggle group for game variant radio buttons. These buttons will be
     * placed into a container and added to the view
     */
    private void initRadioButtons(){
        this.radioBox = new HBox();
        this.radioLabelBox = new HBox();
        this.radioToggle = new ToggleGroup();
        this.standard = new RadioButton();
        this.fireWork = new RadioButton();
        this.wild = new RadioButton();

        this.standard.setId("none");
        this.standard.setToggleGroup(radioToggle);
        this.fireWork.setId("firework");
        this.fireWork.setToggleGroup(radioToggle);

        this.wild.setId("wild");
        this.wild.setToggleGroup(radioToggle);

        this.radioToggle.selectToggle(standard);


        Label standardLabel = new Label("Standard");
        Label fireworkLabel = new Label("Firework");
        Label wildLabel = new Label("Wild");


        standardLabel.setFont(Font.font("Segoe Print", FontWeight.BOLD, FontPosture.REGULAR, 20));
        standardLabel.setTextFill(Color.valueOf("#ffa411"));
        fireworkLabel.setFont(Font.font("Segoe Print", FontWeight.BOLD, FontPosture.REGULAR, 20));
        fireworkLabel.setTextFill(Color.valueOf("#ffa411"));
        wildLabel.setFont(Font.font("Segoe Print", FontWeight.BOLD, FontPosture.REGULAR, 20));
        wildLabel.setTextFill(Color.valueOf("#ffa411"));

        col1 = new VBox();
        col2 = new VBox();
        col3 = new VBox();

        col1.setSpacing(20);
        col1.setAlignment(Pos.CENTER);
        col2.setSpacing(20);
        col2.setAlignment(Pos.CENTER);
        col3.setSpacing(20);
        col3.setAlignment(Pos.CENTER);

        col1.getChildren().addAll(standard, standardLabel);
        col2.getChildren().addAll(fireWork, fireworkLabel);
        col3.getChildren().addAll(wild, wildLabel);
        this.radioBox.setSpacing(50);
        this.radioBox.setAlignment(Pos.CENTER);
        this.radioBox.getChildren().addAll(col1, col2, col3);

    }

    /**
     * create a radio button that will allow the user to quit any games
     * that they may already be in
     */
    private void initRadioSession(){
        this.sessionRadio = new RadioButton(" Force close previous game session");
        this.sessionRadio.setStyle(RADIO);
    }

    /**
     * sets the model attribute
     * @param model an instance of Model (Before)
     */
    @Override
    public void setModel(Model model) {
        this.model = model;
    }

    /**
     * sets the controller attribute as well as the on click button events for the back and submit
     * buttons
     * @param controller
     */
    @Override
    public void setController(ClientController controller) {
        this.controller = controller;
        this.back.setOnMouseClicked(e->this.controller.handleBack(e));
        this.submit.setOnMouseClicked(e->this.controller.handleCreateSubmit(e, nsidText.getText(),
                Integer.parseInt(totalNumPlayers.getText()), timeVal.getText(), sessionRadio.isSelected(), radioToggle));
    }

    /**
     * remove user input when this view is pulled into the root view
     */
    @Override
    public void onChange() {
        this.nsidText.setText("");
        this.sessionRadio.setSelected(false);
    }
}

