import Controller.InventoryController;
import Model.Inventory;
import View.InventoryView;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Inventory inventory = new Inventory(10);
        InventoryView view = new InventoryView();
        InventoryController controller = new InventoryController(inventory, view);

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        System.out.println("\n\n\n\n----------------------------------- Warehouse Inventory Management System -----------------------------------------");

        while (!exit) {
            try {
                System.out.println("\nChoose an option:");
                System.out.println("1. Add an item");
                System.out.println("2. Update item quantity");
                System.out.println("3. Delete an item");
                System.out.println("4. Search for an item");
                System.out.println("5. View items by category");
                System.out.println("6. View top K items");
                System.out.println("7. Exit");

                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1 -> {
                        System.out.print("Enter item ID: ");
                        String id = scanner.nextLine().trim();
                        System.out.print("Enter item name: ");
                        String name = scanner.nextLine().trim();
                        System.out.print("Enter item category: ");
                        String category = scanner.nextLine().trim();
                        System.out.print("Enter item quantity: ");
                        int quantity = Integer.parseInt(scanner.nextLine());

                        if (quantity < 0) {
                            System.out.println("Quantity cannot be negative.");
                            break;
                        }

                        controller.addItem(id, name, category, quantity);
                    }
                    case 2 -> {
                        System.out.print("Enter item ID: ");
                        String id = scanner.nextLine().trim();
                        System.out.print("Enter new quantity: ");
                        int quantity = Integer.parseInt(scanner.nextLine());

                        if (quantity < 0) {
                            System.out.println("Quantity cannot be negative.");
                            break;
                        }

                        controller.updateItemQuantity(id, quantity);
                    }
                    case 3 -> {
                        System.out.print("Enter item ID: ");
                        String id = scanner.nextLine().trim();
                        controller.deleteItem(id);
                    }
                    case 4 -> {
                        System.out.print("Enter item ID: ");
                        String id = scanner.nextLine().trim();
                        controller.searchItem(id);
                    }
                    case 5 -> {
                        System.out.print("Enter category: ");
                        String category = scanner.nextLine().trim();
                        controller.displayItemsByCategory(category);
                    }
                    case 6 -> {
                        System.out.print("Enter the number of top items to view (K): ");
                        int k = Integer.parseInt(scanner.nextLine());

                        if (k <= 0) {
                            System.out.println("K must be a positive integer.");
                            break;
                        }

                        controller.displayTopKItems(k);
                    }
                    case 7 -> {
                        System.out.println("Exiting the system. Goodbye!");
                        exit = true;
                    }
                    default -> System.out.println("Invalid option. Please choose a valid option.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter numeric values where required.");
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }

        scanner.close();
    }
}
