/**
 * Property of Urban Survival Initiative
 * Copyright (c) Notice 2020 - All rights reserved
 * 
 * This software is owned in whole by the Urban Survival Initiative.  It's intent purpose
 * is for training individuals to be software engineers.
 * 
 */
package healthandbeauty;

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
 * @author ibirc and chich
 *
 */
public class HealthAndBeautyDept extends Department {
    String deptName = StoreConstants.deptNames.HEALTH_AND_BEAUTY.name();
    List<String> healthAndBeautyRecords = null;
    HashMap<Integer, String> keyMap = null;
    // HashMap<K, V> to hold HousewaresProd objects.
    HashMap<String, HealthAndBeautyProd> healthAndBeautyProducts;

    /**
     * Constructor
     */
    public HealthAndBeautyDept() {
	super.setDeptName(deptName);
	// Record Load
	DataCsvLoad unLoadTrucks = new DataCsvLoad();
	unLoadTrucks.loadData(StoreConstants.HEALTH_AND_BEAUTY_TRUCK);
	healthAndBeautyRecords = unLoadTrucks.getRecords();
	this.setLoadedRecords(healthAndBeautyRecords);
	// System.out.printf("%s Department open with %d records\n", deptName,
	// autoRecords.size());
	keyMap = new HashMap<Integer, String>();
	// Housewares Product Load
	healthAndBeautyProducts = new HashMap<String, HealthAndBeautyProd>();
	loadProducts();
    }

    @Override
    protected void loadProducts() {
	// Load products
	for (String record : healthAndBeautyRecords) {
	    HealthAndBeautyProd hwp = new HealthAndBeautyProd();
	    boolean recordToProductSuccessful = hwp.recordToProduct(record);

	    // If it fails to convert any field, don't add that object to housewaresProducts
	    if (recordToProductSuccessful == true) {
		String prodKey = ProdKeyGen.genKey(hwp);
		int howMany = hwp.getNumUnitsInstock();
		for (int i = 0; i < howMany; i++) {

		    healthAndBeautyProducts.put(prodKey + 1, hwp);
		}

	    }
	    System.out.printf("%s Department loaded %d (crates) and created %d types of products\n", deptName,
		    healthAndBeautyRecords.size(), healthAndBeautyProducts.size());
	}
    }

    @Override
    public void listProducts() {
	String aKey = null;
	Set<String> healthAndBeautyProductKeys = healthAndBeautyProducts.keySet();

	int totalProducts = healthAndBeautyProductKeys.size();
	int i = 1;
	for (String pKey : healthAndBeautyProductKeys) {
	    Product pd = healthAndBeautyProducts.get(pKey);
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
	    HealthAndBeautyProd pd = healthAndBeautyProducts.get(pKey);
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

	String imageKey = String.format("Welcome to the Health and Beauty Department!");
	Text welcomeTxt = new Text(imageKey);
	welcomeTxt.setText(imageKey);
	welcomeTxt.setX(50.00);
	welcomeTxt.setY(80.00);
	welcomeTxt.setFill(Color.BLUE);
	welcomeTxt.setFont(Font.font("Verdana", FontPosture.REGULAR, 20));
	HBox hbg = new HBox(20, welcomeTxt);
	hbg.setAlignment(Pos.TOP_CENTER);

	Image healthAndBeautyImage = new Image(StoreConstants.HEALTHANDBEAUTYDEPT);
	ImageView iv = new ImageView();
	iv.setImage(healthAndBeautyImage);
	iv.setFitWidth(400);
	iv.setPreserveRatio(true);
	iv.setSmooth(true);
	iv.setCache(true);
	HBox hbp = new HBox(iv);
	hbp.setAlignment(Pos.CENTER);

	VBox hbBox = new VBox(20, hbg, iv);

	String goIn = String.format("Would you like to shop the Health and Beauty Department?");
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
	VBox fl = new VBox(10, hbp, paneCharacter);
	fl.setAlignment(Pos.CENTER);

	hbBox.getChildren().add(paneCharacter);

	Label instructions = new Label("Hover mouse over image for Brand, Product and Price Info.");
	instructions.setAlignment(Pos.CENTER);
	instructions.setFont(Font.font("Rockwell", FontWeight.BOLD, FontPosture.ITALIC, 16));
	instructions.setStyle("-fx-background-color:lightblue");
	VBox hbpr = new VBox(15, hbBox, iv, instructions);
	hbpr.setAlignment(Pos.CENTER);

	GridPane pGrid = new GridPane();
	Insets iSet = new Insets(0, 30, 10, 10);
	pGrid.setPadding(iSet);

	String oProdName = "NoProd";
	Set<String> eProductKeys = healthAndBeautyProducts.keySet();
	// You must sort the Set of keys
	List<String> list = new ArrayList<>(eProductKeys);
	Collections.sort(list);

	int rowIndex = 0;
	int columnIndex = 0;
	String oldFilename = "Firstfile";

	for (String pKey : list) {
	    Product pd = healthAndBeautyProducts.get(pKey);

	    String iFileName = String.format(StoreConstants.PRODUCT_IMAGE, "furniture", pd.getBrandName(),
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
	    String healthAndBeautyToolTip = String.format("%s - %s $%.2f", pd.getProductName(), pd.getBrandName(),
		    pd.getPrice());
	    Tooltip.install(pV, new Tooltip(healthAndBeautyToolTip));

	    EventHandler<MouseEvent> iEvent = new EventHandler<MouseEvent>() {
		public void handle(MouseEvent e) {
		    Product pd2 = healthAndBeautyProducts.get(pKey);
		    Greeting.prodDetails(pd2, "Health and Beauty");
		}
	    };
	    pV.setOnMouseClicked(iEvent);
	    if (oProdName.equals(pd.getProductName()) != true) {
		Label pLabel = new Label();
		pLabel.setFont(Font.font("Rockwell", FontWeight.BOLD, FontPosture.ITALIC, 30));
		pLabel.setStyle("-fx-border-color:black; -fx-background-color:gray;");
		if (pd.getProductName().contains("make up")) {
		    pLabel.setText(pd.getProductName() + " Aisle");
		    pLabel.setStyle("-fx-border-color:black; -fx-background-color:gray;");
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

	VBox hbVBox = new VBox(20, hbpr, sp, dButtons);

	Scene hbScene = new Scene(hbVBox, 600, 575);
	// TODO Auto-generated method stub
	return hbScene;
    }
}
