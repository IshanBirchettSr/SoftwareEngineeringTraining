package customerservice;

import java.util.HashMap;
import java.util.List;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import util.StoreConstants;

public class Greeting extends Application {
    boolean isMember = false;
    TextField txtCharacter;
    TextField phoneNumTxt = null;
    HBox pHBox = null;
    VBox primaryPane = null;
    HashMap<String, MembershipSignUp> membershipCards = null;
    MembershipSignUp mCard = null;
    Customer patron = null;
    Stage parentStage = null;

    /**
     * 
     */
    public Greeting() {
	super();
	membershipCards = new HashMap<String, MembershipSignUp>();
	loadCards();

    }

    public void loadCards() {

    }

    public Customer sayGreeting(String[] args) {

	launch(args);
	// Scanner in = new Scanner(System.in);
	Customer cust = new Customer();
//
//	System.out.printf("Welcome to %s\n", StoreConstants.STORE_NAME);
//	System.out.printf("Do you have a %s Membership Card? ", StoreConstants.STORE_NAME);
//	boolean member = YesNoInput.stringToBoolean(in.next());
//	if (member == true) {
//	    // membershipCards.get();
//	    // get phone number from user
//	    // get membership card
//	    // return customer object
//	    System.out.printf("Great! Today's discount is %d%%, Happy Shopping!!\n",
//		    StoreConstants.TODAYS_MEMBER_DISCOUNT);
//	} else {
//	    System.out.println("Would you like to sign up for a membership card?");
//	    boolean signUp = YesNoInput.stringToBoolean(in.next());
//	    if (signUp == true) {
//		// in.nextLine();
//		MembershipSignUp memSignUp = new MembershipSignUp(in);
//		String memInfo = memSignUp.membershipApplication();
//		membershipCards.put(memSignUp.getPhoneNumber(), memSignUp);
//		// return customer object
//		System.out.println(memInfo);
//	    } else {
//		System.out.printf(
//			"Ok, well you can signup at any time the future and instantly save %d%%.\nHappy Shopping\n",
//			StoreConstants.TODAYS_MEMBER_DISCOUNT);
//		// We will need to still have to create and return a anonomus Customer object to
//		// represent customers who do not have memberships.
//	    }
//
//	}
	return cust;
    }

    public void welcomeScreen(String[] args) {

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
	parentStage = primaryStage;

	String wcl = String.format("Welcome to %s", StoreConstants.STORE_NAME);
	Text welcomeTxt = new Text(wcl);
	welcomeTxt.setText(wcl);
	welcomeTxt.setX(50.00);
	welcomeTxt.setY(80.00);
	welcomeTxt.setFill(Color.BLUE);
	List<String> fontNames = Font.getFontNames();
	for (String fontName : fontNames) {
	    System.out.println(fontName);
	}

	welcomeTxt.setFont(Font.font("Rockwell", FontWeight.BOLD, FontPosture.REGULAR, 20));

	HBox gp = new HBox(20, welcomeTxt);
	gp.setAlignment(Pos.CENTER);
	gp.setPadding(new Insets(20.50));

	/* Now we perform our rendering */

	Label lblCharacter = new Label("Do you have a membership card:");
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
	primaryPane = new VBox(10, gp, paneCharacter);
	// Create the bindings

	Scene scene = new Scene(primaryPane, 800, 500);
	primaryStage.setScene(scene);
	String screenTitle = String.format("%s - %s", StoreConstants.STORE_NAME, "Greeting");
	primaryStage.setTitle(screenTitle);
	primaryStage.show();

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
	phoneNumTxt.textProperty().addListener(new ChangeListener<String>() {

	    @Override
	    public void changed(ObservableValue<? extends String> arg0, String oldValue, String newValue) {
		if (!newValue.matches("\\d{0,10}([\\.]\\d{0,4})?")) {
		    phoneNumTxt.setText(oldValue);
		}
		// Lookup membership card using phone number
		if (newValue.length() == 10) {
		    mCard = membershipCards.get(newValue);
		    if (mCard == null) {
			System.out.println("Membership Card Not found using: " + newValue);
		    }
		}
	    }
	});
	HBox panePhoneNum = new HBox(20, phoneNumberLbl, phoneNumTxt);
	panePhoneNum.setPadding(new Insets(10));
	primaryPane.getChildren().add(panePhoneNum);
	return;
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
	Label secondLabel = new Label("I'm a Label on new Window");

	StackPane secondaryLayout = new StackPane();
	secondaryLayout.getChildren().add(membershipTxt);

	Scene secondScene = new Scene(secondaryLayout, 450, 600);

	// New window (Stage)
	Stage newWindow = new Stage();
	newWindow.setTitle("Shoppers Super Store - Membership Sign Up");
	newWindow.setScene(secondScene);

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
