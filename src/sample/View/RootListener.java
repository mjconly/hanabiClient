package sample.View;

/**
 * This interface is implemented by the RootView and will
 * ensure that the root view has the notifyRoot method
 * which can be called from by the models as root will subscribe to
 * them
 */
public interface RootListener {
    public void notifyRoot(String modelName);
}
