package sample.View;

import sample.Controller.ClientController;
import sample.Model.Model;


/**
 * The ModelListener interface is implemented by all Views. This allows
 * each View to implement the interface methods accordingly and ensures
 * that each View will have these methods.
 *
 * @version 1.0
 * @author Group B3
 */

public interface ModelListener {
    /**
     * Responsible for setting the controller attribute in a Class
     * implementing ModelListener
     * @param control an instance of ClientController
     */
    public void setController(ClientController control);


    /**
     * Responsible for setting the model attribute in a Class
     * implementing ModelListener
     * @param model an instance of Model (Before, During, After)
     */
    public void setModel(Model model);


    /**
     * Responsible for updating the layout of a View when Model
     * data changes
     */
    public void onChange();
}
