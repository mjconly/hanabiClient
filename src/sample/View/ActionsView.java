package sample.View;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
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
 * ActionsView - provides the user with a list of possible actions
 * for gameplay
 */
public class ActionsView extends ClientView {
    /** controller to handle click events **/
    ClientController controller;
    /** model to update view onChange **/
    Model model;
    /** container for all view buttons **/
    VBox buttonBox;
    /** pulls up the play view **/
    Button play;
    /** pulls up the discard view **/
    Button discard;
    /** pulls up the info view **/
    Button info;
    /** pulls up the log view **/
    Button log;
    /** pulls up the discard pile view **/
    Button pile;
    /** quits to main menu view **/
    Button quit;
    private Font buttonText;
    /** css string to update buttons when hovered **/
    final String HOVER;
    /** css string to update buttons when idle **/
    final String IDLE;


    /**
     * Creates a new instance of ActionsView with all UI components laid out
     * accordingly
     * @param id ViewId.ACTION
     */
    public ActionsView (ViewId id){
        super(id);

        VBox.setVgrow(this, Priority.ALWAYS);

        buttonText = Font.font("Segoe Print", FontWeight.BOLD, FontPosture.REGULAR, 27);
        HOVER = "-fx-text-fill: white; -fx-background-color: transparent; -fx-border-color: white; " +
                "-fx-border-width: 0 4 0 4; -fx-border-radius: 20";
        IDLE = "-fx-text-fill: #ffa411; -fx-background-color: transparent";

        initPlayBtn();
        initDiscardBtn();
        initInfoBtn();
        initLogBtn();
        initPileBtn();
        initQuitBtn();
        initButtonBox();

        this.getChildren().add(buttonBox);
    }


    /**
     * Initializes the play button and adds events for mouse entered
     * and mouse exited to style button
     */
    private void initPlayBtn(){
        this.play = new javafx.scene.control.Button("Play Card");
        this.play.setFont(buttonText);
        this.play.setTextFill(Color.valueOf("#ffa411"));
        this.play.setStyle(IDLE);
        this.play.setOnMouseEntered(e->this.play.setStyle(HOVER));
        this.play.setOnMouseExited(e->this.play.setStyle(IDLE));
        this.play.setPrefWidth(300);
//        this.play.prefWidthProperty().bind(this.widthProperty());
    }

    /**
     * Initializes the Discard button and adds events for mouse entered
     * and mouse exited to style button
     */
    private void initDiscardBtn(){
        this.discard = new javafx.scene.control.Button("Discard");
        this.discard.setFont(buttonText);
        this.discard.setTextFill(Color.valueOf("#ffa411"));
        this.discard.setStyle(IDLE);
        this.discard.setOnMouseEntered(e->this.discard.setStyle(HOVER));
        this.discard.setOnMouseExited(e->this.discard.setStyle(IDLE));
        this.discard.setPrefWidth(300);
//        this.discard.prefWidthProperty().bind(this.widthProperty());
    }


    /**
     * Initializes the Info button and adds events for mouse entered
     * and mouse exited to style button
     */
    private void initInfoBtn(){
        this.info = new javafx.scene.control.Button("Give Info");
        this.info.setFont(buttonText);
        this.info.setTextFill(Color.valueOf("#ffa411"));
        this.info.setStyle(IDLE);
        this.info.setOnMouseEntered(e->this.info.setStyle(HOVER));
        this.info.setOnMouseExited(e->this.info.setStyle(IDLE));
        this.info.setPrefWidth(300);
//        this.info.prefWidthProperty().bind(this.widthProperty());
    }


    /**
     * Initializes the Log button and adds events for mouse entered
     * and mouse exited to style button
     */
    private void initLogBtn(){
        this.log = new javafx.scene.control.Button("View Log");
        this.log.setFont(buttonText);
        this.log.setTextFill(Color.valueOf("#ffa411"));
        this.log.setStyle(IDLE);
        this.log.setOnMouseEntered(e->this.log.setStyle(HOVER));
        this.log.setOnMouseExited(e->this.log.setStyle(IDLE));
        this.log.setPrefWidth(300);
//        this.log.prefWidthProperty().bind(this.widthProperty());
    }


    /**
     * Initializes the Pile button and adds events for mouse entered
     * and mouse exited to style button
     */
    private void initPileBtn(){
        this.pile = new javafx.scene.control.Button("View Discard Pile");
        this.pile.setFont(buttonText);
        this.pile.setTextFill(Color.valueOf("#ffa411"));
        this.pile.setStyle(IDLE);
        this.pile.setOnMouseEntered(e->this.pile.setStyle(HOVER));
        this.pile.setOnMouseExited(e->this.pile.setStyle(IDLE));
        this.pile.setPrefWidth(300);
//        this.pile.prefWidthProperty().bind(this.widthProperty());
    }


    /**
     * Initializes the Quit button and adds events for mouse entered
     * and mouse exited to style button
     */
    private void initQuitBtn(){
        this.quit = new javafx.scene.control.Button("Quit");
        this.quit.setFont(buttonText);
        this.quit.setTextFill(Color.valueOf("#ffa411"));
        this.quit.setStyle(IDLE);
        this.quit.setOnMouseEntered(e->this.quit.setStyle(HOVER));
        this.quit.setOnMouseExited(e->this.quit.setStyle(IDLE));
        this.quit.setPrefWidth(300);
//        this.quit.prefWidthProperty().bind(this.widthProperty());
    }


    /**
     * Initializes buttonBox and adds all view buttons to itself
     */
    private void initButtonBox(){
        this.buttonBox = new VBox();
        this.buttonBox.setSpacing(10);
        this.buttonBox.setAlignment(Pos.CENTER);
        this.buttonBox.setStyle("-fx-background-color: #13102f");
//        this.buttonBox.getChildren().addAll(play, discard, info, log, pile, quit);
        this.buttonBox.prefHeightProperty().bind(this.heightProperty());
        this.buttonBox.prefWidthProperty().bind(this.widthProperty());
    }


    /**
     * Sets the controller attribute and adds onClick events to all
     * buttons in the view
     * @param control an instance of ClientController
     */
    @Override
    public void setController(ClientController control) {
        this.controller = control;
        //TODO: hook up with controller when ready
        this.play.setOnMouseClicked(e-> controller.handlePlay(e));
        this.discard.setOnMouseClicked(e-> controller.handleDiscard(e));
        this.log.setOnMouseClicked(e-> controller.handleGameLog(e));
        this.info.setOnMouseClicked(e-> controller.handleInfo(e));
        this.pile.setOnMouseClicked(e-> controller.handleDiscardPile(e));
        this.quit.setOnMouseClicked(e-> controller.handleQuit(e));
    }


    /**
     * Sets the model attribute
     * @param model an instance of Model (During)
     */
    @Override
    public void setModel(Model model) {
        this.model = model;
    }

    @Override
    public void onChange() {
        this.buttonBox.getChildren().clear();
        if (((DuringModel) model).isTurn()){
            this.buttonBox.getChildren().addAll(play);
            if(((DuringModel)model).getInfoToken().getCurrent() <7)
                this.buttonBox.getChildren().addAll(discard);
            if(((DuringModel)model).getInfoToken().getCurrent() != 0)
                this.buttonBox.getChildren().addAll(info);
            this.buttonBox.getChildren().addAll(log, pile, quit);
        }
        else{
            this.buttonBox.getChildren().addAll(log, pile, quit);
        }
    }
}
