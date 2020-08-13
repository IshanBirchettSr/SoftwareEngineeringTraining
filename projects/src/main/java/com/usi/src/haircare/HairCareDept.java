/**
 * 
 */
package haircare;

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
 * @author Allma M. Johnson, Roxanne L. Earnest
 *
 */
public class HairCareDept extends Department {
	String deptName = StoreConstants.deptNames.HAIR_CARE.name();
	List<String> haircareRecords = null;
	HashMap<Integer, String> keyMap = null;
	// HashMap<K, V> to hold haircareProd objects.
	HashMap<String, HaircareProd> haircareProducts;

	/**
	 * Constructor
	 */
	public HairCareDept() {
		super.setDeptName(deptName);
		// Record Load
		DataCsvLoad unLoadTrucks = new DataCsvLoad();
		unLoadTrucks.loadData(StoreConstants.HAIR_CARE_TRUCK);
		haircareRecords = unLoadTrucks.getRecords();
		this.setLoadedRecords(haircareRecords);
		// System.out.printf("%s Department open with %d records\n", deptName,
		// haircareRecords.size());
		keyMap = new HashMap<Integer, String>();
		// haircare Product Load
		haircareProducts = new HashMap<String, HaircareProd>();
		loadProducts();
	}

	@Override
	protected void loadProducts() {
		// Load products
		for (String record : haircareRecords) {
			HaircareProd hcp = new HaircareProd();
			boolean recordToProductSuccessful = hcp.recordToProduct(record);

			// If it fails to convert any field, don't add that object to haircareProducts
			if (recordToProductSuccessful == true) {
				String prodKey = ProdKeyGen.genKey(hcp);
				int howMany = hcp.getNumUnitsInstock();
				for (int i = 0; i < howMany; i++) {

					haircareProducts.put(prodKey + 1, hcp);
				}

			}
			System.out.printf("%s Department loaded %d (crates) and created %d types of products\n", deptName,
					haircareRecords.size(), haircareProducts.size());

		}
	}

	@Override
	public void listProducts() {
		String aKey = null;
		Set<String> haircareProductKeys = haircareProducts.keySet();

		int totalProducts = haircareProductKeys.size();
		int i = 1;
		for (String pKey : haircareProductKeys) {
			Product pd = haircareProducts.get(pKey);
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
			HaircareProd pd = haircareProducts.get(pKey);
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

		String imageKey = String.format("Welcome to the Hair Care Department!");
		Text welcomeTxt = new Text(imageKey);
		welcomeTxt.setText(imageKey);
		welcomeTxt.setX(50.00);
		welcomeTxt.setY(80.00);
		welcomeTxt.setFill(Color.BLUE);
		welcomeTxt.setFont(Font.font("Verdana", FontPosture.REGULAR, 20));
		HBox hg = new HBox(20, welcomeTxt);
		hg.setAlignment(Pos.TOP_CENTER);

		Image haircareImage = new Image(StoreConstants.HAIRCAREDEPT);
		ImageView iv = new ImageView();
		iv.setImage(haircareImage);
		iv.setFitWidth(600);
		iv.setPreserveRatio(true);
		iv.setSmooth(true);
		iv.setCache(true);
		HBox hp = new HBox(iv);
		hp.setAlignment(Pos.CENTER);

		VBox hBox = new VBox(20, hg, iv);

		String goIn = String.format("Would you like to shop the Hair Care Department?");
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
		VBox hl = new VBox(10, hp, paneCharacter);
		hl.setAlignment(Pos.CENTER);

		hBox.getChildren().add(paneCharacter);

		Scene hScene = new Scene(hBox, 600, 575);
		// TODO Auto-generated method stub
		return hScene;
	}
}
