package entity;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import items.ItemTemplate;

/**
 * The NPCType enum represents different types of NPCs in the game and their corresponding attributes.
 * Each NPCType has associated dialogues, a searched item, and inventory items.
 */
public enum NPCType {
    APPLEQUESTGIVER("data/dialogues/applequestgiver/first.txt", "data/dialogues/applequestgiver/second.txt", ItemTemplate.APPLE, new ItemTemplate[]{ItemTemplate.SWORD, ItemTemplate.FORESTKEY}),
    ENDQUESTGIVER("data/dialogues/endquestgiver/first.txt", "data/dialogues/endquestgiver/second.txt", ItemTemplate.KNIFE, new ItemTemplate[]{ItemTemplate.WIN});

    private String firstDialogue;
    private String secondDialogue;
    private ItemTemplate searchedItem;
    private ItemTemplate[] inventoryItems;

    /**
     * Constructs an NPCType with the specified dialogue file paths, searched item, and inventory items.
     * 
     * @param firstDialogueFilePath  The file path to the first dialogue.
     * @param secondDialogueFilePath The file path to the second dialogue.
     * @param searchedItem           The item searched by the NPC.
     * @param inventoryItems         The items in the NPC's inventory.
     */
    NPCType(String firstDialogueFilePath, String secondDialogueFilePath, ItemTemplate searchedItem, ItemTemplate[] inventoryItems) {
        try {
            this.firstDialogue = new String(Files.readAllBytes(Paths.get(firstDialogueFilePath)));
            this.secondDialogue = new String(Files.readAllBytes(Paths.get(secondDialogueFilePath)));
        } catch (IOException e) {
            e.printStackTrace();
            this.firstDialogue = "Error loading dialogue";
            this.secondDialogue = "Error loading dialogue";
        }
        this.searchedItem = searchedItem;
        this.inventoryItems = inventoryItems;
    }

    /**
     * Constructs a default NPCType with default dialogues.
     */
    NPCType() {
        this.firstDialogue = "Default first dialogue";
        this.secondDialogue = "Default second dialogue";
    }

    /**
     * Gets the first dialogue of the NPCType.
     * 
     * @return The first dialogue of the NPCType.
     */
    public String getFirstDialogue() {
        return firstDialogue;
    }

    /**
     * Gets the second dialogue of the NPCType.
     * 
     * @return The second dialogue of the NPCType.
     */
    public String getSecondDialogue() {
        return secondDialogue;
    }

    /**
     * Gets the item searched by the NPC.
     * 
     * @return The item searched by the NPC.
     */
    public ItemTemplate getSearchedItem() {
        return searchedItem;
    }

    /**
     * Gets the inventory items of the NPC.
     * 
     * @return The inventory items of the NPC.
     */
    public ItemTemplate[] getInventoryItems() {
        return inventoryItems;
    }

    /**
     * Loads dialogues from the specified file paths.
     * 
     * @param firstDialogueFilePath  The file path to the first dialogue.
     * @param secondDialogueFilePath The file path to the second dialogue.
     */
    public void loadDialogues(String firstDialogueFilePath, String secondDialogueFilePath) {
        try {
            this.firstDialogue = new String(Files.readAllBytes(Paths.get(firstDialogueFilePath)));
            this.secondDialogue = new String(Files.readAllBytes(Paths.get(secondDialogueFilePath)));
        } catch (IOException e) {
            e.printStackTrace();
            this.firstDialogue = "Error loading dialogue";
            this.secondDialogue = "Error loading dialogue";
        }
    }
}
