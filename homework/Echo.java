import java.util.Scanner;
import java.util.InputMismatchException;

public class Echo {
  public static void main(String[] args) {
    String line;
    Scanner in = new Scanner(System.in);
    int numberOfCars =1;
    System.out.println("How many cars do you own?");
    try {
      numberOfCars = in.nextInt();
      in.nextLine();
    }
    catch(InputMismatchException e) {
      System.out.print("Wrong answer:\n");
    }
    System.out.printf("You have %d cars.\n", numberOfCars);
    System.out.print("Type something: ");
    line = in.nextLine();
    System.out.println("You said: " + line);
    System.out.print("Type something else: ");
    line = in.nextLine();
  }
  
}