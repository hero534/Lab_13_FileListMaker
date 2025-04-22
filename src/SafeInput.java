import java.util.Scanner;

public class SafeInput {

    public static String getNonZeroLenString(Scanner pipe, String prompt)
    {
        String retString = "";
        do
        {
            System.out.print("\n" +prompt + ": ");
            retString = pipe.nextLine();
        }while(retString.length() == 0);

        return retString;

    }
    public static int getInt(Scanner pipe, String prompt) {
        int result = 0;
        boolean done = false;
        String trash = "";
        do {
            System.out.print(prompt);
            if (pipe.hasNextInt()) {
                result = pipe.nextInt();
                done = true;
            } else {
                trash = pipe.nextLine();
                System.out.println("Invalid input: " + trash + ", Please enter an integer.");
            }
        }
        while (!done);
        return result;
    }
    public static double getDouble(Scanner pipe, String prompt) {
        double result = 0;
        boolean done = false;
        String trash = "";
        do {
            System.out.print(prompt);
            if (pipe.hasNextDouble()) {
                result = pipe.nextDouble();
                done = true;
            } else {
                trash = pipe.nextLine();
                System.out.println("Invalid input: " + trash + ", Please enter a number.");
            }
        }
        while (!done);
        return result;
    }
    public static int getRangedInt(Scanner pipe, String prompt, int lo, int hi) {
        int result = 0;
        boolean done = false;
        String trash = "";
        do {
            System.out.print(prompt + "[" + lo + " - " + hi + "]: ");
            if (pipe.hasNextInt()) {
                result = pipe.nextInt();
                if (result >= lo && result <= hi) {
                    done = true;
                } else {
                    System.out.println("You must enter a value in range [" + lo + " - " + hi + "]: " + result);
                }
            } else {
                trash = pipe.nextLine();
                System.out.println("You must enter a int between [" + lo + " - " + hi + "]: " + trash);
            }
        }
        while (!done);
        return result;
    }
    public static double getRangedDouble(Scanner pipe, String prompt, double lo, double hi) {
        double result = 0;
        boolean done = false;
        String trash = "";
        do {
            System.out.print(prompt + "[" + lo + " - " + hi + "]: ");
            if (pipe.hasNextDouble()) {
                result = pipe.nextDouble();
                if (result >= lo && result <= hi) {
                    done = true;
                } else {
                    System.out.println("You must enter a value in range [" + lo + " - " + hi + "]: " + result);
                }
            } else {
                trash = pipe.nextLine();
                System.out.println("You must enter a int between [" + lo + " - " + hi + "]: " + trash);
            }
        }
        while (!done);
        return result;
    }
    public static boolean getYNConfirm(Scanner pipe, String prompt) {
        String result;
        do {
            System.out.println(prompt);
            result = pipe.nextLine().trim().toLowerCase();
        } while (!result.equals("y") && !result.equals("n"));
        return result.equals("y");
    }
    public static String getRegExString(Scanner pipe, String prompt, String regEx) {
        String value = "";
        boolean gotAValue = false;
        do {
            System.out.print(prompt + ": ");
            value = pipe.nextLine();
            if (value.matches(regEx)) {
                gotAValue = true;
            } else {
                System.out.println("\nInvalid input: " + value);
            }
        }while(!gotAValue);
        return value;
    }
    public static void prettyHeader(String msg) {
        int msgLength = msg.length();
        int spaces = (60 - msgLength - 6) / 2;

        for (int i = 0; i < 60; i++) {
            System.out.print("*");
        }
        System.out.println();

        System.out.print("***");
        for (int i = 0; i < spaces; i++) {
            System.out.print(" ");
        }
        System.out.print(msg);
        for (int i = 0; i < spaces; i++) {
            System.out.print(" ");
        }
        System.out.println("***");

        for (int i = 0; i < 60; i++) {
            System.out.print("*");
        }
        System.out.println();
    }
}
