package sample.Controller;

import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import sample.Model.AfterModel;
import sample.Model.BeforeModel;
import sample.Model.DuringModel;
import sample.Model.Entities.Card;
import sample.View.ViewId;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.math.BigInteger;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * The client controller handles all the responses from the view and the server
 * and handles it according to what the message was. It will manipulate the models
 * as needed and will not change the view directly.
 * @author Group B3
 */
public class ClientController {

    /** The Before Model to handle everything before the game*/
    private BeforeModel beforeModel;
    /** The During Model to handle everything during the game*/
    private DuringModel duringModel;
    /** The After Model to handle everything after the game*/
    private AfterModel afterModel;
    /** The socket that is used to communicate to the server*/
    private ClientSocket socket;
    /** An enum value indicated the state of the client (in the before, during or after*/
    private State OverallState;
    /** The boolean determining if the gamemode uses the rainbows as a wildcard*/
    private boolean wild;
    /** The boolean determining if the gamemode uses the rainbows as a 6th firework*/
    private boolean firework;

    private boolean info;

    private boolean created;

    /** The enum values for the overall state of the client*/
    private enum State {
        BEFORE, DURING, AFTER
    }

    /**
     * The constructor of the ClientController
     * sets the overall state to State.BEFORE
     */
    public ClientController(){
        setOverallState(State.BEFORE);
    }

    /**
     * Sets all the models that are a part
     * @param before
     * @param during
     * @param after
     */
    public void setModel(BeforeModel before, DuringModel during, AfterModel after){
        this.beforeModel = before;
        this.duringModel = during;
        this.afterModel = after;
    }

    public void setClientsocket(ClientSocket aSocket){
        this.socket = aSocket;
    }

    public void handleBack(MouseEvent event){
        if(event.getButton() == MouseButton.PRIMARY) {
            if (this.OverallState == State.BEFORE) {
                this.beforeModel.setViews(ViewId.MAIN, this.beforeModel.prevView);
            }
            else {
                this.duringModel.setViews(ViewId.ACTIONS, ViewId.ACTIONS);
            }
        }
    }

    public void handleCreateGame(MouseEvent event){
        if(event.getButton() == MouseButton.PRIMARY) {
            this.beforeModel.setViews(ViewId.MAIN, ViewId.CREATE);
        }

    }

    public void handleJoinGame(MouseEvent event){
        if(event.getButton() == MouseButton.PRIMARY){
            this.beforeModel.setViews(ViewId.MAIN, ViewId.JOIN);
        }
    }

    public void handleRules(MouseEvent event){
        if(event.getButton() == MouseButton.PRIMARY) {
            //TODO make setViews method to replace this
            this.beforeModel.setViews(ViewId.MAIN, ViewId.RULES);
        }
    }

    public void handleQuit(MouseEvent event){
        if(event.getButton() == MouseButton.PRIMARY) {
            socket.stopClientSocket();
            System.exit(0);
        }
    }

    public void handleCreateSubmit(MouseEvent event, String nsid, int numPlayers, String timeOut, boolean force, ToggleGroup rainbowSelection){
        if(event.getButton() == MouseButton.PRIMARY){
            String rainbow = ((RadioButton)rainbowSelection.getSelectedToggle()).getId();
            if (rainbow.equals("firework")){
                this.duringModel.setBoardSize(6);
                this.duringModel.getCurrentBoard().addRainbow();
            }
            else{
                this.duringModel.setBoardSize(5);
            }
            JSONObject createCommand = new JSONObject();
            createCommand.put("cmd", "create");
            createCommand.put("nsid", nsid);
            createCommand.put("players", numPlayers);
            createCommand.put("timeout", timeOut);
            createCommand.put("force", force);
            createCommand.put("rainbow", rainbow);
            createCommand.put("timestamp", (int) Instant.now().getEpochSecond());
            createCommand.put("md5hash", computeHash("The NSID is " + nsid.toLowerCase() + "."));
            System.out.println(createCommand.toString());
            System.out.println(computeHash(nsid));
            String secureHashing = computeHash(createCommand.toString());
            createCommand.replace("md5hash", secureHashing);
            this.socket.sendToServer(createCommand);
//            this.beforeModel.setViews(ViewId.MAIN, ViewId.WAIT);
        }
    }

    public void handleJoinSubmit(MouseEvent event, String gameKey, String nsid, String token){
        if(event.getButton() == MouseButton.PRIMARY) {
            JSONObject joinCommand = new JSONObject();
            joinCommand.put("cmd", "join");
            joinCommand.put("nsid", nsid);
            joinCommand.put("game-id", Integer.parseInt(gameKey));
            joinCommand.put("token", token);
            joinCommand.put("timestamp", (int) Instant.now().getEpochSecond());
            joinCommand.put("md5hash", computeHash("The NSID is " + nsid.toLowerCase() + "."));
            String secureHashing = computeHash(joinCommand.toString());
            joinCommand.replace("md5hash", secureHashing);
            this.socket.sendToServer(joinCommand);
//            this.beforeModel.setViews(ViewId.MAIN, ViewId.WAIT);
        }
    }

    public void handlePlay(MouseEvent event){
        if(event.getButton() == MouseButton.PRIMARY){
            this.duringModel.setViews(ViewId.ACTIONS, ViewId.PLAY);
        }
    }

    public void handleDiscard(MouseEvent event){
        if(event.getButton() == MouseButton.PRIMARY){
            this.duringModel.setViews(ViewId.ACTIONS, ViewId.DISCARD);
        }
    }

    public void handleInfo(MouseEvent event){
        if(event.getButton() == MouseButton.PRIMARY){
            this.duringModel.setViews(ViewId.ACTIONS, ViewId.INFO);
        }
    }

    public void handleGameLog(MouseEvent event){
        if(event.getButton() == MouseButton.PRIMARY){
            this.duringModel.setViews(ViewId.ACTIONS, ViewId.LOG);
        }
    }

    public void handleDiscardPile(MouseEvent event){
        if(event.getButton() == MouseButton.PRIMARY){
            this.duringModel.setViews(ViewId.ACTIONS, ViewId.PILE);
        }
    }

    public void handlePlaySelect(VBox aCard){
        int pos = Integer.parseInt(aCard.getId());
        this.duringModel.setIndexOfCardToPlay(pos);
        System.out.println("FROM CONTROLLER CARD TO PLAY " + pos + "!!!!!!!!!!");
    }

    public void handlePlaySubmit(MouseEvent event, ToggleGroup positionGroup){
        if(event.getButton() == MouseButton.PRIMARY){
            String boardPos = ((RadioButton) positionGroup.getSelectedToggle()).getText();
            boardPos = " " + boardPos;
            JSONObject playCommand = new JSONObject();
            playCommand.put("action", "play");
            playCommand.put("position", new Integer(duringModel.getIndexOfCardToPlay()));
            if(this.wild){
                playCommand.put("firework", parseInfoType(boardPos));
            }
            this.socket.sendToServer(playCommand);
            this.duringModel.setViews(ViewId.PLAY, ViewId.ACTIONS);
            //TODO Handle Play Toggle Group
        }
    }
    public void handleDiscardSubmit(MouseEvent event){
        if(event.getButton() == MouseButton.PRIMARY){
            JSONObject discardCommand = new JSONObject();
            discardCommand.put("action", "discard");
            discardCommand.put("position", duringModel.getIndexOfCardToDiscard());
            this.socket.sendToServer(discardCommand);
            this.duringModel.setViews(ViewId.DISCARD, ViewId.ACTIONS);

        }
    }

    public void handleInfoSubmit(MouseEvent event, ToggleGroup playersGroup, ToggleGroup infoTypeGroup){
        if(event.getButton() == MouseButton.PRIMARY){
            String temp = ((RadioButton) playersGroup.getSelectedToggle()).getText();
            temp = Character.toString(temp.charAt(2));
            System.out.println(temp);
            this.duringModel.setInfoPlayer(temp);
            String tempType = ((RadioButton) infoTypeGroup.getSelectedToggle()).getText();
            String tempParsed = parseInfoType(tempType);
            this.duringModel.setInfoType(tempParsed);
            JSONObject informCommand = new JSONObject();
            informCommand.put("action", "inform");
            informCommand.put("player", Integer.parseInt(temp));
            if (isSuit(tempParsed)) {
                informCommand.put("suit", tempParsed);
            }
            else{
                informCommand.put("rank", Integer.parseInt(tempParsed));
            }
            this.info = true;
            this.socket.sendToServer(informCommand);
            this.duringModel.setViews(ViewId.INFO, ViewId.ACTIONS);

        }
    }

    public String parseInfoType(String response){
        switch(response){
            case " Red":
                return "r";
            case " Blue":
                return "b";
            case " Green":
                return "g";
            case " Yellow":
                return "y";
            case " White":
                return "w";
            case " Rainbow":
                return "m";
            case " 1":
                return "1";
            case " 2":
                return "2";
            case " 3":
                return "3";
            case " 4":
                return "4";
            case " 5":
                return "5";
            default:
                return "";
        }
    }

    public boolean isSuit(String response){
        return (response.equals("r") || response.equals("b") || response.equals("g") || response.equals("y")
                || response.equals("w") || response.equals("m"));
    }

    public void handleDiscardSelect(VBox aCard){
        int pos = Integer.parseInt(aCard.getId());
        this.duringModel.setIndexOfCardToDiscard(pos);
        System.out.println("FROM CONTROLLER CARD TO PLAY " + pos + "!!!!!!!!!!");
    }


    public void handlePlayAgain(MouseEvent event){
        if(event.getButton() == MouseButton.PRIMARY){
            this.setOverallState(State.BEFORE);
        }
    }


    private void setOverallState(State s){
        this.OverallState = s;
    }

    //    public void handleGameQuit(MouseEvent event){
//        if(event.getButton() == MouseButton.PRIMARY) {
//            System.out.println("GameQuit");
//            this.setOverallState(State.BEFORE);
//            this.beforeModel.setViews(ViewId.DISCARD, ViewId.ACTIONS);
//            this.duringModel.setViews(ViewId.GAME, ViewId.GAME);
//            //TODO make setViews method to replace this
//            //TODO close socket connection
//        }
//    }
//
    public void handleResponse(JSONObject response){

        switch (OverallState){
            case BEFORE:
                if (response.containsKey("reply")){
                    switch((String) response.get("reply")){
                        case "extant":
                            String gameId;
                            String secretToken;
                            gameId = response.get("game-id").toString(); //comes back as long, cant cast to string
                            this.beforeModel.setGameId(gameId);
                            secretToken = (String) response.get("token");
                            this.beforeModel.setSecretToken(secretToken);
                            break;
                        case "created":
                            gameId = response.get("game-id").toString(); //comes back as long, cant cast to string
                            System.out.println("FROM CREATE: " + gameId);
                            this.beforeModel.setGameId(gameId);
                            secretToken = (String) response.get("token");
                            this.beforeModel.setSecretToken(secretToken);
                            this.created = true;
                            break;
                        case "joined":
                            int remainingPlayers = Integer.parseInt(response.get("needed").toString()); //comes back as long, cant cast to int
                            System.out.println("FROM JOINED " + remainingPlayers);
                            this.beforeModel.setRemainingPlayers(remainingPlayers);
                            int timeOut = Integer.parseInt(response.get("timeout").toString()); //comes back as long, cant cast
                            this.beforeModel.setTimeOut(timeOut);
                            if(!this.created){
                                // This is for other players who has the joined
                                String gameRule = (String) response.get("rainbow");
                                if (gameRule.equals("wild")) {
                                    this.wild = true;
                                    this.firework = false;
                                    this.duringModel.setBoardSize(5);
                                }
                                else if (gameRule.equals("firework")) {
                                    this.wild = false;
                                    this.firework = true;
                                    this.duringModel.setBoardSize(6);
                                    this.duringModel.getCurrentBoard().addRainbow();
                                }
                                else{
                                    this.wild = false;
                                    this.firework = false;
                                    this.duringModel.setBoardSize(5);
                                }
                            }
                            System.out.println("DOWN HERE");
                            this.beforeModel.setViews(ViewId.MAIN, ViewId.WAIT);
                            break;
                        case "no such game":
                            this.beforeModel.setErrorMsg("Invalid game-id or token");
                            break;
                        case "game full":
                            this.beforeModel.setErrorMsg("All the player seats are filled.");
                            break;
                    }
                }
                else{
                    switch((String) response.get("notice")){
                        case "player joined":
                            int remainingPlayersJoined = Integer.parseInt(response.get("needed").toString());
                            this.beforeModel.setRemainingPlayers(remainingPlayersJoined);
                            break;
                        case "player left":
                            int remainingPlayersLeft = Integer.parseInt(response.get("needed").toString());
                            this.beforeModel.setRemainingPlayers(remainingPlayersLeft);
                            break;
                        case "game starts":
                            setOverallState(State.DURING);
                            JSONArray handResponse = (JSONArray)response.get("hands");
                            ArrayList<ArrayList<String>> hands = new ArrayList<>();
                            for (int i = 0; i < handResponse.size(); i++){
                                ArrayList<String> smallerHand = new ArrayList<>();
                                JSONArray jsonObject = (JSONArray) handResponse.get(i);
                                for (int j = 0; j < jsonObject.size(); j++){
                                    smallerHand.add((String) jsonObject.get(j));
                                }
                                hands.add(smallerHand);
                            }
                            this.duringModel.openingDeal(hands);
                            this.duringModel.setViews(ViewId.GAME, ViewId.ACTIONS);
                            break;
                    }
                }

                break;

            case DURING:
                if (response.containsKey("reply")){
                    switch((String) response.get("reply")){
                        case "invalid":
                            break;
                        case "accepted":
                            if (info) {
                                this.duringModel.playerReceivedInfo(this.duringModel.getInfoType()
                                        , Integer.parseInt(this.duringModel.getInfoPlayer())-1);
                                info = false;
                            }
                            else {
                                this.duringModel.userDiscard(new Card((String)response.get("card")));
                            }
                            this.duringModel.setViews(ViewId.ACTIONS, ViewId.ACTIONS);
                            break;
                        case "built":
                            this.duringModel.userPlayed(new Card((String)response.get("card")));
                            this.duringModel.setViews(ViewId.ACTIONS, ViewId.ACTIONS);
                            break;
                        case "burned":
                            this.duringModel.userBurned(new Card((String)response.get("card")), (boolean)response.get("replaced"));
                            this.duringModel.setViews(ViewId.ACTIONS, ViewId.ACTIONS);
                            break;
                        case "inform":
                            if(response.containsKey("info")){ // user player is receiving notice
                                JSONArray handResponse = (JSONArray)response.get("info");
                                ArrayList<Boolean> hands = new ArrayList<>();
                                for (int i = 0; i < handResponse.size(); i++){
                                    Boolean jsonObject = (boolean) handResponse.get(i);
                                    hands.add(jsonObject);
                                }
                                if(response.containsKey("rank")){
                                    this.duringModel.userReceivedInfo((response.get("rank").toString()),
                                            hands);
                                }else{
                                    this.duringModel.userReceivedInfo((String)response.get("suit"),
                                            hands);
                                }
                            }else{ //other player gets info
                                if(response.containsKey("rank")){
                                    this.duringModel.playerReceivedInfo(response.get("rank").toString(), Integer.parseInt(response.get("player").toString()));
                                }else{
                                    this.duringModel.playerReceivedInfo((String)response.get("suit"), Integer.parseInt(response.get("player").toString()));
                                }
                            }
                            this.duringModel.setViews(ViewId.ACTIONS, ViewId.ACTIONS);
                            break;
                        case "discarded":
                            this.duringModel.discard(this.duringModel.getCurrentPlayer()-1,
                                    Integer.parseInt(response.get("position").toString())-1, (String)response.get("card"));
                            this.duringModel.setViews(ViewId.ACTIONS, ViewId.ACTIONS);
                            break;
                        case "game cancelled":
                            this.afterModel.setScore(this.duringModel.getScore());
                            this.afterModel.calculateRank();
                            this.setOverallState(State.AFTER);
                            this.afterModel.setViews(ViewId.END, ViewId.END);
                            break;
                        case "game ends":
                            this.afterModel.setScore(this.duringModel.getScore());
                            this.afterModel.calculateRank();
                            this.setOverallState(State.AFTER);
                            this.afterModel.setViews(ViewId.END, ViewId.END);
                            break;
                    }
                }else{
                    switch((String) response.get("notice")){
                        
                        case "played":
                            this.duringModel.playCard(this.duringModel.getCurrentPlayer()-1,
                                    Integer.parseInt(response.get("position").toString())-1, (String)response.get("card"));
                            this.duringModel.setViews(ViewId.ACTIONS, ViewId.ACTIONS);
                            break;
                        case "your move":
                            this.duringModel.setCurrentPlayer(this.duringModel.getUserPlayer());
                            this.duringModel.setViews(ViewId.ACTIONS, ViewId.ACTIONS);
                            break;
                    }
                }

                break;
//            case AFTER:
//
//
//                break;
        }
    }


    private static String computeHash(String msg) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(msg.getBytes());
            return new BigInteger(1,md.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            return ("MD5 ... what's MD5?");
        }
    }

}
