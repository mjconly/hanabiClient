package sample.Model.Entities;
import sample.Model.Suit;

import java.util.ArrayList;

public class Board {

    private ArrayList<Firework> fireworks = new ArrayList<>();

    public Board() {
        fireworks.add(new Firework(Suit.RED));
        fireworks.add(new Firework(Suit.GREEN));
        fireworks.add(new Firework(Suit.BLUE));
        fireworks.add(new Firework(Suit.YELLOW));
        fireworks.add(new Firework(Suit.WHITE));
    }

    public void addRainbow(){
        fireworks.add(new Firework(Suit.MULTI));
    }

    public boolean addCard(Card c) {
        boolean added = false;

        /*
         * Go through each firework, find the correct colour and check if it is possible
         * to add on to it
         */
        for (Firework f : fireworks) {
            if (f.getColor() == c.getSuit()) {
                if(f.getTop() == null) {
                    if (c.getRank() == 1) {
                        f.setTop(c);
                        added = true;
                    }
                }
                else {
                    if (f.getTop().getRank() + 1 == c.getRank()) {
                        f.setTop(c);
                        added = true; // Indicate that the card was succesfully added to the board
                    }
                }
            }
        }
        return added;
    }

    public ArrayList<Firework> getFireworks() {
        return fireworks;
    }

    public Card getTopFirework(Suit s){
        for(Firework f: fireworks){
            if(f.getColor() == s){
                return f.getTop();
            }
        }
        return null;
    }

    public static void main(String[] args){

        System.out.println("Board.java testing.");

        Board board = new Board();

        Card y1 = new Card(1,Suit.YELLOW);

        board.addCard(y1);

        for(int i = 0; i < 5; i++) {
            System.out.println(board.getFireworks().get(i).getTop());
        }

        System.out.println("Regression testing done.");

    }

}
