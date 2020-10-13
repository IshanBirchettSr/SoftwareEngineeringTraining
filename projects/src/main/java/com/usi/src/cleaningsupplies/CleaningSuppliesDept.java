package cleaningsupplies;

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
 * @author Allma M. Johnson and REarnest
 *
 */
public class CleaningSuppliesDept extends Department {
    String deptName = StoreConstants.deptNames.CLEANING_SUPPLIES.name();
    List<String> cleaningsuppliesRecords = null;
    HashMap<Integer, String> keyMap = null;
    // HashMap<K, V> to hold CleaningSuppliesProd objects.
    HashMap<String, CleaningSuppliesProd> cleaningsuppliesProducts;

    /**
     * Constructor
     */
    public CleaningSuppliesDept() {
	super.setDeptName(deptName);
	// Record Load
	DataCsvLoad unLoadTrucks = new DataCsvLoad();
	unLoadTrucks.loadData(StoreConstants.CLEANINGSUPPLIES_TRUCK);
	cleaningsuppliesRecords = unLoadTrucks.getRecords();
	this.setLoadedRecords(cleaningsuppliesRecords);
	keyMap = new HashMap<Integer, String>();
	// System.out.printf("%s Department open with %d records\n", deptName,
	// cleaningsuppliesRecords.size());

	// CleaningSupplies Product Load
	cleaningsuppliesProducts = new HashMap<String, CleaningSuppliesProd>();
	loadProducts();
    }

    @Override
    protected void loadProducts() {
	// Load products
	for (String record : cleaningsuppliesRecords) {
	    CleaningSuppliesProd csp = new CleaningSuppliesProd();
	    boolean recordToProductSuccessful = csp.recordToProduct(record);

	    if (recordToProductSuccessful == true) {
		String prodKey = ProdKeyGen.genKey(csp);
		int howMany = csp.getNumUnitsInstock();
		for (int i = 0; i < howMany; i++) {
		    // System.out.println(prodKey);
		    cleaningsuppliesProducts.put(prodKey + i, csp);
		}
	    }
	}
    }

    @Override
    public void listProducts() {
	String aKey = null;
	Set<String> cleaningProductKeys = cleaningsuppliesProducts.keySet();

	int i = 1;
	for (String pKey : cleaningProductKeys) {
	    Product pd = cleaningsuppliesProducts.get(pKey);
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
	    CleaningSuppliesProd pd = cleaningsuppliesProducts.get(pKey);
	    pdList.add(pd);
	}

	return pdList;
    }

    @Override
    public Scene getScene() {
	String sString = String.format("Clean-Up with our %s needs!", StoreConstants.deptNames.CLEANING_SUPPLIES);
	Label slogan = new Label();
	slogan.setText(sString);
	slogan.setAlignment(Pos.CENTER);
	slogan.setTextFill(Color.BLUE);
	slogan.setFont(Font.font("Verdana", FontPosture.REGULAR, 20));

	VBox sloBox = new VBox(slogan);
	sloBox.setAlignment(Pos.CENTER);

	String style_inner = "-fx-border-color: Chartreuse;" + "-fx-border-width: 16;";
	Image cleaningsuppliesImage = new Image(StoreConstants.CLEANINGSUPPLIESDEPT);
	ImageView iv = new ImageView();
	iv.setImage(cleaningsuppliesImage);
	iv.setFitWidth(400);
	iv.setPreserveRatio(true);
	iv.setSmooth(true);
	iv.setCache(true);

	// Create stackpane to hold image view

	StackPane fPane = new StackPane(iv);
	fPane.setStyle(style_inner);
	fPane.setEffect(new DropShadow(20, Color.YELLOW));
	HBox spBox = new HBox(fPane);
	spBox.setAlignment(Pos.CENTER);
	VBox alignBox = new VBox(20, sloBox, spBox);
	Label instructions = new Label("Hover mouse over image for Brand, Product and Price Info.");
	instructions.setAlignment(Pos.CENTER);
	instructions.setFont(Font.font("Rockwell", FontWeight.BOLD, FontPosture.ITALIC, 16));
	instructions.setStyle("-fx-background-color:lightyellow");
	// Create individual VBoxes
	VBox instrBox = new VBox(instructions);
	instrBox.setAlignment(Pos.CENTER);

	VBox bpr = new VBox(15, alignBox, instrBox);
	bpr.setAlignment(Pos.CENTER);

	// Product Grid
	GridPane pGrid = new GridPane();
	Insets iSet = new Insets(0, 30, 10, 10);
	pGrid.setPadding(iSet);
	String oProdName = "NoProd";
	Set<String> aProductKeys = cleaningsuppliesProducts.keySet();
	// You must sort the Set of keys
	List<String> list = new ArrayList<>(aProductKeys);
	Collections.sort(list);

	int rowIndex = 0;
	int columnIndex = 0;
	String oldFilename = "Firstfile";

	for (String pKey : list) {
	    Product pd = cleaningsuppliesProducts.get(pKey);

	    String iFileName = String.format(StoreConstants.PRODUCT_IMAGE, "cleaningsupplies", pd.getBrandName(),
		    pd.getProductName());
	    if (oldFilename.equals(iFileName)) {
		// System.out.printf("%s==%s, %b\n", oldFilename,
		// iFileName,oldFilename.equals(iFileName));
		continue;
	    }
	    // System.out.println(iFileName);
	    oldFilename = iFileName;
	    String ftest = String.format(StoreConstants.APP_HOME + "/images/%s_prod_%s_%s.png", "cleaningsupplies",
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
	    String cleaningsuppliesToolTip = String.format("%s - %s $%.2f", pd.getProductName(), pd.getBrandName(),
		    pd.getPrice());
	    Tooltip.install(pV, new Tooltip(cleaningsuppliesToolTip));

	    EventHandler<MouseEvent> iEvent = new EventHandler<MouseEvent>() {
		public void handle(MouseEvent e) {
		    // System.out.printf("Image Click on %s\n", pV.getId());
		    Product pd2 = cleaningsuppliesProducts.get(pKey);
		    Greeting.prodDetails(pd2, "cleaningsupplies");

		}
	    };
	    pV.setOnMouseClicked(iEvent);
	    if (oProdName.equals(pd.getProductName()) != true) {
		Label pLabel = new Label();
		pLabel.setFont(Font.font("Rockwell", FontWeight.BOLD, FontPosture.ITALIC, 30));
		pLabel.setTextFill(Color.BLUE);
		pLabel.setStyle("-fx-border-color:blue; -fx-background-color:white;");
		if (pd.getProductName().contains("Cleaning")) {
		    pLabel.setText(pd.getProductName() + " Aisle");

		    pLabel.setStyle("-fx-border-color:black; -fx-background-color:orange;");
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
	VBox eVBox = new VBox(10, bpr, sp, dButtons);
	Scene eScene = new Scene(eVBox, 500, 650);
	return eScene;
    }

    @Override
    public List<Product> getProducts() {

	return null;
    }

}
