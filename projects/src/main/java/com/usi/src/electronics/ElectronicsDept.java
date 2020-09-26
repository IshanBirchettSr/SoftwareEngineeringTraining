/**
 * Property of Urban Survival Initiative
 * Copyright (c) Notice 2020 - All rights reserved
 * 
 * This software is owned in whole by the Urban Survival Initiative.  It's intent purpose
 * is for training individuals to be software engineers.
 * 
 */
package electronics;

import java.io.File;
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
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
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
 * @author malac and chich
 *
 */
public class ElectronicsDept extends Department {
    String deptName = StoreConstants.deptNames.ELECTRONICS.name();
    List<String> electronicsRecords = null;
    HashMap<Integer, String> keyMap = null;
// HashMap<K, V> to hold ElectronicsProd objects.
    HashMap<String, ElectronicsProd> electronicsProducts;

    /**
     * Constructor
     */
    public ElectronicsDept() {
	super.setDeptName(deptName);
// Record Load
	DataCsvLoad unLoadTrucks = new DataCsvLoad();
	unLoadTrucks.loadData(StoreConstants.ELECTRONICS_TRUCK);
	electronicsRecords = unLoadTrucks.getRecords();
	this.setLoadedRecords(electronicsRecords);
// System.out.printf("%s Department open with %d records\n", deptName,
// autoRecords.size());
	keyMap = new HashMap<Integer, String>();
// Automotive Product Load
	electronicsProducts = new HashMap<String, ElectronicsProd>();
	loadProducts();
    }

    @Override
    protected void loadProducts() {
// Load products
	for (String record : electronicsRecords) {
	    ElectronicsProd ep = new ElectronicsProd();
	    boolean recordToProductSuccessful = ep.recordToProduct(record);

	    // If it fails to convert any field, don't add that object to haircareProducts
	    if (recordToProductSuccessful == true) {
		String prodKey = ProdKeyGen.genKey(ep);
		int howMany = ep.getNumUnitsInstock();
		for (int i = 0; i < howMany; i++) {

		    electronicsProducts.put(prodKey + 1, ep);
		}

	    }
	}
    }

    @Override
    public void listProducts() {
	String aKey = null;
	Set<String> eProductKeys = electronicsProducts.keySet();

	int i = 1;
	for (String pKey : eProductKeys) {
	    Product pd = electronicsProducts.get(pKey);
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
	    ElectronicsProd pd = electronicsProducts.get(pKey);
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

	String imageKey = String.format("Welcome to the Electronics Department!");
	Text welcomeTxt = new Text(imageKey);
	welcomeTxt.setText(imageKey);
	welcomeTxt.setX(50.00);
	welcomeTxt.setY(80.00);
	welcomeTxt.setFill(Color.BLUE);
	welcomeTxt.setFont(Font.font("Verdana", FontPosture.REGULAR, 20));
	HBox eg = new HBox(20, welcomeTxt);
	eg.setAlignment(Pos.TOP_CENTER);

	String style_inner = "-fx-border-color: Red;" + "-fx-border-width: 5;"
		+ "-fx-border-style: segments(10, 15, 15, 15)  line-cap round ;";

	Image electronicsImage = new Image(StoreConstants.ELECTRONICSDEPT);
	ImageView iv = new ImageView();
	iv.setImage(electronicsImage);
	iv.setFitWidth(400);
	iv.setPreserveRatio(true);
	iv.setSmooth(true);
	iv.setCache(true);
	StackPane ivPane = new StackPane(iv);
	ivPane.setStyle(style_inner);
	ivPane.setEffect(new DropShadow(20, Color.RED));
	HBox ep = new HBox(20, ivPane);

	iv.setStyle(style_inner);
	ep.setAlignment(Pos.CENTER);

	VBox eBox = new VBox(20, eg, ep);

	Label instructions = new Label("Hover mouse over image for Brand, Product and Price Info.");
	instructions.setAlignment(Pos.CENTER);
	instructions.setFont(Font.font("Rockwell", FontWeight.BOLD, FontPosture.ITALIC, 16));
	instructions.setStyle("-fx-background-color:lightblue");
	VBox epr = new VBox(15, eBox, instructions);
	epr.setAlignment(Pos.CENTER);

	// Product Grid
	GridPane pGrid = new GridPane();
	Insets iSet = new Insets(0, 30, 10, 10);
	pGrid.setPadding(iSet);

	String oProdName = "NoProd";
	Set<String> eProductKeys = electronicsProducts.keySet();
	// You must sort the Set of keys
	List<String> list = new ArrayList<>(eProductKeys);
	Collections.sort(list);

	int rowIndex = 0;
	int columnIndex = 0;
	String oldFilename = "Firstfile";
	VBox rectBox = null;

	if (list.size() == 0) {

	    Image map = new Image(StoreConstants.CAUTION);
	    ImagePattern pattern = new ImagePattern(map, 20, 20, 40, 40, false);
	    Image DeptClosed = new Image(StoreConstants.DEPTCLOSED);
	    ImageView iv2 = new ImageView();
	    iv2.setImage(DeptClosed);
	    iv2.setFitWidth(500);
	    iv2.setFitHeight(200);
	    iv2.setPreserveRatio(true);
	    iv2.setSmooth(true);
	    iv2.setCache(true);
	    Rectangle rect = new Rectangle(600, 250, 600, 200);
	    rect.setFill(pattern);
	    // HBox ivPane = new HBox(iv2);
	    rectBox = new VBox(rect);
	    rectBox.setAlignment(Pos.CENTER);

	} else {

	    for (String pKey : list) {
		Product pd = electronicsProducts.get(pKey);

		String iFileName = String.format(StoreConstants.PRODUCT_IMAGE, "electronics", pd.getBrandName(),
			pd.getProductName());
		if (oldFilename.equals(iFileName)) {
		    // System.out.printf("%s==%s, %b\n", oldFilename,
		    // iFileName,oldFilename.equals(iFileName));
		    continue;
		}
		// System.out.println(iFileName);
		oldFilename = iFileName;

		String ftest = String.format(StoreConstants.APP_HOME + "/images/%s_prod_%s_%s.png", "electronics",
			pd.getBrandName(), pd.getProductName());
		File fExist = new File(ftest);

		if (fExist.exists() == false) {
		    continue;
		}

		// Image View
		Image pImage = new Image(iFileName);
		ImageView pV = new ImageView();
		pV.setFitHeight(100);
		// pV.setFitHeight(65);
		pV.setId(pKey);
		pV.setImage(pImage);
		pV.setPreserveRatio(true);

		pV.setSmooth(true);
		pV.setCache(true);
		String electronicToolTip = String.format("%s - %s $%.2f", pd.getProductName(), pd.getBrandName(),
			pd.getPrice());
		Tooltip.install(pV, new Tooltip(electronicToolTip));

		EventHandler<MouseEvent> iEvent = new EventHandler<MouseEvent>() {
		    public void handle(MouseEvent e) {
			Product pd2 = electronicsProducts.get(pKey);
			Greeting.prodDetails(pd2, "electronics");
		    }
		};
		pV.setOnMouseClicked(iEvent);
		if (oProdName.equals(pd.getProductName()) != true) {
		    Label pLabel = new Label();
		    pLabel.setFont(Font.font("Rockwell", FontWeight.BOLD, FontPosture.ITALIC, 30));
		    pLabel.setStyle("-fx-border-color:black; -fx-background-color:gray;");
		    if (pd.getProductName().contains("Ipad")) {
			pLabel.setText(pd.getProductName() + " Aisle");
			pLabel.setStyle("-fx-border-color:black; -fx-background-color:gray;");
		    } else {
			pLabel.setText(pd.getProductName() + " Shelve");
		    }
		    pLabel.setAlignment(Pos.CENTER);
		    columnIndex = 0;
		    rowIndex += 1;
		    // System.out.printf("Label: Column: %d, Row: %d\n", columnIndex, rowIndex);

		    pGrid.add(pLabel, columnIndex, rowIndex, 10, 1);
		    if (rowIndex == 0) {
			rowIndex = 1;
		    } else {
			rowIndex += 1;
		    }
		    // System.out.printf("%s vs %s\n", oProdName, pd.getProductName());
		    oProdName = pd.getProductName();
		}
		// System.out.printf("C-%d, R-%d\n", columnIndex, rowIndex);
		pGrid.add(pV, columnIndex, rowIndex);

		if (columnIndex < 5) {
		    columnIndex++;
		} else {
		    rowIndex += 2;
		    columnIndex = 0;
		}

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
	VBox eVBox = null;

	if (list.size() == 0) {

	    eVBox = rectBox;

	} else {
	    eVBox = new VBox(20, epr, sp, dButtons);
	}

	Scene eScene = new Scene(eVBox, 500, 650);

	return eScene;
    }
}
