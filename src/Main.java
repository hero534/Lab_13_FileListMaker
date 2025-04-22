import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Main {
    private static ArrayList<String> list = new ArrayList<>();
    private static Scanner in = new Scanner(System.in);
    private static boolean needsToBeSaved = false;
    private static String fileName = "";
    public static void main(String[] args) {
        String choice;
        do {
            displayListAndMenu();
            choice = SafeInput.getRegExString(in, "Enter your choice (A, D, I, V, M, O, S, C, Q)", "[AaDdIiVvMmOoSsCcQq]");
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
        System.out.println("V - Print the list");
        System.out.println("M - Move an item");
        System.out.println("O - Open a list file from disk");
        System.out.println("S - Save the current list file to disk");
        System.out.println("C - Clear removes all the elements from the current list");
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
        } else if (choice.equals("V")) {
            printList();
        } else if (choice.equals("Q")) {
            quitProgram();
        } else if (choice.equals("M")) {
            moveItem();
        } else if (choice.equals("O")) {
            openFile();
        } else if (choice.equals("S")) {
            saveFile();
        } else if (choice.equals("C")) {
            clearList();
        }
    }

    private static void addItem() {
        System.out.print("Enter item to add: ");
        String item = in.nextLine();
        list.add(item);
        needsToBeSaved = true;
    }

    private static void deleteItem() {
        System.out.print("Enter index of item to delete: ");
        int index = in.nextInt();
        in.nextLine();
        if (index >= 0 && index < list.size()) {
            list.remove(index);
            needsToBeSaved = true;
        } else {
            System.out.println("Invalid index!");
        }
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

    public static void moveItem() {
        System.out.println("Enter index of item to move:");
        int fromIndex = in.nextInt();
        System.out.println("Enter new position:");
        int toIndex = in.nextInt();
            if (fromIndex >= 0 && fromIndex < list.size() && toIndex >= 0 && toIndex <= list.size()) {
                String item = list.remove(fromIndex);
                list.add(toIndex, item);
                needsToBeSaved = true;
            } else {
                System.out.println("Invalid move!");
        }
    }

    private static void saveFile() {
        if (fileName.isEmpty()) {
            System.out.print("Enter filename to save: ");
            fileName = in.nextLine();
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName + ".txt"))) {
            for (String item : list) {
                writer.println(item);
            }
            needsToBeSaved = false;
            System.out.println("List saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    private static void promptSaveIfDirty() {
        if (needsToBeSaved) {
            System.out.print("Unsaved changes detected! Save before proceeding? (Y/N): ");
            String response = in.nextLine().trim().toUpperCase();  // Capture full response

            if (response.equals("Y")) {
                saveFile();
            } else {
                System.out.println("Warning: Unsaved changes will be lost!");
            }
        }
    }

    private static void openFile() {
        promptSaveIfDirty();

        System.out.print("Enter filename to open: ");
        fileName = in.nextLine();

        try {
            list.clear();
            list.addAll(Files.readAllLines(Paths.get(fileName + ".txt")));
            needsToBeSaved = false;
            System.out.println("File loaded successfully.");
        } catch (IOException e) {
            System.out.println("Error opening file: " + e.getMessage());
        }
    }

    private static void quitProgram() {
        promptSaveIfDirty();

        if (SafeInput.getYNConfirm(in, "Are you sure you want to quit?")) {
            System.out.println("Exiting program.");
            System.exit(0);
        }
    }

    private static void clearList() {
        list.clear();
        needsToBeSaved = true;
        System.out.println("List cleared.");
    }
}