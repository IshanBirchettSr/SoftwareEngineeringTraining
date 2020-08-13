/**
 * 
 */
package housewares;

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

/**
 * @author Allma M. Johnson
 *
 */

public class HousewaresDept extends Department {
	String deptName = StoreConstants.deptNames.HOUSEWARES.name();
	List<String> housewaresRecords = null;
	HashMap<Integer, String> keyMap = null;
	// HashMap<K, V> to hold HousewaresProd objects.
	HashMap<String, HousewaresProd> housewaresProducts;

	/**
	 * Constructor
	 */
	public HousewaresDept() {
		super.setDeptName(deptName);
		// Record Load
		DataCsvLoad unLoadTrucks = new DataCsvLoad();
		unLoadTrucks.loadData(StoreConstants.HOUSEWARES_TRUCK);
		housewaresRecords = unLoadTrucks.getRecords();
		this.setLoadedRecords(housewaresRecords);
		// System.out.printf("%s Department open with %d records\n", deptName,
		// autoRecords.size());
		keyMap = new HashMap<Integer, String>();
		// Housewares Product Load
		housewaresProducts = new HashMap<String, HousewaresProd>();
		loadProducts();
	}

	@Override
	protected void loadProducts() {
		// Load products
		for (String record : housewaresRecords) {
			HousewaresProd hwp = new HousewaresProd();
			boolean recordToProductSuccessful = hwp.recordToProduct(record);

			// If it fails to convert any field, don't add that object to housewaresProducts
			if (recordToProductSuccessful == true) {
				String prodKey = ProdKeyGen.genKey(hwp);
				int howMany = hwp.getNumUnitsInstock();
				for (int i = 0; i < howMany; i++) {

					housewaresProducts.put(prodKey + 1, hwp);
				}

			}
			System.out.printf("%s Department loaded %d (crates) and created %d types of products\n", deptName,
					housewaresRecords.size(), housewaresProducts.size());

		}
	}

	@Override
	public void listProducts() {
		String aKey = null;
		Set<String> housewareProductKeys = housewaresProducts.keySet();

		int totalProducts = housewareProductKeys.size();
		int i = 1;
		for (String pKey : housewareProductKeys) {
			Product pd = housewaresProducts.get(pKey);
			if (aKey != pKey) {
				System.out.printf("%d: %s %s\t%.2f\n", i, pd.getBrandName(), pd.getProductName(), pd.getPrice());
			}
			aKey = pKey;
			keyMap.put(i, pKey);
			i++;
		}
	}

	public List<Product> getProds(int index, int quantity) {
		ArrayList<Product> pdList = new ArrayList<Product>();
		String pKey = keyMap.get(index);
		for (int i = 0; i < quantity; i++) {
			HousewaresProd pd = housewaresProducts.get(pKey);
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

		String imageKey = String.format("Welcome to the Housewares Department!");
		Text welcomeTxt = new Text(imageKey);
		welcomeTxt.setText(imageKey);
		welcomeTxt.setX(50.00);
		welcomeTxt.setY(80.00);
		welcomeTxt.setFill(Color.BLUE);
		welcomeTxt.setFont(Font.font("Verdana", FontPosture.REGULAR, 20));
		HBox hwg = new HBox(20, welcomeTxt);
		hwg.setAlignment(Pos.TOP_CENTER);

		Image housewaresImage = new Image(StoreConstants.HOUSEWARESDEPT);
		ImageView iv = new ImageView();
		iv.setImage(housewaresImage);
		iv.setFitWidth(600);
		iv.setPreserveRatio(true);
		iv.setSmooth(true);
		iv.setCache(true);
		HBox hwp = new HBox(iv);
		hwp.setAlignment(Pos.CENTER);

		VBox hwBox = new VBox(20, hwg, iv);

		String goIn = String.format("Would you like to shop the Housewares Department?");
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
		VBox hwl = new VBox(10, hwp, paneCharacter);
		hwl.setAlignment(Pos.CENTER);

		hwBox.getChildren().add(paneCharacter);

		Scene hwpScene = new Scene(hwBox, 600, 575);
		// TODO Auto-generated method stub
		return hwpScene;
	}
}
