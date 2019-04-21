package sample.View;

import javafx.geometry.Pos;
import javafx.scene.control.*;
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
 * RulesView - Allows the user to view the Hanabi Rules
 *
 * @version 1.0
 * @author Group B3
 */
public class RulesView extends ClientView {
    /** controller to handle click events **/
    ClientController controller;
    /** model to update view onChange **/
    Model model;
    /** container to hold the textArea for rules */
    VBox ruleBox;
    /** container to hold the back button **/
    VBox buttonBox;
    /** container to hold the title label **/
    VBox titleBox;
    /** textArea to display the rules **/
    TextArea rulesText;
    /** button to got back to main **/
    Button back;
    /** the view title **/
    Label title;
    /** sets font for button **/
    Font buttonText;
    /** css string to update buttons when hovered **/
    final String HOVER;
    /** css string to update buttons when idle **/
    final String IDLE;
    /** css string for textArea **/
    final String TEXT_AREA;


    /**
     * Create a new instance of RulesView with all UI components laid
     * out accordingly
     * @param id
     */
    public RulesView(ViewId id){
        super(id);
        VBox.setVgrow(this, Priority.ALWAYS);

        buttonText = Font.font("Segoe Print", FontWeight.BOLD, FontPosture.REGULAR, 35);
        HOVER = "-fx-text-fill: white; -fx-background-color: transparent; -fx-border-color: white; " +
                "-fx-border-width: 0 4 0 4; -fx-border-radius: 20";
        IDLE = "-fx-text-fill: #ffa411; -fx-background-color: transparent";
        TEXT_AREA = "-fx-text-fill: black; -fx-font-size:20; -fx-control-inner-background: white;" +
        "-fx-border-color: white;";

        initTitle();
        initRulesText();
        initBackBtn();
        initButtonBox();
        this.setAlignment(Pos.CENTER);
        this.getChildren().addAll(titleBox, ruleBox, buttonBox);
    }


    /**
     * Initializes the titleBox and the title label with appropriate layout
     */
    public void initTitle(){
        this.titleBox = new VBox();
        this.title = new Label("How To Play");
        this.title.setFont(Font.font("Segoe Print", FontWeight.NORMAL, FontPosture.REGULAR, 125));
        this.title.setTextFill(Color.valueOf("#ffa411"));
        this.titleBox.setAlignment(Pos.CENTER);
        this.titleBox.getChildren().add(title);
        this.titleBox.setStyle("-fx-background-color: #13102f");
        VBox.setVgrow(titleBox, Priority.ALWAYS);
        this.titleBox.prefHeightProperty().bind(this.heightProperty().multiply(.2));
    }


    /**
     * Initializes the rulesBox and the rulesText with appropriate layout
     */
    public void initRulesText(){
        this.ruleBox = new VBox();
        this.rulesText = new TextArea();
        this.rulesText.setEditable(false);
        this.rulesText.setMaxHeight(750);
        this.rulesText.setMaxWidth(750);
        this.ruleBox.setStyle("-fx-background-color: #13102f");
        this.rulesText.setStyle(TEXT_AREA);
        this.ruleBox.setAlignment(Pos.CENTER);
        this.ruleBox.getChildren().add(rulesText);
        this.ruleBox.prefHeightProperty().bind(this.heightProperty().multiply(.6));
        this.rulesText.prefHeightProperty().bind(this.ruleBox.heightProperty());
        this.rulesText.prefWidthProperty().bind(this.ruleBox.widthProperty().multiply(.3));
    }


    /**
     * Initializes the backButton with appropriate layout, also sets
     * mouse entered and mouse exited events to style button
     */
    private void initBackBtn(){
        this.back = new Button("Back");
        this.back.setFont(buttonText);
        this.back.setTextFill(Color.valueOf("#ffa411"));
        this.back.setStyle(IDLE);
        this.back.setOnMouseEntered(e->this.back.setStyle(HOVER));
        this.back.setOnMouseExited(e->this.back.setStyle(IDLE));
        this.back.setPrefWidth(250);
    }


    /**
     * Initializes the buttonBox with appropriate layout
     */
    private void initButtonBox(){
        this.buttonBox = new VBox();
        this.buttonBox.setAlignment(Pos.CENTER);
        this.buttonBox.setSpacing(10);
        this.buttonBox.getChildren().addAll(this.back);
        this.buttonBox.setStyle("-fx-background-color: #13102f");
        this.buttonBox.prefHeightProperty().bind(this.heightProperty().multiply(.2));
        VBox.setVgrow(buttonBox, Priority.ALWAYS);
    }


    /**
     * Sets the controller attribute and adds onClickEvent handlers to each
     * button in the View
     * @param control an instance of ClientController
     */
    @Override
    public void setController(ClientController control) {
        this.controller = control;
        //TODO: set back event handler in controller
        this.back.setOnMouseClicked(e->this.controller.handleBack(e));
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
     * Updates the view when data in the model changes, will add rules to
     * the text area
     */
    @Override
    public void onChange() {
        this.rulesText.clear();
        this.rulesText.setText(((BeforeModel) model).getTheRules());
    }
}
