import java.util.Scanner;
import java.util.InputMismatchException;

public class WhosLineIsItWhileLooping {
  
  static final int MAX_RETIES = 4; 
  
  // [0][1][2][3][4]
  static String[] isItYourName = {"Ishan", "Charlene", "Roxanne", "Allma"};
  Scanner in = null;

public WhosLineIsItWhileLooping() {
      in = new Scanner(System.in);
}

public void knowYourName() {
  int i = 0;
  while(i < MAX_RETIES) {
    System.out.printf("Is it your name?: %s ", isItYourName[i]);
    boolean yourAnswer = in.nextBoolean();
    
    if(yourAnswer == true) {
      System.out.println("Thank you!"); 
      break;
      
    } 
    
    
  
    i++;

  }
 }
}