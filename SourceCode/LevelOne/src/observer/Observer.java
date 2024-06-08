package observer;

/**
 * The Observer interface should be implemented by any class that wishes to be notified of updates from an Observable.
 */
public interface Observer {
    /**
     * This method is called when the observed object is changed.
     */
    void update();
}
