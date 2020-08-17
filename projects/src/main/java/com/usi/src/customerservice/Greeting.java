package customerservice;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import util.DataCsvLoad;
import util.Department;
import util.Product;
import util.StoreConstants;

public class Greeting extends Application {
    private boolean isMember = false;
    private TextField txtCharacter;
    private TextField phoneNumTxt = null;
    private static HBox pHBox = null;
    private VBox primaryPane = null;
    private HashMap<String, MembershipSignUp> membershipCards = null;
    private MembershipSignUp mCard = null;
    private Customer currentCustomer = null;
    private static Stage parentStage = null;
    // List<Department> dList = null;
    private Scene deptsScene = null;
    private List<String> membershipRecords = null;
    private Stage newWindow = null;
    private static Scene scene = null;
    static ComboBox<String> deptComboBox = null;
    static MediaPlayer mPlayer = null;

    /**
     * @return the parentStage
     */
    public static Stage getParentStage() {
	return parentStage;
    }

    /**
     * 
     */
    public Greeting() {
	super();
	membershipCards = new HashMap<String, MembershipSignUp>();
	loadCardRecords();
	loadMemCardRecords();
    }

    public void loadCardRecords() {
	DataCsvLoad loadMembershipRecords = new DataCsvLoad();
	loadMembershipRecords.loadData(StoreConstants.MEMBERSHIPCARD_TRUCK);
	membershipRecords = loadMembershipRecords.getRecords();

    }

    public void loadMemCardRecords() {
	for (String record : membershipRecords) {
	    MembershipSignUp ap = new MembershipSignUp(record);
	    membershipCards.put(ap.getPhoneNumber(), ap);
	}
    }

    public Customer launchUI(String[] args) {
	if (parentStage == null) {
	    launch(args);
	} else {
	    parentStage.setScene(scene);
	}
	currentCustomer = new Customer();
	return currentCustomer;
    }

    public void welcomeScreen(String[] args) {

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
	if (parentStage == null) {
	    parentStage = primaryStage;
	}

	String wcl = String.format("Welcome to %s", StoreConstants.STORE_NAME);
	Text welcomeTxt = new Text(wcl);
	welcomeTxt.setText(wcl);
	welcomeTxt.setX(50.00);
	welcomeTxt.setY(80.00);
	welcomeTxt.setFill(Color.BLUE);
	welcomeTxt.setFont(Font.font("Rockwell", FontPosture.REGULAR, 20));
	HBox gp = new HBox(20, welcomeTxt);
	gp.setAlignment(Pos.CENTER);

	Image entranceImage = new Image(StoreConstants.STORE_ENTRANCE);
	ImageView iv = new ImageView();
	iv.setImage(entranceImage);
	iv.setFitWidth(600);
	iv.setPreserveRatio(true);
	iv.setSmooth(true);
	iv.setCache(true);
	HBox ip = new HBox(iv);
	ip.setAlignment(Pos.CENTER);

	// storeView.setPadding(new Insets(20.50));

	/* Now we perform our rendering */

	Label lblCharacter = new Label();
	lblCharacter.setText("Do you have a membership card:");
	lblCharacter.setMinWidth(100);
	lblCharacter.setAlignment(Pos.CENTER_LEFT);

	Button yesIDo = new Button("Yes");
	EventHandler<ActionEvent> yesEvent = new EventHandler<ActionEvent>() {
	    public void handle(ActionEvent e) {
		isMember = true;
		System.out.println("Customer is a member!");
		getPhoneNumber();

	    }
	};
	yesIDo.setOnAction(yesEvent);

	Button noIDoNot = new Button("No");
	yesIDo.setAlignment(Pos.CENTER);
	noIDoNot.setAlignment(Pos.CENTER);
	EventHandler<ActionEvent> noEvent = new EventHandler<ActionEvent>() {
	    public void handle(ActionEvent e) {
		System.out.println("Customer is NOT a member!");
		isMember = false;
		membershipSignUp();
	    }
	};

	noIDoNot.setOnAction(noEvent);

	HBox paneCharacter = new HBox(20, lblCharacter, yesIDo, noIDoNot);
	paneCharacter.setPadding(new Insets(10));
	// Add the Character and Actor panes to a VBox
	primaryPane = new VBox(10, gp, ip, paneCharacter);
	primaryPane.setAlignment(Pos.CENTER);
	// Create the bindings

	ScrollPane sp = new ScrollPane();
	sp.setContent(primaryPane);
	sp.setPannable(true);
	sp.setHvalue(0.0);
	sp.setVvalue(1.9);

	displayDepts();
	Scene scene = new Scene(sp, 600, 575);

	primaryStage.setScene(scene);
	String screenTitle = String.format("%s - %s", StoreConstants.STORE_NAME, "Greeting");
	primaryStage.getIcons().add(new Image(StoreConstants.SC_ICON_FULL));
	primaryStage.setTitle(screenTitle);
	primaryStage.show();

//	List<String> fontNames = Font.getFontNames();
//	for (String fontName : fontNames) {
//	    welcomeTxt.setFont(Font.font(fontName, FontPosture.REGULAR, 20));
//	    // Thread.sleep(2000);
//	    System.out.println(fontName);
//	}

    }

    private void getPhoneNumber() {

	Label phoneNumberLbl = new Label("Enter Phone Number:");
	phoneNumberLbl.setMinWidth(100);
	phoneNumberLbl.setAlignment(Pos.BOTTOM_RIGHT);
	// Create the Actor text field
	phoneNumTxt = new TextField();
	phoneNumTxt.setMinWidth(200);
	phoneNumTxt.setMaxWidth(200);
	phoneNumTxt.setPromptText("10 digits [0-9], including area code.");
	phoneNumTxt.setFocusTraversable(true);
	phoneNumTxt.requestFocus();
	phoneNumTxt.textProperty().addListener(new ChangeListener<String>() {

	    @Override
	    public void changed(ObservableValue<? extends String> arg0, String oldValue, String newValue) {
		if (!newValue.matches("\\d{0,10}([\\.]\\d{0,4})?")) {
		    phoneNumTxt.setText(oldValue);
		}
		// Lookup membership card using phone number
		if (newValue.length() == 10) {
		    mCard = membershipCards.get(newValue);
		    if (mCard != null) {
			displayMemCard(mCard);
			System.out.println("Membership Card Found!");
		    }
		}
	    }
	});

	HBox panePhoneNum = new HBox(20, phoneNumberLbl, phoneNumTxt);
	panePhoneNum.setPadding(new Insets(10));
	primaryPane.getChildren().add(panePhoneNum);
    }

    /**
     * @param mCard2
     */
    protected void displayMemCard(MembershipSignUp mCard2) {
	String imageKey = String.format(StoreConstants.MCARD, mCard2.getPhoneNumber());
	Image mCardImage = new Image(imageKey);
	Scene mCardScene = null;

	ImageView iv = new ImageView();
	iv.setImage(mCardImage);
	iv.setFitWidth(450);
	iv.setPreserveRatio(true);
	iv.setSmooth(true);
	iv.setCache(true);
	HBox mp = new HBox(iv);
	mp.setAlignment(Pos.CENTER);

	String membershipDate = String.format("Membership Date: %s", mCard2.getDateOfMembership());

	Label mDate = new Label(membershipDate);
	mDate.setAlignment(Pos.CENTER);

	String mString = String.format("Membership Discount: %d%%", StoreConstants.TODAYS_MEMBER_DISCOUNT);
	Label mDiscount = new Label(mString);

	VBox memSignUp = new VBox(5, iv, mDate, mDiscount);

	if (mCard2.isAarpMember()) {
	    String mAarpM = String.format("AARP Discount: %d%%", StoreConstants.AARP_DISCOUNT);
	    Label mAarp = new Label(mAarpM);

	    memSignUp.getChildren().add(mAarp);
	}
	String mEmail = String.format("Email: %s", mCard2.getEmailAddress());
	Label memEmail = new Label(mEmail);
	memSignUp.getChildren().add(memEmail);

	String rigMem = "Is this information correct?";
	Label rightMem = new Label();
	rightMem.setText(rigMem);

	Button yesButton = new Button("Yes");
	yesButton.setAlignment(Pos.CENTER);
	yesButton.setAlignment(Pos.CENTER);

	EventHandler<ActionEvent> yesEvent = new EventHandler<ActionEvent>() {
	    public void handle(ActionEvent e) {
		System.out.println("Welcome! Enjoy your trip!");
		newWindow.close();
		displayDepts();

	    }
	};
	yesButton.setOnAction(yesEvent);

	Button noButton = new Button("No");
	noButton.setAlignment(Pos.CENTER);
	noButton.setAlignment(Pos.CENTER);

	EventHandler<ActionEvent> noEvent = new EventHandler<ActionEvent>() {
	    public void handle(ActionEvent e) {
		System.out.println("Please try again");
		newWindow.close();
	    }
	};
	noButton.setOnAction(noEvent);

	HBox correctMember = new HBox(10, rightMem, yesButton, noButton);
	memSignUp.getChildren().add(correctMember);

	// StackPane secondaryLayout = new StackPane();

	mCardScene = new Scene(memSignUp, 450, 500);

	// New window (Stage)
	newWindow = new Stage();
	newWindow.setTitle(String.format("%s- Membership Card", mCard.getFirstName()));
	newWindow.setScene(mCardScene);

	// Specifies the modality for new window.
	newWindow.initModality(Modality.WINDOW_MODAL);

	// Specifies the owner Window (parent) for new window
	newWindow.initOwner(parentStage);

	// Set position of second window, related to primary window.
	newWindow.setX(parentStage.getX() + 25);
	newWindow.setY(parentStage.getY() + 20);

	newWindow.show();

    }

    private void membershipSignUp() {

	Label lblCharacter = new Label(
		"Would you like to maximize your savings today by signing up for a Membership Card? ");
	lblCharacter.setMinWidth(100);
	lblCharacter.setAlignment(Pos.CENTER_LEFT);

	Button yesIDo = new Button("Yes");
	EventHandler<ActionEvent> signUpEvent = new EventHandler<ActionEvent>() {
	    public void handle(ActionEvent e) {
		isMember = true;
		newMembership();
	    }
	};
	yesIDo.setOnAction(signUpEvent);

	Button noIDoNot = new Button("No");
	yesIDo.setAlignment(Pos.CENTER);
	noIDoNot.setAlignment(Pos.CENTER);
	EventHandler<ActionEvent> noSignUpEvent = new EventHandler<ActionEvent>() {
	    public void handle(ActionEvent e) {
		declineMembership();
		isMember = false;
	    }
	};

	noIDoNot.setOnAction(noSignUpEvent);

	HBox paneMembershipSignUp = new HBox(20, lblCharacter, yesIDo, noIDoNot);
	paneMembershipSignUp.setPadding(new Insets(10));

	primaryPane.getChildren().add(paneMembershipSignUp);
	return;
    }

    private void declineMembership() {
	String declineMessage = String.format(
		"Ok, well you can signup at any time in the future and instantly save %d%%. Happy Shopping!",
		StoreConstants.TODAYS_MEMBER_DISCOUNT);

	Label lblCharacter = new Label(declineMessage);
	lblCharacter.setMinWidth(100);
	lblCharacter.setAlignment(Pos.CENTER_LEFT);

	HBox paneDeclineSignUp = new HBox(30, lblCharacter);
	paneDeclineSignUp.setPadding(new Insets(10));

	primaryPane.getChildren().add(paneDeclineSignUp);
	return;
    }

    private void newMembership() {

	String wcl = String.format("%s Membership Sign Up", StoreConstants.STORE_NAME);
	Text membershipTxt = new Text(wcl);
	membershipTxt.setText(wcl);
	membershipTxt.setX(50.00);
	membershipTxt.setY(80.00);
	membershipTxt.setFill(Color.BLUE);

	membershipTxt.setFont(Font.font("Rockwell", FontWeight.BOLD, FontPosture.REGULAR, 20));

	HBox gp = new HBox(20, membershipTxt);
	gp.setAlignment(Pos.CENTER);
	gp.setPadding(new Insets(20.50));
	HBox memSignUp = new HBox();
	memSignUp.getChildren().add(membershipTxt);

	Scene signUpScene = new Scene(memSignUp, 450, 600);

	// New window (Stage)
	Stage newWindow = new Stage();
	newWindow.setTitle("Shoppers Super Store - Membership Sign Up");
	newWindow.setScene(signUpScene);

	// Specifies the modality for new window.
	newWindow.initModality(Modality.WINDOW_MODAL);

	// Specifies the owner Window (parent) for new window
	newWindow.initOwner(parentStage);

	// Set position of second window, related to primary window.
	newWindow.setX(parentStage.getX() + 25);
	newWindow.setY(parentStage.getY() + 20);

	newWindow.show();
    }

    /**
     * @param dList
     */
    public void displayDepts() {
	List<Department> dList = SuperStore.getdList();

	Image storeMapImage = new Image(StoreConstants.STORE_MAP);
	ImageView iv = new ImageView();
	iv.setImage(storeMapImage);
	iv.setFitWidth(600);
	iv.setPreserveRatio(true);
	iv.setSmooth(true);
	iv.setCache(true);

	HBox storeMapView = new HBox(iv);
	storeMapView.setAlignment(Pos.CENTER_LEFT);

	System.out.printf("dList length %d\n", dList.size());
	int dsize = dList.size();
	String[] dStrs = new String[dsize];
	int i = 0;
	for (Department dp : dList) {
	    dStrs[i] = dp.getDeptName();
	    i++;
	}

	String hShop = String.format("Happy Shopping!");
	Text happyShoppingTxt = new Text(hShop);
	happyShoppingTxt.setText(hShop);
//	happyShoppingTxt.setX(50.00);
//	happyShoppingTxt.setY(80.00);
	happyShoppingTxt.setFill(Color.BLUE);

	happyShoppingTxt.setFont(Font.font("Rockwell", FontWeight.BOLD, FontPosture.REGULAR, 20));

	deptComboBox = new ComboBox<String>(FXCollections.observableArrayList(dStrs));
	deptComboBox.setValue("Select Department");
	Label selected = new Label("Please Observe MASK On COVID 19 - Social Distancing 6 X 6 ft.");

	EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
	    public void handle(ActionEvent e) {
		String dName = deptComboBox.getValue();
		selected.setText("Scrolling to " + dName + " Department ...");
		displayDepartmentScene(dName);
	    }
	};

	deptComboBox.setOnAction(event);

	TilePane tp = new TilePane(deptComboBox);
	tp.setTileAlignment(Pos.CENTER);

	String sDept = String.format("Choose Department: ");
	Text shoppingTxt = new Text(sDept);
	shoppingTxt.setText(sDept);
	shoppingTxt.setX(50.00);
	shoppingTxt.setY(80.00);
	shoppingTxt.setFill(Color.BLUE);

	shoppingTxt.setFont(Font.font("Rockwell", FontPosture.REGULAR, 20));

	VBox gp0 = new VBox(20, happyShoppingTxt);
	VBox gp1 = new VBox(20, storeMapView, selected);
	HBox gp2 = new HBox(20, shoppingTxt, tp);
	VBox gp = new VBox(20, gp0, gp1, gp2);
	gp0.setAlignment(Pos.CENTER);
	gp1.setAlignment(Pos.CENTER);
	gp2.setAlignment(Pos.CENTER);
	gp.setAlignment(Pos.CENTER);
	gp2.setPadding(new Insets(10));

	deptsScene = new Scene(gp, 600, 575);
	parentStage.setScene(deptsScene);
	parentStage.show();
    }

    /**
     * @param dName
     */
    protected void displayDepartmentScene(String dName) {
	List<Department> dList = SuperStore.getdList();

	// This can be refactored to use a HashMap with a String dName key
	// So you don't have to do a for each loop every time. We will
	// refactor later time permitting.
	for (Department dp : dList) {
	    if (dName.equals(dp.getDeptName())) {
		System.out.printf("Found %s directory\n", dName);
		String dTitle = String.format("%s - %s Department", StoreConstants.STORE_NAME, dName);
		parentStage.setTitle(dTitle);
		Scene dpScene = dp.getScene();
		parentStage.setScene(dp.getScene());
		parentStage.show();
	    }
	}

    }

    public static HBox getBottonDeptButtons() {
	HBox pHbox = new HBox();

	Button cButton = new Button("Checkout");
	EventHandler<ActionEvent> cEvent = new EventHandler<ActionEvent>() {
	    public void handle(ActionEvent e) {
		System.out.println("Time to checkout!");
	    }
	};

	cButton.setOnAction(cEvent);
	cButton.setAlignment(Pos.CENTER);
	Image shoppingCartIcon = new Image(StoreConstants.SC_ICON_EMPTY);
	ImageView iv = new ImageView();
	iv.setImage(shoppingCartIcon);
	// iv.setFitWidth(30);
	iv.setFitHeight(15);
	iv.setPreserveRatio(true);
	iv.setSmooth(true);
	iv.setCache(true);
	Button sCart = new Button("View Cart", iv);

	sCart.setAlignment(Pos.CENTER);
	EventHandler<ActionEvent> scEvent = new EventHandler<ActionEvent>() {
	    public void handle(ActionEvent e) {
		System.out.println("Customer is NOT a member!");
	    }
	};

	sCart.setOnAction(scEvent);

	pHBox = new HBox(20, sCart, deptComboBox, cButton);
	// paneCharacter.setPadding(new Insets(10));

	return pHBox;

    }

    public static void prodDetails(Product inProd, String dept) {

	String pName[] = inProd.getProductName().split(" ");
	String bName[] = inProd.getBrandName().split(" ");
	StringBuilder pNameCat = new StringBuilder();
	for (String pN : pName) {
	    pNameCat.append(pN);
	}
	StringBuilder bNameCat = new StringBuilder();
	for (String bN : bName) {
	    bNameCat.append(bN);
	}
	System.out.printf("%s vs. %s\n", inProd.getProductName(), pNameCat);
	System.out.printf("%s vs. %s\n", inProd.getBrandName(), bNameCat);

	String iFileName = String.format(StoreConstants.PRODUCT_IMAGE, dept, inProd.getBrandName(),
		inProd.getProductName());
	String iVideoName = String.format(StoreConstants.PRODUCT_VIDEO, dept, bNameCat, pNameCat);
	System.out.println(iVideoName);
	// Image View
	Image pImage = new Image(iFileName);
	ImageView pV = new ImageView();
	pV.setFitWidth(225);
	// pV.setFitHeight(200);
	// pV.setId(inProd.get );
	pV.setImage(pImage);
	pV.setPreserveRatio(true);
	pV.setSmooth(true);
	pV.setCache(true);

	Button Buy = new Button("Add to cart");
	EventHandler<ActionEvent> addToCartEvent = new EventHandler<ActionEvent>() {
	    public void handle(ActionEvent e) {

		System.out.println("ITEM ADDED TO CART");

	    }
	};
	Buy.setOnAction(addToCartEvent);

	Button howMany = new Button("Quantity");
	Buy.setAlignment(Pos.BOTTOM_CENTER);
	howMany.setAlignment(Pos.BOTTOM_CENTER);
	EventHandler<ActionEvent> howManyEvent = new EventHandler<ActionEvent>() {
	    public void handle(ActionEvent e) {

	    }
	};
	howMany.setOnAction(howManyEvent);

	Integer[] quantity = new Integer[10];
	quantity[0] = 1;
	quantity[1] = 2;
	quantity[2] = 3;
	quantity[3] = 4;
	quantity[4] = 5;
	quantity[5] = 6;
	quantity[6] = 7;
	quantity[7] = 8;
	quantity[8] = 9;
	quantity[9] = 10;

	ChoiceBox<Integer> choose = new ChoiceBox<>();
	choose.getItems().addAll(quantity);
	choose.setValue(1);

	Button closeButton = new Button("Close");
	closeButton.setAlignment(Pos.CENTER);
	// New window (Stage)
	Stage newWindow = new Stage();
	newWindow.getIcons().add(new Image(StoreConstants.SC_ICON_FULL));

	// Description Pane
	VBox descBox = new VBox();
	descBox.setStyle("-fx-background-color: red;");
	descBox.setPrefSize(175, 200);

	// Borders
	BorderWidths bs = new BorderWidths(1, 1, 1, 1);
	Color bColor = Color.GRAY;

	descBox.setBorder(new Border(new BorderStroke(bColor, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, bs)));
	descBox.setAlignment(Pos.CENTER_RIGHT);

	// Banding Pane
	VBox brandingBox = new VBox();
	brandingBox.setStyle("-fx-background-color: yellow;");
	brandingBox.setPrefSize(225, 200);
	brandingBox.setAlignment(Pos.TOP_CENTER);
	brandingBox.setBorder(new Border(new BorderStroke(bColor, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, bs)));

	String vPath = iVideoName;
	String iVideoNameCheck = String.format(StoreConstants.PRODUCT_VIDEO_Check, dept, bNameCat, pNameCat);
	File file = new File(iVideoNameCheck);
	mPlayer = null;
	System.out.printf("%s\n", iVideoNameCheck);
	if (file.exists() == true) {
	    Media vMedia = new Media(vPath);
	    mPlayer = new MediaPlayer(vMedia);
	    mPlayer.setAutoPlay(true);
	    MediaView mView = new MediaView(mPlayer);
	    mView.setFitWidth(225);
	    brandingBox.setAlignment(Pos.TOP_CENTER);
	    brandingBox.getChildren().add(mView);
	}

	EventHandler<ActionEvent> closeEvent = new EventHandler<ActionEvent>() {
	    public void handle(ActionEvent e) {
		if (mPlayer != null) {
		    mPlayer.stop();
		}
		newWindow.close();
	    }
	};
	closeButton.setOnAction(closeEvent);

	VBox iDetailPane = new VBox();
	iDetailPane.setStyle("-fx-background-color: green;");
	iDetailPane.setPrefSize(175, 200);
	iDetailPane.setBorder(new Border(new BorderStroke(bColor, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, bs)));
	iDetailPane.setFillWidth(true);
	iDetailPane.setAlignment(Pos.CENTER_RIGHT);

	VBox mp = new VBox(pV);
	mp.setStyle("-fx-background-color: white;");
	mp.setAlignment(Pos.CENTER_LEFT);
	mp.setBorder(new Border(new BorderStroke(bColor, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, bs)));
	mp.setFillWidth(true);
	HBox pTopPane = new HBox(mp, iDetailPane);
	pTopPane.setAlignment(Pos.CENTER);

	HBox pButtons = new HBox(30, Buy, howMany, choose, closeButton);
	pButtons.setStyle("-fx-background-color: gray;");
	pButtons.setAlignment(Pos.CENTER);

	HBox pBottomPane = new HBox(brandingBox, descBox);
	pBottomPane.setAlignment(Pos.BASELINE_CENTER);

	VBox dPane = new VBox(pTopPane, pBottomPane, pButtons);
	dPane.setStyle("-fx-background-color: gray;");
	Scene pScene = new Scene(dPane, 400, 500);

	newWindow.setTitle(String.format("%s- Details", inProd.getProductName()));
	newWindow.setScene(pScene);

	// Specifies the modality for new window.
	newWindow.initModality(Modality.WINDOW_MODAL);

	// Specifies the owner Window (parent) for new window
	newWindow.initOwner(parentStage);
	newWindow.setResizable(false);

	// Set position of second window, related to primary window.
	newWindow.setX(parentStage.getX() + 85);
	newWindow.setY(parentStage.getY() + 50);

	newWindow.show();
    }

}
