package sample.Model;

/*
 * Copyright (c) 2019 CMPT 370 Group B3
 */

import sample.View.ViewId;

import java.util.ArrayList;

/**
 * The class that represents the After Model in Hanabi.
 *
 * @version 1.0 18 Mar 2019
 * @author Aeysol Rosaldo
 */
public class AfterModel extends Model{

    public static class Memento{

        private AfterModel zeroState;

        public Memento(AfterModel state){
            this.zeroState = state;
        }

        private AfterModel getSavedState(){
            return this.zeroState;
        }

    }


    /* The rank of the score.*/
    public String rank;

    /* The total score of the game.*/
    public int score;

    //TODO change ViewId to ClientView and adjust accordingly
    /* The list of subscribers.*/
    public ArrayList<ViewId> subscribers;

    private static AfterModel AMInstance;

    private Memento zeroState;


    /* The constructor of the After Model class.*/
    private AfterModel(){
        super("After");
        //zeroState = this.saveToMemento();
    }

    public static AfterModel getAfterModelInstance(){
        if(AMInstance == null){
            AMInstance = new AfterModel();
        }
        return AMInstance;
    }

    public void restoreFromMemento(){
        //getAfterModelInstance() = this.zeroState.getSavedState();
    }

    private Memento saveToMemento(){
        AfterModel instance = new AfterModel();
        return new Memento(instance);
    }


    /**
     * Add view to the list of subscribers
     * @param aView the view id to be added
     */
    public void addSubscribers(ViewId aView){
        subscribers.add(aView);
    }

    /**
     * Notify the list of subscribers for the change
     */
    public void notifySubs(){
        subscribers.notify();
    }

    /**
     * Sets the score of the game
     * @param score the score of the game
     */
    public void setScore(int score){
        this.score = score;
    }


    /**
     * get the score attr
     */
    public int getScore(){
        return this.score;
    }

    /**
     * get the rank
     */
    public String getRank(){
        return this.rank;
    }


    /**
     * Sets the rank of the game
     */
    public void calculateRank(){
        if(this.score <= 5){
            this.rank = "Horrible";
        } else if(this.score <= 10){
            this.rank = "Mediocre";
        }else if(this.score <= 15){
            this.rank = "Honorable";
        }else if(this.score <= 20){
            this.rank = "Amazing";
        }else if(this.score <=24){
            this.rank = "Excellent";
        }else if(this.score == 25){
            this.rank = "Legendary";
        }else if(this.score > 25){
            this.rank = "Godly";
        }else{
            this.rank = "How'd you even get this score?";
        }
    }



    /**
     * Resets the After Model
     */
    public void resetModel(){
        //TODO for pair programming
    }
}
