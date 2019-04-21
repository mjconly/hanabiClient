package sample.View;

import javafx.geometry.Insets;
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
import sample.Model.DuringModel;
import sample.Model.Model;
import sample.Model.Suit;


/**
 * DiscardView - This view will be displayed in the right component of
 * the GameView when the user clicks "discard" from the actionsView. This
 * view will show the users current hand and allow them to discard a card
 */
public class DiscardView extends ClientView {
    ClientController controller;
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
    /** css string to style card when CLICK **/
    final String CARD_CLICK;


    /**
     * Creates a new instance of DiscardView with all UI attributes laid out
     * accordingly
     * @param id
     */
    public DiscardView(ViewId id){
        super(id);

        VBox.setVgrow(this, Priority.ALWAYS);

        buttonText = Font.font("Segoe Print", FontWeight.BOLD, FontPosture.REGULAR, 27);
        HOVER = "-fx-text-fill: white; -fx-background-color: transparent; -fx-border-color: white; " +
                "-fx-border-width: 0 4 0 4; -fx-border-radius: 20";
        IDLE = "-fx-text-fill: #ffa411; -fx-background-color: transparent";
        CARD_HOVER = "-fx-border-width: 2, 0, 0, 0; -fx-border-color: transparent";
        CARD_IDLE = "-fx-border-width: 0";
        CARD_CLICK = "-fx-border-width: 2, 0, 0, 0; -fx-border-color: tomato";


        initTitle();
        initUserHand();
        initHowTo();
        initSubmitBtn();
        initBackBtn();
        initBackBtn();
        initButtonBox();

        //create mock data to test layout in view
        //buildMock();

        this.setAlignment(Pos.CENTER);
        this.getChildren().addAll(title, userBox, howTo, buttonBox);
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
        this.title = new Label("Discard");
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
    public void buildMock(){
        for (int k = 0; k < 5; k++) {
            ImageView imageView = new ImageView();
            Image image = new Image(this.getClass().getResourceAsStream("Assets/cardBack.png"));
            imageView.setImage(image);
            imageView.setFitWidth(110);
            imageView.setFitHeight(145);

            VBox imageBox = new VBox();
            imageBox.getChildren().add(imageView);
            imageBox.setId(Integer.toString(k));

            this.userHand.getChildren().add(imageBox);

            imageBox.setOnMouseEntered(e->imageBox.setStyle(CARD_HOVER));
            imageBox.setOnMouseExited(e->imageBox.setStyle(CARD_IDLE));
            imageBox.setOnMouseClicked(e->onCardClick(imageBox));
        }
    }


    /**
     * currently a test method, can be revised to send card index to controller
     * @param card a VBox representing a card that can be styled
     */
    private void onCardClick(VBox card){
        System.out.println("Card " + card.getId() +  " was selected");
        card.setStyle(CARD_CLICK);
    }


    /**
     * Set the controller attribute and onClick events for all buttons
     * in the view
     * @param control an instance of ClientController
     */
    @Override
    public void setController(ClientController control) {
        this.controller = control;
        //TODO: set controller methods
        this.back.setOnMouseClicked(e->controller.handleBack(e));
        this.submit.setOnMouseClicked(e->controller.handleDiscardSubmit(e));
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
                            filePath = "b" + indexRank + ".png"; //ex: b1.png
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
            imageBox.setOnMouseClicked(e->controller.handleDiscardSelect(imageBox));

            this.userHand.getChildren().add(imageBox);
        }
    }
}
