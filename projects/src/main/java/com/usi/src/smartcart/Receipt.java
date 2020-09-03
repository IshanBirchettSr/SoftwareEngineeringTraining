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
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
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
    double valueEnteredCash = 0.00f;
    String valueEnteredCard = null;

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
    protected double getValueEnteredCash() {
	return valueEnteredCash;
    }

    protected String getValueEnteredCard() {
	return valueEnteredCard;
    }

    /**
     * @param money the valueEntered to set
     */
    protected void setValueEnteredCard(String money) {
	this.valueEnteredCard = money;
    }

    protected void setValueEnteredCash(double money) {
	this.valueEnteredCash = money;
    }

    private static int counter = 0;

    public VBox printNode() {
	prods = cust.getListOfProds();

	Image StoreIcon = new Image(StoreConstants.SC_ICON_FULL);
	ImageView si = new ImageView();
	si.setFitWidth(35);
	si.setImage(StoreIcon);
	si.setPreserveRatio(true);
	si.setSmooth(true);
	si.setCache(true);
	HBox sp = new HBox(si);
	sp.setAlignment(Pos.TOP_CENTER);

	String sTextString = String.format("%s", StoreConstants.STORE_NAME);
	Text sText = new Text(sTextString);
	sText.setFont(Font.font("Tahoma", FontWeight.BOLD, FontPosture.REGULAR, 25));
	sText.setStroke(Color.BLACK);
	sText.setStyle("-fx-fill: linear-gradient(to right, red, orange , yellow, lime, purple);");
	sText.setX(30);
	sText.setStrokeWidth(1);
	sText.setY(50);
	HBox sBox = new HBox(sText);
	sBox.setAlignment(Pos.CENTER);

	Line line = new Line();
	line.setStartX(100.0f);
	line.setStartY(150.0f);
	line.setEndX(700.0f);
	line.setEndY(150.0f);
	line.setStrokeWidth(1);
	line.setStroke(Color.BLACK);
	line.getStrokeDashArray().addAll(2d);

	Line line1 = new Line();
	line1.setStartX(100.0f);
	line1.setStartY(150.0f);
	line1.setEndX(700.0f);
	line1.setEndY(150.0f);
	line1.setStrokeWidth(1);
	line1.setStroke(Color.BLACK);
	line1.getStrokeDashArray().addAll(2d);

	Line line2 = new Line();
	line2.setStartX(100.0f);
	line2.setStartY(150.0f);
	line2.setEndX(200.0f);
	line2.setEndY(150.0f);
	line2.setStrokeWidth(1);
	line2.setStroke(Color.BLACK);
	line2.getStrokeDashArray().addAll(2d);

	String st = ("Receipt");
	Text str = new Text(st);
	str.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
	str.setFill(Color.BLACK);
	str.setX(30);
	str.setY(50);
	HBox r = new HBox(10, str);
	r.setAlignment(Pos.CENTER);

	String sd = String.format("%s", new Date());
	Text ssd = new Text(sd);
	ssd.setFont(Font.font("Arial", FontPosture.REGULAR, 5));
	HBox date = new HBox(ssd);
	date.setAlignment(Pos.BOTTOM_CENTER);

	String quantity = ("Quantity");
	String description = ("Description");
	String price = ("Price");
	Text tile = new Text(quantity);
	tile.setFont(Font.font("Arial", FontPosture.REGULAR, 10));
	Text tile2 = new Text(description);
	tile2.setFont(Font.font("Arial", FontPosture.REGULAR, 10));
	Text tile3 = new Text(price);
	tile3.setFont(Font.font("Arial", FontPosture.REGULAR, 10));
	HBox tiles = new HBox(40, tile, tile2, tile3);

	// VBox itemList = new VBox(2);

	List<Product> pList = cust.getCart().getProductList();
	int totalQuantity = 1;
	double total = 0;
	boolean firstTime = true;
	String oldPn = "no name";
	Product oldPd = null;
	Text item = null;
	TilePane tp = new TilePane();
	tp.getChildren().add(tiles);
	tp.setAlignment(Pos.BASELINE_CENTER);
//	tp.setVgap(8);
	tp.setHgap(5);
	tp.setMaxWidth(10);

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

		    String listItem = String.format("Qty: %s     	 Product: %s    	  Price: %.2f\n",
			    totalQuantity, cPd.getProductName(), cPd.getPrice());
		    item = new Text(listItem);
		    item.setX(30);
		    item.setY(30);
		    item.setFill(Color.BLACK);
		    item.setFont(Font.font("Sans Seriff", FontPosture.REGULAR, 10));
		    tp.getChildren().add(item);
		    tp.setAlignment(Pos.BASELINE_LEFT);
		    total += cPd.getPrice();
		    totalQuantity = 1;
		    oldPn = cPd.getProductName();
		    oldPd = cPd;
		}
	    }
	}
	if (firstTime == true) {
	    item = new Text();
	    item.setX(30);
	    item.setY(30);
	    item.setFill(Color.BLUE);
	    item.setFont(Font.font("Sans Seriff", FontPosture.REGULAR, 10));

	    total += oldPd.getPrice();
	    if (oldPd != null) {
		item.setText(String.format("Qty: %s     	 Product: %s     	 Price: %.2f\n", totalQuantity,
			oldPd.getProductName(), oldPd.getPrice()));
	    }

	    tp.getChildren().add(item);
	    tp.setAlignment(Pos.BASELINE_LEFT);
	    firstTime = false;
	}

	double subtotalAmount = total;
	String tt = String.format("SubTotal: $%.2f", subtotalAmount);
	Text subtotalToday = new Text(tt);
	subtotalToday.setFont(Font.font("Arial", FontPosture.REGULAR, 10));
	subtotalToday.setX(30);
	subtotalToday.setY(250);

	TilePane adt = new TilePane();

	String at = String.format("Amount Tendered: $%.2f", getValueEnteredCash());
	Text amountTendered = new Text(at);
	amountTendered.setFont(Font.font("Arial", FontPosture.REGULAR, 10));
	amountTendered.setX(30);
	amountTendered.setY(250);

	double tax = 6.3;
	String add = String.format("Tax: $%.2f", tax);
	Text addTax = new Text(add);
	addTax.setFont(Font.font("Arial", FontPosture.REGULAR, 10));
	addTax.setX(30);
	addTax.setY(250);

	Double addTaxToTotal = (subtotalAmount + tax);
	String AmountDue = String.format("Your total today is $%.2f", addTaxToTotal);
	Text ad = new Text(AmountDue);
	ad.setFont(Font.font("Arial", FontPosture.REGULAR, 10));
	ad.setX(30);
	ad.setY(250);

	VBox align = new VBox(subtotalToday, addTax, line2, ad);
	align.setAlignment(Pos.CENTER_RIGHT);

	VBox taxpane = new VBox(5, amountTendered);
	adt.getChildren().add(taxpane);
	adt.setAlignment(Pos.BASELINE_RIGHT);

	String thankYouMessage = String.format("%s %s thank you for your purchase today!",
		cust.getmCard().getFirstName(), cust.getmCard().getLastName());
	Text thankYouText = new Text(thankYouMessage);
	thankYouText.setFont(Font.font("Arial", FontPosture.REGULAR, 10));
	subtotalToday.setFont(Font.font("Arial", FontPosture.REGULAR, 10));
	subtotalToday.setX(30);
	subtotalToday.setY(300);
	HBox thankYouBox = new HBox(thankYouText);
	thankYouBox.setAlignment(Pos.BASELINE_LEFT);

	String tTextString = null;
	double ct = isThereChange(total, valueEnteredCash);
	double cct = isThereChange(total, valueEnteredCard);

	if (ct >= 0.00f) {
	    tTextString = String.format("Thank you for shopping at the %s today! Your change is %.2f",
		    StoreConstants.STORE_NAME, ct);
	} else {
	    tTextString = String.format("Thank you for shopping at the %s today! You still owe %.2f",
		    StoreConstants.STORE_NAME, ct);
	}
	if (cct >= 0.00f) {
	    tTextString = String.format(
		    "Thank you for shopping at the %s today! Your purchase for %.2f has been approved",
		    StoreConstants.STORE_NAME, total);
	} else {
	    tTextString = String.format("Thank you for shopping at the %s today! You still owe %.2f",
		    StoreConstants.STORE_NAME, ct);
	}

	Text tText = new Text(tTextString);
	tText.setFont(Font.font("Arial", FontPosture.REGULAR, 8));
	subtotalToday.setX(30);
	subtotalToday.setY(300);
	HBox tBox = new HBox(tText);
	tBox.setAlignment(Pos.BASELINE_LEFT);

	VBox receiptNode = new VBox(5, sp, sBox, line, r, tp, line1, align, adt, thankYouBox, tBox, date);

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
	System.out.printf("total %.2f money %.2f", total, money);
	if (total >= money) {
	    String.format("Thank you for shopping at the %s today! Your change is %.2f", StoreConstants.STORE_NAME,
		    change);
	} else {
	    String.format("Thank you for shopping at the %s today! You still owe %.2f", StoreConstants.STORE_NAME,
		    money);
	}

	change = money - total;

	return change;

    }

    public static double isThereChange(double total, String card) {

	double change = 0.0f;
	System.out.printf("total %.2f money %.2f", total, change);
	if (total >= 0.00f) {
	    String.format("Thank you for shopping at the %s today! Your purchase of %.2f has been approved",
		    StoreConstants.STORE_NAME, total);

	}

	return change;

    }

}
