/**
 * Property of Urban Survival Initiative
 * Copyright (c) Notice 2020 - All rights reserved
 * 
 * This software is owned in whole by the Urban Survival Initiative.  It's intent purpose
 * is for training individuals to be software engineers.
 * 
 */
package meat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import customerservice.Greeting;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import util.DataCsvLoad;
import util.Department;
import util.ProdKeyGen;
import util.Product;
import util.StoreConstants;

/**
 * @author ibirc and Allma M. Johnson
 *
 */
public class MeatDept extends Department {
    String deptName = StoreConstants.deptNames.MEAT.name();
    List<String> meatRecords = null;
    HashMap<Integer, String> keyMap = null;
    // HashMap<K, V> to hold MeatProd objects.
    HashMap<String, MeatProd> meatProducts;

    /**
     * Constructor
     */
    public MeatDept() {
	super.setDeptName(deptName);
	// Record Load
	DataCsvLoad unLoadTrucks = new DataCsvLoad();
	unLoadTrucks.loadData(StoreConstants.MEAT_TRUCK);
	meatRecords = unLoadTrucks.getRecords();
	this.setLoadedRecords(meatRecords);
	// System.out.printf("%s Department open with %d records\n", deptName,
	// meatRecords.size());
	keyMap = new HashMap<Integer, String>();
	// Product MeatLoad
	meatProducts = new HashMap<String, MeatProd>();
	loadProducts();
    }

    @Override
    protected void loadProducts() {
	// Load products
	for (String record : meatRecords) {
	    MeatProd mp = new MeatProd();
	    boolean recordToProductSuccessful = mp.recordToProduct(record);

	    // If it fails to convert any field, don't add that object to autoProducts
	    if (recordToProductSuccessful == true) {
		String prodKey = ProdKeyGen.genKey(mp);
		int howMany = mp.getNumUnitsInstock();
		for (int i = 0; i < howMany; i++) {

		    meatProducts.put(prodKey + 1, mp);
		}

	    }
	    System.out.printf("%s Department loaded %d (crates) and created %d types of products\n", deptName,
		    meatRecords.size(), meatProducts.size());

	}
    }

    @Override
    public void listProducts() {
	String aKey = null;
	Set<String> meatProductKeys = meatProducts.keySet();

	int totalProducts = meatProductKeys.size();
	int i = 1;
	for (String pKey : meatProductKeys) {
	    Product pd = meatProducts.get(pKey);
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
	    MeatProd pd = meatProducts.get(pKey);
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

	String imageKey = String.format("Welcome to the Meat Department!");
	Text welcomeTxt = new Text(imageKey);
	welcomeTxt.setText(imageKey);
	welcomeTxt.setX(50.00);
	welcomeTxt.setY(80.00);
	welcomeTxt.setFill(Color.BLUE);
	welcomeTxt.setFont(Font.font("Verdana", FontPosture.REGULAR, 20));
	HBox mg = new HBox(20, welcomeTxt);
	mg.setAlignment(Pos.TOP_CENTER);

	Image meatImage = new Image(StoreConstants.MEATDEPT);
	ImageView iv = new ImageView();
	iv.setImage(meatImage);
	iv.setFitWidth(300);
	iv.setPreserveRatio(true);
	iv.setSmooth(true);
	iv.setCache(true);
	HBox mp = new HBox(iv);
	mp.setAlignment(Pos.CENTER);

	VBox mBox = new VBox(20, mg, iv);

	String goIn = String.format("Would you like to shop the Meat Department?");
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
	VBox ml = new VBox(10, mp, paneCharacter);
	ml.setAlignment(Pos.CENTER);

	mBox.getChildren().add(paneCharacter);

	Label instructions = new Label("Hover mouse over image for Brand, Product and Price Info.");
	instructions.setAlignment(Pos.CENTER);
	instructions.setFont(Font.font("Rockwell", FontWeight.BOLD, FontPosture.ITALIC, 16));
	instructions.setStyle("-fx-background-color:lightblue");
	VBox mpr = new VBox(15, mBox, iv, instructions);
	mpr.setAlignment(Pos.CENTER);

	GridPane pGrid = new GridPane();
	Insets iSet = new Insets(0, 30, 10, 10);
	pGrid.setPadding(iSet);

	String oProdName = "NoProd";
	Set<String> eProductKeys = meatProducts.keySet();
	// You must sort the Set of keys
	List<String> list = new ArrayList<>(eProductKeys);
	Collections.sort(list);

	int rowIndex = 0;
	int columnIndex = 0;
	String oldFilename = "Firstfile";

	for (String pKey : list) {
	    Product pd = meatProducts.get(pKey);

	    String iFileName = String.format(StoreConstants.PRODUCT_IMAGE, "meat", pd.getBrandName(),
		    pd.getProductName());
	    if (oldFilename.equals(iFileName)) {
		// System.out.printf("%s==%s, %b\n", oldFilename,
		// iFileName,oldFilename.equals(iFileName));
		continue;
	    }
	    System.out.println(iFileName);
	    oldFilename = iFileName;

	    // Image View
	    Image pImage = new Image(iFileName);
	    ImageView pV = new ImageView();
	    pV.setFitHeight(125);
	    // pV.setFitHeight(65);
	    pV.setId(pKey);
	    pV.setImage(pImage);
	    pV.setPreserveRatio(true);

	    pV.setSmooth(true);
	    pV.setCache(true);
	    String meatToolTip = String.format("%s - %s $%.2f", pd.getProductName(), pd.getBrandName(), pd.getPrice());
	    Tooltip.install(pV, new Tooltip(meatToolTip));

	    EventHandler<MouseEvent> iEvent = new EventHandler<MouseEvent>() {
		public void handle(MouseEvent e) {
		    Product pd2 = meatProducts.get(pKey);
		    Greeting.prodDetails(pd2, "meat");
		}
	    };
	    pV.setOnMouseClicked(iEvent);
	    if (oProdName.equals(pd.getProductName()) != true) {
		Label pLabel = new Label();
		pLabel.setFont(Font.font("Rockwell", FontWeight.BOLD, FontPosture.ITALIC, 30));
		pLabel.setStyle("-fx-border-color:black; -fx-background-color:gray;");
		if (pd.getProductName().contains("steak")) {
		    pLabel.setText(pd.getProductName() + " Aisle");
		    pLabel.setStyle("-fx-border-color:black; -fx-background-color:darksalmon;");
		} else {
		    pLabel.setText(pd.getProductName() + " Shelve");
		}
		pLabel.setAlignment(Pos.CENTER);
		columnIndex = 0;
		rowIndex += 1;
		System.out.printf("Label: Column: %d, Row: %d\n", columnIndex, rowIndex);

		pGrid.add(pLabel, columnIndex, rowIndex, 10, 1);
		if (rowIndex == 0) {
		    rowIndex = 1;
		} else {
		    rowIndex += 1;
		}
		System.out.printf("%s vs %s\n", oProdName, pd.getProductName());
		oProdName = pd.getProductName();
	    }
	    System.out.printf("C-%d, R-%d\n", columnIndex, rowIndex);
	    pGrid.add(pV, columnIndex, rowIndex);

	    if (columnIndex < 5) {
		columnIndex++;
	    } else {
		rowIndex += 2;
		columnIndex = 0;
	    }

	}
	// ap.getChildren().add(pGrid);
	pGrid.setHgap(20);
	pGrid.setVgap(40);
	ScrollPane sp = new ScrollPane();
	sp.setContent(pGrid);
	sp.setPannable(true);
	sp.setHvalue(0.0);
	sp.setVvalue(0.0);

	HBox dButtons = Greeting.getBottonDeptButtons();
	dButtons.setAlignment(Pos.CENTER);
	dButtons.setSpacing(30);
	dButtons.setPadding(new Insets(15, 0, 15, 0));

	VBox mVBox = new VBox(20, mpr, sp, dButtons);

	Scene mScene = new Scene(mVBox, 600, 575);
	// TODO Auto-generated method stub
	return mScene;
    }
}
