/**
 * 
 */
package bedding;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import customerservice.Greeting;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import util.DataCsvLoad;
import util.Department;
import util.ProdKeyGen;
import util.Product;
import util.StoreConstants;

/**
 * @author Allma M. Johnson and Chich and Roxanne Earnest
 *
 */
public class BeddingDept extends Department {

    String deptName = StoreConstants.deptNames.BEDDING.name();
    List<String> beddingRecords = null;
    HashMap<Integer, String> keyMap = null;
    HashMap<String, BeddingProd> BeddingProducts;

    /**
     * Constructor
     */
    public BeddingDept() {
	super.setDeptName(deptName);
	DataCsvLoad unLoadTrucks = new DataCsvLoad();
	unLoadTrucks.loadDbRecords(deptName);
	beddingRecords = unLoadTrucks.getRecords();
	this.setLoadedRecords(beddingRecords);
	keyMap = new HashMap<Integer, String>();
	BeddingProducts = new HashMap<String, BeddingProd>();
	loadProducts();
    }

    @Override
    protected void loadProducts() {
	// Load products
	for (String record : beddingRecords) {
	    BeddingProd bp = new BeddingProd();
	    boolean recordToProductSuccessful = bp.recordToProduct(record);
	    if (recordToProductSuccessful == true) {
		String prodKey = ProdKeyGen.genKey(bp);
		int howMany = bp.getNumUnitsInstock();
		for (int i = 0; i < howMany; i++) {
		    // System.out.println(prodKey);
		    BeddingProducts.put(prodKey + i, bp);
		}
	    }
	}

    }

    @Override
    public void listProducts() {
	String aKey = null;
	Set<String> aProductKeys = BeddingProducts.keySet();
	int i = 1;
	for (String pKey : aProductKeys) {
	    Product pd = BeddingProducts.get(pKey);
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
	    BeddingProd pd = BeddingProducts.get(pKey);
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

	String sString = String.format("%s Nighty Nite Sleep Tight!", StoreConstants.deptNames.BEDDING);
	Label slogan = new Label();
	slogan.setText(sString);
	slogan.setAlignment(Pos.CENTER);

	slogan.setTextFill(Color.BLUEVIOLET);
	slogan.setFont(Font.font("Veranda", FontPosture.ITALIC, 20));
	// create individual VBoxes
	VBox sloBox = new VBox(slogan);
	sloBox.setAlignment(Pos.CENTER);

	// this is the code for the CSS
	String style_inner = "-fx-border-color: steelblue;" + "-fx-border-width: 10;";

	Image beddingImage = new Image(StoreConstants.BEDDINGDEPT);
	ImageView iv = new ImageView();
	iv.setImage(beddingImage);
	iv.setFitWidth(400);
	iv.setPreserveRatio(true);
	iv.setSmooth(true);
	iv.setCache(true);
	// Create stackpane to hold image view

	StackPane fPane = new StackPane(iv);
	fPane.setStyle(style_inner);
	fPane.setEffect(new DropShadow(30, Color.BLUEVIOLET));

	HBox spBox = new HBox(fPane);
	spBox.setAlignment(Pos.CENTER);
	VBox alignBox = new VBox(20, sloBox, spBox);

	Label instructions = new Label("Hover mouse over image for Brand, Product and Price Info.");
	instructions.setAlignment(Pos.CENTER);

	instructions.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 16));
	instructions.setStyle("-fx-background-color:cornsilk");

	VBox instrBox = new VBox(instructions);
	instrBox.setAlignment(Pos.CENTER);
	VBox bp = new VBox(15, slogan, alignBox, instructions);
	bp.setAlignment(Pos.CENTER);

	// Product Grid
	GridPane pGrid = new GridPane();
	Insets iSet = new Insets(0, 30, 10, 10);
	pGrid.setPadding(iSet);
	String oProdName = "NoProd";
	Set<String> aProductKeys = BeddingProducts.keySet();

	// You must sort the Set of keys
	List<String> list = new ArrayList<>(aProductKeys);
	Collections.sort(list);

	int rowIndex = 0;
	int columnIndex = 0;
	String oldFilename = "Firstfile";
	for (String pKey : list) {
	    Product pd = BeddingProducts.get(pKey);

	    String iFileName = String.format(StoreConstants.PRODUCT_IMAGE, "bedding", pd.getBrandName(),
		    pd.getProductName());
	    if (oldFilename.equals(iFileName)) {
		// System.out.printf("%s==%s, %b\n", oldFilename,
		// iFileName,oldFilename.equals(iFileName));
		continue;
	    }
	    // System.out.println(iFileName);
	    oldFilename = iFileName;
	    String ftest = String.format(StoreConstants.APP_HOME + "/images/%s_prod_%s_%s.png", "bedding",
		    pd.getBrandName(), pd.getProductName());
	    File fExist = new File(ftest);

	    if (fExist.exists() == false) {
		continue;
	    }
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
	    String beddingToolTip = String.format("%s - %s $%.2f", pd.getProductName(), pd.getBrandName(),
		    pd.getPrice());
	    Tooltip.install(pV, new Tooltip(beddingToolTip));

	    EventHandler<MouseEvent> iEvent = new EventHandler<MouseEvent>() {
		public void handle(MouseEvent e) {
		    // System.out.printf("Image Click on %s\n", pV.getId());
		    Product pd2 = BeddingProducts.get(pKey);
		    Greeting.prodDetails(pd2, "bedding");
		}
	    };
	    pV.setOnMouseClicked(iEvent);
	    if (oProdName.equals(pd.getProductName()) != true) {
		Label pLabel = new Label();
		pLabel.setFont(Font.font("Lucinda Sans)", FontWeight.SEMI_BOLD, FontPosture.ITALIC, 30));
		pLabel.setTextFill(Color.BLACK);
		pLabel.setStyle("-fx-border-color:midnightblue; -fx-border-width:2;-fx-background-color:lemonchiffon;");
		if (pd.getProductName().contains("Pillow")) {
		    pLabel.setText(pd.getProductName() + " Aisle");
		    pLabel.setStyle("-fx-border-color:midnightblue; -fx-background-color:lavenderblush;");
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
	VBox aVBox = new VBox(10, bp, sp, dButtons);
	Scene beddScene = new Scene(aVBox, 500, 650);
	return beddScene;
    }
}
