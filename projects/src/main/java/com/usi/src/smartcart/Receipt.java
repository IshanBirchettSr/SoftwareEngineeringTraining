/**
 * 
 */
package smartcart;

<<<<<<< Updated upstream


import java.util.Scanner;

import smartcart.ShoppingCart;
=======
import java.util.Scanner;
>>>>>>> Stashed changes
import util.StorePrinter;

/**
 * @author chich
 *
 */
<<<<<<< Updated upstream
public class Receipt  {
	
	Scanner scan = new Scanner(System.in);
	Scanner in = null;
	
	
	boolean yes = true;
	StorePrinter storePrinter = null;
		
			public boolean transaction() {
				storePrinter = new StorePrinter();
				storePrinter.actionPerformed(null);
				return false;
			} 
			
			private boolean printReciept(boolean transaction) {
			return false;
			}
			 {
			    
	    if (yes = true ) {
	    	System.out.print ("Thank you for shopping today! Have a great day!");
	    }
	    
	    else { 
	    	System.out.printf("Here's your reciept, Have a great day!"); }
	    
             
			}
	
=======
public class Receipt extends StorePrinter {
	 Scanner scan = null;
	 
	 
	 
	 public void printReceipt() {
		 
	 }
	 
	 
	 
	 
	 
	 
	 public void emailReceipt() {
		 
	 }

	/**
	 * 
	 */
	public Receipt() {
		// TODO Auto-generated constructor stub
		scan = new Scanner(System.in);
		
	}
>>>>>>> Stashed changes

}


