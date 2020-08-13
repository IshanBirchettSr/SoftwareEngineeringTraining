package luggage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import util.DataCsvLoad;
import util.Department;
import util.ProdKeyGen;
import util.Product;
import util.StoreConstants;

public class LuggageDept extends Department {
	String deptName = StoreConstants.deptNames.LUGGAGE.name();
	List<String> luggageRecords = null;
	HashMap<Integer, String> keyMap = null;
// HashMap<K, V> to hold LuggageProd objects.
	HashMap<String, LuggageProd> LuggageProducts;

	/**
	 * Constructor
	 */
	public LuggageDept() {
		super.setDeptName(deptName);

// Record Load
		DataCsvLoad unLoadTrucks = new DataCsvLoad();
		unLoadTrucks.loadData(StoreConstants.LUGGAGE_TRUCK);
		luggageRecords = unLoadTrucks.getRecords();
		this.setLoadedRecords(luggageRecords);
		keyMap = new HashMap<Integer, String>();
		LuggageProducts = new HashMap<String, LuggageProd>();
		loadProducts();
	}

	@Override
	protected void loadProducts() {
		// TODO Auto-generated method stub
		// Load products
		for (String record : luggageRecords) {
			LuggageProd lp = new LuggageProd();
			boolean recordToProductSuccessful = lp.recordToProduct(record);

			// If it fails to convert any field, don't add that object to luggageProducts
			if (recordToProductSuccessful == true) {
				String prodKey = ProdKeyGen.genKey(lp);
				int howMany = lp.getNumUnitsInstock();
				for (int i = 0; i < howMany; i++) {

					LuggageProducts.put(prodKey + 1, lp);
				}

			}
			System.out.printf("%s Department loaded %d (crates) and created %d types of products\n", deptName,
					luggageRecords.size(), LuggageProducts.size());
		}
	}

	@Override
	public void listProducts() {
		String aKey = null;
		Set<String> luggageProductKeys = LuggageProducts.keySet();

		int totalProducts = luggageProductKeys.size();
		int i = 1;
		for (String pKey : luggageProductKeys) {
			Product pd = LuggageProducts.get(pKey);
			if (aKey != pKey) {
				System.out.printf("%d: %s %s\t%.2f\n", i, pd.getBrandName(), pd.getProductName(), pd.getPrice());
			}
			aKey = pKey;
			keyMap.put(i, pKey);
			i++;
		}
	}

	public List<Product> getProds(int index, int qauntity) {
		ArrayList<Product> pdList = new ArrayList<Product>();
		String pKey = keyMap.get(index);
		for (int i = 0; i < qauntity; i++) {
			LuggageProd pd = LuggageProducts.get(pKey);
			pdList.add(pd);
		}

		return pdList;
	}

	@Override
	public List<Product> getProducts() {
		List<Product> pList = null;

		return pList;
	}

	@Override
	public Scene getScene() {

		String imageKey = String.format("Welcome to the Luggage Department!");
		Text welcomeTxt = new Text(imageKey);
		welcomeTxt.setText(imageKey);
		welcomeTxt.setX(50.00);
		welcomeTxt.setY(80.00);
		welcomeTxt.setFill(Color.BLUE);
		welcomeTxt.setFont(Font.font("Verdana", FontPosture.REGULAR, 20));
		HBox lug = new HBox(20, welcomeTxt);
		lug.setAlignment(Pos.TOP_CENTER);

		Image luggageImage = new Image(StoreConstants.LUGGAGEDEPT);
		ImageView iv = new ImageView();
		iv.setImage(luggageImage);
		iv.setFitWidth(600);
		iv.setPreserveRatio(true);
		iv.setSmooth(true);
		iv.setCache(true);
		HBox lup = new HBox(iv);
		lup.setAlignment(Pos.CENTER);

		VBox lugBox = new VBox(20, lug, iv);

		String goIn = String.format("Would you like to shop the Luggage Department?");
		Text shopTxt = new Text(goIn);
		shopTxt.setText(goIn);
		shopTxt.setX(50.00);
		shopTxt.setY(80.00);
		shopTxt.setFill(Color.BLUE);
		shopTxt.setFont(Font.font("Rockwell", FontPosture.REGULAR, 20));

		Label comeIn = new Label(goIn);
		comeIn.setAlignment(Pos.BOTTOM_CENTER);

		Button Enter = new Button("YES!");
		EventHandler<ActionEvent> yesEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {

				System.out.println("Welcome!");

			}
		};
		Enter.setOnAction(yesEvent);

		Button noIDoNot = new Button("Next Department Please");
		Enter.setAlignment(Pos.BOTTOM_CENTER);
		noIDoNot.setAlignment(Pos.BOTTOM_CENTER);
		EventHandler<ActionEvent> noEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				System.out.println("No");

			}
		};

		noIDoNot.setOnAction(noEvent);

		HBox paneCharacter = new HBox(20, comeIn, Enter, noIDoNot);
		paneCharacter.setPadding(new Insets(10));
		// Add the Character and Actor panes to a VBox
		VBox el = new VBox(10, lup, paneCharacter);
		el.setAlignment(Pos.CENTER);

		lugBox.getChildren().add(paneCharacter);

		Scene luScene = new Scene(lugBox, 600, 575);
		// TODO Auto-generated method stub
		return luScene;
	}
}
