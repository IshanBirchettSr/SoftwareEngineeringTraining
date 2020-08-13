/**
 * 
 */
package furniture;

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
 * @author chich and Allma M. Johnson
 *
 */
public class FurnitureDept extends Department {
	String deptName = StoreConstants.deptNames.FURNITURE.name();
	List<String> furnitureRecords = null;
	HashMap<Integer, String> keyMap = null;
	// HashMap<K, V> to hold FurnitureProd objects.
	HashMap<String, FurnitureProd> furnitureProducts;

	/**
	 * Constructor
	 */
	public FurnitureDept() {
		super.setDeptName(deptName);
		// Record Load
		DataCsvLoad unLoadTrucks = new DataCsvLoad();
		unLoadTrucks.loadData(StoreConstants.FURNITURE_TRUCK);
		furnitureRecords = unLoadTrucks.getRecords();
		this.setLoadedRecords(furnitureRecords);
		// System.out.printf("%s Department open with %d records\n", deptName,
		// furnitureRecords.size());
		keyMap = new HashMap<Integer, String>();
		// Furniture Product Load
		furnitureProducts = new HashMap<String, FurnitureProd>();
		loadProducts();
	}

	@Override
	protected void loadProducts() {
		// Load products
		for (String record : furnitureRecords) {
			FurnitureProd fp = new FurnitureProd();
			boolean recordToProductSuccessful = fp.recordToProduct(record);

			// If it fails to convert any field, don't add that object to furnitureProducts
			if (recordToProductSuccessful == true) {
				String prodKey = ProdKeyGen.genKey(fp);
				int howMany = fp.getNumUnitsInstock();
				for (int i = 0; i < howMany; i++) {

					furnitureProducts.put(prodKey + 1, fp);
				}

			}
			System.out.printf("%s Department loaded %d (crates) and created %d types of products\n", deptName,
					furnitureRecords.size(), furnitureProducts.size());

		}
	}

	@Override
	public void listProducts() {
		String aKey = null;
		Set<String> furnitureProductKeys = furnitureProducts.keySet();

		int totalProducts = furnitureProductKeys.size();
		int i = 1;
		for (String pKey : furnitureProductKeys) {
			Product pd = furnitureProducts.get(pKey);
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
			FurnitureProd pd = furnitureProducts.get(pKey);
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

		String imageKey = String.format("Welcome to the Furniture Department!");
		Text welcomeTxt = new Text(imageKey);
		welcomeTxt.setText(imageKey);
		welcomeTxt.setX(50.00);
		welcomeTxt.setY(80.00);
		welcomeTxt.setFill(Color.BLUE);
		welcomeTxt.setFont(Font.font("Verdana", FontPosture.REGULAR, 20));
		HBox furg = new HBox(20, welcomeTxt);
		furg.setAlignment(Pos.TOP_CENTER);

		Image furnitureImage = new Image(StoreConstants.FURNITUREDEPT);
		ImageView iv = new ImageView();
		iv.setImage(furnitureImage);
		iv.setFitWidth(600);
		iv.setPreserveRatio(true);
		iv.setSmooth(true);
		iv.setCache(true);
		HBox furp = new HBox(iv);
		furp.setAlignment(Pos.CENTER);

		VBox furBox = new VBox(20, furg, iv);

		String goIn = String.format("Would you like to shop the Furniture Department?");
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
		VBox fl = new VBox(10, furp, paneCharacter);
		fl.setAlignment(Pos.CENTER);

		furBox.getChildren().add(paneCharacter);

		Scene furScene = new Scene(furBox, 600, 575);

		return furScene;
	}
}
