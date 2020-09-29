/**
 * 
 */
package smartcart;

import java.io.IOException;
import java.util.ArrayList;
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
import javafx.scene.Node;
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

    static Customer cust = new Customer();
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
	sText.setFont(Font.font("Tahoma", FontWeight.BOLD, FontPosture.REGULAR, 35));
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
	Label tile = new Label(quantity);
	tile.setFont(Font.font("Arial", FontPosture.REGULAR, 8));

	Label tile2 = new Label(description);
	tile2.setFont(Font.font("Arial", FontPosture.REGULAR, 8));

	Label tile3 = new Label(price);
	tile3.setFont(Font.font("Arial", FontPosture.REGULAR, 8));

	HBox tiles = new HBox(40, tile, tile2, tile3);
	tiles.setAlignment(Pos.CENTER_LEFT);

	// VBox itemList = new VBox(2);

	List<Product> pList = cust.getCart().getProductList();
	int totalQuantity = 1;
	double total = cust.getCart().getGrandTotal();
	boolean firstTime = true;
	String oldPn = "no name";
	Product oldPd = null;
	Label item = null;
	TilePane tp = new TilePane();
	tp.getChildren().add(tiles);
	tp.setAlignment(Pos.CENTER_LEFT);
	// tp.setVgap(10);
	tp.setHgap(40);
	tp.setPrefColumns(20);
	tp.setMaxWidth(20);

	if (pList == null) {
	    System.out.println("pList is null\n");
	}
	@SuppressWarnings("unused")
	int totalEntries = 0;
	@SuppressWarnings("unused")
	int totalPages = 0;
	List<VBox> pages = new ArrayList<VBox>();
	VBox pageItem = new VBox(sBox, line, r, tiles);

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
		    String qntyItem = String.format("Qty: %d", totalQuantity);
		    Label qItem = new Label(qntyItem);
		    qItem.setFont(Font.font("Sans Seriff", FontPosture.REGULAR, 8));
		    qItem.setAlignment(Pos.CENTER_LEFT);

		    String prodItem = String.format("Product: %s", cPd.getBrandName());
		    Label pItem = new Label(prodItem);
		    pItem.setFont(Font.font("Sans Seriff", FontPosture.REGULAR, 8));
		    pItem.setAlignment(Pos.CENTER_LEFT);

		    String priceItem = String.format("Price: %.2f", cPd.getPrice());
		    Label pcItem = new Label(priceItem);
		    pcItem.setAlignment(Pos.CENTER_LEFT);
		    pcItem.setFont(Font.font("Sans Seriff", FontPosture.REGULAR, 8));

		    // Spacing product line.
		    HBox rLine = new HBox(100, qItem, pItem, pcItem);

		    tp.getChildren().add(rLine);
		    tp.setAlignment(Pos.CENTER_LEFT);
		    total += (totalQuantity * oldPd.getPrice());
		    totalQuantity = 1;
		    oldPn = cPd.getBrandName();
		    oldPd = cPd;
		    totalEntries++;
		    // Modular gives you remainder example: count % 50
		    if ((tp.getChildren().size() % 50) == 0) {
			totalPages++;
			pageItem.setMaxSize(504, 1000);
			pageItem.setVisible(true);
			String pageId = String.format("Page: %d", pageNodes.size() + 1);
			pageItem.setId(pageId);
			pageItem = new VBox(sBox, line, r, tiles, tp);
			pages.add(pageItem);
			tp = new TilePane();
			tp.setAlignment(Pos.CENTER_LEFT);
			// tp.setVgap(10);
			// tp.setHgap(40);
			tp.setPrefColumns(40);
			tp.setMaxWidth(40);
		    }
		}
	    }
	}
	if (firstTime == true) {
	    total += (totalQuantity * oldPd.getPrice());
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
	subtotalToday.setY(150);
	Text totalToday = new Text(tt);
	totalToday.setFont(Font.font("Arial", FontPosture.REGULAR, 8));
	totalToday.setX(30);
	totalToday.setY(150);

	TilePane adt = new TilePane();

	String at = String.format("Cash                      TENDER: $%.2f\n", getTotalTendered());
	Text amountTendered = new Text(at);
	amountTendered.setFont(Font.font("Arial", FontPosture.REGULAR, 8));
	amountTendered.setX(30);
	amountTendered.setY(150);

	String add = String.format("Tax: $%.2f", taxAmount);
	Text addTax = new Text(add);
	addTax.setFont(Font.font("Arial", FontPosture.REGULAR, 8));
	addTax.setX(30);
	addTax.setY(150);

	String AmountDue = String.format("TOTAL                      $%.2f", totalAmount);
	Text ad = new Text(AmountDue);
	ad.setFont(Font.font("Arial", FontPosture.REGULAR, 10));
	ad.setX(30);
	ad.setY(150);

	String savings = String.format("Total savings today is $%.2f", cust.getCart().getTotalSavings());
	Text totalSavings = new Text(savings);
	totalSavings.setFont(Font.font("Arial", FontPosture.REGULAR, 10));
	totalSavings.setX(30);
	totalSavings.setY(150);

	String change = String.format("Cash                      CHANGE $%.2f",
		getTotalTendered() - cust.getCart().getGrandTotal());
	Text changeDue = new Text(change);
	changeDue.setFont(Font.font("Arial", FontPosture.REGULAR, 8));
	changeDue.setX(30);
	changeDue.setY(150);

	VBox taxpane = new VBox(5, amountTendered, line3, changeDue);
	taxpane.setAlignment(Pos.CENTER_LEFT);

	VBox align = new VBox(subtotalToday, addTax, line2, ad);
	align.getChildren().add(taxpane);
	align.setAlignment(Pos.CENTER_LEFT);

	String thankYouMessage = String.format("%s %s thank you for your purchase today!",
		cust.getmCard().getFirstName(), cust.getmCard().getLastName());
	Text thankYouText = new Text(thankYouMessage);
	thankYouText.setFont(Font.font("Arial", FontPosture.REGULAR, 8));
	subtotalToday.setFont(Font.font("Arial", FontPosture.REGULAR, 8));
	subtotalToday.setX(30);
	subtotalToday.setY(100);
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
	tText.setX(30);
	tText.setY(100);
	HBox tBox = new HBox(tText);
	tBox.setAlignment(Pos.BASELINE_LEFT);
	VBox receiptNode = null;
	if (pages.size() == 0) {
	    receiptNode = new VBox(sBox, line, r, tiles, tp, line1, align, adt, thankYouBox, totalSavings, date);
	} else {
	    receiptNode = new VBox(tp, line1, align, adt, thankYouBox, totalSavings, date);
	}
	receiptNode.setMaxSize(504, 800);
	totalTendered = 0.0;
	String pageId = String.format("Last Page: %d", pageNodes.size());
	receiptNode.setId(pageId);
	pages.add(receiptNode);
	pageNodes.addAll(pages);
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

    public void generateReceipt() {
	print(printNode());
    }

    public static void emailReceipt() {

	final String mustard = String.format("%s@%s", "superstore0502", "gmail.com");
	final String hotdog = "x1y2*axx3b";

	// Recipient's email ID needs to be mentioned.
	String toEmail = cust.getmCard().getEmailAddress();

	//
	System.out.println(toEmail);
	// Assuming you are sending email from localhost

	// Get system properties
	// Properties properties = System.getProperties();

	// Setup mail server
	// properties.setProperty("mail.smtp.host", host);

	Properties properties = new Properties();
	// properties.put(from, to);
	properties.put("mail.smtp.auth", true);
	properties.put("mail.smtp.starttls.enable", true);
	properties.put("mail.smtp.host", "smtp.gmail.com");
	properties.put("mail.smtp.port", "587");
	// properties.put("mail.smtp.socketFactory.class",
	// "javax.net.ssl.SSLSocketFactory");

	// Get the default Session object.
	// Session session = Session.getDefaultInstance(properties);

	Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
	    protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(mustard, hotdog);
	    }
	});
	// Start our mail message
	MimeMessage msg = new MimeMessage(session);
	try {
	    msg.setFrom(new InternetAddress(mustard));
	    msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
	    msg.setSubject("USI SUPER STORE RECIEPT");

	    Multipart emailContent = new MimeMultipart();

	    // Text body part
	    MimeBodyPart textBodyPart = new MimeBodyPart();
	    textBodyPart.setText("Thank you for shopping at the USI Super Store Today! Attached is your reciept.");

	    // Attached body part
	    MimeBodyPart pdfAttachment = new MimeBodyPart();
	    pdfAttachment
		    .attachFile("/usi-git/SoftwareEngineeringTraining/projects/src/main/java/com/usi/emailReceipt.pdf");

	    // Attach body parts
	    emailContent.addBodyPart(textBodyPart);
	    emailContent.addBodyPart(pdfAttachment);

	    // Attach multipart to message
	    msg.setContent(emailContent);

	    Transport.send(msg);
	    System.out.println("Sent message successfully....");
	} catch (MessagingException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
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
	return change;

    }

    public static double isThereChange(double total, String card) {

	double change = 0.0f;
	if (total >= 0.00f) {
	    String.format("Thank you for shopping at the %s today! Your purchase of %.2f has been approved",
		    StoreConstants.STORE_NAME, total);

	}

	return change;

    }

}
