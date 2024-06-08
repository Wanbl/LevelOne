package design;

import observer.ChatObserver;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import chat.GlobalChat;

/**
 * The DisplayChat class is responsible for displaying chat messages in a scrollable pane.
 * It implements the ChatObserver interface to update the chat display when new messages are added.
 */
public class DisplayChat implements ChatObserver {
    private Label chatText;
    private ScrollPane chatScrollPane;

    /**
     * Constructs a DisplayChat instance.
     */
    public DisplayChat() {
        chatText = new Label();
        chatText.setText(GlobalChat.getChat());
        chatScrollPane = new ScrollPane();
        chatScrollPane.setContent(chatText);
        chatScrollPane.setFitToWidth(true); // Adjust the width of the TextArea to fit the ScrollPane
        chatScrollPane.setPrefHeight(250); // Set the preferred height
    }

    /**
     * Gets the Label displaying the chat text.
     * 
     * @return The chat text Label.
     */
    public Label getChatText() {
        return chatText;
    }

    /**
     * Gets the ScrollPane containing the chat text.
     * 
     * @return The chat ScrollPane.
     */
    public ScrollPane getChatScrollPane() {
        return chatScrollPane;
    }

    /**
     * Updates the chat text with the new message and scrolls to the bottom.
     * 
     * @param message The new chat message.
     */
    public void updateChat(String message) {
        chatText.setText(message);
        scrollToBottom();
    }

    /**
     * Overrides the update method from the ChatObserver interface to update the chat display.
     * 
     * @param message The new chat message.
     */
    @Override
    public void update(String message) {
        updateChat(message);
    }

    /**
     * Scrolls the chat ScrollPane to the bottom.
     */
    private void scrollToBottom() {
        Platform.runLater(() -> {
            chatScrollPane.layout();
            chatScrollPane.setVvalue(1.0); // Scroll to the end
        });
    }
}
