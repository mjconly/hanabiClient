package sample.Model;

import java.util.ArrayList;
import sample.Model.Entities.*;
import sample.View.ViewId;

public class DuringModel extends Model {

    public static class Memento{

        private final DuringModel zeroState;

        public Memento(DuringModel state){
            this.zeroState = state;
        }

        private DuringModel getSavedState(){
            return this.zeroState;
        }

    }

    private Board currentBoard;
    private ArrayList<HumanPlayer> players;
    private Token infoToken;
    private Token fuseToken;
    private ArrayList<ViewId> subscribers;
    private DiscardPile discards;
    private GameLog log;
    private int numOfPlayers;
    private int numCards;
    private int indexOfCardToPlay;
    private int indexOfCardToDiscard;
    private int indexOfPlayerToInform;
    private int currentPlayer;
    private int userPlayer;
    private int boardSize;
    private String infoType;
    private String infoPlayer;

    private static DuringModel DMInstance = new DuringModel();

    private Memento zeroState;


    private DuringModel(){
        super("During");
        this.currentBoard = new Board();
        this.players = new ArrayList<>();
        this.infoToken = new Token(Token.TType.INFOTOKEN);
        this.fuseToken = new Token(Token.TType.FUSETOKEN);
        this.discards = new DiscardPile();
        this.log = new GameLog();
        this.currentPlayer = 1;
        //zeroState = this.saveToMemento();
    }

    public String getInfoType() {
        return infoType;
    }

    public void setInfoType(String infoType) {
        this.infoType = infoType;
    }

    public String getInfoPlayer() {
        return infoPlayer;
    }

    public void setInfoPlayer(String infoPlayer) {
        this.infoPlayer = infoPlayer;
    }

    public void setBoardSize(int size){
        this.boardSize = size;
    }

    public int getBoardSize(){
        return boardSize;
    }

    public Board getCurrentBoard() {
        return currentBoard;
    }

    public void setCurrentBoard(Board currentBoard) {
        this.currentBoard = currentBoard;
    }

    public ArrayList<HumanPlayer> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<HumanPlayer> players) {
        this.players = players;
    }

    public HumanPlayer getPlayer(int seat) {
        return this.players.get(seat);
    }


    public Token getInfoToken() {
        return infoToken;
    }

    public void setInfoToken(Token infoToken) {
        this.infoToken = infoToken;
    }

    public Token getFuseToken() {
        return fuseToken;
    }

    public void setFuseToken(Token fuseToken) {
        this.fuseToken = fuseToken;
    }

    public DiscardPile getDiscards() {
        return discards;
    }

    public void setDiscards(DiscardPile discards) {
        this.discards = discards;
    }

    public GameLog getLog() {
        return log;
    }

    public void setLog(GameLog log) {
        this.log = log;
    }

    public int getNumOfPlayers() {
        return numOfPlayers;
    }

    public void setNumOfPlayers(int numOfPlayers){
        this.numOfPlayers = numOfPlayers;
    }

    public int getIndexOfCardToPlay() {
        return indexOfCardToPlay;
    }

    public void setIndexOfCardToPlay(int indexOfCardToPlay) {
        this.indexOfCardToPlay = indexOfCardToPlay;
    }

    public int getIndexOfCardToDiscard() {
        return indexOfCardToDiscard;
    }

    public void setIndexOfCardToDiscard(int indexOfCardToDiscard) {
        this.indexOfCardToDiscard = indexOfCardToDiscard;
    }

    public int getIndexOfPlayerToInform() {
        return indexOfPlayerToInform;
    }

    public void setIndexOfPlayerToInform(int indexOfPlayerToInform) {
        this.indexOfPlayerToInform = indexOfPlayerToInform;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public int getUserPlayer() {
        return userPlayer;
    }

    public void setUserPlayer(int userPlayer) {
        this.userPlayer = userPlayer;
    }

    public void addSubscribers(ViewId sub){
        this.subscribers.add(sub);
    }

    public void notifySubs(){
        this.subscribers.notify();
    }

    public Card readCard(String cText){
        return new Card(cText);
    }

    public Hand readHand(ArrayList<String> inCards){
        Hand newHand = new Hand(inCards.size());
        for(int i = 0; i<inCards.size(); i++){
            newHand.setCard(this.readCard(inCards.get(i)), i);
        }
        return newHand;
    }

    public void openingDeal(ArrayList<ArrayList<String>> deals){
        this.numOfPlayers = deals.size();
        if (this.numOfPlayers == 2 || this.numOfPlayers == 3){
            this.numCards = 5;
        }
        else {
            this.numCards = 4;
        }
        System.out.println(deals.size());
        for(int i = 0; i<deals.size();i++){
            if(deals.get(i).size() != 0)
                this.players.add(new HumanPlayer(i+1, this.readHand(deals.get(i))));
            else{
                HumanPlayer Player = new HumanPlayer(i+1, new Hand(numCards));
                Player.setUser(true);
                this.userPlayer = i+1;
                this.players.add(Player);
            }

        }

    }

    // Allows for a player to play a card
    // seat is the seat that the player is in
    // index is the position of the card in the players hand that the card will be
    // played
    // c is the card to be played Postcondition: a card is added to the board

    public void playCard(int seat, int index, String theCard) {
        if(!theCard.equals("NONE")){
            Card newcard = new Card(theCard);
            HumanPlayer curPlayer = this.getPlayer(seat);
            System.out.println("CURRENT SEAT "+seat);
            Card cardToPlay = curPlayer.getpHand().getCard(index); // Get it's hand a nd the specific
            System.out.println("HERE WITH INDEX " + index);
            System.out.println("CARD: " + cardToPlay.getSuit());
            // card

            curPlayer.getpHand().resetInfo(index); // Resets the information at that hand index
            curPlayer.getpHand().setCard(newcard, index);

        // Attempt to add the card to the board
        this.addCardToBoard(cardToPlay);
        }else{
            HumanPlayer curPlayer = this.getPlayer(seat);

            Card cardToPlay = curPlayer.getpHand().removeCard(index); // Removes the card to be discarded from the player's hand
            curPlayer.getpHand().getRevealedRanks().remove(index); // Resets the information at that hand index
            curPlayer.getpHand().getRevealedSuits().remove(index); // Resets the information at that hand index
            this.addCardToBoard(cardToPlay);
        }
        this.changePlayer();
    }


    public void spendInfo(){
        this.infoToken.spend();
    }

    public void burnFuse(){
        this.fuseToken.burn();
    }

    public void gainInfo(){
        this.infoToken.add();
    }

    public void addToLog(String log){
        this.log.addEntry(log);
    }

    public void userDiscard(Card c) {
        HumanPlayer curPlayer = this.getPlayer(this.currentPlayer-1);

        curPlayer.getpHand().resetInfo(this.indexOfCardToDiscard-1); // Resets the information at that hand index

        DiscardPile discardPile = getDiscards(); // Get the discard pile

        discardPile.addCard(c); // Add the card to the discard pile

        infoToken.add(); // Increase the number of inforamtion tokens by 1 if possible
        this.log.addEntry("Token Retrieved!");

        /*
         * Add the new card given by the controller to the current players hand at the
         * same index of the previous card that was discarded
         */
        curPlayer.getpHand().setCard(c, this.indexOfCardToDiscard-1);

        this.log.addEntry("Player in seat "+this.currentPlayer+" discarded a card with rank "+
                c.getRank()+" and suit " +c.getSuit() +" .");
        this.changePlayer();
    }


    public void userPlayed(Card c) {
        HumanPlayer curPlayer = this.getPlayer(this.currentPlayer-1);
        // Get the card in the players hand
        curPlayer.getpHand().resetInfo(this.indexOfCardToPlay-1); // Resets the information at that hand index
        // Attempt to add the card to the board
        this.addCardToBoard(c);


        this.changePlayer();
    }


    public void addCardToBoard(Card c) {
        // Burn fuse token if card was no succesfully added
        boolean attemptToAdd = this.currentBoard.addCard(c);
        if (!attemptToAdd) {
            this.log.addEntry("MISTAKE MADE! Player in seat "+this.currentPlayer+" tried playing a card with rank "+
                    c.getRank()+" and suit " +c.getSuit() +".");
            this.userBurned(c); // Indicate that user burned
            if (fuseToken.current == 0) {
                System.out.println("GAME OVER");

            }
            this.log.addEntry("FUSE TOKEN BURNED!");
        }else{
            // Log that card was successfully played
            this.log.addEntry("Player in seat "+this.currentPlayer+" played card with rank "+
                    c.getRank()+" and suit " +c.getSuit() +" .");
        }
    }

    public void userBurned(Card c){
        fuseToken.burn();
        this.discards.addCard(c);
    }

    public void userBurned(Card c, boolean replaced){

        HumanPlayer curPlayer = this.getPlayer(this.currentPlayer-1);
        curPlayer.getpHand().resetInfo(this.indexOfCardToDiscard-1); // Resets the information at that hand index
        fuseToken.burn();
        this.discards.addCard(c);

        if(!replaced){
        }
        this.log.addEntry("MISTAKE MADE! Player in seat "+this.currentPlayer+" tried playing a card with rank "+
                c.getRank()+" and suit " +c.getSuit() +".");
        this.userBurned(c); // Indicate that user burned
        if (fuseToken.current == 0) {
            System.out.println("GAME OVER");

        }
        this.log.addEntry("FUSE TOKEN BURNED!");
        this.changePlayer();
    }

    // Removes a card from the player's hand and adds it to the discard pile
    // Increases the count of info tokens if possible
    // Deals the player a new card
    public void discard(int seat, int index, String c) {

        Card newCard = new Card(c); // New card to add to hand
        HumanPlayer currentPlayer = this.getPlayer(seat); // Get player by seat
        Card cardToDiscard = currentPlayer.getpHand().getCard(index); // Get it's hand a nd the specific card

        currentPlayer.getpHand().resetInfo(index); // Resets the information at that hand index
        currentPlayer.getpHand().setCard(newCard, index);
        DiscardPile discardPile = getDiscards(); // Get the discard pile

        discardPile.addCard(cardToDiscard); // Add the card to the discard pile

        infoToken.add(); // Increase the number of information tokens by 1 if possible
        this.log.addEntry("Token Retrieved!");

        currentPlayer.getpHand().setCard(newCard, index); // Add the new card given by the controller to the current players
        // hand.

        this.log.addEntry("Player in seat "+this.currentPlayer+" discarded a card with rank "+
                cardToDiscard.getRank()+" and suit " +cardToDiscard.getSuit() +" .");
        this.changePlayer();
    }


    public void userReceivedInfo(String infoType, ArrayList<Boolean> info){
        if(isNumeric(infoType)){
            int rank = Integer.parseInt(infoType);
            this.players.get(this.userPlayer-1).userInfoRank(rank, info);
            this.log.addEntry("Player in seat "+this.currentPlayer+" received info about cards with rank "+
                    rank+".");
        }else{
            Suit iSuit = parseSuit(infoType);
            this.players.get(this.userPlayer-1).userInfoSuit(iSuit, info);
            String theSuit = "";
            switch(iSuit){
                case RED:
                    theSuit = "RED";
                    break;
                case BLUE:
                    theSuit = "BLUE";
                    break;
                case YELLOW:
                    theSuit = "YELLOW";
                    break;
                case WHITE:
                    theSuit = "WHITE";
                    break;
                case GREEN:
                    theSuit = "GREEN";

                default:
                    theSuit = "MULTI";

            }
            this.log.addEntry("Player in seat "+this.currentPlayer+" received info about cards with suit "+
                    theSuit+".");
        }
        this.spendInfo();
        this.changePlayer();
        System.out.println("CHANGE! USER");
    }

    public void playerReceivedInfo(String infoType, int seat){
        if(isNumeric(infoType)){
            int rank = Integer.parseInt(infoType);
            this.players.get(seat).infoRank(rank);
            this.log.addEntry("Player in seat "+this.currentPlayer+" received info about cards with rank "+
                    rank+".");
        }else{
            Suit iSuit = null;
            switch(infoType){
                case "w":
                    iSuit = Suit.WHITE;
                    break;
                case "y":
                    iSuit = Suit.YELLOW;
                    break;
                case "b":
                    iSuit = Suit.BLUE;
                    break;
                case "r":
                    iSuit = Suit.RED;
                    break;
                case "g":
                    iSuit = Suit.GREEN;
                    break;
                case "m":
                    iSuit = Suit.MULTI;
                    break;
            }
            this.players.get(seat).infoSuit(iSuit);




            String theSuit = "";
            switch(iSuit){
                case RED:
                    theSuit = "RED";
                    break;
                case BLUE:
                    theSuit = "BLUE";
                    break;
                case YELLOW:
                    theSuit = "YELLOW";
                    break;
                case WHITE:
                    theSuit = "WHITE";
                    break;
                case GREEN:
                    theSuit = "GREEN";

                default:
                    theSuit = "MULTI";

            }
            this.log.addEntry("Player in seat "+this.currentPlayer+" received info about cards with suit "+
                    theSuit+".");
        }
        this.spendInfo();
        this.changePlayer();
        System.out.println("CHANGE!");
    }

    private Suit parseSuit(String response) {
        Suit theSuit;
        switch (response) {
            case "r":
                theSuit = Suit.RED;
                break;
            case "b":
                theSuit = Suit.BLUE;
                break;
            case "y":
                theSuit = Suit.YELLOW;
                break;
            case "w":
                theSuit = Suit.WHITE;
                break;
            case "g":
                theSuit = Suit.GREEN;
                break;
            default:
                theSuit = Suit.MULTI;
        }
        return theSuit;
    }
    private static boolean isNumeric(String str){
        try{
            Integer.parseInt(str);
            return true;
        }catch(NumberFormatException e){
            return false;
        }
    }

    public int getScore(){
        ArrayList<Firework> fireworks = this.currentBoard.getFireworks();
        int score = 0;
        for (Firework f : fireworks) {
            if(f.getTop() != null)
                score += f.getTop().getRank();
        }
        return score;

    }


    public static DuringModel getDuringModelInstance(){
        if(DMInstance == null){
            DMInstance = new DuringModel();
        }
        return DMInstance;
    }

    public void restoreFromMemento(){
        this.DMInstance = this.zeroState.getSavedState();
    }

    private Memento saveToMemento()
    {
        DuringModel instance = new DuringModel();
        return new Memento(instance);
    }


    public int getNumCards() {
        return numCards;
    }

    public void changePlayer(){
        if(currentPlayer == this.numOfPlayers)
            currentPlayer = 1;
        else{
            currentPlayer += 1;
        }
    }

    public boolean isTurn(){
        return userPlayer == currentPlayer;
    }
}
