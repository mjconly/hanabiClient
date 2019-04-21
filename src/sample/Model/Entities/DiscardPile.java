package sample.Model.Entities;

import java.util.ArrayList;

public class DiscardPile {

    private ArrayList<Card> cards;

    public DiscardPile(){
        this.cards = new ArrayList<>();
    }

    public void addCard(Card c){
        cards.add(c);
    }

    public ArrayList<Card> getCards() {
        return cards;
    }
}
