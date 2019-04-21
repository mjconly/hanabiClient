package sample.Model;

/*
 * Copyright (c) 2019 CMPT 370 Group B3
 */

import sample.View.ClientView;
import sample.View.RootListener;
import sample.View.ViewId;

import java.util.ArrayList;

/**
 * The class that represents the Model in Hanabi.
 *
 * @version 1.0 18 Mar 2019
 * @author Aeysol Rosaldo
 */
public class Model {

    /* The view id of the next view.*/
    public ViewId nextView;

    /* The view id of the previous view.*/
    public ViewId prevView;

    //TODO change ViewId to ClientView and adjust accordingly
    /* The master list of the view ids.*/
    public RootListener master;

    public String myID;


    /* The constructor of the Model class. */
    public Model(String modelID){
        this.myID = modelID;
    }

    /**
     * Add a view to the master list.
     * @param view the view id to add to the list
     */
    public void addMaster(RootListener view){
        this.master = view;
    }

    /**
     * Notify the master list that a change has been made.
     */
    public void notifyMaster(){
        master.notifyRoot(this.myID);
    }

    /**
     * Set the next view id.
     * @param id the view id of the next view
     */
    public void setNextView(ViewId id){
        nextView = id;
    }

    /**
     * Set the previous view id.
     * @param id the view id of the previous view
     */
    public void setPrevView(ViewId id){
        prevView = id;
    }

    public void setViews(ViewId prevId, ViewId nextId){
        setPrevView(prevId);
        setNextView(nextId);
        notifyMaster();
    }

    public ViewId getPrevView() {
        return prevView;
    }

    public ViewId getNextView(){
        return nextView;
    }
}
