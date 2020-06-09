import java.util.Scanner;
import java.util.InputMismatchException;

public class VarExercise {
  public static void main(String[] args) {
    byte aTown = 'C';
    /*
     * Create a DeclaringVariable instances
     * 
    */
    // Declare variables
    Scanner in = new Scanner(System.in);
    DeclaringVariables dv = new DeclaringVariables();
    dv.setTown(aTown);
    byte myTown = dv.getTown();
    System.out.printf("This is my town: %c\n", myTown);
    
      short ballParks = 1000;
      float costOfSmartWater = 3.75f;
      dv.setOuttings(ballParks, costOfSmartWater);
      int howManyHomes = 20000;
      long population = 34000;
      dv.neighbors(howManyHomes, population);
      boolean lightsOn = true;
      dv.isBallParkLightsOn(lightsOn);
      lightsOn = false;
      dv.isBallParkLightsOn(lightsOn);
      dv.isThereChange(5.45f, 5.45f);
      
      System.out.print("Please enter your full name: ");
      String line = in.nextLine();
      dv.setFullName(line);
      
      System.out.print("This is my Full Name: " + dv.getFullName() + "\n");
      
      System.out.print("Are you a senior enter(true/false)?");
      Boolean senior = false;
      
      try {
        senior = in.nextBoolean();
      } catch( InputMismatchException e) {
        System.out.print("You can only enter true or false: " + e + "\n");
      }
      System.out.printf("%b a senior\n", senior);
      
      Short age = 1;
      System.out.print("Please enter your age: ");
      
      try {
        age = in.nextShort();
        if (age >= 65 ) {
          System.out.println("You get a discount because you are a senior\n");
          } else {
            System.out.println("You must pay full price");
          }                  
          } catch (InputMismatchException e) {
            System.out.println("Please enter a value between 1 - 150");
          }
      
  }   
}
    