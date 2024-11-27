package Controller;

import Model.Inventory;
import Model.InventoryItem;
import View.InventoryView;

import java.util.List;

public class InventoryController {
    private final Inventory inventory;
    private final InventoryView view;

    public InventoryController(Inventory inventory, InventoryView view) {
        this.inventory = inventory;
        this.view = view;
    }

    public void addItem(String id, String name, String category, int quantity) {
        InventoryItem item = new InventoryItem(id, name, category, quantity);
        if (inventory.addItem(item)) {
            view.displayMessage("Item added successfully.");
        } else {
            view.displayMessage("Item ID already exists.");
        }
    }

    public void updateItemQuantity(String id, int newQuantity) {
        if (inventory.updateItemQuantity(id, newQuantity)) {
            view.displayMessage("Item quantity updated.");
        } else {
            view.displayMessage("Item not found.");
        }
    }

    public void deleteItem(String id) {
        InventoryItem removed = inventory.deleteItem(id);
        if (removed != null) {
            view.displayMessage("Item removed: " + removed);
        } else {
            view.displayMessage("Item not found.");
        }
    }

    public void searchItem(String id) {
        InventoryItem item = inventory.searchItem(id);
        if (item != null) {
            view.displayMessage(item.toString());
        } else {
            view.displayMessage("Item not found.");
        }
    }

    public void displayItemsByCategory(String category) {
        List<InventoryItem> items = inventory.getItemsByCategory(category);
        view.displayItems(items);
    }

    public void displayTopKItems(int k) {
        List<InventoryItem> items = inventory.getTopKItems(k);
        view.displayItems(items);
    }

    public void mergeInventory(Inventory other) {
        inventory.mergeInventory(other);
        view.displayMessage("Inventories merged.");
    }
}
