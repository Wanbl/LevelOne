package chat;

import observer.ChatObservable;
import observer.ChatObserver;

/**
 * The GlobalChat class manages the global chat messages and notifies observers of any changes.
 */
public class GlobalChat {
    // The chat message history
    private static String chat = "Welcome to LevelOne !\n";
    // The observable for chat messages
    private static ChatObservable chatObservable = new ChatObservable();

    /**
     * Returns the current chat message history.
     * 
     * @return The chat message history.
     */
    public static String getChat() {
        return chat;
    }

    /**
     * Adds an observer to the chat.
     * 
     * @param observer The observer to be added.
     */
    public static void addObserver(ChatObserver observer) {
        chatObservable.addObserver(observer);
    }

    /**
     * Removes an observer from the chat.
     * 
     * @param observer The observer to be removed.
     */
    public static void removeObserver(ChatObserver observer) {
        chatObservable.removeObserver(observer);
    }

    /**
     * Adds a message to the chat and notifies all observers.
     * 
     * @param message The message to be added.
     */
    public static void addMessage(String message) {
        chat += message + "\n";
        System.out.println("GLOBALCHAT : ADDMESSAGE() : Chat message added: " + message);
        System.out.println("GLOBALCHAT : ADDMESSAGE() : Chat is now: " + chat);
        chatObservable.notifyObservers(chat);
    }
}
