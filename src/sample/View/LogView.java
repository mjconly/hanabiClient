package sample.View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import sample.Controller.ClientController;
import sample.Model.DuringModel;
import sample.Model.Model;


/**
 * LogView - Allows the user to view a log of all actions performed
 * by players during the game
 *
 * @version 1.0
 * @author Group B3
 */
public class LogView extends ClientView {
    /** controller to handle click events **/
    private ClientController controller;
    /** model to update view onChange **/
    private Model model;
    /** scroll pane to display game logs **/
    ScrollPane scrollLog;
    /** textflow to hold logs **/
    TextFlow flowText;
    /** back to actions view **/
    Button back;
    /** view title **/
    Label title;
    /** button font object **/
    private Font buttonText;
    /** css string to update buttons when hovered **/
    final String HOVER;
    /** css string to update buttons when idle **/
    final String IDLE;
    /** css string for scrollPane **/
    final String SCROLL;



    /**
     * Creates a new instance of LogView with all UI components laid out accordingly
     * @param id ViewId.LOG
     */
    public LogView(ViewId id){
        super(id);

        VBox.setVgrow(this, Priority.ALWAYS);

        buttonText = Font.font("Segoe Print", FontWeight.BOLD, FontPosture.REGULAR, 35);
        HOVER = "-fx-text-fill: white; -fx-background-color: transparent; -fx-border-color: white; " +
                "-fx-border-width: 0 4 0 4; -fx-border-radius: 20";
        IDLE = "-fx-text-fill: #ffa411; -fx-background-color: transparent";
        SCROLL = "-fx-border-color: #ffa411; -fx-border-width: 15; -fx-border-radius: 10;" +
                "-fx-background-color: #13102f";


        initTitle();
        initScrollLog();
        initFlowText();
        initBackBtn();

        this.scrollLog.setContent(flowText);
        this.setAlignment(Pos.CENTER);
        this.setSpacing(20);
        this.setPadding(new Insets(0, 50, 0, 0));

        //builds mock data to test layout
        //buildMock();

        this.getChildren().addAll(title, scrollLog, back);

    }

    /**
     * Initializes the view title with appropriate layout
     */
    private void initTitle(){
        this.title = new Label("Game Log");
        this.title.setFont(Font.font("Segoe Print", FontWeight.BOLD, FontPosture.REGULAR, 50));
        this.title.setTextFill(Color.valueOf("#ffa411"));
        this.title.prefHeightProperty().bind(this.prefHeightProperty().multiply(.3));
    }


    /**
     * Initializes scrollLog with appropriate layout
     */
    private void initScrollLog(){
        this.scrollLog = new ScrollPane();
        this.scrollLog.setPrefViewportHeight(500);
        this.scrollLog.setMinViewportWidth(500);
        this.scrollLog.setStyle(SCROLL);
        this.scrollLog.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        this.scrollLog.prefWidthProperty().bind(this.prefWidthProperty().multiply(.5));
    }


    /**
     * Initializes flowText with appropriate layout
     */
    private void initFlowText(){
        this.flowText = new TextFlow();
        this.flowText.prefWidthProperty().bind(this.scrollLog.widthProperty().multiply(.8));
        this.flowText.setPadding(new Insets(25, 0, 0, 75));
    }


    /**
     * Initializes back button with mouse entered and mouse exited events
     * to style button
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


    //builds mock data to test layout of the view
    public void buildMock(){
        String s1 = "Test Log 1 .....................";
        String s2 = "Test Log 2 .....................";

        for (int i = 0; i < 20; i++){
            this.flowText.getChildren().add(new Text(s1));
            this.flowText.getChildren().add(new Text(s2));
        }

    }



    /**
     * Set the controller attribute and adds onClick events for each button in
     * the view
     * @param control an instance of ClientController
     */
    @Override
    public void setController(ClientController control) {
        this.controller = control;
        //TODO: hook up controller
        this.back.setOnMouseClicked(e->controller.handleBack(e));
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
        flowText.getChildren().clear();
        for(String s : ((DuringModel)model).getLog().getLog()) {
            flowText.getChildren().add(new Text(s + "\n"));
        }
    }
}
