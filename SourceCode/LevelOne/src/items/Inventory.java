package items;

import java.util.ArrayList;
import java.util.List;
import observer.Observer;

/**
 * The Inventory class represents an inventory that can hold items in a grid.
 */
public class Inventory {
    private int cols;
    private int rows;
    private int size;
    private int sizeModifier;
    private int nbSlotUsed;
    private Item[][] items;
    private List<Observer> observers = new ArrayList<>();

    /**
     * Constructs an Inventory with the specified number of rows and columns.
     * 
     * @param rows The number of rows in the inventory.
     * @param cols The number of columns in the inventory.
     */
    public Inventory(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.setSize(cols * rows);
        this.setSizeModifier(sizeModifier);
        this.items = new Item[rows][cols];
    }

    /**
     * Constructs a default Inventory with 10 rows and 5 columns.
     */
    public Inventory() {
        this(10, 5);
    }

    /**
     * Adds an observer to the inventory.
     * 
     * @param observer The observer to add.
     */
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    /**
     * Notifies all observers of changes to the inventory.
     */
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    /**
     * Removes an observer from the inventory.
     * 
     * @param observer The observer to remove.
     */
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    /**
     * Adds a new line to the inventory.
     */
    public void addLine() {
        Item[][] newItems = new Item[rows + 1][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                newItems[i][j] = items[i][j];
            }
        }
        this.rows++;
        this.size = cols * rows;
    }

    /**
     * Adds multiple new lines to the inventory.
     * 
     * @param nbLines The number of lines to add.
     */
    public void addLines(int nbLines) {
        for (int i = 0; i < nbLines; i++) {
            addLine();
        }
    }

    /**
     * Gets an item from the inventory at the specified row and column.
     * 
     * @param row The row of the item.
     * @param col The column of the item.
     * @return The item at the specified position, or null if there is no item.
     */
    public Item getItem(int row, int col) {
        return items[row][col];
    }

    /**
     * Gets an item from the inventory at the specified index.
     * 
     * @param index The index of the item.
     * @return The item at the specified index, or null if there is no item.
     */
    public Item getItem(int index) {
        int targetRow = index / cols;
        int targetCol = index % cols;
        return getItem(targetRow, targetCol);
    }

    /**
     * Sets an item in the inventory at the specified row and column.
     * 
     * @param row  The row of the item.
     * @param col  The column of the item.
     * @param item The item to set.
     */
    public void setItem(int row, int col, Item item) {
        items[row][col] = item;
    }

    /**
     * Sets an item in the inventory at the specified index.
     * 
     * @param index The index of the item.
     * @param item  The item to set.
     */
    public void setItem(int index, Item item) {
        int targetRow = index / cols;
        int targetCol = index % cols;
        items[targetRow][targetCol] = item;
    }

    /**
     * Adds an item to the first available slot in the inventory.
     * 
     * @param item The item to add.
     */
    public void addItem(Item item) {
        for (int i = 0; i < size; i++) {
            if (getItem(i) == null) {
                setItem(i, item);
                break;
            }
        }
        notifyObservers();
    }

    /**
     * Removes an item from the inventory at the specified row and column.
     * 
     * @param row The row of the item.
     * @param col The column of the item.
     */
    public void removeItem(int row, int col) {
        items[row][col] = null;
        notifyObservers();
    }

    /**
     * Removes an item from the inventory.
     * 
     * @param item The item to remove.
     */
    public void removeItem(Item item) {
        for (int i = 0; i < size; i++) {
            if (getItem(i) == item) {
                setItem(i, null);
                break;
            }
        }
        notifyObservers();
    }

    /**
     * Adds an item to the inventory using the specified item template.
     * 
     * @param template The item template to use for adding the item.
     */
    public void addItem(ItemTemplate template) {
        addItem(new Item(template, false));
    }

    /**
     * Adds an item to the inventory using the specified item template, with an option to make it unique.
     * 
     * @param template The item template to use for adding the item.
     * @param unique   Whether the item is unique.
     */
    public void addItem(ItemTemplate template, boolean unique) {
        addItem(new Item(template, unique));
    }

    /**
     * Gets the items in the inventory as a 2D array.
     * 
     * @return A 2D array of items.
     */
    public Item[][] getItems() {
        return items;
    }

    /**
     * Moves an item from one position in the inventory to another.
     * 
     * @param row    The current row of the item.
     * @param col    The current column of the item.
     * @param newRow The new row of the item.
     * @param newCol The new column of the item.
     */
    public void moveItem(int row, int col, int newRow, int newCol) {
        Item item = items[row][col];
        if (item != null) {
            Item newItem = items[newRow][newCol];
            if (newItem != null) {
                if (item.getTemplate() == newItem.getTemplate()) {
                    if (item.canDurabilityIncrease()) {
                        newItem.setDurability(item.getDurability() + newItem.getDurability());
                        if (newItem.getDurability() > newItem.getMaxDurability()) {
                            item.setDurability(newItem.getDurability() - newItem.getMaxDurability());
                            newItem.setDurability(newItem.getMaxDurability());
                        } else {
                            items[row][col] = null;
                        }
                    } else {
                        items[row][col] = newItem;
                        items[newRow][newCol] = item;
                    }
                } else {
                    items[row][col] = newItem;
                    items[newRow][newCol] = item;
                }
            } else {
                items[newRow][newCol] = item;
                items[row][col] = null;
            }
        }
    }

    /**
     * Gets the size of the inventory.
     * 
     * @return The size of the inventory.
     */
    public int getSize() {
        return size;
    }

    /**
     * Sets the size of the inventory.
     * 
     * @param size The new size of the inventory.
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * Gets the size modifier of the inventory.
     * 
     * @return The size modifier of the inventory.
     */
    public int getSizeModifier() {
        return sizeModifier;
    }

    /**
     * Sets the size modifier of the inventory.
     * 
     * @param sizeModifier The new size modifier of the inventory.
     */
    public void setSizeModifier(int sizeModifier) {
        this.sizeModifier = sizeModifier;
    }

    /**
     * Gets the number of slots used in the inventory.
     * 
     * @return The number of slots used in the inventory.
     */
    public int getNbSlotUsed() {
        return nbSlotUsed;
    }

    /**
     * Sets the number of slots used in the inventory.
     * 
     * @param nbSlotUsed The new number of slots used in the inventory.
     */
    public void setNbSlotUsed(int nbSlotUsed) {
        this.nbSlotUsed = nbSlotUsed;
    }

    /**
     * Constructs an Inventory with the specified size.
     * 
     * @param size The size of the inventory.
     */
    public Inventory(int size) {
        this.setSize(size);
    }

    /**
     * Gets the number of columns in the inventory.
     * 
     * @return The number of columns in the inventory.
     */
    public int getCols() {
        return cols;
    }

    /**
     * Gets the number of rows in the inventory.
     * 
     * @return The number of rows in the inventory.
     */
    public int getRows() {
        return rows;
    }

    /**
     * Uses an item in the inventory, updating its durability and removing it if broken.
     * 
     * @param item The item to use.
     */
    public void useItem(Item item) {
        item.updateDurability(-1);
        if (item.isBroken()) {
            removeItem(item);
        }
        notifyObservers();
    }

    /**
     * Gets the items in the inventory as a flat array.
     * 
     * @return An array of items in the inventory.
     */
    public Item[] getItemList() {
        Item[] itemList = new Item[size];
        int index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                itemList[index] = items[i][j];
                index++;
            }
        }
        return itemList;
    }

    /**
     * Removes an item from the inventory at the specified index.
     * 
     * @param index The index of the item to remove.
     */
    public void removeItem(int index) {
        int targetRow = index / cols;
        int targetCol = index % cols;
        removeItem(targetRow, targetCol);
        notifyObservers();
    }

    /**
     * Clears all items from the inventory.
     */
    public void clearInventory() {
        for (int i = 0; i < size; i++) {
            removeItem(i);
        }
    }
}
