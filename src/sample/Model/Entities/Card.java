package sample.Model.Entities;

import sample.Model.Suit;
/**
 * The class that represents the card game item in Hanabi.
 *
 * @version 1.0 16 Mar 2019
 * @author
 */

public class Card {

    /* The rank of the card*/
    private int rank;

    /* The suit of the card*/
    private Suit suit;

    /* The filename of the image of the card to be used for the views*/
    private String fileName;

    private String suitString;

    /**
     * The constructor of the card class
     * @param r the rank of the card
     * @param s the suit of the card
     */
    public Card(int r, Suit s){
        rank = r;
        suit = s;
        this.setFileName(r, s);
    }

    public Card(String cText){
        switch(cText.charAt(0)){
            case 'b':
                suit = Suit.BLUE;
                break;
            case 'r':
                suit = Suit.RED;
                break;
            case 'g':
                suit = Suit.GREEN;
                break;
            case 'y':
                suit = Suit.YELLOW;
                break;
            case 'w':
                suit = Suit.WHITE;
                break;
            case 'm':
                suit = Suit.MULTI;
                break;
        }
        rank = Character.getNumericValue(cText.charAt(1));
        this.setFileName(rank, suit);
    }

    /**
     * Get the rank of the card
     * @return the rank of the card
     */
    public int getRank() {
        return rank;
    }

    /**
     * Get the suit of the card
     * @return the suit of the card
     */
    public Suit getSuit() {
        return suit;
    }

    /**
     * Get the filename of the card image
     * @return the filename of the card image
     */
    public String getFilePath(){
        return this.fileName;
    }

    /**
     * Set the filename of the card image
     * @param rank the rank of the card
     * @param suit the suit of the card
     */
    private void setFileName(int rank, Suit suit){
        switch(suit){
            case BLUE:
                this.fileName = "b" + rank + ".png"; //ex: b1.png
                break;
            case RED:
                this.fileName = "r" + rank + ".png"; //ex: r1.png
                break;
            case GREEN:
                this.fileName = "g" + rank + ".png"; //ex: g1.png
                break;
            case YELLOW:
                this.fileName = "y" + rank + ".png"; //ex: y1.png
                break;
            case WHITE:
                this.fileName = "w" + rank + ".png"; //ex: w1.png
                break;
            case MULTI:
                this.fileName = "rb" + rank + ".png"; //ex: m1.png
        }
    }

    public static void main(String[] args){

        System.out.println("Card.java Testing");
        System.out.println();

        Card r1 = new Card(1, Suit.RED);
        Card r2 = new Card(2, Suit.RED);
        Card b3 = new Card(3, Suit.BLUE);
        Card b4 = new Card(4, Suit.BLUE);
        Card w5 = new Card(5, Suit.WHITE);

        if(r1.getRank() != 1){
            System.out.println("The card's rank should be 1.");
        }

        if(r2.getSuit() != Suit.RED){
            System.out.println("The card's suit should be RED.");
        }

        if(b3.getRank() != 3){
            System.out.println("The card's rank should be 3.");
        }

        if(b4.getSuit() != Suit.BLUE){
            System.out.println("The card's suit should be BLUE.");
        }

        if(w5.getRank() != 5 && w5.getSuit() != Suit.WHITE){
            System.out.println("The card's rank and suit should be 5 and WHITE, respectively.");
        }

        System.out.println("Regression testing done.");

    }

}
