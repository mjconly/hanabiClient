package sample.View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
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
import sample.Model.DuringModel;
import sample.Model.Model;
import sample.Model.Suit;


/**
 * PlayView - Allows the user to play a card from their hand
 *
 * @version 1.0
 * @author Group B3
 */
public class PlayView extends ClientView {
    /** controller to handle click events **/
    ClientController controller;
    /** model to update view on change **/
    Model model;
    /** HBox representing the users hand, will fill with cards **/
    HBox userHand;
    /** container for userHand **/
    VBox userBox;
    /** title of the view **/
    Label title;
    /** message to user on how to interact with view **/
    Label howTo;
    /** sends user input to controller **/
    Button submit;
    /** sends back to ActionsView **/
    Button back;
    /** container for view buttons **/
    HBox buttonBox;
    /** button font object **/
    private Font buttonText;
    /** css string to update buttons when hovered **/
    final String HOVER;
    /** css string to update buttons when idle **/
    final String IDLE;
    /** css string to style cards when hovered **/
    final String CARD_HOVER;
    /** css string to style card when IDLE **/
    final String CARD_IDLE;
    /** hBox for board postition Radio **/
    private HBox boardPos;
    /** container for play position **/
    private VBox playPostion;
    /** label for play position instructions **/
    private Label posInstructions;
    /** toggle group for position radio buttons **/
    private ToggleGroup posToggle;
    /** css string for radio button style **/
    final String RADIO;





    /**
     * Creates a new instance of PlayView with all UI attributes laid out
     * accordingly
     * @param id
     */
    public PlayView(ViewId id){
        super(id);

        VBox.setVgrow(this, Priority.ALWAYS);

        buttonText = Font.font("Segoe Print", FontWeight.BOLD, FontPosture.REGULAR, 27);
        HOVER = "-fx-text-fill: white; -fx-background-color: transparent; -fx-border-color: white; " +
                "-fx-border-width: 0 4 0 4; -fx-border-radius: 20";
        IDLE = "-fx-text-fill: #ffa411; -fx-background-color: transparent";
        CARD_HOVER = "-fx-border-width: 1, 1, 1, 1; -fx-border-color: tomato";
        CARD_IDLE = "-fx-border-width: 0";
        RADIO = "-fx-text-fill: #ffa411; -fx-font-size: 18; -fx-font-family: 'Segoe Print'";


        initTitle();
        initUserHand();
        initHowTo();
        initSubmitBtn();
        initBackBtn();
        initBackBtn();
        initButtonBox();
        initPositionSelection();

        //build mock data to test the view
        //buildMock();

        this.setAlignment(Pos.CENTER);
        this.getChildren().addAll(title, userBox, howTo, playPostion, buttonBox);
    }


    /**
     * Initializes the userBox, userHand and sets layout accordingly
     */
    private void initUserHand(){
        this.userBox = new VBox();
        this.userHand = new HBox();
        this.userBox.setAlignment(Pos.CENTER);
        this.userBox.prefWidthProperty().bind(this.widthProperty());
        this.userBox.prefHeightProperty().bind(this.heightProperty().multiply(.2));
        this.userHand.setSpacing(5);
        this.userHand.setAlignment(Pos.CENTER);
        this.userHand.prefWidthProperty().bind(this.userBox.widthProperty());
        this.userBox.getChildren().addAll(userHand);
        this.userBox.setStyle("-fx-background-color: #13102f");
    }


    /**
     * Initializes the message to user indicating what to do in this view
     */
    private void initHowTo(){
        this.howTo = new Label("Click a card and submit!");
        this.howTo.setFont(Font.font("Segoe Print", FontWeight.BOLD, FontPosture.REGULAR, 20));
        this.howTo.setTextFill(Color.valueOf("#ffa411"));
        this.howTo.setPadding(new Insets(20, 0, 20, 0));
    }

    /**
     * Initializes the title of the action to be performed
     */
    private void initTitle(){
        this.title = new Label("Play");
        this.title.setFont(Font.font("Segoe Print", FontWeight.BOLD, FontPosture.REGULAR, 50));
        this.title.setTextFill(Color.valueOf("#ffa411"));
    }


    /**
     * Initializes the backButton with appropriate layout, also sets
     * mouse entered and mouse exited events to style button
     */
    private void initBackBtn(){
        this.back = new Button("Back");
        this.back.setFont(buttonText);
        this.back.setStyle(IDLE);
        this.back.setOnMouseEntered(e->this.back.setStyle(HOVER));
        this.back.setOnMouseExited(e->this.back.setStyle(IDLE));
        this.back.setPrefWidth(150);
    }


    /**
     * Initializes the submitButton with appropriate layout, also sets
     * mouse entered and mouse exited events to style button
     */
    private void initSubmitBtn(){
        this.submit = new Button("Submit");
        this.submit.setFont(buttonText);
        this.submit.setStyle(IDLE);
        this.submit.setOnMouseEntered(e->this.submit.setStyle(HOVER));
        this.submit.setOnMouseExited(e->this.submit.setStyle(IDLE));
        this.submit.setPrefWidth(150);
    }


    /**
     * Initializes the buttonBox, adding all view button to the buttonBox
     */
    private void initButtonBox(){
        this.buttonBox = new HBox();
        this.buttonBox.setAlignment(Pos.CENTER);
        this.buttonBox.setSpacing(30);
        this.buttonBox.getChildren().addAll(this.back, this.submit);
        this.buttonBox.setStyle("-fx-background-color: #13102f");
        this.buttonBox.setPadding(new Insets(75, 0, 0, 0));
    }


    /**
     * Mock method to test visual implementation
     */
//    public void buildMock(){
//        for (int k = 0; k < 5; k++) {
//            ImageView imageView = new ImageView();
//            Image image = new Image(this.getClass().getResourceAsStream("Assets/cardBack.png"));
//            imageView.setImage(image);
//            imageView.setFitWidth(110);
//            imageView.setFitHeight(145);
//
//            VBox imageBox = new VBox();
//            imageBox.getChildren().add(imageView);
//            imageBox.setId(Integer.toString(k));
//
//            this.userHand.getChildren().add(imageBox);
//
//
//            imageBox.setOnMouseEntered(e->imageBox.setStyle(CARD_HOVER));
//            imageBox.setOnMouseExited(e->imageBox.setStyle(CARD_IDLE));
//            imageBox.setOnMouseClicked(e->onCardClick(imageBox));
//        }
//    }

    /**
     * Initialize the position label instructions, position toggle group and radio buttons
     * as well as the VBox and HBox containers for these UI components.
     */
    private void initPositionSelection(){
        this.playPostion = new VBox();
        this.boardPos = new HBox();
        this.posToggle = new ToggleGroup();
        this.posInstructions = new Label("Indicate the board position for the card to be played");
        this.posInstructions.setFont(Font.font("Segoe Print", FontWeight.BOLD, FontPosture.REGULAR, 20));
        this.posInstructions.setTextFill(Color.valueOf("#ffa411"));
        this.boardPos.setSpacing(10);
        this.boardPos.setAlignment(Pos.CENTER);
        this.playPostion.setAlignment(Pos.CENTER);

        for (int i = 0; i < 5; i++){
            RadioButton tempRb = new RadioButton();
            tempRb.setStyle(RADIO);
            switch (i){
                case 0:
                    tempRb.setText("Green");
                    tempRb.setSelected(true);
                    this.boardPos.getChildren().add(tempRb);
                    tempRb.setToggleGroup(posToggle);
                    break;
                case 1:
                    tempRb.setText("Red");
                    tempRb.setToggleGroup(posToggle);
                    this.boardPos.getChildren().add(tempRb);
                    break;
                case 2:
                    tempRb.setText("White");
                    tempRb.setToggleGroup(posToggle);
                    this.boardPos.getChildren().add(tempRb);
                    break;
                case 3:
                    tempRb.setText("Blue");
                    tempRb.setToggleGroup(posToggle);
                    this.boardPos.getChildren().add(tempRb);
                    break;
                case 4:
                    tempRb.setText("Yellow");
                    tempRb.setToggleGroup(posToggle);
                    this.boardPos.getChildren().add(tempRb);
                    break;
            }

        }

        this.playPostion.getChildren().addAll(boardPos, posInstructions);


    }

    /**
     * currently a test method, can be revised to send card index to controller
     * @param card a VBox representing a card that can be styled
     */
//    private void onCardClick(VBox card){
//        int pos = Integer.parseInt(card.getId());
//        card.setStyle(CARD_CLICK);
//        card.setOnMouseClicked(e->controller.handlePlaySelect(pos));
//    }


    /**
     * Set the controller attribute and onClick events for all buttons
     * in the view
     * @param control an instance of ClientController
     */
    @Override
    public void setController(ClientController control) {
        //TODO: set controller methods
        this.controller = control;
        this.back.setOnMouseClicked(e->controller.handleBack(e));
        this.submit.setOnMouseClicked(e->controller.handlePlaySubmit(e, this.posToggle));
    }

    /**
     * Sets the model attribute
     * @param model an instance of Model (During)
     */
    @Override
    public void setModel(Model model) {
        this.model = model;
    }


    /**
     * clears the users hand and then populates it by grabbing the users hand from
     * the model and then creating ImageViews for each card in the users hand and adding
     * them to the view
     */
    @Override
    public void onChange() {
        this.userHand.getChildren().clear();

        int user = ((DuringModel) model).getUserPlayer()-1;
        int handSize = ((DuringModel) model).getNumCards();

        for (int i = 0; i < handSize; i++) {
            Suit indexSuit = ((DuringModel) model).getPlayer(user).getpHand().getRevealedSuits().get(i);
            int indexRank = ((DuringModel) model).getPlayer(user).getpHand().getRevealedRanks().get(i);
            String filePath = "";

            if(indexRank == 0 && indexSuit == null){
                filePath = "cardBack.png";
            }
            else if (indexRank != 0 && indexSuit == null) {
                filePath = "rank" + indexRank + ".png";
            }
            else{
                switch(indexSuit){
                    case BLUE:
                        if(indexRank != 0){
                            filePath = "b" + indexRank + ".png";
                        }
                        else {
                            filePath = "bInfo.png"; //ex: b1.png
                        }
                        break;
                    case RED:
                        if(indexRank != 0) {
                            filePath = "r" + indexRank + ".png"; //ex: r1.png
                        }
                        else {
                            filePath = "rInfo.png"; //ex: r1.png
                        }
                        break;
                    case GREEN:
                        if(indexRank != 0) {
                            filePath = "g" + indexRank + ".png"; //ex: g1.png
                        }
                        else {
                            filePath = "gInfo.png"; //ex: g1.png
                        }
                        break;
                    case YELLOW:
                        if(indexRank != 0) {
                            filePath = "y" + indexRank + ".png"; //ex: y1.png
                        }
                        else{
                            filePath = "yInfo.png"; //ex: y1.png
                        }
                        break;
                    case WHITE:
                        if(indexRank != 0) {
                            filePath = "w" + indexRank + ".png"; //ex: w1.png
                        }
                        else{
                            filePath = "wInfo.png"; //ex: w1.png
                        }
                        break;
                    case MULTI:
                        if(indexRank != 0) {
                            filePath = "m" + indexRank + ".png"; //ex: m1.png
                        }
                        else{
                            filePath = "mInfo.png"; //ex: m1.png
                        }
                        break;
                }
            }

            ImageView imageView = new ImageView();
            Image image = new Image(this.getClass().getResourceAsStream("Assets/" + filePath));
            imageView.setImage(image);
            imageView.setFitWidth(110);
            imageView.setFitHeight(145);


            VBox imageBox = new VBox();
            imageBox.getChildren().add(imageView);
            imageBox.setId(Integer.toString(i + 1));

            imageBox.setOnMouseEntered(e->imageBox.setStyle(CARD_HOVER));
            imageBox.setOnMouseExited(e->imageBox.setStyle(CARD_IDLE));
            imageBox.setOnMouseClicked(e->controller.handlePlaySelect(imageBox));

            this.userHand.getChildren().add(imageBox);
        }
    }






}
