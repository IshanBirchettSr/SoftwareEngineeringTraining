/**
 * 
 */
package smartcart;

import java.util.Date;
import java.util.List;
import java.util.Properties;

import customerservice.Customer;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import util.Product;
import util.StoreConstants;
import util.StoreConstants.paymentType;
import util.StorePrinterFx;

/**
 * @author chich
 *
 * 
 */

public class Receipt extends StorePrinterFx {

    Customer cust = new Customer();
    private String membershipId;
    String member = membershipId;
    List<Product> prods = null;
    paymentType payType = null;
    String valueEntered = null;
    double money = 0;

    /**
     * Constructor
     */
    public Receipt() {

	super();
    }

    /**
     * @return the cust
     */
    protected Customer getCust() {
	return cust;
    }

    /**
     * @param cust the cust to set
     */
    protected void setCust(Customer cust) {
	this.cust = cust;
    }

    /**
     * @return the payType
     */
    protected paymentType getPayType() {
	return payType;
    }

    /**
     * @param payType the payType to set
     */
    protected void setPayType(paymentType payType) {
	this.payType = payType;
    }

    /**
     * @return the valueEntered
     */
    protected String getValueEntered() {
	return valueEntered;
    }

    /**
     * @param valueEntered the valueEntered to set
     */
    protected void setValueEntered(String valueEntered) {
	this.valueEntered = valueEntered;
    }

    private static int counter = 0;

    public VBox printNode() {
	prods = cust.getListOfProds();

	String sTextString = String.format("%s", StoreConstants.STORE_NAME);
	Text sText = new Text(sTextString);
	sText.setFont(Font.font("Tahoma", FontWeight.BOLD, FontPosture.REGULAR, 15));
	sText.setStroke(Color.BLACK);
	sText.setStyle("-fx-fill: linear-gradient(to right, red, orange , yellow, lime, purple);");
	sText.setX(30);
	sText.setStrokeWidth(1);
	sText.setY(50);
	HBox sBox = new HBox(sText);
	sBox.setAlignment(Pos.CENTER);

	String st = ("Receipt");
	Text str = new Text(st);
	str.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 8));
	str.setFill(Color.BLACK);
	str.setX(30);
	str.setY(50);
	HBox r = new HBox(10, str);
	r.setAlignment(Pos.CENTER);

	String sd = String.format("%s", new Date());
	Text ssd = new Text(sd);
	ssd.setFont(Font.font("Arial", FontPosture.REGULAR, 3));
	HBox date = new HBox(ssd);
	date.setAlignment(Pos.BASELINE_LEFT);

	Image StoreIcon = new Image(StoreConstants.SC_ICON_FULL);
	ImageView si = new ImageView();
	si.setFitWidth(25);
	si.setImage(StoreIcon);
	si.setPreserveRatio(true);
	si.setSmooth(true);
	si.setCache(true);
	HBox sp = new HBox(si);
	sp.setAlignment(Pos.TOP_CENTER);

	VBox itemList = new VBox(2);

	List<Product> pList = cust.getCart().getProductList();
	int totalQuantity = 1;
	double total = 0;
	boolean firstTime = true;
	String oldPn = "no name";
	Product oldPd = null;
	Text item = null;

	if (pList == null) {
	    System.out.println("pList is null\n");

	}

	for (Product cPd : pList) {
	    if (oldPn.equals("no name") == true) {
		oldPn = cPd.getProductName();
		oldPd = cPd;
		firstTime = true;
		// System.out.println("No Name Hit\n");
		continue;
	    }

	    if (oldPn.equals(cPd.getProductName()) == true) {
		totalQuantity++;
		oldPd = cPd;
		continue;

	    } else {
		// System.out.printf("Else %b\n", firstTime);
		if (firstTime == true) {

		    String listItem = String.format("Product: %s, Quantity: %s, Price: %.2f\n", cPd.getProductName(),
			    totalQuantity, cPd.getPrice());
		    item = new Text(listItem);
		    item.setX(30);
		    item.setY(200);
		    item.setFill(Color.BLUE);
		    item.setFont(Font.font("Arial", FontPosture.REGULAR, 6));
		    itemList.getChildren().add(item);
		    itemList.setAlignment(Pos.BASELINE_LEFT);
		    total += cPd.getPrice();
		    totalQuantity = 1;
		    oldPn = cPd.getProductName();
		    oldPd = cPd;
		}
	    }
	}
	if (firstTime == true) {
	    item = new Text();
	    item.setText("No items in your Smart Cart.");
	    item.setX(30);
	    item.setY(200);
	    item.setFill(Color.BLUE);
	    item.setFont(Font.font("Arial", FontPosture.REGULAR, 6));

	    total += oldPd.getPrice();
	    if (oldPd != null) {
		item.setText(String.format("Product: %s, Quantity: %s, Price: %.2f\n", oldPd.getProductName(),
			totalQuantity, oldPd.getPrice()));
	    }
	    itemList.getChildren().add(item);
	    firstTime = false;
	}

	double totalAmount = total;
	String tt = String.format("Your total today is $%.2f", totalAmount);
	Text totalToday = new Text(tt);
	totalToday.setFont(Font.font("Arial", FontPosture.REGULAR, 7));
	totalToday.setX(30);
	totalToday.setY(250);
	itemList.getChildren().add(totalToday);

	isThereChange(total, money);

	String thankYouMessage = String.format("%s %s thank you for your purchase today!",
		cust.getmCard().getFirstName(), cust.getmCard().getLastName());
	Text thankYouText = new Text(thankYouMessage);
	thankYouText.setFont(Font.font("Arial", FontPosture.REGULAR, 5));
	totalToday.setFont(Font.font("Arial", FontPosture.REGULAR, 7));
	totalToday.setX(30);
	totalToday.setY(300);
	HBox thankYouBox = new HBox(thankYouText);
	thankYouBox.setAlignment(Pos.BASELINE_LEFT);

	String tTextString = String.format("Thank you for shopping at the %s today!", StoreConstants.STORE_NAME);
	Text tText = new Text(tTextString);
	tText.setFont(Font.font("Arial", FontPosture.REGULAR, 7));
	totalToday.setX(30);
	totalToday.setY(300);
	HBox tBox = new HBox(tText);
	tBox.setAlignment(Pos.BASELINE_LEFT);

	VBox receiptNode = new VBox(5, sBox, r, date, sp, itemList, thankYouBox, tBox);

	/* tell the caller that this page is part of the printed document */
	return receiptNode;
    }

    public void printReceipt() {
	print(printNode());
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

    public void setProdList(List<Product> prodList) {
	prods = prodList;
    }

    public static double isThereChange(double total, double money) {

	double change = 0.0f;
	change = money - total;

	if (change > 0)
	    ;
	{
	    System.out.printf("Your change is: %4.2f\n", change);
	}
	if (change < 0) {
	    System.out.printf("You still owe: %2.2f\n", change);
	} else {
	    System.out.printf("Thank you for purchase. No change: %2.2f, Have a great day!\n", change);
	}
	return change;

    }

}
