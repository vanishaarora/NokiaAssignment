package services;

import java.util.Scanner;

public class BaseService {

  public Scanner scanner = new Scanner(System.in);

  public int readInt() {
    try {
      return scanner.nextInt();
    } catch (Exception e) {
      System.out.println("Please enter a valid input");
      return readInt();
    }
  }

  public float readFloat() {
    try {
      return scanner.nextFloat();
    } catch (Exception e) {
      System.out.println("Please enter a valid input");
      return readFloat();
    }
  }

  public String readString() {
    try {
      return scanner.nextLine();
    } catch (Exception e) {
      System.out.println("Please enter a valid input");
      return readString();
    }
  }
}
