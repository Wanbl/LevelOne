package items;

import chat.GlobalChat;

/**
 * The PlayerInventory class represents the player's inventory,
 * including the hotbar and selected item functionalities.
 */
public class PlayerInventory extends Inventory {
    private Item[] hotBar;
    private Item selectedItem;
    private int selectedSlot;

    /**
     * Constructs a PlayerInventory with default rows and columns.
     */
    public PlayerInventory() {
        super(1, 5);
        this.hotBar = this.getItems()[0];
        this.selectedSlot = 0;
        this.selectedItem = this.hotBar[this.selectedSlot];
    }
    
    /**
     * Updates the selected item in the hotbar.
     */
    public void updateSelectedItem() {
        this.selectedItem = this.hotBar[this.selectedSlot];
    }

    /**
     * Returns the hotbar items.
     * 
     * @return An array of hotbar items.
     */
    public Item[] getHotBar() {
        return this.hotBar;
    }

    /**
     * Sets the selected item in the hotbar.
     * 
     * @param index The index of the selected item.
     */
    public void setSelectedItem(int index) {
        this.selectedItem = this.hotBar[index];
        this.selectedSlot = index;
    }

    /**
     * Returns the currently selected item.
     * 
     * @return The currently selected item.
     */
    public Item getSelectedItem() {
        return this.selectedItem;
    }

    /**
     * Returns the index of the selected slot.
     * 
     * @return The index of the selected slot.
     */
    public int getSelectedSlot() {
        return this.selectedSlot;
    }

    /**
     * Selects the next slot in the hotbar.
     */
    public void nextSlot() {
        if (this.selectedSlot < 4) {
            this.selectedSlot++;
        } else {
            this.selectedSlot = 0;
        }
        this.selectedItem = this.hotBar[this.selectedSlot];
        printInventoryState();
        printItemDescription();
    }

    /**
     * Selects the previous slot in the hotbar.
     */
    public void previousSlot() {
        if (this.selectedSlot > 0) {
            this.selectedSlot--;
        } else {
            this.selectedSlot = 4;
        }
        this.selectedItem = this.hotBar[this.selectedSlot];
        printInventoryState();
        printItemDescription();
    }
    
    @Override
    public void addItem(Item item) {
        super.addItem(item);
        this.updateSelectedItem();
    }

    /**
     * Prints the current state of the inventory to the console.
     */
    private void printInventoryState() {
        System.out.println("PLAYERINVENTORY : PRINTINVENTORYSTATE() : Selected slot: " + this.selectedSlot + " Selected item: " + (this.selectedItem != null ? this.selectedItem.getName() : "Empty"));
        System.out.println("PLAYERINVENTORY : PRINTINVENTORYSTATE() : Hotbar: " + this.hotBar);

        // Print all Hotbar
        for (int k = 0; k < this.hotBar.length; k++) {
            if (this.hotBar[k] != null) {
                System.out.println("PLAYERINVENTORY : PRINTINVENTORYSTATE() : Item: " + this.hotBar[k].getName() + " at " + k);
            } else {
                System.out.println("PLAYERINVENTORY : PRINTINVENTORYSTATE() : Item: Empty at " + k);
            }
        }

        // Print all inventory
        System.out.println("PLAYERINVENTORY : PRINTINVENTORYSTATE() : Inventory: ");
        for (int i = 0; i < this.getRows(); i++) {
            for (int j = 0; j < this.getCols(); j++) {
                Item item = this.getItem(i, j);
                if (item != null) {
                    System.out.println("PLAYERINVENTORY : PRINTINVENTORYSTATE() : Item: " + item.getName() + " at " + i + ", " + j);
                } else {
                    System.out.println("PLAYERINVENTORY : PRINTINVENTORYSTATE() : Item: Empty at " + i + ", " + j);
                }
            }
        }
    }
    
    /**
     * Prints the description of the currently selected item to the global chat.
     */
    public void printItemDescription() {
        if (selectedItem != null) {
            switch (selectedItem.getTemplate().getType()) {
                case FOOD:
                    GlobalChat.addMessage("Food : Gives you 10 health points");
                    break;
                case WEAPON:
                    GlobalChat.addMessage("Weapon : Deals more damage to monsters");
                    break;
                case FORESTKEY:
                    GlobalChat.addMessage("ForestKey : Unlocks the forest door");
                    break;
                case WIN:
                    GlobalChat.addMessage("Win : Makes you win the game");
                    break;
                default:
                    System.out.println("PLAYERINVENTORY : PRINTITEMDESCRIPTION() : Item selected");
                    break;
            }
        }
    }
}
