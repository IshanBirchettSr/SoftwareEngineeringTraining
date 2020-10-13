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
 * @author ibirc and chich
 *
 */
public class HealthAndBeautyDept extends Department {
    String deptName = StoreConstants.deptNames.HEALTH_AND_BEAUTY.name();
    List<String> healthAndBeautyRecords = null;
    HashMap<Integer, String> keyMap = null;
    HashMap<String, HealthAndBeautyProd> healthAndBeautyProducts;

    /**
     * Constructor
     */
    public HealthAndBeautyDept() {
	super.setDeptName(deptName);
	// Record Load
	DataCsvLoad unLoadTrucks = new DataCsvLoad();
	unLoadTrucks.loadDbRecords(deptName);
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
	    // System.out.printf("%s Department loaded %d (crates) and created %d types of
	    // products\n", deptName,
	    // healthAndBeautyRecords.size(), healthAndBeautyProducts.size());
	}
    }

    @Override
    public void listProducts() {
	String aKey = null;
	Set<String> healthAndBeautyProductKeys = healthAndBeautyProducts.keySet();

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

	VBox hbBox = new VBox(20, hbg, hbp);

	// Add the Character and Actor panes to a VBox

	Label instructions = new Label("DUE TO NEW COVID REGULATIONS DEPARTMENT IS CLOSED");
	instructions.setAlignment(Pos.CENTER);
	instructions.setFont(Font.font("Rockwell", FontWeight.BOLD, FontPosture.ITALIC, 16));
	instructions.setStyle("-fx-background-color:Yellow");
	VBox hbpr = new VBox(15, hbBox, iv, instructions);
	hbpr.setAlignment(Pos.CENTER);

	Image map = new Image(StoreConstants.CAUTION);
	ImagePattern pattern = new ImagePattern(map, 20, 20, 40, 40, false);

	Image DeptClosed = new Image(StoreConstants.DEPTCLOSED);
	ImageView iv2 = new ImageView();
	iv2.setImage(DeptClosed);
	iv2.setFitWidth(300);
	iv2.setFitHeight(200);
	iv2.setPreserveRatio(true);
	iv2.setSmooth(true);
	iv2.setCache(true);
	Rectangle rect = new Rectangle(600, 250, 600, 200);
	rect.setFill(pattern);
	HBox ivPane = new HBox(iv2);
	ivPane.setAlignment(Pos.CENTER);
	StackPane rectPane = new StackPane(rect);
	rectPane.getChildren().add(ivPane);
	HBox rectBox = new HBox(10, rectPane);
	rectBox.setAlignment(Pos.CENTER);

	HBox dButtons = Greeting.getBottonDeptButtons();
	dButtons.setAlignment(Pos.CENTER);
	dButtons.setSpacing(30);
	dButtons.setPadding(new Insets(15, 0, 15, 0));

	VBox hbVBox = new VBox(20, hbpr, rectBox, dButtons);

	Scene hbScene = new Scene(hbVBox, 500, 650);
	return hbScene;
    }
}
