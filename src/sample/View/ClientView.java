package sample.View;

import javafx.scene.layout.VBox;
/**
 * ClientView is an abstract class that extends a JavaFX UI component
 * and implements model listener. All Views will extend this abstract
 * class allowing them to be moved in and out of the main Scene and to
 * participate in the MVC architecture
 *
 * @version 1.0
 * @author Group B3
 */
public abstract class ClientView extends VBox implements ModelListener {
    /** an enumerated ViewId that is unique for each ClientView */
    private ViewId myId;


    /**
     * Called when a class extending Client View is instantiated,
     * assigns a ViewId to myId.
     * @param id a ViewId
     */
    public ClientView(ViewId id){
        this.myId = id;

    }


    /**
     * Returns the ViewId of the ClientView
     * @return a ViewId
     */
    public ViewId getMyId(){
        return this.myId;
    }
}
