package customerservice;

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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import util.DataCsvLoad;
import util.Department;
import util.StoreConstants;

public class Greeting extends Application {
	boolean isMember = false;
	TextField txtCharacter;
	TextField phoneNumTxt = null;
	HBox pHBox = null;
	VBox primaryPane = null;
	HashMap<String, MembershipSignUp> membershipCards = null;
	MembershipSignUp mCard = null;
	Customer currentCustomer = null;
	Stage parentStage = null;
	// List<Department> dList = null;
	Scene deptsScene = null;
	List<String> membershipRecords = null;

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

	public Customer sayGreeting(String[] args) {

		launch(args);
		currentCustomer = new Customer();
		return currentCustomer;
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
		welcomeTxt.setFont(Font.font("Rockwell", FontPosture.REGULAR, 20));
		HBox gp = new HBox(20, welcomeTxt);
		gp.setAlignment(Pos.CENTER);
//	
//	List<String> fontNames = Font.getFontNames();
//	for (String fontName : fontNames) {
//	    System.out.println(fontName);
//	}
//
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
					if (mCard != null) {
						displayMemCard(mCard);
						System.out.println("Membership Card Found!");
					}
//		    parentStage.hide();
//		    parentStage.setScene(deptsScene);
//		    // parentStage.setTitle("Store Map");
//		    parentStage.setTitle("USI - Store Map");
//		    parentStage.show();
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
		// StackPane secondaryLayout = new StackPane();

		Scene mCardScene = new Scene(memSignUp, 450, 500);

		// New window (Stage)
		Stage newWindow = new Stage();
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
		Label secondLabel = new Label("I'm a Label on new Window");

		StackPane secondaryLayout = new StackPane();
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

		ComboBox<String> combo_box = new ComboBox(FXCollections.observableArrayList(dStrs));
		combo_box.setValue("<SELECT>");
		Label selected = new Label("Please Observe MASK On COVID 19 - Social Distancing 6 X 6 ft.");

		EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				selected.setText("Scrolling to " + combo_box.getValue() + " Department ...");
			}
		};

		combo_box.setOnAction(event);

		TilePane tp = new TilePane(combo_box);
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
	}

}
