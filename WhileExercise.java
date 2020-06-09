/**
 * Auto Generated Java Class.
 */
import java.util.Scanner;

  public class WhileExercise{ 
  Scanner in = null;
  private static int lotto = 12;
  
  public void WhileExercise() {
    
  }

  public boolean testWhile(int lottoNumber) {
    in = new Scanner(System.in);
    boolean winner = false;
    while(lottoNumber != lotto) {
    System.out.println("Enter your lotto pick number:  ");
  
    lottoNumber = in.nextInt ();
    if(lottoNumber == 0) {
       break;
    }
    if(lottoNumber == lotto) {
       winner = true;
       break;
    }
     
  }
    return winner; 
 }  
}
