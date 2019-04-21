package sample.View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
import sample.Model.Entities.Card;
import sample.Model.Model;
import sample.Model.Suit;

import java.util.ArrayList;


/**
 * DiscardPileView - displays all cards that have been discarded during
 * the current game. This view will occupy the right component of the
 * GameView
 */
public class DiscardPileView extends ClientView {
    /** controller to handle click events **/
    ClientController controller;
    /** model to update view onChange **/
    Model model;
    /** view title **/
    Label title;
    /** scroll pane to display discarded cards **/
    ScrollPane scrollPile;
    /** flowpane to hold discarded cards **/
    FlowPane pilePane;
    /** back to actions view **/
    Button back;
    /** button font object **/
    private Font buttonText;
    /** css string to update buttons when hovered **/
    final String HOVER;
    /** css string to update buttons when idle **/
    final String IDLE;
    /** css string for scrollPane **/
    final String SCROLL;


    /**
     * create a new instance of DiscardPileView with all UI laid out
     * accordingly
     * @param id a ViewID PILE
     */
    public DiscardPileView(ViewId id){
        super(id);

        VBox.setVgrow(this, Priority.ALWAYS);
        buttonText = Font.font("Segoe Print", FontWeight.BOLD, FontPosture.REGULAR, 35);
        HOVER = "-fx-text-fill: white; -fx-background-color: transparent; -fx-border-color: white; " +
                "-fx-border-width: 0 4 0 4; -fx-border-radius: 20";
        IDLE = "-fx-text-fill: #ffa411; -fx-background-color: transparent";
        SCROLL = "-fx-border-color: #ffa411; -fx-border-width: 15; -fx-border-radius: 10;" +
                "-fx-background-color: #13102f";

        this.setAlignment(Pos.CENTER);
        this.setSpacing(20);
        this.setPadding(new Insets(0, 50, 0, 0));


        initTitle();
        initScrollPile();
        initPilePane();
        initBackBtn();
        this.scrollPile.setContent(pilePane);

        //build view with mock data to test layout
        //buildMock();


        this.getChildren().addAll(title, scrollPile, back);

    }

    /**
     * initialize the title for the view
     */
    private void initTitle(){
        this.title = new Label("Discard Pile");
        this.title.setFont(Font.font("Segoe Print", FontWeight.BOLD, FontPosture.REGULAR, 50));
        this.title.setTextFill(Color.valueOf("#ffa411"));
        this.title.prefHeightProperty().bind(this.prefHeightProperty().multiply(.3));
    }

    /**
     * initialize a scroll pane for the discraded cards to be
     * displayed
     */
    private void initScrollPile(){
        this.scrollPile = new ScrollPane();
        this.scrollPile.setPrefViewportHeight(500);
        this.scrollPile.setMinViewportWidth(500);
        this.scrollPile.setStyle(SCROLL);
    }


    /**
     * inititalize a flow pane for that will hold the discarded cards
     * and be placed inside of the scroll pane
     */
    private void initPilePane(){
        this.pilePane = new FlowPane();
        this.pilePane.prefWidthProperty().bind(this.scrollPile.prefViewportWidthProperty());
        this.pilePane.setHgap(5);
        this.pilePane.setVgap(3);
        this.pilePane.setPadding(new Insets(0, 0, 0, 30));
    }


    /**
     * initialize the back button that will bring the user back to the gameView with
     * the actionsView displayed in the right component
     */
    private void initBackBtn(){
        this.back = new Button("Back");
        this.back.setFont(buttonText);
        this.back.setStyle(IDLE);
        this.back.setOnMouseEntered(e->this.back.setStyle(HOVER));
        this.back.setOnMouseExited(e->this.back.setStyle(IDLE));
        this.back.setPrefWidth(150);
        this.back.prefHeightProperty().bind(this.prefHeightProperty().multiply(.2));
    }


    /**
     * builds mock assets (cards) that can be used to populate the discard
     * pile view to test layout
     */
    public void buildMock(){
        Card[] red = new Card[5];
        Card[] blue = new Card[5];
        Card[] green = new Card[5];
        Card[] yellow = new Card[5];
        Card[] white = new Card[5];

        for (int i = 0; i < 5; i++) {
            for (int j = 1; j < 6; j++) {
                if (i == 0) {
                    Card aCard = new Card(j, Suit.RED);
                    red[j - 1] = aCard;
                } else if (i == 1) {
                    Card aCard = new Card(j, Suit.BLUE);
                    blue[j - 1] = aCard;
                } else if (i == 2) {
                    Card aCard = new Card(j, Suit.WHITE);
                    white[j - 1] = aCard;
                } else if (i == 3) {
                    Card aCard = new Card(j, Suit.YELLOW);
                    yellow[j - 1] = aCard;
                } else {
                    Card aCard = new Card(j, Suit.GREEN);
                    green[j - 1] = aCard;
                }
            }
        }

        ArrayList<Card[]> allCards = new ArrayList<>();
        allCards.add(red);
        allCards.add(yellow);
        allCards.add(white);
        allCards.add(blue);
        allCards.add(green);

        for (int p = 0; p < 5; p++) {
            HBox aHand = new HBox();
            aHand.setAlignment(Pos.CENTER);
            aHand.setSpacing(15);
            aHand.setPadding(new Insets(10, 0, 0, 0));
            for (int n = 0; n < allCards.get(p).length; n++) {
                Card tmp = allCards.get(p)[n];
                javafx.scene.image.ImageView imageView = new javafx.scene.image.ImageView();
                Image image = new Image(this.getClass().getResourceAsStream("Assets/" + tmp.getFilePath()));
                imageView.setImage(image);
                imageView.setFitWidth(110);
                imageView.setFitHeight(145);
                aHand.getChildren().add(imageView);
            }
            this.pilePane.getChildren().add(aHand);
        }

    }


    /**
     * sets the controller attribute and the on click event for the back
     * button
     * @param control an instance of ClientController
     */
    @Override
    public void setController(ClientController control) {
        this.controller = control;
        this.back.setOnMouseClicked(e->controller.handleBack(e));
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
     * clears the flowpane and then grabs the discard object from the model,
     * creating card ImageViews for each card in the discard object. These
     * cards are then added to the flowpane and displayed
     */
    @Override
    public void onChange() {
        this.pilePane.getChildren().clear();

        for (Card c : ((DuringModel) model).getDiscards().getCards()){
            ImageView imageView = new ImageView();
            Image image = new Image(this.getClass().getResourceAsStream("Assets/" + c.getFilePath()));
            imageView.setImage(image);
            imageView.setFitWidth(110);
            imageView.setFitHeight(145);
            this.pilePane.getChildren().add(imageView);
        }
    }
}
