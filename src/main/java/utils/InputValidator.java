package utils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputValidator {

  private static InputValidator instance = null;

  private InputValidator() {
  }

  public static InputValidator getInstance() {
    if (instance == null) {
      instance = new InputValidator();
    }

    return instance;
  }

  public String getStringInput(String message) {
    try {
      System.out.println("\n" + message + "\n");
      Scanner sc = new Scanner(System.in);
      return sc.nextLine();
    } catch (InputMismatchException e) {
      System.out.println("Input type should be string");
      return getStringInput(message);
    }
  }

  public int getIntegerInput(String message) {
    try {
      System.out.println("\n" + message + "\n");
      Scanner sc = new Scanner(System.in);
      return sc.nextInt();
    } catch (InputMismatchException e) {
      System.out.println("Input type should be number");
      return getIntegerInput(message);
    }
  }

  public Float getFloatInput(String message) {
    try {
      System.out.println("\n" + message + "\n");
      Scanner sc = new Scanner(System.in);
      return sc.nextFloat();
    } catch (InputMismatchException e) {
      System.out.println("Input type should be number");
      return getFloatInput(message);
    }
  }
}
