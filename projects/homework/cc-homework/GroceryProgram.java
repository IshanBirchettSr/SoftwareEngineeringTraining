import java.util.Scanner;
import java.util.InputMismatchException;


public class GroceryProgram {
  
  public void inStock() {
    
   Scanner in = new Scanner(System.in);
   
   DreamGroceryShop dgs = new DreamGroceryShop(); 
   
  try {   
   
   System.out.print("Do you have any green beans in stock?: ");
   boolean k = in.nextBoolean();
   float bind = 0.0f;
   if (k == true) {
     System.out.printf( "We have %d cans of green beans left. \n"  ,  dgs.getHowManyInStock());
     
     System.out.printf("How much does each can cost?: " );
     bind = in.nextFloat();
     dgs.setPricePerUnit(bind);
     System.out.printf( "each can cost %1.2f dollars. \n" ,  + dgs.getPricePerUnit());
     System.out.println("What else do you have in stock?");
 
   
   } else { 
     System.out.println("We are SOLDOUT!");
     System.out.println("This is what else we have in stock!");
   }
    
   }
  
  
  catch (InputMismatchException e) {
     e.printStackTrace();
   }
  }
}  