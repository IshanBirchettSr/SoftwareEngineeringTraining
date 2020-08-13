/**
 * Property of Urban Survival Initiative
 * Copyright (c) Notice 2020 - All rights reserved
 * 
 * This software is owned in whole by the Urban Survival Initiative.  It's intent purpose
 * is for training individuals to be software engineers.
 * 
 */
package garden;

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
 * @author chich
 *
 */

public class GardenDept extends Department {
	String deptName = StoreConstants.deptNames.GARDEN.name();
	List<String> gardenRecords = null;
	HashMap<Integer, String> keyMap = null;
	// HashMap<K, V> to hold GardenProd objects.
	HashMap<String, GardenProd> gardenProducts;

	/**
	 * Constructor
	 */
	public GardenDept() {
		super.setDeptName(deptName);
		// Record Load
		DataCsvLoad unLoadTrucks = new DataCsvLoad();
		unLoadTrucks.loadData(StoreConstants.GARDEN_TRUCK);
		gardenRecords = unLoadTrucks.getRecords();
		this.setLoadedRecords(gardenRecords);
		// System.out.printf("%s Department open with %d records\n", deptName,
		// autoRecords.size());
		keyMap = new HashMap<Integer, String>();
		// Automotive Product Load
		gardenProducts = new HashMap<String, GardenProd>();
		loadProducts();
	}

	@Override
	protected void loadProducts() {
		// Load products
		for (String record : gardenRecords) {
			GardenProd gp = new GardenProd();
			boolean recordToProductSuccessful = gp.recordToProduct(record);

			// If it fails to convert any field, don't add that object to autoProducts
			if (recordToProductSuccessful == true) {
				String prodKey = ProdKeyGen.genKey(gp);
				int howMany = gp.getNumUnitsInstock();
				for (int i = 0; i < howMany; i++) {

					gardenProducts.put(prodKey + 1, gp);
				}

			}
			System.out.printf("%s Department loaded %d (crates) and created %d types of products\n", deptName,
					gardenRecords.size(), gardenProducts.size());
		}
	}

	@Override
	public void listProducts() {
		String aKey = null;
		Set<String> gardenProductKeys = gardenProducts.keySet();

		int totalProducts = gardenProductKeys.size();
		int i = 1;
		for (String pKey : gardenProductKeys) {
			Product pd = gardenProducts.get(pKey);
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
			GardenProd pd = gardenProducts.get(pKey);
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
		String imageKey = String.format("Welcome to the Garden Department!");
		Text welcomeTxt = new Text(imageKey);
		welcomeTxt.setText(imageKey);
		welcomeTxt.setX(50.00);
		welcomeTxt.setY(80.00);
		welcomeTxt.setFill(Color.BLUE);
		welcomeTxt.setFont(Font.font("Verdana", FontPosture.REGULAR, 20));
		HBox gg = new HBox(20, welcomeTxt);
		gg.setAlignment(Pos.TOP_CENTER);

		Image gardenImage = new Image(StoreConstants.GARDENDEPT);
		ImageView iv = new ImageView();
		iv.setImage(gardenImage);
		iv.setFitWidth(600);
		iv.setPreserveRatio(true);
		iv.setSmooth(true);
		iv.setCache(true);
		HBox gp = new HBox(iv);
		gp.setAlignment(Pos.CENTER);

		VBox gBox = new VBox(20, gg, iv);

		String goIn = String.format("Would you like to shop the Garden Department?");
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
		VBox gl = new VBox(10, gp, paneCharacter);
		gl.setAlignment(Pos.CENTER);

		gBox.getChildren().add(paneCharacter);

		Scene gScene = new Scene(gBox, 600, 575);
		// TODO Auto-generated method stub
		return gScene;
	}
}
