package sample.Model.Entities;

/*
 * Copyright (c) 2019 CMPT 370 Group B3
 */

import java.util.ArrayList;


/**
 * The class that represents the game log in Hanabi.
 *
 * @version 1.0 16 Mar 2019
 * @author Aeysol Rosaldo
 */
public class GameLog {

    /* The list of String log in the game. */
    public ArrayList<String> log;

    /* The constructor of GameLog class. */
    public GameLog(){
        log = new ArrayList<String>();
    }

    /**
     * Get the list of the String log
     * @return list of the String log
     */
    public ArrayList<String> getLog(){
        return log;
    }

    /**
     * Add a log into the list
     * @param log a String log
     */
    public void addEntry(String log){
        this.log.add(log);
    }
}

