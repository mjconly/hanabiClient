package sample.Model.Entities;

import sample.Model.Suit;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.BlockingDeque;

public class HumanPlayer {
    private boolean turn;
    private boolean user;
    private Hand pHand;
    private int seat;
    private ArrayList<Suit> revealedSuit;
    private ArrayList<Integer> revealedRank;

    public HumanPlayer(int seat, Hand pHand){
        this.turn = false;
        this.pHand = pHand;
        this.seat = seat;

    }

    public boolean isUser(){
        return user;
    }

    public void setUser(Boolean value){
        this.user = value;
    }

    public int getSeat() {
        return this.seat;
    }

    public Hand getpHand() {
        return this.pHand;
    }

    public boolean isTurn(){
        return this.turn;
    }

    public void toggleTurn(){
        if(this.turn){
            this.turn = false;
        }else{
            this.turn = true;
        }

    }

    public Card removeCard(int index, Card dealt){
        Card removed = this.pHand.removeCard(index);
        this.pHand.resetInfo(index);
        this.resetpInfo(index);
        this.pHand.setCard(dealt, index);
        return removed;
    }

    public void infoSuit(Suit s){
        this.pHand.revealSuit(s);
    }


    public void infoRank(int rank){
        this.pHand.revealRank(rank);
    }




    public void resetpInfo(int n){
        this.revealedSuit.set(n, null);
        this.revealedRank.set(n, 0);
    }

    public void userInfoSuit(Suit s, ArrayList<Boolean> info){
        this.pHand.userSuit(s, info);
    }

    public void userInfoRank(int rank, ArrayList<Boolean> info){
        this.pHand.userRank(rank,info);
    }



}
