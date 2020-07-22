/**
 * 
 */
package smartcart;

import java.awt.event.ActionEvent;
import java.util.Scanner;
import util.StorePrinter;

/**
 * @author chich
 *
 */

public class Receipt extends StorePrinter {
	 Scanner scan = null;
	 static StorePrinter print = new StorePrinter();
	 
	 
	 
	 public static void printReceipt(ActionEvent e) {
		 print.actionPerformed(e);
		 System.out.println("Here is your reciept. Thank you for shopping at the Super Store today!");
		 
		 
	 }
	 
	 
	 
	 
	 
	 
	 public static void emailReceipt() {
		 
		 
		 System.out.print("Your reciept will be emailed to you. Thank you for shopping at the Super Store today!" );
	 }

	/**
	 * 
	 */
	public Receipt() {
		// TODO Auto-generated constructor stub
		scan = new Scanner(System.in);
		
		
		
	}


}


