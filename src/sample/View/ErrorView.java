package sample.View;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import sample.Controller.ClientController;
import sample.Model.Model;

public class ErrorView extends ClientView {
    ClientController controller;
    Model model;
    Label title;
    VBox titleBox;
    Label errorMessage;
    VBox errorBox;
    Button back;
    HBox buttonBox;
    /** Button font object **/
    private Font buttonText;
    /** css string to update buttons when hovered **/
    final String HOVER;
    /** css string to update buttons when idle **/
    final String IDLE;
    public ErrorView(ViewId id){
        super(id);

        VBox.setVgrow(this, Priority.ALWAYS);


        buttonText = Font.font("Segoe Print", FontWeight.BOLD, FontPosture.REGULAR, 27);
        HOVER = "-fx-text-fill: white; -fx-background-color: transparent; -fx-border-color: white; " +
                "-fx-border-width: 0 4 0 4; -fx-border-radius: 20";
        IDLE = "-fx-text-fill: #ffa411; -fx-background-color: transparent";
        this.setAlignment(Pos.CENTER);

        initTitle();
        initErrorLabels();
        initBackBtn();
        initButtonBox();

        this.getChildren().addAll(titleBox, errorBox, buttonBox);


    }

    private void initTitle(){
        this.titleBox = new VBox();
        this.title = new Label("Error");
        this.title.setFont(Font.font("Segoe Print", FontWeight.NORMAL, FontPosture.REGULAR, 125));
        this.title.setTextFill(Color.valueOf("#ffa411"));
        this.titleBox.setAlignment(Pos.CENTER);
        this.titleBox.setStyle("-fx-background-color: #13102f");
        this.titleBox.getChildren().add(title);
        this.titleBox.prefHeightProperty().bind(this.heightProperty().multiply(.2));
        VBox.setVgrow(titleBox, Priority.ALWAYS);
    }

    private void initErrorLabels(){
        this.errorMessage = new Label("XXXXXXX");
        this.errorBox = new VBox();
        this.errorMessage.setFont(Font.font("Segoe Print", FontWeight.NORMAL, FontPosture.REGULAR, 30));
        this.errorMessage.setTextFill(Color.valueOf("#ffa411"));
        this.errorBox.getChildren().addAll(errorMessage);
        this.errorBox.setStyle("-fx-background-color: #13102f");
        this.errorBox.setSpacing(10);
        this.errorBox.setAlignment(Pos.CENTER);
        this.errorBox.prefHeightProperty().multiply(this.heightProperty().multiply(.2));
    }



    private void initBackBtn(){
        this.back = new Button("Back");
        this.back.setFont(buttonText);
        this.back.setStyle(IDLE);
        this.back.setOnMouseEntered(e->this.back.setStyle(HOVER));
        this.back.setOnMouseExited(e->this.back.setStyle(IDLE));
        this.back.setPrefWidth(150);
    }

    private void initButtonBox(){
        this.buttonBox = new HBox();
        this.buttonBox.setAlignment(Pos.CENTER);
        this.buttonBox.setSpacing(10);
        this.buttonBox.getChildren().addAll(this.back);
        this.buttonBox.setStyle("-fx-background-color: #13102f");
        this.buttonBox.prefHeightProperty().bind(this.heightProperty().multiply(.6));
    }

    @Override
    public void setController(ClientController control) {

    }

    @Override
    public void setModel(Model model) {

    }

    @Override
    public void onChange() {

    }
}
