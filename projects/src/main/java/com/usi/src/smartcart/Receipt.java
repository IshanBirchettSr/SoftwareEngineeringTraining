/**
 * 
 */
package smartcart;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import customerservice.Customer;
import javafx.geometry.Pos;
import javafx.scene.Node;
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
    static double valueEnteredCash = 0.00f;
    static double totalTendered = 0.0f;
    static String valueEnteredCard = null;

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
    static protected double getValueEnteredCash() {
	return valueEnteredCash;
    }

    static protected String getValueEnteredCard() {
	return valueEnteredCard;
    }

    /**
     * @param money the valueEntered to set
     */
    @SuppressWarnings("static-access")
    protected void setValueEnteredCard(String money) {
	this.valueEnteredCard += money;

    }

    @SuppressWarnings("static-access")
    protected void setValueEnteredCash(double money) {
	this.valueEnteredCash = money;
	this.setTotalTendered(money);
	// System.out.printf("Amount tendered so far: %.2f\n", totalTendered);
    }

    public List<Node> printNode() {
	prods = cust.getListOfProds();
	List<Node> pageNodes = new ArrayList<Node>();
	Image StoreIcon = new Image(StoreConstants.SC_ICON_FULL);
	ImageView si = new ImageView();
	si.setFitWidth(35);
	si.setImage(StoreIcon);
	si.setPreserveRatio(true);
	si.setSmooth(true);
	si.setCache(true);

	String sTextString = String.format("%s", StoreConstants.STORE_NAME);
	Text sText = new Text(sTextString);
	sText.setFont(Font.font("Tahoma", FontWeight.BOLD, FontPosture.REGULAR, 14));
	sText.setStroke(Color.BLACK);
	sText.setStyle("-fx-fill: linear-gradient(to right, red, orange , yellow, lime, purple);");
	sText.setX(30);
	sText.setStrokeWidth(1);
	sText.setY(50);
	HBox sBox = new HBox(5, sText, si);
	sBox.setAlignment(Pos.CENTER);

	Line line = new Line();
	line.setStartX(100.0f);
	line.setStartY(150.0f);
	line.setEndX(504.0f);
	line.setEndY(150.0f);
	line.setStrokeWidth(1);
	line.setStroke(Color.BLACK);
	line.getStrokeDashArray().addAll(2d);

	Line line1 = new Line();
	line1.setStartX(100.0f);
	line1.setStartY(150.0f);
	line1.setEndX(504.0f);
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
	str.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
	str.setFill(Color.BLACK);
	str.setX(30);
	str.setY(50);
	HBox r = new HBox(10, str);
	r.setAlignment(Pos.CENTER);

	String sd = String.format("%s", new Date());
	Text ssd = new Text(sd);
	ssd.setFont(Font.font("Arial", FontPosture.REGULAR, 8));
	HBox date = new HBox(ssd);
	date.setAlignment(Pos.BOTTOM_CENTER);

	String quantity = ("Quantity");
	String description = ("Description");
	String price = ("Price");
	Text tile = new Text(quantity);
	tile.setFont(Font.font("Arial", FontPosture.REGULAR, 8));
	Text tile2 = new Text(description);
	tile2.setFont(Font.font("Arial", FontPosture.REGULAR, 8));
	Text tile3 = new Text(price);
	tile3.setFont(Font.font("Arial", FontPosture.REGULAR, 8));
	HBox tiles = new HBox(40, tile, tile2, tile3);

	// VBox itemList = new VBox(2);

	List<Product> pList = cust.getCart().getProductList();
	int totalQuantity = 1;
	double total = cust.getCart().getGrandTotal();
	boolean firstTime = true;
	String oldPn = "no name";
	Product oldPd = null;
	Text item = null;
	TilePane tp = new TilePane();
	tp.getChildren().add(tiles);
	tp.setAlignment(Pos.BASELINE_CENTER);
//	tp.setVgap(8);
	tp.setHgap(5);
	tp.setPrefColumns(20);
	tp.setMaxWidth(10);

	if (pList == null) {
	    System.out.println("pList is null\n");
	}
	int totalEntries = 0;
	for (Product cPd : pList) {
	    if (oldPn.equals("no name") == true) {
		oldPn = cPd.getBrandName();
		oldPd = cPd;
		firstTime = true;
		continue;
	    }

	    if (oldPn.equals(cPd.getBrandName()) == true) {
		totalQuantity++;
		oldPd = cPd;
		continue;
	    } else {
		if (firstTime == true) {
		    String listItem = String.format("Qty: %s     	 Product: %s    	  Price: %.2f\n",
			    totalQuantity, cPd.getBrandName(), cPd.getPrice());
		    item = new Text(listItem);
		    item.setX(10);
		    item.setY(10);
		    item.setFill(Color.BLACK);
		    item.setFont(Font.font("Sans Seriff", FontPosture.REGULAR, 6));
		    tp.getChildren().add(item);
		    tp.setAlignment(Pos.BASELINE_LEFT);
		    total += (totalQuantity * oldPd.getPrice());
		    totalQuantity = 1;
		    oldPn = cPd.getBrandName();
		    oldPd = cPd;
		    totalEntries++;

		    if ((tp.getChildren().size() % 5) == 0) {
			VBox page = new VBox(5, sBox, line, r, tp);
			page.setMaxSize(504, 800);
			System.out.printf("Pages %d, items %d\n", pageNodes.size(), tp.getChildren().size());
			String pageId = String.format("Page: %d", pageNodes.size());
			page.setId(pageId);
			pageNodes.add(page);

			System.out.printf("Pages %d, items %d\n", pageNodes.size(), tp.getChildren().size());
		    } else {
			System.out.printf("Total Entries so far: %d\n", tp.getChildren().size());
		    }
		}
	    }
	}
	if (firstTime == true) {
	    item = new Text();
	    item.setX(30);
	    item.setY(30);
	    item.setFill(Color.BLUE);
	    item.setFont(Font.font("Sans Seriff", FontPosture.REGULAR, 6));
	    total += (totalQuantity * oldPd.getPrice());
	    // total += oldPd.getPrice();
	    if (oldPd != null) {
		item.setText(String.format("Qty: %s     	 Product: %s     	 Price: %.2f\n", totalQuantity,
			oldPd.getBrandName(), oldPd.getPrice()));
	    }

	    tp.getChildren().add(item);
	    tp.setAlignment(Pos.BASELINE_LEFT);
	    firstTime = false;
	}

	double subtotalAmount = cust.getCart().getSubTotal();
	double taxAmount = cust.getCart().getTaxesTotal();
	double totalAmount = cust.getCart().getGrandTotal();
	String sbt = String.format("SubTotal: $%.2f", subtotalAmount);
	String tt = String.format("Your total today is $%.2f", totalAmount);
	Text subtotalToday = new Text(sbt);
	subtotalToday.setFont(Font.font("Arial", FontPosture.REGULAR, 8));

	subtotalToday.setX(30);
	subtotalToday.setY(250);
	Text totalToday = new Text(tt);
	totalToday.setFont(Font.font("Arial", FontPosture.REGULAR, 8));
	totalToday.setX(30);
	totalToday.setY(250);

	TilePane adt = new TilePane();

	String at = String.format("Amount Tendered: $%.2f", getTotalTendered());
	Text amountTendered = new Text(at);
	amountTendered.setFont(Font.font("Arial", FontPosture.REGULAR, 8));
	amountTendered.setX(30);
	amountTendered.setY(250);

	String add = String.format("Tax: $%.2f", taxAmount);
	Text addTax = new Text(add);
	addTax.setFont(Font.font("Arial", FontPosture.REGULAR, 8));
	addTax.setX(30);
	addTax.setY(250);

	String AmountDue = String.format("Your total today is $%.2f", totalAmount);
	Text ad = new Text(AmountDue);
	ad.setFont(Font.font("Arial", FontPosture.REGULAR, 8));
	ad.setX(30);
	ad.setY(250);

	String savings = String.format("Total savings today is $%.2f", cust.getCart().getTotalSavings());
	Text totalSavings = new Text(savings);
	totalSavings.setFont(Font.font("Arial", FontPosture.REGULAR, 8));
	totalSavings.setX(30);
	totalSavings.setY(250);

	String change = String.format("Your change due is $%.2f", getTotalTendered() - cust.getCart().getGrandTotal());
	Text changeDue = new Text(change);
	changeDue.setFont(Font.font("Arial", FontPosture.REGULAR, 8));
	changeDue.setX(30);
	changeDue.setY(250);

	VBox align = new VBox(subtotalToday, addTax, line2, ad, totalSavings);
	align.setAlignment(Pos.CENTER_RIGHT);

	VBox taxpane = new VBox(5, amountTendered, changeDue);
	adt.getChildren().add(taxpane);
	adt.setAlignment(Pos.BASELINE_RIGHT);

	String thankYouMessage = String.format("%s %s thank you for your purchase today!",
		cust.getmCard().getFirstName(), cust.getmCard().getLastName());
	Text thankYouText = new Text(thankYouMessage);
	thankYouText.setFont(Font.font("Arial", FontPosture.REGULAR, 8));
	subtotalToday.setFont(Font.font("Arial", FontPosture.REGULAR, 8));
	subtotalToday.setX(30);
	subtotalToday.setY(300);
	HBox thankYouBox = new HBox(thankYouText);
	thankYouBox.setAlignment(Pos.BASELINE_LEFT);

	String tTextString = null;
	double ct = isThereChange(total, valueEnteredCash);
	double cct = isThereChange(total, valueEnteredCard);

	if (cct >= 0.00f) {
	    tTextString = String.format(
		    "Thank you for shopping at the %s today! Your purchase for %.2f has been approved",
		    StoreConstants.STORE_NAME, totalAmount);
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

	VBox receiptNode = new VBox(5, sBox, line, r, tp, line1, align, adt, thankYouBox, date);
	// VBox receiptNode = new VBox(tp);
//	double localWidth = receiptNode.getBoundsInLocal().getWidth();
//	double localHeight = receiptNode.getBoundsInLocal().getHeight();
//	//System.out.printf("Receipt Bounds width %.2f and Height %.2f\n", localWidth, localHeight);
	receiptNode.setMaxSize(504, 800);
	totalTendered = 0.0;
	String pageId = String.format("Page: %d", pageNodes.size());
	receiptNode.setId(pageId);
	pageNodes.add(receiptNode);
	System.out.printf("Last Page %d, items %d\n", pageNodes.size(), tp.getChildren().size());
	return pageNodes;
    }

    /**
     * @return the totalTendered
     */
    static private double getTotalTendered() {
	return totalTendered;
    }

    /**
     * @param totalTendered the totalTendered to set
     */
    private void setTotalTendered(double amount) {
	totalTendered += amount;
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

	// System.out.printf("Change: total %.2f money %.2f\n", total, money);
//	if (change >= 0) {
//	    System.out.printf("Thank you for shopping at the %s today! Your change is %.2f\n",
//		    StoreConstants.STORE_NAME, change);
//	} else {
//	    System.out.printf("Thank you for shopping at the %s today! You still owe %.2f\n", StoreConstants.STORE_NAME,
//		    change);
//	}

	return change;

    }

    public static double isThereChange(double total, String card) {

	double change = 0.0f;
	// System.out.printf("Card ID %s\n", card);
	if (total >= 0.00f) {
	    String.format("Thank you for shopping at the %s today! Your purchase of %.2f has been approved",
		    StoreConstants.STORE_NAME, total);

	}

	return change;

    }

}
