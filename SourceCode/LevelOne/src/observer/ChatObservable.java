package observer;

import java.util.ArrayList;
import java.util.List;

/**
 * The ChatObservable class manages a list of ChatObserver objects and notifies them of changes.
 */
public class ChatObservable {
    private List<ChatObserver> observers = new ArrayList<>();

    /**
     * Adds an observer to the list of observers.
     * 
     * @param observer The observer to be added.
     */
    public void addObserver(ChatObserver observer) {
        observers.add(observer);
        System.out.println("CHATOBSERVABLE : ADDOBSERVER() : Observer added");
    }

    /**
     * Removes an observer from the list of observers.
     * 
     * @param observer The observer to be removed.
     */
    public void removeObserver(ChatObserver observer) {
        observers.remove(observer);
        System.out.println("CHATOBSERVABLE : REMOVEOBSERVER() : Observer removed");
    }

    /**
     * Notifies all observers of a new message.
     * 
     * @param message The message to be sent to all observers.
     */
    public void notifyObservers(String message) {
        for (ChatObserver observer : observers) {
            observer.update(message);
            System.out.println("CHATOBSERVABLE : NOTIFYOBSERVERS() : Observer notified with message: " + message);
        }
    }
}
