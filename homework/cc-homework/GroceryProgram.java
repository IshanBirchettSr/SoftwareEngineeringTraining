import java.util.Scanner;
import java.util.InputMismatchException;

public class GroceryProgram {
  
  public static void main(String[] args) {
    
  float setPrice = 1.2f;
  int setStock = 200;
    
   DreamGroceryShop dgs = new DreamGroceryShop();  
   
   String type;
   int line;
   float bind;
   
   Scanner in = new Scanner(System.in);
   
   System.out.print("do you have any green beans in stock?: ");
   type = in.nextLine();
   
   System.out.print("how much does each can cost?: " );
   bind = in.nextFloat();
   
  int i = 0;
  while (setStock > i) {
    System.out.print("How many cans of green beans are in stock?: ");
   line = in.nextInt();
     
  }
  
  }
}  