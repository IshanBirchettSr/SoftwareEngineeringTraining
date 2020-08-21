/**
 * 
 */
package smartcart;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.TextAttribute;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import customerservice.Customer;
import util.Product;
import util.StoreConstants;
import util.StorePrinter;

/**
 * @author chich
 *
 * 
 */

public class Receipt extends StorePrinter {

    Customer cust = new Customer();
    private String membershipId;
    String member = membershipId;
    List<Product> prods = null;
    // private static int counter = 0;

    @Override
    public int print(Graphics g, PageFormat pf, int page) throws PrinterException {
	int xSpace = 50;
	int ySpace = 30;

	if (page > 0) { /* We have only one page, and 'page' is zero-based */
	    return NO_SUCH_PAGE;
	}

	Graphics2D g2d = (Graphics2D) g;
	g2d.translate(pf.getImageableX(), pf.getImageableY());

	Map<TextAttribute, Integer> fontAttributes = new HashMap<TextAttribute, Integer>();
	fontAttributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);

	/* Now we perform our rendering */
	Font newFont = new Font(Font.SANS_SERIF, Font.BOLD, 16).deriveFont(fontAttributes);
	g.setFont(newFont);
	String storeName = StoreConstants.STORE_NAME;
	g.drawString(storeName, xSpace, ySpace);

	Date curDate = new Date();
	String todaysDate = DateFormat.getDateTimeInstance().format(curDate);
	String datePurchased = String.format("Date: %s", todaysDate);
	newFont = new Font(Font.SANS_SERIF, Font.PLAIN, 12);
	g.setFont(newFont);
	// Next line
	ySpace += 20;
	g.drawString(datePurchased, xSpace, ySpace);

	double total = 0.00;
	// For each loop
	ySpace += 20;
	for (Product prod : prods) {
	    String list = String.format("Product: %s, Quantity: %s, Price: %.2f\n", prod.getProductName(),
		    prod.getQuantity(), prod.getPrice());
	    total += prod.getPrice();
	    g.drawString(list, xSpace, ySpace);
	    ySpace += 15;
	}
	double totalAmount = total;
	String tt = String.format("Your total today is $%.2f", totalAmount);
	ySpace += 5;
	g.drawString(tt, xSpace, ySpace);
	/* tell the caller that this page is part of the printed document */
	return PAGE_EXISTS;
    }

    public void printReceipt() {
	System.out.printf("Here is your receipt. Thank you for shopping at the %s\n today!", StoreConstants.STORE_NAME);
	// Printer printer = Printer.getDefaultPrinter();
	// PageLayout pageLayout = printer.createPageLayout(Paper.NA_LETTER,
	// PageOrientation.PORTRAIT,
	// Printer.MarginType.DEFAULT);
//	        double scaleX = pageLayout.getPrintableWidth() / node.getBoundsInParent().getWidth();
//	        double scaleY = pageLayout.getPrintableHeight() / node.getBoundsInParent().getHeight();
//	        node.getTransforms().add(new Scale(scaleX, scaleY));

	PrinterJob job = PrinterJob.getPrinterJob();
	job.setPrintable(this);
	boolean ok = job.printDialog();
	if (ok) {
	    try {
		job.print();

		System.out.printf("Number of copies: %d", job.getCopies());
	    } catch (PrinterException ex) {
		System.out.println("The job did not successfully print");
	    }
	}
    }

    public static void emailReceipt() {
	// Prototype
	String key = "membershipId";
	String value = "member's email";
	Properties properties = new Properties();
	properties.put(key, value);
	properties.put("mail.smtp.auth", true);
	properties.put("mail.smtp.starttls.enable", true);
	properties.put("mail.smtp.host", "smtp.gmail.com");
	properties.put("mail.smtp.port", "587");

	// String MyAccountEmail = "superstore0502@gmail.com";
	// String password = "superstore0502";

	System.out.printf("Your reciept will be emailed to you. Thank you for shopping at the %s\\n today!",
		StoreConstants.STORE_NAME);

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

    public void setProdList(List<Product> prodList) {
	prods = prodList;
    }

}
