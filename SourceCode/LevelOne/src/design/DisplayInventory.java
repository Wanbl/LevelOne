package design;

import javafx.scene.layout.GridPane;
import items.PlayerInventory;
import items.Item;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.geometry.Insets;
import observer.Observer;

/**
 * DisplayInventory is responsible for rendering the player's inventory in a GridPane.
 * It updates the inventory display whenever changes occur.
 */
public class DisplayInventory implements Observer {
    private GridPane inventoryGrid;
    private StackPane selectedPane;
    private PlayerInventory inventory;
    private int selectedRow = -1;
    private int selectedCol = -1;

    /**
     * Constructs a DisplayInventory instance.
     *
     * @param inventory The PlayerInventory object.
     */
    public DisplayInventory(PlayerInventory inventory) {
        this.inventory = inventory;
        this.inventoryGrid = new GridPane();
        updateInventoryGrid();
    }

    /**
     * Creates a StackPane for each inventory slot.
     *
     * @param row The row of the inventory grid.
     * @param col The column of the inventory grid.
     * @return The StackPane representing the inventory slot.
     */
    private StackPane createInventoryPane(int row, int col) {
        StackPane pane = new StackPane();
        ImageView image = new ImageView();
        Item item = inventory.getItem(row, col);

        if (item == null) {
            image.setImage(new Image("file:data/templates/items/default.png"));
            pane.getChildren().add(image);
        } else {
            image.setImage(new Image("file:" + item.getTemplate().getSpriteName()));

            // Create a label to display the durability
            Label durabilityLabel = new Label(item.getDurability() + "/" + item.getMaxDurability());
            durabilityLabel.setStyle("-fx-background-color: rgba(255, 255, 255, 0.7); -fx-text-fill: black; -fx-padding: 2px;");
            StackPane.setMargin(durabilityLabel, new Insets(5));
            pane.getChildren().add(image);
            pane.getChildren().add(durabilityLabel);
        }

        pane.setOnMouseClicked(event -> handleItemClick(event, row, col));
        return pane;
    }

    /**
     * Handles item click events for selecting and moving items in the inventory.
     *
     * @param event The MouseEvent object.
     * @param row The row of the clicked item.
     * @param col The column of the clicked item.
     */
    private void handleItemClick(MouseEvent event, int row, int col) {
        if (selectedPane != null) {
            selectedPane.setBorder(null);
        }

        StackPane pane = (StackPane) event.getSource();
        if (selectedRow == -1 && selectedCol == -1) {
            // First click: select the item
            selectedRow = row;
            selectedCol = col;
            selectedPane = pane;
            selectedPane.setBorder(new Border(new BorderStroke(
                Color.BLUE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderStroke.THICK)));
        } else {
            // Second click: move the item
            inventory.moveItem(selectedRow, selectedCol, row, col);
            selectedRow = -1;
            selectedCol = -1;
            updateInventoryGrid();
        }
        System.out.println("DisplayInventory: handleItemClick(): Item clicked at row " + row + " col " + col);
    }

    /**
     * Updates the inventory grid display.
     */
    public void updateInventoryGrid() {
        inventoryGrid.getChildren().clear();
        for (int row = 0; row < inventory.getRows(); row++) {
            for (int col = 0; col < inventory.getCols(); col++) {
                StackPane pane = createInventoryPane(row, col);
                inventoryGrid.add(pane, col, row);
                GridPane.setMargin(pane, new Insets(5));
                if (row == 0 && col == inventory.getSelectedSlot()) {
                    selectItem(pane);
                }
            }
        }
        System.out.println("DisplayInventory: updateInventoryGrid(): Inventory grid updated");
    }

    /**
     * Highlights the selected inventory item.
     *
     * @param pane The StackPane representing the selected item.
     */
    private void selectItem(StackPane pane) {
        if (selectedPane != null) {
            selectedPane.setBorder(null);
        }
        selectedPane = pane;
        selectedPane.setBorder(new Border(new BorderStroke(
            Color.YELLOW, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderStroke.THICK)));
    }

    /**
     * Selects an item by its index.
     *
     * @param index The index of the item to select.
     */
    public void selectItem(int index) {
        inventory.setSelectedItem(index);
        updateInventoryGrid();
    }

    /**
     * Selects the next item in the inventory.
     */
    public void selectNextItem() {
        inventory.nextSlot();
        updateInventoryGrid();
    }

    /**
     * Selects the previous item in the inventory.
     */
    public void selectPreviousItem() {
        inventory.previousSlot();
        updateInventoryGrid();
    }

    /**
     * Gets the inventory grid pane.
     *
     * @return The inventory grid GridPane.
     */
    public GridPane getInventoryGrid() {
        return inventoryGrid;
    }

    /**
     * Updates the inventory display when the observed object changes.
     */
    @Override
    public void update() {
        updateInventoryGrid();
    }
}
