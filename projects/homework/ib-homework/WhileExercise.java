import java.util.Scanner;

public class WhileExercise {
  Scanner in = null;
  private static int lotto = 12;
  
  public void WhileExercise() {
    
  }
  
  public boolean testWhile(int lottoNumber) {
    in = new Scanner(System.in);
    boolean winner = false;
    while(lottoNumber != lotto) {
      System.out.println("Please enter your lotto pick: ");
      
      lottoNumber = in.nextInt();
      if(lottoNumber == 0) {
        
        break;
      }
      if(lottoNumber == lotto) {
        winner = true;
        System.out.println("Winner Winner Winner, you have won the Lotto");
        break;
      }
      System.out.println("Sorry that is not correct.  Please try again.");
    }
          return winner;
  }
}
