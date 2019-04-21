package sample.Model.Entities;


import com.sun.org.apache.xpath.internal.operations.Bool;
import sample.Model.Suit;

import java.util.ArrayList;

/**
 * The class that represents the hand of a player in Hanabi.
 *
 * @version 1.0 16 Mar 2019
 * @author
 */
public class Hand {

    /* The list of cards in the player's hand*/
    private ArrayList<Card> myCards;

    /* Refers to the number of cards that the hand of player should have*/
    private int handSize;

    /* The list of suits that is revealed*/
    private ArrayList<Suit> revealedSuits;

    /* The list of ranks that is revealed*/
    private ArrayList<Integer> revealedRanks;

    /**
     * The constructor of the hand class
     * @param size the size of the hand
     */
    public Hand(int size){
        handSize = size;
        myCards = new ArrayList<Card>(size);
        revealedSuits = new ArrayList<Suit>(size);
        revealedRanks = new ArrayList<Integer>(size);

        for(int i = 0; i < size; i++){
            myCards.add(i,null);
            revealedSuits.add(i, null);
            revealedRanks.add(i, 0);
        }

    }

    /**
     * Get the size of the hand
     * @return the size of the hand
     */
    public int getHandSize() {
        return handSize;
    }

    /**
     * Get the card at the certain position in the hand
     * @param index the position of the hand
     * @return the card at the position of the hand
     */
    public Card getCard(int index){
        return this.myCards.get(index);
    }

    public ArrayList<Card> getMyCards() {
        return myCards;
    }

    public void setMyCards(ArrayList<Card> myCards) {
        this.myCards = myCards;
    }

    public ArrayList<Suit> getRevealedSuits() {
        return revealedSuits;
    }

    public void setRevealedSuits(ArrayList<Suit> revealedSuits) {
        this.revealedSuits = revealedSuits;
    }

    public ArrayList<Integer> getRevealedRanks() {
        return revealedRanks;
    }

    public void setRevealedRanks(ArrayList<Integer> revealedRanks) {
        this.revealedRanks = revealedRanks;
    }

    /**
     * Reveals the rank of the cards in the hand
     * @param rank the given rank
     * @param info the array of which positions need to be revealed
     */
    public void userRank(int rank, ArrayList<Boolean> info){
        for(int i = 0; i<info.size(); i++){
            if(info.get(i)){
                revealedRanks.set(i, rank);
            }
        }
    }

    /**
     * Reveals the suit of the cards in the hand
     * @param s the given suit
     * @param info the array of which positions need to be revealed
     */
    public void userSuit(Suit s, ArrayList<Boolean> info){
        for(int i = 0; i<info.size(); i++){
            if(info.get(i)){
                revealedSuits.set(i, s);
            }
        }
    }

    /**
     * Removes a card from the hand, does not reveal so should never be called on it's own
     * @param index position of card to remove in the hand
     * @return card removed, returned for discard, gamelog etc.
     */
    public Card removeCard(int index){
        Card savedCard = this.getCard(index);
        this.getMyCards().remove(index);
        return savedCard;
    }

    public void setCard(Card c, int Index){
        this.myCards.set(Index, c);

    }

    /**
     *  Iterates through the card array and reveals if there is a card same as the given suit
     * @param s the given suit
     */
    public void revealSuit(Suit s){

        //Iterate through the card array
        for(int i =0; i<this.myCards.size();i++){
            //Check if the suits are the same
            if(this.getCard(i) != null && this.getCard(i).getSuit() == s){
                //Reveal the card's suit
                this.revealedSuits.set(i,s);
            }
        }
    }

    /**
     * Iterates through the card array and reveals if there is a card same as the given rank
     * @param r the given rank
     */
    public void revealRank(int r){
        System.out.println(myCards.size());
        //Iterate through the card array
        for(int i =0; i<this.myCards.size();i++){
            //Check if the ranks are the same
            if(this.getCard(i) != null && this.getCard(i).getRank() == r){
                //Reveal the card's rank
                this.revealedRanks.set(i,r);
            }
        }
    }

    /**
     * Resets the suit and rank of the hand's index
     * @param n the hand's index
     */
    public void resetInfo(int n){
        this.revealedSuits.set(n, null);
        this.revealedRanks.set(n, 0);
    }

    public static void main(String[] args){

        Hand hand = new Hand(5);

//        Card r1 = new Card(1, Suit.RED);
        Card r2 = new Card(3, Suit.RED);
        Card b3 = new Card(3, Suit.BLUE);
//        Card b4 = new Card(4, Suit.BLUE);
//        Card w5 = new Card(5, Suit.WHITE);

        boolean[] infos = {true, true, false, true, false};

        boolean[] infor = {false, false, true, false, true};

//        hand.userRank(1,infor);
//        hand.userSuit(Suit.BLUE,infos);

        System.out.println(hand.getMyCards());
        System.out.println(hand.getRevealedRanks());
        System.out.println(hand.getRevealedSuits());

        hand.setCard(r2,0);
        hand.setCard(b3,1);

        System.out.println(hand.getMyCards());

        hand.revealRank(2);
        hand.revealSuit(Suit.BLUE);

        System.out.println(hand.getRevealedRanks());
        System.out.println(hand.getRevealedSuits());


    }
}
