/**
 * 
 */
package smartcart;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.font.TextAttribute;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.net.ssl.SSLSession;
import customerservice.MembershipSignUp;
import util.Product;
import util.StoreConstants;
import util.StorePrinter;

/**
 * @author chich

 *
 */

public class Receipt extends StorePrinter {
	 
	
	
     @Override
	 public int print(Graphics g, PageFormat pf, int page) throws PrinterException {
     
    	    Graphics2D g2d = (Graphics2D) g;
    		g2d.translate(pf.getImageableX(), pf.getImageableY());
    		Map<TextAttribute, Integer> fontAttributes = new HashMap<TextAttribute, Integer>();
    		fontAttributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);

    		/* Now we perform our rendering */
    		Font newFont = new Font(Font.SANS_SERIF, Font.BOLD, 16).deriveFont(fontAttributes);
    		g.setFont(newFont);
    		String storeName = StoreConstants.STORE_NAME;
    		g.drawString(storeName, 50, 30);

    		Date curDate = new Date();
    		String todaysDate = DateFormat.getDateTimeInstance().format(curDate);
    		String datePurchased = String.format("Date: %s", todaysDate);
    		newFont = new Font(Font.SANS_SERIF, Font.PLAIN, 12);
    		g.setFont(newFont);
    		g.drawString(datePurchased, 50, 50);
    		
    		//Waiting for Customer class to be created to return Membership Id
    		MembershipSignUp mem = new MembershipSignUp(null);
    		String member = mem.membershipApplication();

    		double total = 0.00;
    		for (int i = 1; i < 10; i++) {
    		    String prod = String.format("Product: %s, price: %.2f\n", "pr" + i, (i * (i * .25)));
    		    total += (i * (i * .25));
    		    g.drawString(prod, 50, ((50) + i * 20));
    		}
    		double totalAmount = total;
    		String tt = String.format("Your total today is $%.2f", totalAmount);
    		g.drawString(tt, 50, ((50) + 11 * 20));
    		/* tell the caller that this page is part of the printed document */
    		return PAGE_EXISTS;
    	    }
    	 
    	 
	 
	  
	 public static void printReceipt(ActionEvent e)  {
		 System.out.printf("Here is your reciept. Thank you for shopping at the %s\n today!", StoreConstants.STORE_NAME);
		 
	 }
	 
	 public static void emailReceipt() {
		//Prototype
	     String key = "membershipId";
		 String value = "member's email";
		 Properties properties = new Properties();
		 properties.put(key , value);
		 properties.put("mail.smtp.auth", true);
		 properties.put("mail.smtp.starttls.enable", true);
		 properties.put("mail.smtp.host", "smtp.gmail.com");
		 properties.put("mail.smtp.port","587");
		 
		 String MyAccountEmail = "superstore0502@gmail.com";
		 String password = "superstore0502";
		 
		 
		 System.out.printf("Your reciept will be emailed to you. Thank you for shopping at the %s\\n today!", StoreConstants.STORE_NAME);
		 
	 }
	 
	 public static void member() {
	    System.out.printf("You saved $%.2f today!");
	 }

	 public static void nonMember() {
		 System.out.printf("You could have saved $%.2f today!");
	 }
	/**
	 * 
	 */
	public Receipt() {
		super();
		
		
		
		
		
	}


}


