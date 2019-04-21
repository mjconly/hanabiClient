package sample.Model;

/*
 * Copyright (c) 2019 CMPT 370 Group B3
 */

import sample.View.ViewId;

import java.util.ArrayList;

/**
 * The class that represents the Model in Hanabi.
 *
 * @version 1.0 18 Mar 2019
 * @author Aeysol Rosaldo
 */
public class BeforeModel extends Model {

    public static class Memento{

        private final BeforeModel zeroState;

        public Memento(BeforeModel state){
            this.zeroState = state;
        }

        private BeforeModel getSavedState(){
            return this.zeroState;
        }

    }

    //TODO change ViewId to ClientView and adjust accordingly
    /* The list of subscribers*/
    public ArrayList<ViewId> subscribers;

    /* The list of rules.*/
    public String theRules;

    /* The number of players.*/
    public int numPlayers;

    /* The number of CPU players.*/
    public int numCpu;

    /* The number of remaining players.*/
    public int remainingPlayers;

    /* The number of seconds for timeout.*/
    public int timeOut;

    /* The game id of the game lobby.*/
    public String gameId;

    /* The secret token of the game lobby.*/
    public String secretToken;

    /* The view id of the current view.*/
    public ViewId currView;

    /* The error message of the game lobby.*/
    public String errorMsg;

    private static BeforeModel BMInstance;

    private Memento zeroState;


    /* The constructor of the Before Model class.*/
    /* For BMSTATE*/
    private BeforeModel(){
        super("Before");
        this.initRules();
        //zeroState = this.saveToMemento();
    }

    public static BeforeModel getBeforeModelInstance(){
        if(BMInstance == null){
            BMInstance =  new BeforeModel();
        }
        return BMInstance;
    }

    public void restoreFromMemento(){
        this.BMInstance = this.zeroState.getSavedState();
    }

    private Memento saveToMemento(){
        BeforeModel instance = new BeforeModel();
        return new Memento(instance);
    }


    /**
     * Set the view id to the current view
     * @param id the view id of the current view
     */
    public void setCurrView(ViewId id){
        currView = id;
    }

    /**
     * Add a subscriber to the list of subscribers
     * @param aSub
     */
    public void addSubscribers(ViewId aSub){
        subscribers.add(aSub);
    }

    /**
     * Initialize the list of rules
     */
    public void initRules(){
        this.theRules = "THE BASICS:" +
                "\n\tFive suits of ten cards of five ranks."+
                "\n\tSuits are Colors: Red, Blue, Green, Yellow, White."+
                "\n\tEach suit represents a firework"+
                "\n\tRanks are numbers:"+
                "\n\t\tThree of rank '1'"+
                "\n\t\tTwo of rank '2'"+
                "\n\t\tTwo of rank '3'"+
                "\n\t\tTwo of rank '4'"+
                "\n\t\tOne of rank '5'"+
                "\n\nTHE OBJECTIVE:"+
                "\n\tPlayers will cooperate to construct fireworks."+
                "\n\tA firework is built when the '5' rank of that suit is played."+
                "\n\tCards must be played in sequential rank for their suit, for example,\n\t"+
                "a player can play a red 2, only if there is a red 1 already in play."+
                "\n\tWhen a card is played, all remaining cards of that suit and rank are no\n\t"+
                "longer required to build that firework"+
                "\n\nTHE SETUP:"+
                "\n\tShuffle and deal cards"+
                "\n\t\t2-3 players get 5 cards each"+
                "\n\t\t4-5 players get 4 cards each"+
                "\n\tPlayers will reveal their hands to each other, but must never look at their\n\t"+
                "own hands!"+
                "\n\nTOKENS:"+
                "\n\tThe game starts with 8 Information tokens. These tokens allow a player"+
                "\n\tto give another player information about their hand. To gain an"+
                "\n\tinformation token, a player must successfully discard a card from their" +
                "\n\thand. Each time a player gives information, a token is spent."+
                "\n\n\tThe game starts with 3 Fuse tokens. A fuse token is burned when a"+
                "\n\tplayer discards a card that is necessary for the construction of a"+
                "\n\tfirework. When the third and final fuse is burned the game is over."+
                "\n\nGAME PLAY:"+
                "\n\tA player can choose one of three moves when it is their turn."+
                "\n\t\tPlay a Card:"+
                "\n\t\t\tA Player makes a blind or informed play from their hand and"+
                "\n\t\t\treceives a new card from the deck."+
                "\n\t\tDiscard a Card:"+
                "\n\t\t\tA Player make a blind or informed discard from their hand and " +
                "\n\t\t\treceives a new card from the deck."+
                "\n\t\tGive Information:"+
                "\n\t\t\tIf there are information tokens available a player can give"+
                "\n\t\t\tinformation. A player will inform another player at the table"+
                "\n\t\t\tabout a card Suit or Rank in their hand. This player will then"+
                "\n\t\t\tknow the position of all cards in their hand that matched the"+
                "\n\t\t\tinformation they were given."+
                "\n\nGAME ENDS WHEN:"+
                "\n\tIf all five fireworks are built."+
                "\n\tIf all three fuses are burnt."+
                "\n\tIf the last card is drawn from the deck, each player will get one more turn."+
                "\n\nSCORING:"+
                "\n\tCount up the top card of each fire work built"+
                "\n\t\t0 - 5: horrible"+
                "\n\t\t6 - 10: mediocre"+
                "\n\t\t11 - 15: honorable"+
                "\n\t\t16 - 20: excellent"+
                "\n\t\t21 - 24: amazing"+
                "\n\t\t25: legendary"+
                "\n\n\n\n\n\n\n\n\n\nThe last rule of Hanabi, is dont talk about Hanabi";

    }

    /**
     * Notify the list of subscribers.
     */
    public void notifySubs(){
        subscribers.notify();
    }

    /**
     * Set the number of players
     * @param num the number of players
     */
    public void setNumPlayers(int num){
        numPlayers = num;
    }

    /**
     * Get the number of players
     * @return the number of players
     */
    public int getNumPlayers(){
        return numPlayers;
    }

    /**
     * Set the number of CPU players
     * @param num the number of CPU players
     */
    public void setNumCpu(int num){
        numCpu = num;
    }

    /**
     * Set the secret token for the game lobby
     * @param secret the secret token
     */
    public void setSecretToken(String secret){
        secretToken = secret;
    }

    /**
     * Get the secret token of the game lobby
     * @return the secret token
     */
    public String getSecretToken(){
        return secretToken;
    }

    /**
     * Set the game id of the game lobby
     * @param key the key for the game id
     */
    public void setGameId(String key){
        gameId = key;
    }

    /**
     * Get the game id of the game lobby
     */
    public String getGameId(){
        return gameId;
    }

    /**
     * Set the number of remaining players needed for the game
     * @param num the number of remaining players
     */
    public void setRemainingPlayers(int num){
        remainingPlayers = num;
    }

    /**
     * Get the number of remaining players needed for the game
     * @return the number of remaining players
     */
    public int getRemainingPlayers(){
        return remainingPlayers;
    }

    /**
     * Get the value of timeout
     * @return the value of timeout
     */
    public int getTimeOut() {
        return timeOut;
    }

    /**
     * Set the value of timeout
     * @param timeOut the value of timeout
     */
    public void setTimeOut(int timeOut) {
        this.timeOut = timeOut;
    }

    /**
     * Set the error message
     * @param msg the error message
     */
    public void setErrorMsg(String msg){
        errorMsg = msg;
    }

    /**
     * Get the error message
     * @return the error message
     */
    public String getErrorMsg(){
        return errorMsg;
    }

    public String getTheRules(){
        return this.theRules;
    }

    /**
     * Reset the Before Model
     */

}
