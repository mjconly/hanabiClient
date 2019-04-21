package sample.Model.Entities;
import sample.Model.Suit;


public class Firework {

    public Card topCard;

    public Suit color;

    public Firework(Suit suit){
        this.topCard = null;
        this.color = suit;
    }

    public Card getTop(){
        return this.topCard;
    }

    public void setTop(Card c){
        this.topCard = c;
    }

    public Suit getColor() {
        return color;
    }

    public static void main(String[] args){

        System.out.println("Firework.java testing.");

        Firework firework = new Firework(Suit.RED);

        Card y1 = new Card(1,Suit.YELLOW);

        firework.setTop(y1);

        if(firework.getTop().getRank() != 1){
            System.out.println("The top card should have a rank 1.");
        }

        if(firework.getTop().getSuit() != Suit.YELLOW){
            System.out.println("The top card should have a suit yellow.");
        }

        if(firework.getColor() != Suit.YELLOW){
            System.out.println("The firework should be suit yellow.");
        }

        System.out.println("Regression testing done.");

    }
}
