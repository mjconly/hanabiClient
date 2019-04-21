package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import sample.Controller.ClientController;
import sample.Controller.ClientSocket;
import sample.Model.AfterModel;
import sample.Model.BeforeModel;
import sample.Model.DuringModel;
import sample.Model.Entities.Card;
import sample.View.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        //Create Views
        CreateGameView createGameView = new CreateGameView(ViewId.CREATE);
        RootView rootView = new RootView(ViewId.ROOT);
        MainMenuView mainMenuView = new MainMenuView(ViewId.MAIN);
        JoinGameView joinGameView = new JoinGameView(ViewId.JOIN);
        RulesView rulesView = new RulesView(ViewId.RULES);
        ClientController clientController = new ClientController();
        WaitingView waitingView = new WaitingView(ViewId.WAIT);
        GameView gameView = new GameView(ViewId.GAME);
        ActionsView actionsView = new ActionsView(ViewId.ACTIONS);
        PlayView playView = new PlayView(ViewId.PLAY);
        DiscardView discardView = new DiscardView(ViewId.DISCARD);
        InfoView infoView = new InfoView(ViewId.INFO);
        LogView logView = new LogView(ViewId.LOG);
        DiscardPileView pileView = new DiscardPileView(ViewId.PILE);
        EndGameView endGameView = new EndGameView(ViewId.END);


        //initiate ClientSocket object
        ClientSocket clientSocket = new ClientSocket();
        //set controller for ClientSocket
        clientSocket.setController(clientController);
        //spin up client socket thread
        Thread threadCS = new Thread(clientSocket);
        threadCS.start();

        clientController.setClientsocket(clientSocket);

        //get instance of model (singleton)
        BeforeModel beforeModel = BeforeModel.getBeforeModelInstance();
        DuringModel duringModel = DuringModel.getDuringModelInstance();
        AfterModel afterModel = AfterModel.getAfterModelInstance();

        clientController.setModel(beforeModel, duringModel, afterModel);

        //add subscribers to models
        beforeModel.addMaster(rootView);
        duringModel.addMaster(rootView);
        afterModel.addMaster(rootView);

        //add views as children
        rootView.addModel(beforeModel);
        rootView.addModel(duringModel);
        rootView.addModel(afterModel);
        rootView.addChild(mainMenuView);
        rootView.addChild(joinGameView);
        rootView.addChild(rulesView);
        rootView.addChild(createGameView);
        rootView.addChild(waitingView);
        rootView.addChild(gameView);
        rootView.addChild(actionsView);
        rootView.addChild(playView);
        rootView.addChild(infoView);
        rootView.addChild(discardView);
        rootView.addChild(logView);
        rootView.addChild(pileView);
        rootView.addChild(endGameView);

        //set controllers to views
        mainMenuView.setController(clientController);
        joinGameView.setController(clientController);
        rulesView.setController(clientController);
        createGameView.setController(clientController);
        gameView.setController(clientController);
        actionsView.setController(clientController);
        playView.setController(clientController);
        discardView.setController(clientController);
        infoView.setController(clientController);
        pileView.setController(clientController);
        logView.setController(clientController);
        endGameView.setController(clientController);


        //set models for each view
        mainMenuView.setModel(beforeModel);
        createGameView.setModel(beforeModel);
        joinGameView.setModel(beforeModel);
        rulesView.setModel(beforeModel);
        waitingView.setModel(beforeModel);
        gameView.setModel(duringModel);
        actionsView.setModel(duringModel);
        playView.setModel(duringModel);
        discardView.setModel(duringModel);
        infoView.setModel(duringModel);
        pileView.setModel(duringModel);
        logView.setModel(duringModel);
        endGameView.setModel(afterModel);


        rootView.getChildren().add(mainMenuView);

        primaryStage.setScene(new Scene(rootView, 900, 900));
        primaryStage.show();



    }


    public static void main(String[] args) {
        launch(args);
    }
}
