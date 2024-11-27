package View;

import Model.InventoryItem;

import java.util.List;

public class InventoryView {
    public void displayItems(List<InventoryItem> items) {
        for (InventoryItem item : items) {
            System.out.println(item);
        }
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }
}
