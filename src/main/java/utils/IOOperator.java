package utils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class IOOperator {

    private static IOOperator instance = null;

    Scanner sc = new Scanner(System.in);

    private IOOperator() {
    }

    public static IOOperator getInstance() {
        if (instance == null) {
            instance = new IOOperator();
        }

        return instance;
    }

    public String getStringInput(String message) {
        try {
            System.out.println("\n" + message + "\n");
            Scanner sc = new Scanner(System.in);
            return sc.nextLine();
        }
        catch (InputMismatchException e) {
            System.out.println("\033[41m" + "Input type should be string" + "\033[0m");
            return getStringInput(message);
        }
    }

    public int getIntegerInput(String message) {
        try {
            System.out.println("\n" + message + "\n");
            Scanner sc = new Scanner(System.in);
            return sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("\033[41m" + "Input type should be number" + "\033[0m");
            return getIntegerInput(message);
        }
    }

    public Float getFloatInput(String message) {
        try {
            System.out.println("\n" + message + "\n");
            Scanner sc = new Scanner(System.in);
            return sc.nextFloat();
        } catch (InputMismatchException e) {
            System.out.println("\033[41m" + "Input type should be number" + "\033[0m");
            return getFloatInput(message);
        }
    }
}
