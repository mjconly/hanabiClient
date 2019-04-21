package sample.View;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import sample.Controller.ClientController;
import sample.Model.DuringModel;
import sample.Model.Entities.Card;
import sample.Model.Entities.Firework;
import sample.Model.Entities.HumanPlayer;
import sample.Model.Model;
import sample.Model.Suit;

import java.util.ArrayList;


/**
 * GameView - this is the primary game play view. It is split into 3 components.
 * A left component which will display the players cards and user cards, a right
 * component which will display the sub game view (actions, discard, play, info, log)
 * and a button component which will store the UI relating to the game board
 */
public class GameView extends ClientView {
    /** controller to handle click events **/
    ClientController controller;
    /** model to update view onChange **/
    Model model;
    /** container for left and right boxes **/
    private HBox mainBox;
    /** container for left side of view **/
    private VBox leftBox;
    /** container for right side of view **/
    private VBox rightBox;
    /** container for player hands **/
    private VBox playersBox;
    /** container for user's hand **/
    private VBox userBox;
    /** floawPane for user's cards **/
    private HBox userPane;
    /** container for board objects **/
    private HBox boardBox;
    /** flowPane for played cards **/
    private HBox boardPane;
    /** container for tokens **/
    private VBox tokenBox;
    /** main background css string **/
    final String BG_COLOR;
    /** Label remaining fuses **/
    private Label fuseNumber;
    /** Label reaming info **/
    private Label infoNumber;


    /**
     * creates a new instance of GameView with UI laid out accordingly
     * @param id
     */
    public GameView(ViewId id){
        super(id);
        VBox.setVgrow(this, Priority.ALWAYS);
        BG_COLOR = "-fx-background-color: #13102f";


        this.mainBox = new HBox();
        this.mainBox.prefHeightProperty().bind(this.heightProperty().multiply(.8));

        initLeftBox();
        initRightBox();
        initBoardBox();
        initPlayerBox();
        initUserBox();
        initUserPane();
        initBoardPane();
        initTokenBox();

        //build view with mock data to test layout
        //buildMock();

        this.userBox.getChildren().add(userPane);
        this.leftBox.getChildren().addAll(playersBox, userBox);
        this.boardBox.getChildren().addAll(boardPane, tokenBox);

        this.mainBox.getChildren().addAll(this.leftBox, this.rightBox);

        this.getChildren().addAll(mainBox, boardBox);

    }

    /**
     * initialize the left side of the view and lock it into place
     */
    private void initLeftBox(){
        this.leftBox = new VBox();
        this.leftBox.prefWidthProperty().bind(this.mainBox.widthProperty().multiply(.6));
        this.leftBox.prefHeightProperty().bind(this.mainBox.heightProperty());
        this.leftBox.setSpacing(10);
        this.leftBox.setStyle(BG_COLOR);
    }


    /**
     * initialize the right side of the view and lock it into place
     */
    private void initRightBox(){
        this.rightBox = new VBox();
        this.rightBox.prefWidthProperty().bind(this.mainBox.widthProperty().multiply(.4));
        this.rightBox.prefHeightProperty().bind(this.mainBox.heightProperty());
        this.rightBox.setStyle(BG_COLOR);
    }


    /**
     * initialize the bottom part of the view and lock it into place
     */
    private void initBoardBox(){
        this.boardBox = new HBox();
        this.boardBox.prefWidthProperty().bind(this.widthProperty());
        this.boardBox.prefHeightProperty().bind(this.heightProperty().multiply(.2));
        this.boardBox.setAlignment(Pos.CENTER);
        this.boardBox.setStyle(BG_COLOR);

    }

    /**
     * create a container that will hold and display the players hands
     */
    private void initPlayerBox(){
        this.playersBox = new VBox();
        this.playersBox.prefWidthProperty().bind(this.leftBox.widthProperty());
        this.playersBox.prefHeightProperty().bind(this.leftBox.heightProperty().multiply(.75));
        this.playersBox.setStyle(BG_COLOR);

    }

    /**
     * create a container that will hold and display the users hand
     */
    private void initUserBox(){
        this.userBox = new VBox();
        this.userBox.prefWidthProperty().bind(this.leftBox.widthProperty());
        this.userBox.prefHeightProperty().bind(this.leftBox.heightProperty().multiply(.25));
        this.userBox.setStyle(BG_COLOR);

    }

    /**
     * create a container that will act as the users hand holding the
     * users cards
     */
    private void initUserPane(){
        //this.userPane = new FlowPane();
        this.userPane = new HBox();
        //this.userPane.setHgap(15);
        this.userPane.setSpacing(15);
        this.userPane.setAlignment(Pos.CENTER);
    }


    /**
     * create a container to hold the cards that have been played
     */
    private void initBoardPane(){
//        this.boardPane = new FlowPane();
        this.boardPane = new HBox();
        this.boardPane.setStyle(BG_COLOR);
//        this.boardPane.setHgap(20);
        this.boardPane.setSpacing(20);
        this.boardPane.setAlignment(Pos.CENTER);
        this.boardPane.prefWidthProperty().bind(this.boardBox.widthProperty().multiply(.7));
        this.boardPane.prefHeightProperty().bind(this.boardBox.heightProperty());
    }

    /**
     * create a container to hold the UI components relating to the
     * number of fuse and info tokens
     */
    private void initTokenBox(){
        this.infoNumber = new Label("8");
        this.fuseNumber = new Label("3");
        this.tokenBox = new VBox();
        this.tokenBox.setStyle(BG_COLOR);
        HBox infoBox = new HBox();
        HBox fuseBox = new HBox();
        Label info = new Label("INFO : ");
        Label fuse = new Label("FUSE : ");
        info.setFont(Font.font("Segoe Print", FontWeight.BOLD, FontPosture.REGULAR, 25));
        info.setTextFill(Color.valueOf("#ffa411"));
        fuse.setFont(Font.font("Segoe Print", FontWeight.BOLD, FontPosture.REGULAR, 25));
        fuse.setTextFill(Color.valueOf("#ffa411"));
        this.infoNumber.setFont(Font.font("Segoe Print", FontWeight.BOLD, FontPosture.REGULAR, 25));
        this.infoNumber.setTextFill(Color.valueOf("#ffa411"));
        this.fuseNumber.setFont(Font.font("Segoe Print", FontWeight.BOLD, FontPosture.REGULAR, 25));
        this.fuseNumber.setTextFill(Color.valueOf("#ffa411"));
        this.tokenBox.getChildren().addAll(info, fuse);
        this.tokenBox.setSpacing(20);
        this.tokenBox.setAlignment(Pos.CENTER);
        infoBox.getChildren().addAll(info, infoNumber);
        infoBox.setSpacing(20);
        fuseBox.getChildren().addAll(fuse, fuseNumber);
        fuseBox.setSpacing(20);
        this.tokenBox.getChildren().addAll(infoBox, fuseBox);
    }


    //test data for view
    public void buildMock() {
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
        allCards.add(blue);
        allCards.add(green);
        allCards.add(white);
        allCards.add(yellow);

        for (int k = 0; k < 5; k++) {
            ImageView imageView = new ImageView();
            Image image = new Image(this.getClass().getResourceAsStream("Assets/cardBack.png"));
            imageView.setImage(image);
            imageView.setFitWidth(110);
            imageView.setFitHeight(145);
            this.userPane.getChildren().add(imageView);
        }

        for (int k = 0; k < 5; k++){
            ImageView imageView = new ImageView();
            Image image = new Image(this.getClass().getResourceAsStream("Assets/" + yellow[k].getFilePath()));
            imageView.setImage(image);
            imageView.setFitWidth(115);
            imageView.setFitHeight(165);
            this.boardPane.getChildren().add(imageView);
        }

        for (int p = 0; p < 4; p++) {
//            FlowPane aHand = new FlowPane();
            HBox aHand = new HBox();
            aHand.setAlignment(Pos.CENTER);
//            aHand.setHgap(15);
            aHand.setSpacing(15);
            aHand.setPadding(new Insets(10, 0, 0, 0));
            for (int n = 0; n < allCards.get(p).length; n++) {
                Card tmp = allCards.get(p)[n];
                ImageView imageView = new ImageView();
                Image image = new Image(this.getClass().getResourceAsStream("Assets/" + tmp.getFilePath()));
                imageView.setImage(image);
                imageView.setFitWidth(110);
                imageView.setFitHeight(145);
                aHand.getChildren().add(imageView);
            }
            this.playersBox.getChildren().add(aHand);
        }

        System.out.println("Mock built in game view");
    }

    /** Called from root view, will remove the current ClientView in the right
     * VBox and add a new ClientView to it.
     * @param gameNode a during game ClientView
     */
    public void setRightNode(ClientView gameNode){
        this.rightBox.getChildren().clear();
        this.rightBox.getChildren().add(gameNode);
    }


    /**
     * Sets the controller attribute and adds onClick events to all buttons
     * in the view
     * @param control an instance of ClientController
     */
    @Override
    public void setController(ClientController control) {
        this.controller = control;
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
     * Updates the view when data in the model changes
     */
    @Override
    public void onChange() {
        this.playersBox.getChildren().clear();
        this.userPane.getChildren().clear();
        this.boardPane.getChildren().clear();
        for (HumanPlayer p : ((DuringModel) model).getPlayers()){
            buildPlayerHands(p);
        }
        this.fuseNumber.setText(Integer.toString(((DuringModel)model).getFuseToken().current));
        this.infoNumber.setText(Integer.toString(((DuringModel)model).getInfoToken().current));
        this.buildBoard();

    }

    /**
     * loops over each player in the model and builds there hand accordingly. Creates imageViews
     * for each card in a players hand and adds it to the user hand UI component of the view
     * or to the players hand UI component of the view
     * @param p a player in the model with a hand
     */
    private void buildPlayerHands(HumanPlayer p){
        HBox aHand = new HBox();
        aHand.setAlignment(Pos.CENTER);
        aHand.setSpacing(15);
        aHand.setPadding(new Insets(10, 0, 0, 0));
        int handSize = ((DuringModel)model).getNumCards();
        String seat = "";
        if (p.isUser()){
            seat = "Your Hand";
        } else {
            seat = "Seat " + Integer.toString(p.getSeat());
        }
        Label playerSeat = new Label(seat);
        playerSeat.setFont(Font.font("Segoe Print", FontWeight.BOLD, FontPosture.REGULAR, 25));
        playerSeat.setTextFill(Color.valueOf("#ffa411"));
        aHand.getChildren().add(playerSeat);


        for (int i = 0; i < handSize; i++){
            if (!p.isUser()) {
                Card tmp = p.getpHand().getCard(i);
                ImageView imageView = new ImageView();
                Image image = new Image(this.getClass().getResourceAsStream("Assets/" + tmp.getFilePath()));
                imageView.setImage(image);
                imageView.setFitWidth(110);
                imageView.setFitHeight(145);
                aHand.getChildren().add(imageView);
            }
            else{
                Suit indexSuit = p.getpHand().getRevealedSuits().get(i);
                int indexRank = p.getpHand().getRevealedRanks().get(i);
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
                aHand.getChildren().add(imageView);
            }
        }

        if (p.isUser()){
            this.userPane.getChildren().add(aHand);
        }
        else {
            this.playersBox.getChildren().add(aHand);
        }
    }

    /**
     * grab the board object from the during game model and build the UI components of
     * the object and display them to in the view. This will display the cards that have
     * been played during the game and the number of fuse and info tokens remaining.
     */
    private void buildBoard(){
        int boardLength = ((DuringModel) model).getBoardSize();
        for (int i = 0; i < boardLength; i++){
            Suit suitPos = ((DuringModel) model).getCurrentBoard().getFireworks().get(i).color;
            Firework firePos = ((DuringModel) model).getCurrentBoard().getFireworks().get(i);
            String filepath = "";
            switch(suitPos){
                case RED:
                    if (firePos.topCard == null){
                        filepath = "rInfo.png";
                    }
                    else {
                        filepath = firePos.getTop().getFilePath();
                    }
                    break;
                case BLUE:
                    if (firePos.topCard == null){
                        filepath = "bInfo.png";
                    }
                    else {
                        filepath = firePos.getTop().getFilePath();
                    }
                    break;

                case GREEN:
                    if (firePos.topCard == null){
                        filepath = "gInfo.png";
                    }
                    else {
                        filepath = firePos.getTop().getFilePath();
                    }
                    break;
                case YELLOW:
                    if (firePos.topCard == null){
                        filepath = "yInfo.png";
                    }
                    else {
                        filepath = firePos.getTop().getFilePath();
                    }
                    break;
                case WHITE:
                    if (firePos.topCard == null){
                        filepath = "wInfo.png";
                    }
                    else {
                        filepath = firePos.getTop().getFilePath();
                    }
                    break;
                case MULTI:
                    if (firePos.topCard == null){
                        filepath = "rbInfo.png";
                    }
                    else {
                        filepath = firePos.getTop().getFilePath();
                    }
                    break;
            }


            ImageView imageView = new ImageView();
            Image image = new Image(this.getClass().getResourceAsStream("Assets/" + filepath));
            imageView.setImage(image);
            imageView.setFitWidth(115);
            imageView.setFitHeight(165);
            this.boardPane.getChildren().add(imageView);
        }
    }

}
