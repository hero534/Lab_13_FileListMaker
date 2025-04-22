import java.util.ArrayList;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static ArrayList<String> list = new ArrayList<>();
    private static Scanner in = new Scanner(System.in);
    public static void main(String[] args) {
        String choice;
        do {
            displayListAndMenu();
            choice = SafeInput.getRegExString(in, "Enter your choice (A, D, I, P, Q)", "[AaDdIiPpQq]");
            executeChoice(choice);
        } while (!choice.equalsIgnoreCase("Q"));
    }

    private static void displayListAndMenu() {
        System.out.println("\nCurrent List:");
        displayNumberedList();
        System.out.println("\nMenu:");
        System.out.println("A - Add an item");
        System.out.println("D - Delete an item");
        System.out.println("I - Insert an item");
        System.out.println("P - Print the list");
        System.out.println("Q - Quit the program");
    }

    private static void displayNumberedList() {
        if (list.isEmpty()) {
            System.out.println("List is empty.");
        } else {
            for (int i = 0; i < list.size(); i++) {
                System.out.println((i + 1) + ". " + list.get(i));
            }
        }
    }

    private static void executeChoice(String choice) {
        if (choice.equals("A")) {
            addItem();
        } else if (choice.equals("D")) {
            deleteItem();
        } else if (choice.equals("I")) {
            insertItem();
        } else if (choice.equals("P")) {
            printList();
        } else if (choice.equals("Q")) {
            quitProgram();
        }
    }

    private static void addItem() {
        System.out.print("Enter item to add: ");
        String item = in.nextLine();
        list.add(item);
        System.out.println("Item added.");
    }

    private static void deleteItem() {
        if (list.isEmpty()) {
            System.out.println("List is empty. Nothing to delete.");
            return;
        }
        displayNumberedList();
        int itemNumber = SafeInput.getRangedInt(in, "Enter item number to delete: ", 1, list.size());
        list.remove(itemNumber - 1);
        System.out.println("Item deleted.");
    }

    private static void insertItem() {
        String item = SafeInput.getNonZeroLenString(in, "Enter item to insert: ");
        int index = SafeInput.getRangedInt(in, "Enter position to insert at: ", 1, list.size() + 1);
        list.add(index - 1, item);
        System.out.println("Item inserted.");
    }

    private static void printList() {
        if (list.isEmpty()) {
            System.out.println("List is empty.");
        } else {
            System.out.println("Current List:");
            for (String item : list) {
                System.out.println("- " + item);
            }
        }
    }

    private static void quitProgram() {
        if (SafeInput.getYNConfirm(in, "Are you sure you want to quit?")) {
            System.out.println("Exiting program.");
            System.exit(0);
        }
    }
}