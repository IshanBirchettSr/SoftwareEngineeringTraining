import java.util.Scanner;
import java.util.InputMismatchException;
/**
 * An exercise in learning how to input data.
 */
public class Echo {
  public static void main(String[] args) {
    String line;
    Scanner in = new Scanner(System.in);
    int numberOfCars =0;
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
    System.out.println("You also said: " + line);
    System.out.println("Have you gone anywhere in the last thirty days?:\n");
    String answer = in.nextLine();
    if(answer.equals( "Yes" ) == true) {
      System.out.printf("Where did you go?\n");
      answer = in.nextLine();
      System.out.printf("Who did you contact?\n");
      String personContacted = in.nextLine();
    } else {
      System.out.printf("Thank you for sheltering in place.\n");
    }
  }
}