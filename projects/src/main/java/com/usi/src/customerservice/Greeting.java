package customerservice;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.print.DocFlavor.URL;

import javafx.application.Application;
import javafx.application.Platform;
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
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
import javafx.stage.WindowEvent;
import smartcart.Receipt;
import smartcart.StoreCheckOut;
import util.DataCsvLoad;
import util.Department;
import util.Product;
import util.StoreConstants;
import util.StoreConstants.paymentType;
import util.YesNoInput;

public class Greeting extends Application {
    static Label phoneNumberLbl = null;
    private TextField phoneNumTxt = null;
    static HBox panePhoneNum = null;
    private static HBox pHBox = null;
    private VBox primaryPane = null;
    private HashMap<String, MembershipSignUp> membershipCards = null;
    private MembershipSignUp mCard = null;
    static private Customer currentCustomer = null;
    private static Stage parentStage = null;
    // List<Department> dList = null;
    private Scene deptsScene = null;
    private List<String> membershipRecords = null;
    private static Stage newWindowPopup = null;
    private static Scene scene = null;
    static ComboBox<String> deptComboBox = null;
    static MediaPlayer mPlayer = null;
    static HBox paneCardNumber = null;
    private static TextField cardNumberTxt = null;
    static Scene paymentScene = null;
    private static ImageView camPhotoView = null;
    static Stage newWindow = null;
    Stage newWindowMembership = null;
    static double money = 0.0;
    static double change = 0.0;
    static double total = 0.0;
    static boolean applied = false;

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
	loadMembershipRecords.loadData(StoreConstants.MEMBERSHIPCARD_RECORDS);
	membershipRecords = loadMembershipRecords.getRecords();

    }

    public void loadMemCardRecords() {
	for (String record : membershipRecords) {
	    MembershipSignUp ap = new MembershipSignUp(record);
	    membershipCards.put(ap.getPhoneNumber(), ap);
	}
    }

    public Customer launchUI(String[] args) {
	currentCustomer = new Customer();
	if (parentStage == null) {
	    launch(args);
	} else {
	    // parentStage.setScene(scene);
	}

	return currentCustomer;
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
		getPhoneNumber();

	    }
	};
	yesIDo.setOnAction(yesEvent);

	Button noIDoNot = new Button("No");
	yesIDo.setAlignment(Pos.CENTER);
	noIDoNot.setAlignment(Pos.CENTER);
	EventHandler<ActionEvent> noEvent = new EventHandler<ActionEvent>() {
	    public void handle(ActionEvent e) {
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
	EventHandler<WindowEvent> closeStageEvent = new EventHandler<WindowEvent>() {
	    public void handle(WindowEvent e) {
		Platform.exit();
		System.exit(0);
	    }
	};
	primaryStage.setOnCloseRequest(closeStageEvent);

	scene = new Scene(sp, 600, 575);

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
			if (currentCustomer != null) {
			    currentCustomer = new Customer();
			    money = 0.0;
			    change = 0.00f;
			    total = 0.0;
			    applied = false;
			    currentCustomer.getCart().resetShoppingCart();
			    currentCustomer.resetAmountPaid();
			}
			currentCustomer.setmCard(mCard);
			displayMemCard(mCard);
		    }
		}
	    }
	});
	primaryPane.getChildren().remove(panePhoneNum);
	total = 0.0;
	panePhoneNum = new HBox(20, phoneNumberLbl, phoneNumTxt);
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

	String mString = String.format("Membership Discount: %.2f%%", StoreConstants.TODAYS_MEMBER_DISCOUNT);
	Label mDiscount = new Label(mString);

	VBox memSignUp = new VBox(5, iv, mDate, mDiscount);

	if (mCard2.isAarpMember()) {
	    String mAarpM = String.format("AARP Discount: %.2f%%", StoreConstants.AARP_DISCOUNT);
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
		newWindowPopup.close();
		displayDepts();

	    }
	};
	yesButton.setOnAction(yesEvent);

	Button noButton = new Button("No");
	noButton.setAlignment(Pos.CENTER);
	noButton.setAlignment(Pos.CENTER);

	EventHandler<ActionEvent> noEvent = new EventHandler<ActionEvent>() {
	    public void handle(ActionEvent e) {
		newWindowPopup.close();
	    }
	};
	noButton.setOnAction(noEvent);

	HBox correctMember = new HBox(10, rightMem, yesButton, noButton);
	memSignUp.getChildren().add(correctMember);

	// StackPane secondaryLayout = new StackPane();

	mCardScene = new Scene(memSignUp, 450, 500);

	// New window (Stage)
	newWindowPopup = new Stage();
	newWindowPopup.setTitle(String.format("%s- Membership Card", mCard.getFirstName()));
	newWindowPopup.setScene(mCardScene);

	// Specifies the modality for new window.
	newWindowPopup.initModality(Modality.WINDOW_MODAL);

	// Specifies the owner Window (parent) for new window
	newWindowPopup.initOwner(parentStage);

	// Set position of second window, related to primary window.
	newWindowPopup.setX(parentStage.getX() + 25);
	newWindowPopup.setY(parentStage.getY() + 20);

	newWindowPopup.show();

    }

    private void membershipSignUp() {

	Label lblCharacter = new Label(
		"Would you like to maximize your savings today by signing up for a Membership Card? ");
	lblCharacter.setMinWidth(100);
	lblCharacter.setAlignment(Pos.CENTER_LEFT);

	Button yesIDo = new Button("Yes");
	EventHandler<ActionEvent> signUpEvent = new EventHandler<ActionEvent>() {
	    public void handle(ActionEvent e) {
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

	membershipTxt.setFont(Font.font("Rockwell", FontWeight.BOLD, FontPosture.REGULAR, 14));

	// collecting membership data
	MembershipSignUp newMemberCard = new MembershipSignUp();

	// first name
	Text fNText = new Text("First Name: ");

	TextField fNTextField = new TextField();
	fNTextField.setPromptText("Please enter first name: ");
	HBox firstNameBox = new HBox(fNText, fNTextField);
	fNTextField.textProperty().addListener(new ChangeListener<String>() {

	    @Override
	    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		newMemberCard.setFirstName(fNTextField.getText());
	    }

	});

	HBox gp = new HBox(20, membershipTxt);
	gp.setAlignment(Pos.CENTER);
	gp.setPadding(new Insets(20.50));

	// middle initial
	Text mIText = new Text("Middle Initial Name: ");

	TextField mITextField = new TextField();
	mITextField.setPromptText("Please enter middle initial: ");
	HBox middleInitialBox = new HBox(mIText, mITextField);
	mITextField.textProperty().addListener(new ChangeListener<String>() {

	    @Override
	    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
//		newMemberCard.setmInitial(mITextField.getText());
	    }

	});

	// last name
	Text lNText = new Text("Last Name: ");

	TextField lNTextField = new TextField();
	lNTextField.setPromptText("Please enter last name: ");
	HBox lastNameBox = new HBox(lNText, lNTextField);
	lNTextField.textProperty().addListener(new ChangeListener<String>() {

	    @Override
	    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		newMemberCard.setLastName(lNTextField.getText());
	    }

	});

	// email address
	Text eAText = new Text("Email Address: ");

	TextField eATextField = new TextField();
	eATextField.setPromptText("Please enter email address: ");
	HBox emailAddressBox = new HBox(eAText, eATextField);
	eATextField.textProperty().addListener(new ChangeListener<String>() {

	    @Override
	    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		newMemberCard.setEmailAddress(eATextField.getText());
	    }

	});

	// street
	Text sText = new Text("Street: ");

	TextField sTextField = new TextField();
	sTextField.setPromptText("Please enter street: ");
	HBox streetBox = new HBox(sText, sTextField);
	sTextField.textProperty().addListener(new ChangeListener<String>() {

	    @Override
	    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		newMemberCard.setStreetAddress(sTextField.getText());
	    }

	});

	// city
	Text cText = new Text("City: ");

	TextField cTextField = new TextField();
	cTextField.setPromptText("Please enter city: ");
	HBox cityBox = new HBox(cText, cTextField);
	cTextField.textProperty().addListener(new ChangeListener<String>() {

	    @Override
	    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		newMemberCard.setCity(cTextField.getText());
	    }

	});

	// state
	Text stateText = new Text("State: ");

	TextField stateTextField = new TextField();
	stateTextField.setPromptText("Please enter state: ");
	HBox stateBox = new HBox(stateText, stateTextField);
	stateTextField.textProperty().addListener(new ChangeListener<String>() {

	    @Override
	    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		newMemberCard.setState(sTextField.getText());
	    }

	});

	// postal code
	Text pCText = new Text("Postal Code: ");

	TextField pCTextField = new TextField();
	pCTextField.setPromptText("Please enter postal code: ");
	HBox postalCodeBox = new HBox(pCText, pCTextField);
	pCTextField.textProperty().addListener(new ChangeListener<String>() {

	    @Override
	    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		newMemberCard.setPostalCode(pCTextField.getText());
	    }

	});

	// phone number
	Text pNText = new Text("Phone Number: ");

	TextField pNTextField = new TextField();
	pNTextField.setPromptText("Please enter phone number: ");
	pNTextField.textProperty().addListener(new ChangeListener<String>() {
	    @Override
	    public void changed(ObservableValue<? extends String> arg0, String oldValue, String newValue) {
		if (!newValue.matches("\\d{0,10}([\\.]\\d{0,4})?")) {
		    pNTextField.setText(oldValue);
		}
		// Lookup membership card using phone number
		if (newValue.length() == 10) {
		    newMemberCard.setPhoneNumber(pNTextField.getText());
		}
	    }
	});
	HBox phoneNumberBox = new HBox(pNText, pNTextField);

	// Aarp
	Text AarpText = new Text("Are you an AARP Mmeber?: ");

	TextField AarpTextField = new TextField();
	AarpTextField.setPromptText("Yes/No: ");
	HBox AarpTextBox = new HBox(AarpText, AarpTextField);
	boolean AarpMem = YesNoInput.stringToBoolean(AarpTextField.getText());
	newMemberCard.setAarpMember(AarpMem);
	VBox memSignUp = new VBox(gp, firstNameBox, middleInitialBox, lastNameBox, emailAddressBox, streetBox, cityBox,
		stateBox, postalCodeBox, phoneNumberBox, AarpTextBox);

	newMemberCard.setDateOfMembership(new Date());

	Button cameraButton = new Button("Take Picture");
	cameraButton.setAlignment(Pos.CENTER);
	cameraButton.setAlignment(Pos.CENTER);
	Image defaultImage = new Image(StoreConstants.MEMBERSHIP_DEFAULT_PROFILE_IMAGE);
	// defaultImage = null;
	camPhotoView = new ImageView(defaultImage);
	camPhotoView.setImage(defaultImage);
	camPhotoView.setFitWidth(176);
	camPhotoView.setFitHeight(144);

	EventHandler<ActionEvent> takePhotoEvent = new EventHandler<ActionEvent>() {
	    public void handle(ActionEvent e) {
		Image pImage = newMemberCard.takePhoto();
		if (pImage == null) {
		    camPhotoView.setImage(defaultImage);
		} else {
		    camPhotoView.setImage(pImage);
		}
	    }
	};

	cameraButton.setOnAction(takePhotoEvent);
	TextArea pText = new TextArea();
	String photoIns = String.format(
		"Press the \"Take Picture\" button below (3 second delay).  If you don't like your photo click again until you like it. \n\nPress \"Save\" button to complete the Sign Up process.  Your card will arrive in 5 - 10 Business days. "
			+ " Meanwhile use your phone number.\nWelcome to the %s Family.",
		StoreConstants.STORE_NAME, StoreConstants.TODAYS_MEMBER_DISCOUNT);
	pText.setText(photoIns);
	pText.setWrapText(true);
	HBox viewPane = new HBox(camPhotoView);
	viewPane.setPadding(new Insets(0, 10, 0, 0));

	viewPane.setStyle("-fx-background-color: black;");
	viewPane.setAlignment(Pos.CENTER_LEFT);
	HBox photoPane = new HBox(10, pText, viewPane);
	photoPane.setAlignment(Pos.CENTER);
	photoPane.setStyle("-fx-background-color: black;");
	Button cancelButton = new Button("Cancel");
	EventHandler<ActionEvent> cancelEvent = new EventHandler<ActionEvent>() {
	    public void handle(ActionEvent e) {
		newWindowMembership.close();
	    }
	};
	cancelButton.setOnAction(cancelEvent);

	Button saveButton = new Button("Save");
	EventHandler<ActionEvent> saveEvent = new EventHandler<ActionEvent>() {
	    public void handle(ActionEvent e) {
		// This is when we will write out the new member data.
		membershipRecord(newMemberCard);
		newWindowMembership.close();
	    }
	};

	saveButton.setOnAction(saveEvent);
	HBox photoButtons = new HBox(20, cameraButton, cancelButton);
	photoButtons.setAlignment(Pos.CENTER);

	HBox saveBox = new HBox(20, saveButton);
	saveBox.setAlignment(Pos.CENTER);

	memSignUp.getChildren().add(photoPane);
	memSignUp.getChildren().add(photoButtons);
	memSignUp.getChildren().add(saveBox);
	Scene signUpScene = new Scene(memSignUp, 450, 600);
	newWindowMembership = new Stage();
	newWindowMembership.setScene(signUpScene);

	// Specifies the modality for new window.
	newWindowMembership.initModality(Modality.WINDOW_MODAL);

	// Specifies the owner Window (parent) for new window
	newWindowMembership.initOwner(parentStage);

	// Set position of second window, related to primary window.
	newWindowMembership.setX(parentStage.getX() + 25);
	newWindowMembership.setY(parentStage.getY() + 20);

	newWindowMembership.show();
    }

    public void membershipRecord(MembershipSignUp newMemberCard) {

	BufferedWriter bw = null;
	FileWriter fw = null;
	// First name, Middle Initial, Last Name, email address, street, city, state,
	// zipcode, phone number, aarp membership, Membership Date
	String formattedRecord = String.format(StoreConstants.MEMBERSHIPCARD_RECORD_FORMATTER,
		newMemberCard.getFirstName().toString(), newMemberCard.getmInitial(),
		newMemberCard.getLastName().toString(), newMemberCard.getEmailAddress().toString(),
		newMemberCard.getStreetAddress().toString(), newMemberCard.getCity().toString(),
		newMemberCard.getState().toString(), newMemberCard.getPostalCode().toString(),
		newMemberCard.getPhoneNumber().toString(), newMemberCard.isAarpMember(),
		newMemberCard.getDateOfMembership().toString());
	try {

	    fw = new FileWriter(StoreConstants.MEMBERSHIPCARD_RECORDS, true);
	    bw = new BufferedWriter(fw);
	    bw.write(formattedRecord);
	    bw.close();
	    System.out.println(formattedRecord);
	} catch (IOException ex) {
	    System.err.format("IOException: %s", ex);
	} finally {
	    try {
		if (bw != null)
		    bw.close();

		if (fw != null)
		    fw.close();
	    } catch (IOException ex) {
		System.err.format("IOException: %s", ex);
	    }
	}

    }

    /**
     * @param newMemberCard
     */
    protected void newMemberCard(MembershipSignUp newMemberCard) {
	// TODO Auto-generated method stub

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
		String dTitle = String.format("%s - %s Department", StoreConstants.STORE_NAME, dName);
		parentStage.setTitle(dTitle);
		parentStage.setScene(dp.getScene());
		parentStage.show();
	    }
	}

    }

    public static HBox getBottonDeptButtons() {
	Button cButton = new Button("Checkout");

	EventHandler<ActionEvent> cEvent = new EventHandler<ActionEvent>() {
	    public void handle(ActionEvent e) {

		// resetParentStage();
		parentStage.close();
		currentCustomer.cart.setGrandTotal(total);
		paymentPreference();

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
		viewCart();
	    }
	};

	sCart.setOnAction(scEvent);

	pHBox = new HBox(20, sCart, deptComboBox, cButton);
	// paneCharacter.setPadding(new Insets(10));

	return pHBox;

    }
//
//    static private void resetParentStage() {
//	phoneNumTxt.setVisible(false);
//	phoneNumberLbl.setVisible(false);
//    }

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

	String iFileName = String.format(StoreConstants.PRODUCT_IMAGE, dept, inProd.getBrandName(),
		inProd.getProductName());
	String iVideoName = String.format(StoreConstants.PRODUCT_VIDEO, dept, bNameCat, pNameCat);
	// System.out.printf("%s\n%s\n", iFileName, iVideoName);
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

	Integer[] quantity = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };

	ChoiceBox<Integer> choose = new ChoiceBox<>();
	choose.getItems().addAll(quantity);
	choose.setValue(1);

	Button howMany = new Button("Quantity");

	howMany.setAlignment(Pos.BOTTOM_CENTER);
	EventHandler<ActionEvent> howManyEvent = new EventHandler<ActionEvent>() {
	    public void handle(ActionEvent e) {

	    }
	};
	howMany.setOnAction(howManyEvent);

	Button closeButton = new Button("Close");
	closeButton.setAlignment(Pos.CENTER);
	// New window (Stage)
	Stage newWindowProd = new Stage();
	newWindowProd.getIcons().add(new Image(StoreConstants.SC_ICON_FULL));

	Button Buy = new Button("Add to cart");
	EventHandler<ActionEvent> addToCartEvent = new EventHandler<ActionEvent>() {
	    public void handle(ActionEvent e) {
		currentCustomer.addProduct(inProd, choose.getValue());
		if (mPlayer != null) {
		    mPlayer.stop();
		}
		newWindowProd.close();
	    }
	};
	Buy.setOnAction(addToCartEvent);
	Buy.setAlignment(Pos.BOTTOM_CENTER);
	// Description Pane
	VBox descBox = new VBox();
	descBox.setStyle("-fx-background-color: cadetblue;");
	descBox.setPrefSize(375, 200);

	Label deslblCharacter = new Label("Description ");
	deslblCharacter.setFont(Font.font("Lucinda Sans", FontWeight.BOLD, FontPosture.REGULAR, 14));
	deslblCharacter.setMinWidth(100);
	deslblCharacter.setAlignment(Pos.TOP_LEFT);

	deslblCharacter.setFont(Font.font("Lucinda Sans", FontWeight.NORMAL, FontPosture.REGULAR, 14));
	deslblCharacter.setTextFill(Color.BLACK);
	TextArea desText = new TextArea(inProd.getDescription());
	desText.setMaxWidth(375);
	desText.setWrapText(true);
	ScrollPane dp = new ScrollPane(desText);

	// Ingredients VBox with label and scroll bar
	VBox ingredBox = new VBox();
	ingredBox.setStyle("-fx-background-color: cadetblue;");
	ingredBox.setPrefSize(375, 200);
	Label lblCharacter = new Label("Ingrediants ");
	lblCharacter.setMinWidth(100);
	lblCharacter.setAlignment(Pos.TOP_LEFT);
	lblCharacter.setFont(Font.font("Lucinda Sans", FontWeight.NORMAL, FontPosture.REGULAR, 14));
	lblCharacter.setTextFill(Color.BLACK);
	TextArea IText = new TextArea(inProd.getIngredient());
	IText.setMaxWidth(375);
	IText.setWrapText(true);
	ScrollPane sp = new ScrollPane(IText);
	descBox.setAlignment(Pos.TOP_LEFT);
	descBox.setPadding(new Insets(5));
	descBox.getChildren().add(deslblCharacter);
	descBox.getChildren().add(dp);
	descBox.getChildren().add(lblCharacter);
	descBox.getChildren().add(sp);

	// create label vbox for descrip

	// Borders
	BorderWidths bs = new BorderWidths(2, 2, 2, 2);
	Color bColor = Color.GRAY;

	descBox.setBorder(new Border(new BorderStroke(bColor, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, bs)));
	descBox.setAlignment(Pos.TOP_LEFT);

	// Branding Pane
	VBox brandingBox = new VBox();
	brandingBox.setStyle("-fx-background-color: black;");
	brandingBox.setPrefSize(225, 200);
	brandingBox.setAlignment(Pos.CENTER);
	brandingBox.setBorder(new Border(new BorderStroke(bColor, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, bs)));

	String vPath = iVideoName;
	String iVideoNameCheck = String.format(StoreConstants.PRODUCT_VIDEO_Check, dept, bNameCat, pNameCat);
	File file = new File(iVideoNameCheck);
	mPlayer = null;
	if (file.exists() == true) {
	    Media vMedia = new Media(vPath);
	    mPlayer = new MediaPlayer(vMedia);
	    mPlayer.setAutoPlay(true);
	    MediaView mView = new MediaView(mPlayer);
	    mView.setFitWidth(225);
	    brandingBox.setAlignment(Pos.CENTER);
	    brandingBox.getChildren().add(mView);
	}

	EventHandler<ActionEvent> closeEvent = new EventHandler<ActionEvent>() {
	    public void handle(ActionEvent e) {
		if (mPlayer != null) {
		    mPlayer.stop();
		}
		newWindowProd.close();
	    }
	};
	closeButton.setOnAction(closeEvent);

	VBox iDetailPane = new VBox(5);
	iDetailPane.setStyle("-fx-background-color: cadetblue;");
	iDetailPane.setPrefSize(375, 200);
	iDetailPane.setBorder(new Border(new BorderStroke(bColor, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, bs)));
	iDetailPane.setFillWidth(true);
	iDetailPane.setAlignment(Pos.TOP_LEFT);
	iDetailPane.setPadding(new Insets(5));

	// Product Name.
	String prNameString = String.format("Product Name: %s", inProd.getProductName());
	Text prName = new Text(prNameString);
	prName.setFont(Font.font("Lucinda Sans", FontWeight.BOLD, FontPosture.REGULAR, 14));
	// Brand Name
	String brNameString = String.format("Brand Name: %s", inProd.getBrandName());
	Text brName = new Text(brNameString);
	brName.setFont(Font.font("Lucinda Sans", FontWeight.BOLD, FontPosture.REGULAR, 14));
	// Price
	String priceString = String.format("Price: %.2f", inProd.getPrice());
	Text price = new Text(priceString);
	price.setFont(Font.font("Lucinda Sans", FontWeight.BOLD, FontPosture.REGULAR, 14));
	// Unit of Measure
	String uomString = String.format("Unit of Measure: %s", inProd.getUnitOfMeasureEnum().toString());
	Text uom = new Text(uomString);
	// Size
	String sizeString = String.format("Size: %s", inProd.getSize());
	Text size = new Text(sizeString);
	// Weight
	String wghtString = String.format("Weight: %s", inProd.getWeight());
	Text wght = new Text(wghtString);
	// Units in Stock
	String uisString = String.format("Units in Stock: %s", inProd.getNumUnitsInstock());
	Text uis = new Text(uisString);

	iDetailPane.getChildren().add(prName);
	iDetailPane.getChildren().add(brName);
	iDetailPane.getChildren().add(size);
	iDetailPane.getChildren().add(uom);
	iDetailPane.getChildren().add(wght);
	iDetailPane.getChildren().add(uis);
	iDetailPane.getChildren().add(price);

	// Media Player Branding Box
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
	pBottomPane.setAlignment(Pos.CENTER);

	VBox dPane = new VBox(pTopPane, pBottomPane, pButtons);
	dPane.setStyle("-fx-background-color: gray;");
	Scene pScene = new Scene(dPane, 600, 550);
	// URL css = new URL(StoreConstants.STYLE1);
	// pScene.getStylesheets().add(css.getClass().getResource(StoreConstants.STYLE1).toExternalForm());
	// pScene.getStylesheets().add("rcorners");
	newWindowProd.setTitle(String.format("%s- Details", inProd.getProductName()));
	newWindowProd.setScene(pScene);

	// Specifies the modality for new window.
	newWindowProd.initModality(Modality.WINDOW_MODAL);

	// Specifies the owner Window (parent) for new window
	newWindowProd.initOwner(parentStage);
	newWindowProd.setResizable(false);

	// Set position of second window, related to primary window.
	newWindowProd.setX(parentStage.getX() + 85);
	newWindowProd.setY(parentStage.getY() + 50);
	newWindowProd.show();

    }

    public static void paymentPreference() {

	if (change < 0) {
	    total = Math.abs(change);
	} else {
	    total = currentCustomer.cartTotal();
	}
	if (applied == false) {
	    // total - aarp membership discount
	    if (currentCustomer.mCard.isAarpMember() == true) {
		total = total - currentCustomer.cart.getAarpDiscount();
	    }
	    // total - membership discount
	    if (currentCustomer.mCard != null) {
		total = total - currentCustomer.cart.getMembershipDiscount();
	    }

	    // total + taxes (after all discounts have applied.
	    total = (total + currentCustomer.cart.getTaxesTotal());
	    applied = true;
	}
	String pKey = String.format("Total $%.2f", total);
	Text welcomeTxt = new Text(pKey);
	welcomeTxt.setText(pKey);
	welcomeTxt.setX(50.00);
	welcomeTxt.setY(80.00);
	welcomeTxt.setFill(Color.WHITE);
	welcomeTxt.setFont(Font.font("Verdana", FontPosture.REGULAR, 20));
	HBox pg = new HBox(20, welcomeTxt);
	pg.setAlignment(Pos.CENTER);

	String cashierImage = StoreConstants.COVID_CASHIER;

	Image ccImage = new Image(cashierImage);
	ImageView ccV = new ImageView();
	ccV.setFitWidth(400);
	// pV.setFitHeight(200);
	// pV.setId(inProd.get );
	ccV.setImage(ccImage);
	ccV.setPreserveRatio(true);
	ccV.setSmooth(true);
	ccV.setCache(true);
	HBox ccp = new HBox(20, ccV);
	ccp.setAlignment(Pos.CENTER);

	VBox ccBox = new VBox(20, pg, ccp);
	ccBox.setAlignment(Pos.CENTER);

	Label swipe = new Label("Please choose your payment method");
	swipe.setAlignment(Pos.BOTTOM_CENTER);

	// HBox ccBox = new HBox(ccV);

	Image vImage = new Image(StoreConstants.SC_ICON_VISA);
	ImageView vV = new ImageView();
	vV.setFitWidth(25);
	// pV.setFitHeight(200);
	// pV.setId(inProd.get );
	vV.setImage(vImage);
	vV.setPreserveRatio(true);
	vV.setSmooth(true);
	vV.setCache(true);

	Image mImage = new Image(StoreConstants.SC_ICON_MASTERCARD);
	ImageView mV = new ImageView();
	mV.setFitWidth(25);
	// pV.setFitHeight(200);
	// pV.setId(inProd.get );
	mV.setImage(mImage);
	mV.setPreserveRatio(true);
	mV.setSmooth(true);
	mV.setCache(true);

	Image eImage = new Image(StoreConstants.SC_ICON_EBT);
	ImageView eV = new ImageView();
	eV.setFitWidth(25);
	// pV.setFitHeight(200);
	// pV.setId(inProd.get );
	eV.setImage(eImage);
	eV.setPreserveRatio(true);
	eV.setSmooth(true);
	eV.setCache(true);

	Image cImage = new Image(StoreConstants.SC_ICON_CASH);
	ImageView cV = new ImageView();
	cV.setFitWidth(25);
	// pV.setFitHeight(200);
	// pV.setId(inProd.get );
	cV.setImage(cImage);
	cV.setPreserveRatio(true);
	cV.setSmooth(true);
	cV.setCache(true);

	Button visa = new Button("VISA", vV);
	EventHandler<ActionEvent> visaEvent = new EventHandler<ActionEvent>() {
	    public void handle(ActionEvent e) {
		VBox cBox = addCardPaymentInput(paymentType.VISA);
		ccBox.getChildren().add(cBox);
	    }
	};
	visa.setOnAction(visaEvent);

	Button mc = new Button("MASTERCARD", mV);
	visa.setAlignment(Pos.BOTTOM_CENTER);
	mc.setAlignment(Pos.BOTTOM_CENTER);
	EventHandler<ActionEvent> mastercardEvent = new EventHandler<ActionEvent>() {
	    public void handle(ActionEvent e) {
		VBox cBox = addCardPaymentInput(paymentType.MASTERCARD);
		ccBox.getChildren().add(cBox);
	    }
	};
	mc.setOnAction(mastercardEvent);

	Button ebt = new Button("EBT", eV);
	ebt.setAlignment(Pos.BOTTOM_CENTER);
	EventHandler<ActionEvent> ebtEvent = new EventHandler<ActionEvent>() {
	    public void handle(ActionEvent e) {
		VBox cBox = addCardPaymentInput(paymentType.EBT);
		ccBox.getChildren().add(cBox);
	    }
	};

	ebt.setOnAction(ebtEvent);

	Button cash = new Button("CASH", cV);
	cash.setAlignment(Pos.BOTTOM_CENTER);
	EventHandler<ActionEvent> cashEvent = new EventHandler<ActionEvent>() {
	    public void handle(ActionEvent e) {
		VBox cBox = addCashPaymentInput(paymentType.CASH);
		ccBox.getChildren().add(cBox);

	    }

	};

	cash.setOnAction(cashEvent);
	if (newWindow == null) {
	    newWindow = new Stage();
	} else {
	    newWindow.close();
	    newWindow = new Stage();
	}

	newWindow.getIcons().add(new Image(StoreConstants.SC_ICON_FULL));
	HBox swipeBox = new HBox(swipe);
	swipeBox.setAlignment(Pos.BOTTOM_CENTER);
	ccBox.getChildren().add(swipeBox);
	ccBox.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #dc143c, #32cd32);");
	HBox payButtons = new HBox(10, visa, mc, ebt, cash);
	payButtons.setAlignment(Pos.BOTTOM_CENTER);
	payButtons.setSpacing(30);
	payButtons.setPadding(new Insets(15, 0, 15, 0));
	payButtons.setAlignment(Pos.BOTTOM_CENTER);

	ccBox.getChildren().add(payButtons);
	paymentScene = new Scene(ccBox, 500, 550);

	// Need to configure way to link running total to this method
	newWindow.setTitle(String.format("Payment Transaction"));
	newWindow.setScene(paymentScene);

	// Specifies the modality for new window.
	newWindow.initModality(Modality.WINDOW_MODAL);

	// Specifies the owner Window (parent) for new window
	newWindow.initOwner(parentStage);
	newWindow.setResizable(false);

	newWindow.show();
    }

    private static VBox addCardPaymentInput(paymentType pt) {
	Label cardNumberLbl = new Label("Please Enter Your 16 digit Card Number :");
	cardNumberLbl.setMinWidth(100);
	cardNumberLbl.setAlignment(Pos.BOTTOM_RIGHT);
	// Create the Actor text field
	cardNumberTxt = new TextField();
	cardNumberTxt.setMinWidth(200);
	cardNumberTxt.setMaxWidth(200);
	cardNumberTxt.setPromptText("Enter 16 digit Card Number Here.");
	cardNumberTxt.setFocusTraversable(true);
	cardNumberTxt.requestFocus();
	cardNumberTxt.textProperty().addListener(new ChangeListener<String>() {
	    @Override
	    public void changed(ObservableValue<? extends String> arg0, String oldValue, String newValue) {
		if (!newValue.matches("\\d{0,16}([\\.]\\d{0,4})?")) {
		    cardNumberTxt.setText(newValue);
		}
		// Lookup membership card using phone number
		if (newValue.length() == 16) {
		    cardNumberTxt.setText(newValue);

		}
	    }
	});

	Image payI = new Image(StoreConstants.SC_ICON_PAY);
	ImageView pi = new ImageView();
	pi.setImage(payI);
	pi.setFitWidth(20);
	pi.setPreserveRatio(true);
	pi.setSmooth(true);
	pi.setCache(true);

	HBox payNode = new HBox();
	Button payButton = new Button("PAY", pi);
	payNode.getChildren().add(payButton);
	payNode.setAlignment(Pos.BOTTOM_CENTER);
	EventHandler<ActionEvent> payEvent = new EventHandler<ActionEvent>() {
	    public void handle(ActionEvent e) {
		doYouHaveChangePopUpWindow(Double.parseDouble(cardNumberTxt.getText()), pt);
		newWindowPopup.close();
	    }
	};
	payButton.setOnAction(payEvent);

	Image cancelImage = new Image(StoreConstants.SC_ICON_CANCEL);
	ImageView cancelV = new ImageView();
	cancelV.setFitWidth(25);
	cancelV.setImage(cancelImage);
	cancelV.setPreserveRatio(true);
	cancelV.setSmooth(true);
	cancelV.setCache(true);

	Button cancel = new Button("cancel", cancelV);
	cancel.setAlignment(Pos.BOTTOM_CENTER);
	payNode.getChildren().add(cancel);
	EventHandler<ActionEvent> cancelTransaction = new EventHandler<ActionEvent>() {
	    public void handle(ActionEvent e) {
		newWindowPopup.close();
	    }
	};

	cancel.setOnAction(cancelTransaction);

	HBox cashInfo = new HBox(cardNumberLbl, cardNumberTxt);
	VBox cardBox = new VBox();
	cardBox.getChildren().add(cashInfo);
	cardBox.getChildren().add(payNode);

	return cardBox;
    }

    private static VBox addCashPaymentInput(paymentType pt) {
	Label cashLbl = new Label("Please tender CASH amount:");
	cashLbl.setMinWidth(100);
	cashLbl.setAlignment(Pos.BOTTOM_RIGHT);
	// Create the Actor text field
	TextField cashTxt = new TextField();
	cashTxt.setMinWidth(200);
	cashTxt.setMaxWidth(200);
	cashTxt.setPromptText("Please enter cash amount here");
	cashTxt.setFocusTraversable(true);
	cashTxt.requestFocus();

	EventHandler<KeyEvent> cashKeyEvent = new EventHandler<KeyEvent>() {

	    public void handle(KeyEvent ke) {
		if (ke.getCode().equals(KeyCode.ENTER) && ke.getText().length() > 0) {
		    // System.out.printf("Cash Value Entered: %s\n", cashTxt.getText());
		    money = Double.parseDouble(cashTxt.getText());
		}
	    }
	};
	cashTxt.setOnKeyPressed(cashKeyEvent);
	// System.out.printf("Cash Value: %.2f\n", money);

	Image payI = new Image(StoreConstants.SC_ICON_PAY);
	ImageView pi = new ImageView();
	pi.setImage(payI);
	pi.setFitWidth(20);
	pi.setFitHeight(30);
	pi.setPreserveRatio(true);
	pi.setSmooth(true);
	pi.setCache(true);

	HBox payNode = new HBox();
	Button payButton = new Button("PAY", pi);
	payNode.getChildren().add(payButton);
	payNode.setAlignment(Pos.BOTTOM_CENTER);
	EventHandler<ActionEvent> payEvent = new EventHandler<ActionEvent>() {
	    public void handle(ActionEvent e) {
		money = Double.parseDouble(cashTxt.getText());
		currentCustomer.setAmountPaid(money);
		newWindowPopup.close();
		// newWindow.close();
		doYouHaveChangePopUpWindow(money, pt);

	    }
	};
	payButton.setOnAction(payEvent);

	Image cancelImage = new Image(StoreConstants.SC_ICON_CANCEL);
	ImageView cancelV = new ImageView();
	cancelV.setFitWidth(25);
	cancelV.setImage(cancelImage);
	cancelV.setPreserveRatio(true);
	cancelV.setSmooth(true);
	cancelV.setCache(true);

	Button cancel = new Button("cancel", cancelV);
	cancel.setAlignment(Pos.BOTTOM_CENTER);
	payNode.getChildren().add(cancel);
	EventHandler<ActionEvent> cancelTransaction = new EventHandler<ActionEvent>() {
	    public void handle(ActionEvent e) {
		newWindowPopup.close();
	    }
	};

	cancel.setOnAction(cancelTransaction);

	HBox cashInfo = new HBox(10, cashLbl, cashTxt);
	VBox cashBox = new VBox();
	cashBox.getChildren().add(cashInfo);
	cashBox.getChildren().add(payNode);
	return cashBox;
    }

    public static void doYouHaveChangePopUpWindow(double valueEntered, paymentType pt) {
	Image cashRegister = new Image(StoreConstants.CASH_REGISTER);
	ImageView cr = new ImageView();
	cr.setImage(cashRegister);
	cr.setFitWidth(500);
	cr.setPreserveRatio(true);
	cr.setSmooth(true);
	cr.setCache(true);
	VBox crBox = new VBox(cr);
	crBox.setAlignment(Pos.CENTER);

	// newWindow = new Stage();
	newWindow.getIcons().add(new Image(StoreConstants.SC_ICON_FULL));

	if (Math.abs(change) > 0) {
	    total = Math.abs(change);
	}

	VBox choose = new VBox();
	@SuppressWarnings("unused")
	String tTextString = null;
	if (pt == paymentType.CASH) {
	    double ct = Receipt.isThereChange(total, valueEntered);
	    if (ct >= 0.00) {
		tTextString = String.format("Thank you for shopping at the %s today! Your change is %.2f\n",
			StoreConstants.STORE_NAME, ct);
	    } else {
		tTextString = String.format("Thank you for shopping at the %s today! You still owe %.2f\n",
			StoreConstants.STORE_NAME, ct);
	    }
	} else {
	    tTextString = String.format("Thank you your %s has been charged %.2f for shopping at the %s", pt.name(),
		    total, StoreConstants.STORE_NAME);
	}
	change = Receipt.isThereChange(total, money);
	String balance = String.format("Pay Balance->$%.2f", change);

	Button payBalance = new Button(balance);
	payBalance.setStyle("-fx-background-color: red");
	EventHandler<ActionEvent> balanceEvent = new EventHandler<ActionEvent>() {
	    public void handle(ActionEvent e) {
		newWindow.close();
		paymentPreference();
	    }
	};
	payBalance.setOnAction(balanceEvent);

	String lblChange = null;
	MathContext mc = new MathContext(2, RoundingMode.HALF_UP);
	BigDecimal bd = new BigDecimal(change, mc);
	System.out.printf("Before %.8f and After %.8f\n", change, bd.round(mc).doubleValue());
	change = bd.doubleValue();
	if (change > 0.00) {
	    lblChange = String.format("Your Change is %.2f", change);
	} else {
	    if (change < 0.00) {
		lblChange = String.format("Please pay remaining balance of %.2f.", change);
		choose.getChildren().add(payBalance);
	    } else {
		lblChange = "Thank you for exact change.";
	    }
	    total = change;
	    System.out.printf("Change [%.9f]\n", total);
	}
	Label cents = new Label(lblChange);
	cents.setMinWidth(500);
	cents.setAlignment(Pos.CENTER);
	cents.setFont(Font.font("Verdana", FontPosture.REGULAR, 20));
	cents.setStyle("-fx-background-color: lightblue");
	VBox changeBox = new VBox(crBox, cents);
	changeBox.setAlignment(Pos.CENTER);

	String play = ("Thank you for shopping today!!\nWould you like your receipt on paper or emailed to you?\n");
	Text playTxt = new Text(play);
	playTxt.setText(play);
	playTxt.setX(50.00);
	playTxt.setY(80.00);
	playTxt.setFill(Color.BLACK);
	playTxt.autosize();
	playTxt.setFont(Font.font("Verdana", FontPosture.REGULAR, 20));
	VBox scriptBox = new VBox(playTxt);
	scriptBox.setAlignment(Pos.CENTER);

	Button printReciept = new Button("print");
	EventHandler<ActionEvent> printTransaction = new EventHandler<ActionEvent>() {
	    public void handle(ActionEvent e) {
		StoreCheckOut checkoutLane01 = new StoreCheckOut();
		checkoutLane01.checkoutCustomer(currentCustomer, pt, valueEntered, currentCustomer.getAmountPaid());
		newWindow.setScene(scene);
		newWindow.setTitle("Greeting");
		panePhoneNum.setVisible(false);
		newWindow.show();
	    }
	};

	printReciept.setOnAction(printTransaction);

	Button emailReceipt = new Button("email");
	EventHandler<ActionEvent> emailTransaction = new EventHandler<ActionEvent>() {
	    public void handle(ActionEvent e) {
		newWindow.close();
	    }
	};
	emailReceipt.setOnAction(emailTransaction);

	HBox chooseButtons = new HBox(20, printReciept, emailReceipt);
	chooseButtons.setAlignment(Pos.BOTTOM_CENTER);

	choose.getChildren().add(changeBox);
	choose.getChildren().add(chooseButtons);
	choose.getChildren().add(scriptBox);

	choose.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, gray, lightblue);");
	choose.setAlignment(Pos.CENTER);
	Scene chooseScene = new Scene(choose, 600, 650);

	// Need to configure way to link running total to this method
	if (total >= 0) {
	    newWindow.setTitle(String.format("Total: $%.2f", total));
	} else {
	    newWindow.setTitle(String.format("Balance Due: $%.2f", total));
	}
	newWindow.setScene(chooseScene);
	newWindow.setResizable(false);

	newWindow.show();
    }

    private static void viewCart() {

	Label lblCharacter = new Label("Let's see the items in your cart.");
	lblCharacter.setMinWidth(100);
	lblCharacter.setAlignment(Pos.CENTER_LEFT);

	String viewCartImage = StoreConstants.VIEW_CART;

	Image vcImage = new Image(viewCartImage);
	ImageView vcV = new ImageView();
	vcV.setFitWidth(400);
	// pV.setFitHeight(200);
	// pV.setId(inProd.get );
	vcV.setImage(vcImage);
	vcV.setPreserveRatio(true);
	vcV.setSmooth(true);
	vcV.setCache(true);
	VBox vcp = new VBox(20, lblCharacter, vcV);
	vcp.setAlignment(Pos.CENTER);

	VBox spv = new VBox();
	ScrollPane sp = new ScrollPane();
	sp.setPannable(true);
	sp.setHvalue(0.0);
	sp.setVvalue(0.0);
	List<Product> pList = currentCustomer.cart.getProductList();
	int totalQuantity = 1;
	boolean firstTime = true;
	String oldPn = "no name";
	Product oldPd = null;
	Label cLabel = new Label();

	for (Product cPd : pList) {
	    if (oldPn.equals("no name") == true) {
		oldPn = cPd.getBrandName();
		oldPd = cPd;
		firstTime = true;
		// System.out.println("No Name Hit\n");
		continue;
	    }

	    if (oldPn.equals(cPd.getBrandName()) == true) {
		totalQuantity++;
		oldPd = cPd;
		continue;

	    } else {
		// System.out.printf("Else %b\n", firstTime);
		if (firstTime == true) {
		    cLabel = new Label();
		    String item = String.format("%s - %s Qty %d $%.2f", oldPd.getBrandName(), oldPd.getProductName(),
			    totalQuantity, oldPd.getPrice());
		    cLabel.setText(item);
		    spv.getChildren().add(cLabel);
		    totalQuantity = 1;
		    oldPn = cPd.getBrandName();
		    oldPd = cPd;
		}
	    }
	}

	if (firstTime == true) {
	    String item = "No items in your Smart Cart.";
	    if (oldPd != null) {
		item = String.format("%s - %s Qty %d $%.2f", oldPd.getBrandName(), oldPd.getProductName(),
			totalQuantity, oldPd.getPrice());
	    }
	    cLabel = new Label();
	    cLabel.setText(item);
	    spv.getChildren().add(cLabel);
	    firstTime = false;
	}
	sp.setContent(spv);
	// Stage newWindow = new Stage();
	Button closeButton = new Button("Close");
	EventHandler<ActionEvent> closeEvent = new EventHandler<ActionEvent>() {
	    public void handle(ActionEvent e) {
		newWindow.close();
	    }
	};
	closeButton.setOnAction(closeEvent);

	Button noIDoNot = new Button("No");
	closeButton.setAlignment(Pos.BOTTOM_CENTER);
	noIDoNot.setAlignment(Pos.CENTER);
	EventHandler<ActionEvent> noEvent = new EventHandler<ActionEvent>() {
	    public void handle(ActionEvent e) {
		// declineItemsInCart();
	    }

	};

	noIDoNot.setOnAction(noEvent);
	HBox cBox = new HBox(closeButton);
	cBox.setAlignment(Pos.BOTTOM_CENTER);
	VBox paneViewCart = new VBox(20, vcp, sp, cBox);
	paneViewCart.setPadding(new Insets(10));

	Scene viewCartScene = new Scene(paneViewCart, 450, 625);

	// New window (Stage)
	String priceTitle = String.format("Smart Cart Total $%.2f", currentCustomer.cart.getRunningTotal());
	newWindow.setTitle(priceTitle);
	newWindow.setScene(viewCartScene);
	newWindow.getIcons().add(new Image(StoreConstants.SC_ICON_FULL));
	newWindow.hide();
	// Specifies the modality for new window.
	newWindow.initModality(Modality.WINDOW_MODAL);

	// Specifies the owner Window (parent) for new window
	newWindow.initOwner(parentStage);

	// Set position of second window, related to primary window.
	newWindow.setX(parentStage.getX() + 25);
	newWindow.setY(parentStage.getY() + 20);

	newWindow.show();
    }

}