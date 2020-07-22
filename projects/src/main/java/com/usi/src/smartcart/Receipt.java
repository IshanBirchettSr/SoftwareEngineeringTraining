/**
 * 
 */
package smartcart;



import java.util.Scanner;

import smartcart.ShoppingCart;
import util.StorePrinter;

/**
 * @author chich
 *
 */
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
	

}


