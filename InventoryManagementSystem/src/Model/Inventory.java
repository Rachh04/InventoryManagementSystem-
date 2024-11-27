package Model;

import java.util.*;

public class Inventory {
    private final Map<String, InventoryItem> itemsById = new HashMap<>();
    private final Map<String, PriorityQueue<InventoryItem>> categoryMap = new HashMap<>();
    private final int restockThreshold;

    public Inventory(int restockThreshold) {
        this.restockThreshold = restockThreshold;
    }

    public boolean addItem(InventoryItem item) {
        if (itemsById.containsKey(item.getId())) return false;

        itemsById.put(item.getId(), item);
        categoryMap.putIfAbsent(item.getCategory(), new PriorityQueue<>());
        categoryMap.get(item.getCategory()).add(item);

        checkRestock(item);
        return true;
    }

    public boolean updateItemQuantity(String id, int newQuantity) {
        InventoryItem item = itemsById.get(id);
        if (item == null) return false;

        categoryMap.get(item.getCategory()).remove(item);
        item.setQuantity(newQuantity);
        categoryMap.get(item.getCategory()).add(item);

        checkRestock(item);
        return true;
    }

    public InventoryItem deleteItem(String id) {
        InventoryItem removed = itemsById.remove(id);
        if (removed != null) {
            categoryMap.get(removed.getCategory()).remove(removed);
        }
        return removed;
    }

    public InventoryItem searchItem(String id) {
        return itemsById.get(id);
    }

    public List<InventoryItem> getItemsByCategory(String category) {
        PriorityQueue<InventoryItem> queue = categoryMap.get(category);
        return queue == null ? Collections.emptyList() : new ArrayList<>(queue);
    }

    public List<InventoryItem> getTopKItems(int k) {
        PriorityQueue<InventoryItem> allItems = new PriorityQueue<>(itemsById.values());
        List<InventoryItem> topK = new ArrayList<>();
        while (!allItems.isEmpty() && topK.size() < k) {
            topK.add(allItems.poll());
        }
        return topK;
    }

    public void mergeInventory(Inventory other) {
        for (InventoryItem item : other.itemsById.values()) {
            if (itemsById.containsKey(item.getId())) {
                if (itemsById.get(item.getId()).getQuantity() < item.getQuantity()) {
                    updateItemQuantity(item.getId(), item.getQuantity());
                }
            } else {
                addItem(item);
            }
        }
    }

    private void checkRestock(InventoryItem item) {
        if (item.getQuantity() < restockThreshold) {
            System.out.println("-------------Restock Warning------------------");
            System.out.println("Restock needed for item: " + item.getName() );
            System.out.println("Current Stock is : " + item.getQuantity());
            System.out.println("Threshold Stock is : " + this.restockThreshold );
        }
    }
}
