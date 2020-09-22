/**
 * 
 */
package smartcart;

import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import customerservice.Customer;
import customerservice.MembershipSignUp;
import javafx.geometry.Pos;
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

    public VBox printNode() {
	prods = cust.getListOfProds();

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
	line.setEndX(650.0f);
	line.setEndY(150.0f);
	line.setStrokeWidth(0.5);
	line.setStroke(Color.BLACK);

	Line line1 = new Line();
	line1.setStartX(100.0f);
	line1.setStartY(150.0f);
	line1.setEndX(650.0f);
	line1.setEndY(150.0f);
	line1.setStrokeWidth(0.5);
	line1.setStroke(Color.BLACK);

	Line line2 = new Line();
	line2.setStartX(100.0f);
	line2.setStartY(150.0f);
	line2.setEndX(300.0f);
	line2.setEndY(150.0f);
	line2.setStrokeWidth(1);
	line2.setStroke(Color.BLACK);
	line2.getStrokeDashArray().addAll(2d);

	Line line3 = new Line();
	line3.setStartX(100.0f);
	line3.setStartY(150.0f);
	line3.setEndX(300.0f);
	line3.setEndY(150.0f);
	line3.setStrokeWidth(1);
	line3.setStroke(Color.BLACK);
	line3.getStrokeDashArray().addAll(2d);

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
	tiles.setAlignment(Pos.CENTER);

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
		    item.setFont(Font.font("Sans Seriff", FontPosture.REGULAR, 8));
		    tp.getChildren().add(item);
		    tp.setAlignment(Pos.BASELINE_LEFT);
		    total += (totalQuantity * oldPd.getPrice());
		    totalQuantity = 1;
		    oldPn = cPd.getBrandName();
		    oldPd = cPd;
		}
	    }
	}
	if (firstTime == true) {
	    item = new Text();
	    item.setX(30);
	    item.setY(30);
	    item.setFill(Color.BLUE);
	    item.setFont(Font.font("Sans Seriff", FontPosture.REGULAR, 8));
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
	subtotalToday.setFont(Font.font("Arial", FontPosture.REGULAR, 10));

	subtotalToday.setX(30);
	subtotalToday.setY(250);
	Text totalToday = new Text(tt);
	totalToday.setFont(Font.font("Arial", FontPosture.REGULAR, 10));
	totalToday.setX(30);
	totalToday.setY(250);

	TilePane adt = new TilePane();

	String at = String.format("Cash                      TENDER: $%.2f", getTotalTendered());
	Text amountTendered = new Text(at);
	amountTendered.setFont(Font.font("Arial", FontPosture.REGULAR, 10));
	amountTendered.setX(30);
	amountTendered.setY(250);

	String add = String.format("Tax: $%.2f", taxAmount);
	Text addTax = new Text(add);
	addTax.setFont(Font.font("Arial", FontPosture.REGULAR, 10));
	addTax.setX(30);
	addTax.setY(250);

	String AmountDue = String.format("TOTAL                      $%.2f", totalAmount);
	Text ad = new Text(AmountDue);
	ad.setFont(Font.font("Arial", FontPosture.REGULAR, 12));
	ad.setX(30);
	ad.setY(250);

	String savings = String.format("Total savings today is $%.2f", cust.getCart().getTotalSavings());
	Text totalSavings = new Text(savings);
	totalSavings.setFont(Font.font("Arial", FontPosture.REGULAR, 12));
	totalSavings.setX(30);
	totalSavings.setY(250);

	String change = String.format("Cash                      CHANGE $%.2f",
		getTotalTendered() - cust.getCart().getGrandTotal());
	Text changeDue = new Text(change);
	changeDue.setFont(Font.font("Arial", FontPosture.REGULAR, 10));
	changeDue.setX(30);
	changeDue.setY(250);

	VBox taxpane = new VBox(5, amountTendered, line3, changeDue);
	taxpane.setAlignment(Pos.CENTER_RIGHT);

	VBox align = new VBox(subtotalToday, addTax, line2, ad);
	align.getChildren().add(taxpane);
	align.setAlignment(Pos.CENTER_RIGHT);

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

	if (cct >= 0.00f) {
	    tTextString = String.format(
		    "Thank you for shopping at the %s today! Your purchase for %.2f has been approved",
		    StoreConstants.STORE_NAME, totalAmount);
	} else {
	    tTextString = String.format("Thank you for shopping at the %s today! You still owe %.2f",
		    StoreConstants.STORE_NAME, ct);
	}

	Text tText = new Text(tTextString);
	tText.setFont(Font.font("Arial", FontPosture.REGULAR, 10));
	subtotalToday.setX(30);
	subtotalToday.setY(300);
	HBox tBox = new HBox(tText);
	tBox.setAlignment(Pos.BASELINE_LEFT);

	VBox receiptNode = new VBox(5, sBox, line, r, tp, line1, align, adt, thankYouBox, totalSavings, date);
	// VBox receiptNode = new VBox(tp);
//	double localWidth = receiptNode.getBoundsInLocal().getWidth();
//	double localHeight = receiptNode.getBoundsInLocal().getHeight();
//	//System.out.printf("Receipt Bounds width %.2f and Height %.2f\n", localWidth, localHeight);
	receiptNode.setMaxSize(504, 800);
	totalTendered = 0.0;
	return receiptNode;
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
	MembershipSignUp mem = new MembershipSignUp();

	String to = mem.getEmailAddress();

	// Sender's email ID needs to be mentioned
	String from = "superstore0502@gmail.com";

	// Assuming you are sending email from localhost
	String host = "localhost";

	// Get system properties
	// Properties properties = System.getProperties();

	// Setup mail server
	// properties.setProperty("mail.smtp.host", host);

	Properties properties = new Properties();
	properties.put(from, to);
	properties.put("mail.smtp.auth", true);
	properties.put("mail.smtp.starttls.enable", true);
	properties.put("mail.smtp.host", "smtp.gmail.com");
	properties.put("mail.smtp.port", "465");
	properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

	// Get the default Session object.
	// Session session = Session.getDefaultInstance(properties);
	String username = "superstore0502@gmail.com";
	String password = "superstore0502";
	Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
	    protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(username, password);
	    }
	});

	try {
	    // Create a default MimeMessage object.
	    MimeMessage message = new MimeMessage(session);

	    // Set From: header field of the header.
	    message.setFrom(new InternetAddress(from));

	    // Set To: header field of the header.
	    message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

	    // Set Subject: header field
	    message.setSubject("USI Superstore Receipt");

	    // Now set the actual message
	    message.setText("Thank you for shopping at USI Super Store");

	    BodyPart messageBodyPart = new MimeBodyPart();

	    messageBodyPart.setText("Thank you for shopping at USI Super Store");

	    Multipart multipart = new MimeMultipart();

	    messageBodyPart = new MimeBodyPart();
	    String filename = "file:///C:/Users/chich/OneDrive/Documents/11.pdf";
	    DataSource source = new FileDataSource(filename);
	    messageBodyPart.setDataHandler(new DataHandler(source));
	    messageBodyPart.setFileName(filename);
	    multipart.addBodyPart(messageBodyPart);

	    message.setContent(multipart);

	    // Send messages
//	    Transport transport = session.getTransport("smtp");
//	    transport.connect("smtp.gmail.com", 587, "superstore0502", password);
	    Transport.send(message);
	    System.out.println("Sent message successfully....");
	} catch (MessagingException mex) {
	    mex.printStackTrace();
	}
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
	System.out.printf("Card ID %s\n", card);
	if (total >= 0.00f) {
	    String.format("Thank you for shopping at the %s today! Your purchase of %.2f has been approved",
		    StoreConstants.STORE_NAME, total);

	}

	return change;

    }

}
