import java.util.Scanner;
import java.util.InputMismatchException;

public class WhileLooping {
  
  static final int MAX_RETIES = 5;
  
  // [0][1][2][3][4]
  static String[] employeeIDs = {"Dave", "Rose", "Lynn", "Rick"};
  
  Scanner in = null;
  
  public WhileLooping() { 
        in = new Scanner(System.in);
  }
  
  public void getEmployeeId() {
    int i = 0;
    while(i < MAX_RETIES) {
      System.out.println("Please enter your EmployeeId: ");
      String employeeId = in.nextLine();
      boolean auth = authorized(employeeId);
      
      System.out.println("Your access is: " + auth);
      if(auth == true) {
        break;
      }
      i++;
    }
  
  }
  public boolean authorized(String empId) {
    int t = 0;
    boolean authorized = true;
    
    while(empId.equals(employeeIDs[t]) == false) {
      t++;
      if(t>3) {
        authorized = false;
        break;
      }
    }
    return authorized;
  }
}
