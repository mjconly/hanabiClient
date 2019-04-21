package sample.View;

import com.sun.security.ntlm.Client;
import javafx.application.Platform;
import sample.Controller.ClientController;
import sample.Model.Model;
import java.util.ArrayList;


/**
 * The main JavaFX scene maintains a reference to the RootView during
 * the runtime of the application. This allows RootView to act as a
 * container, adding and removing Views from itself to display to the
 * user.
 *
 * @version 1.0
 * @author Group B3
 */
public class RootView extends ClientView implements RootListener {
    /** List of all ClientViews that can be added/removed from RootView **/
    private ArrayList<ClientView> myChildren;

    /** List of all Models that RootView can Listen to **/
    private ArrayList<Model> clientModels;

    /** The current Model that RootView is Listening to **/
    private Model currentModel;

    private ClientView theGameView;


    /**
     * RootView Class constructor, creates a new instance of
     * RootView and assigns ViewId attribute
     * @param id a ViewId
     */
    public RootView(ViewId id){
        super(id);
        myChildren = new ArrayList<>();
        clientModels = new ArrayList<>();
    }


    /**
     * Adds a ClientView to the myChildren list
     * @param aChild a ClientView
     */
    public void addChild(ClientView aChild){
        if (aChild.getMyId() == ViewId.GAME){
            theGameView = aChild;
        }
        this.myChildren.add(aChild);
    }


    /**
     * Adds a Model to the clientModel list
     * @param model a Model (Before, During, After)
     */
    public void addModel(Model model){
        this.clientModels.add(model);
    }


    /**
     * Required since ClientView implements ModelListener interface,
     * but RootView does not have a Controller. This method is silent.
     * @param control an instance of ClientController
     */
    @Override
    public void setController(ClientController control) {
        //No controller attribute -- silent method
    }


    /**
     * Sets the current Model attribute
     * @param model an instance of Model (Before, During, After)
     */
    @Override
    public void setModel(Model model) {
        this.currentModel = model;
    }

    /**
     * Identifies the model that has notified the RootView of a change
     * @param modelName
     */
    @Override
    public void notifyRoot(String modelName) {
        //Add models in order (before, during, after)
        if (modelName.equals("Before")){
            this.setModel(clientModels.get(0));
        }
        else if (modelName.equals("During")){
            this.setModel(clientModels.get(1));
        }
        else{
            this.setModel(clientModels.get(2));
        }

        this.onChange();
    }

    /**
     * Loop over each of the models in the clientModels list and identify
     * the model with a nextViewId attribute that is not set to NOT_IN_USE,
     * select this ViewId and find a ClientView in the myChildren list that
     * matches and update child node accordingly
     */
    @Override
    public void onChange() {
        ViewId toGet = this.currentModel.getNextView();
        System.out.println("Getting view " + toGet);

        if (toGet == ViewId.ACTIONS){
            for (ClientView cv : myChildren){
                if (cv.getMyId() == toGet){
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            getChildren().clear();
                            theGameView.onChange();
                            cv.onChange();
                            ((GameView)theGameView).setRightNode(cv);
                            getChildren().add(theGameView);

                        }
                    });
                }
            }
        }
        else if (toGet == ViewId.PLAY){
            for (ClientView cv : myChildren){
                if (cv.getMyId() == toGet){
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            cv.onChange();
                            ((GameView)theGameView).setRightNode(cv);
                        }
                    });
                }
            }
        }
        else if (toGet == ViewId.DISCARD){
            for (ClientView cv : myChildren){
                if (cv.getMyId() == toGet){
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            cv.onChange();
                            ((GameView)theGameView).setRightNode(cv);
                        }
                    });
                }
            }
        }
        else if (toGet == ViewId.INFO){
            for (ClientView cv : myChildren){
                if (cv.getMyId() == toGet){
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            cv.onChange();
                            ((GameView)theGameView).setRightNode(cv);
                        }
                    });
                }
            }
        }
        else if (toGet == ViewId.PILE){
            for (ClientView cv : myChildren){
                if (cv.getMyId() == toGet){
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            cv.onChange();
                            ((GameView)theGameView).setRightNode(cv);
                        }
                    });
                }
            }
        }
        else if (toGet == ViewId.LOG){
            for (ClientView cv : myChildren){
                if (cv.getMyId() == toGet){
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            cv.onChange();
                            ((GameView)theGameView).setRightNode(cv);
                        }
                    });
                }
            }
        }
        else {
            for (ClientView cv : myChildren) {
                if (cv.getMyId() == toGet) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            cv.onChange();
                            getChildren().clear();
                            getChildren().add(cv);
                        }
                    });
                }
            }
        }
    }
}
