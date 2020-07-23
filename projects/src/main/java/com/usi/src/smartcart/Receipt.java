/**
 * 
 */
package smartcart;

import java.awt.event.ActionEvent;

import java.util.Scanner;

import util.StoreConstants;
import util.StorePrinter;

/**
 * @author chich

 *
 */

public class Receipt extends StorePrinter {
	 
	static StorePrinter print = new StorePrinter();
	Scanner scan = null;
    
	 
	  
	 public static void printReceipt(ActionEvent e)  {
		
		 
		 System.out.printf("Here is your reciept. Thank you for shopping at the %s\n today!", StoreConstants.STORE_NAME);
		 print.actionPerformed(e);
		 
	 }
	 
	 
	 public static void emailReceipt() {
		 
		 
		 System.out.printf("Your reciept will be emailed to you. Thank you for shopping at the %s\\n today!", StoreConstants.STORE_NAME);
	 }

	/**
	 * 
	 */
	public Receipt() {
		// TODO Auto-generated constructor stub
		scan = new Scanner(System.in);
		
		
		
	}


}


