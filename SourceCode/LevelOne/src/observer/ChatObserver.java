package observer;

/**
 * The ChatObserver interface should be implemented by any class that wishes to be notified of updates from a ChatObservable.
 */
public interface ChatObserver {
    /**
     * This method is called when the observed object is changed.
     * 
     * @param message The update message sent to the observers.
     */
    void update(String message);
}
