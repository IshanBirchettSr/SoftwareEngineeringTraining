package fragrance;

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
	keyMap = new HashMap<Integer, String>();
	// Fragrance Product Load
	fragranceProducts = new HashMap<String, FragranceProd>();
	loadProducts();
    }

    @Override
    protected void loadProducts() {
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
    }

    @Override
    public void listProducts() {
	String aKey = null;
	Set<String> fragranceProductKeys = fragranceProducts.keySet();

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
	iv.setFitWidth(400);
	iv.setPreserveRatio(true);
	iv.setSmooth(true);
	iv.setCache(true);
	HBox fp = new HBox(iv);
	fp.setAlignment(Pos.CENTER);

	VBox fBox = new VBox(20, fg, iv);

	// Add the Character and Actor panes to a VBox
	VBox fl = new VBox(10, fp);
	fl.setAlignment(Pos.CENTER);

	Label instructions = new Label("Hover mouse over image for Brand, Product and Price Info.");
	instructions.setAlignment(Pos.CENTER);
	instructions.setFont(Font.font("Rockwell", FontWeight.BOLD, FontPosture.ITALIC, 16));
	instructions.setStyle("-fx-background-color:lightblue");
	VBox fpr = new VBox(15, fBox, fl, iv, instructions);
	fpr.setAlignment(Pos.CENTER);

	// Product Grid
	GridPane pGrid = new GridPane();
	Insets iSet = new Insets(0, 30, 10, 10);
	pGrid.setPadding(iSet);

	String oProdName = "NoProd";
	Set<String> eProductKeys = fragranceProducts.keySet();
	// You must sort the Set of keys
	List<String> list = new ArrayList<>(eProductKeys);
	Collections.sort(list);

	int rowIndex = 0;
	int columnIndex = 0;
	String oldFilename = "Firstfile";

	for (String pKey : list) {
	    Product pd = fragranceProducts.get(pKey);

	    String iFileName = String.format(StoreConstants.PRODUCT_IMAGE, "fragrance", pd.getBrandName(),
		    pd.getProductName());
	    if (oldFilename.equals(iFileName)) {
		// System.out.printf("%s==%s, %b\n", oldFilename,
		// iFileName,oldFilename.equals(iFileName));
		continue;
	    }
	    // System.out.println(iFileName);
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
	    String fragranceToolTip = String.format("%s - %s $%.2f", pd.getProductName(), pd.getBrandName(),
		    pd.getPrice());
	    Tooltip.install(pV, new Tooltip(fragranceToolTip));

	    EventHandler<MouseEvent> iEvent = new EventHandler<MouseEvent>() {
		public void handle(MouseEvent e) {
		    Product pd2 = fragranceProducts.get(pKey);
		    Greeting.prodDetails(pd2, "fragrance");
		    ;
		}
	    };
	    pV.setOnMouseClicked(iEvent);
	    if (oProdName.equals(pd.getProductName()) != true) {
		Label pLabel = new Label();
		pLabel.setFont(Font.font("Rockwell", FontWeight.BOLD, FontPosture.ITALIC, 30));
		pLabel.setStyle("-fx-border-color:black; -fx-background-color:pink;");
		if (pd.getProductName().contains("fragrance")) {
		    pLabel.setText(pd.getProductName() + " Aisle");
		    pLabel.setStyle("-fx-border-color:pink; -fx-background-color:pink;");
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

	VBox fVBox = new VBox(20, fpr, sp, dButtons);

	Scene fScene = new Scene(fVBox, 500, 650);

	return fScene;
    }
}
