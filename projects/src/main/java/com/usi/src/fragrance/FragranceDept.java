package fragrance;

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

public class FragranceDept extends Department {
	String deptName = StoreConstants.deptNames.FRAGRANCE.name();
	List<String> fragranceRecords = null;
	HashMap<Integer, String> keyMap = null;
	// HashMap<K, V> to hold FragranceProd objects.
	HashMap<String, FragranceProd> fragranceProducts;

	/**
	 * Constructor
	 */
	public FragranceDept() {
		super.setDeptName(deptName);
		// Record Load
		DataCsvLoad unLoadTrucks = new DataCsvLoad();
		unLoadTrucks.loadData(StoreConstants.FRAGRANCE_TRUCK);
		fragranceRecords = unLoadTrucks.getRecords();
		this.setLoadedRecords(fragranceRecords);
		// System.out.printf("%s Department open with %d records\n", deptName,
		// autoRecords.size());
		keyMap = new HashMap<Integer, String>();
		// Fragrance Product Load
		fragranceProducts = new HashMap<String, FragranceProd>();
		loadProducts();
	}

	@Override
	protected void loadProducts() {
		// TODO Auto-generated method stub
		// Load products
		for (String record : fragranceRecords) {
			FragranceProd fdd = new FragranceProd();
			boolean recordToProductSuccessful = fdd.recordToProduct(record);

			// If it fails to convert any field, don't add that object to autoProducts
			if (recordToProductSuccessful == true) {
				String prodKey = ProdKeyGen.genKey(fdd);
				int howMany = fdd.getNumUnitsInstock();
				for (int i = 0; i < howMany; i++) {

					fragranceProducts.put(prodKey + 1, fdd);
				}

			}

		}
		System.out.printf("%s Department loaded %d (crates) and created %d types of products\n", deptName,
				fragranceRecords.size(), fragranceProducts.size());

	}

	@Override
	public void listProducts() {
		String aKey = null;
		Set<String> fragranceProductKeys = fragranceProducts.keySet();

		int totalProducts = fragranceProductKeys.size();
		int i = 1;
		for (String pKey : fragranceProductKeys) {
			Product pd = fragranceProducts.get(pKey);
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
			FragranceProd pd = fragranceProducts.get(pKey);
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

		String imageKey = String.format("Welcome to the Fragrance Department!");
		Text welcomeTxt = new Text(imageKey);
		welcomeTxt.setText(imageKey);
		welcomeTxt.setX(50.00);
		welcomeTxt.setY(80.00);
		welcomeTxt.setFill(Color.BLUE);
		welcomeTxt.setFont(Font.font("Verdana", FontPosture.REGULAR, 20));
		HBox fg = new HBox(20, welcomeTxt);
		fg.setAlignment(Pos.TOP_CENTER);

		Image fragranceImage = new Image(StoreConstants.FRAGRANCEDEPT);
		ImageView iv = new ImageView();
		iv.setImage(fragranceImage);
		iv.setFitWidth(600);
		iv.setPreserveRatio(true);
		iv.setSmooth(true);
		iv.setCache(true);
		HBox fp = new HBox(iv);
		fp.setAlignment(Pos.CENTER);

		VBox fBox = new VBox(20, fg, iv);

		String goIn = String.format("Would you like to shop the Fragrance Department?");
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
		VBox fl = new VBox(10, fp, paneCharacter);
		fl.setAlignment(Pos.CENTER);

		fBox.getChildren().add(paneCharacter);

		Scene fScene = new Scene(fBox, 600, 575);

		return fScene;
	}
}
