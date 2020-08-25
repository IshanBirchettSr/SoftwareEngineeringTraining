/**
 * 
 */
package smartcart;

import java.util.Date;
import java.util.List;
import java.util.Properties;

import customerservice.Customer;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
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
	String sTextString = String.format("%s Receipt -- %s", StoreConstants.STORE_NAME, new Date());
	Text sText = new Text(sTextString);
	sText.setFont(Font.font("Arial", FontPosture.REGULAR, 8));
	sText.setX(30);
	sText.setY(50);
	HBox sBox = new HBox(sText);
	sBox.setAlignment(Pos.BASELINE_LEFT);

	VBox itemList = new VBox(2);
	int total = 0;
	for (Product prod : prods) {
	    String listItem = String.format("Product: %s, Quantity: %s, Price: %.2f\n", prod.getProductName(),
		    prod.getQuantity(), prod.getPrice());
	    Text item = new Text(listItem);
	    item.setX(30);
	    item.setY(150);
	    item.setFill(Color.BLUE);
	    item.setFont(Font.font("Arial", FontPosture.REGULAR, 6));
	    itemList.getChildren().add(item);
	    itemList.setAlignment(Pos.BASELINE_LEFT);
	    total += prod.getPrice();
	}
	double totalAmount = total;
	String tt = String.format("Your total today is $%.2f", totalAmount);
	Text totalToday = new Text(tt);
	totalToday.setFont(Font.font("Arial", FontPosture.REGULAR, 7));
	totalToday.setX(30);
	totalToday.setY(250);
	itemList.getChildren().add(totalToday);

	String thankYouMessage = String.format("%s %s thank you for your purchase today!",
		cust.getmCard().getFirstName(), cust.getmCard().getLastName());
	Text thankYouText = new Text(thankYouMessage);
	totalToday.setFont(Font.font("Arial", FontPosture.REGULAR, 7));
	totalToday.setX(30);
	totalToday.setY(150);
	HBox thankYouBox = new HBox(thankYouText);
	thankYouBox.setAlignment(Pos.BASELINE_LEFT);

	String tTextString = String.format("Thank you for shopping at the %s today!", StoreConstants.STORE_NAME);
	Text tText = new Text(tTextString);
	tText.setFont(Font.font("Arial", FontPosture.REGULAR, 7));
	totalToday.setX(30);
	totalToday.setY(150);
	HBox tBox = new HBox(tText);
	tBox.setAlignment(Pos.BASELINE_LEFT);

	VBox receiptNode = new VBox(5, sBox, itemList, thankYouBox, tBox);

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

}
