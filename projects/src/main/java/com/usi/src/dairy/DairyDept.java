package dairy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import util.DataCsvLoad;
import util.Department;
import util.ProdKeyGen;
import util.Product;
import util.StoreConstants;

/**
 * @author malac
 *
 */
public class DairyDept extends Department {
    String deptName = StoreConstants.deptNames.DAIRY.name();
    List<String> dairyRecords = null;
    HashMap<Integer, String> keyMap = null;
    // HashMap<K, V> to hold DairyProd objects.
    HashMap<String, DairyProd> dairyProducts;

    /**
     * Constructor
     */
    public DairyDept() {
	super.setDeptName(deptName);
// Record Load
	DataCsvLoad unLoadTrucks = new DataCsvLoad();
	unLoadTrucks.loadData(StoreConstants.DAIRY_TRUCK);
	dairyRecords = unLoadTrucks.getRecords();
	this.setLoadedRecords(dairyRecords);
	keyMap = new HashMap<Integer, String>();
// System.out.printf("%s Department open with %d records\n", deptName,
// dairyRecords.size());  }

	// Automotive Product Load
	dairyProducts = new HashMap<String, DairyProd>();
	loadProducts();
    }

    @Override
    protected void loadProducts() {
	// Load products
	for (String record : dairyRecords) {
	    DairyProd dp = new DairyProd();
	    boolean recordToProductSuccessful = dp.recordToProduct(record);
	    if (recordToProductSuccessful == true) {
		String prodKey = ProdKeyGen.genKey(dp);
		int howMany = dp.getNumUnitsInstock();
		for (int i = 0; i < howMany; i++) {
		    // System.out.println(prodKey);
		    dairyProducts.put(prodKey + i, dp);
		}
	    }
	}
	System.out.printf("%s Department loaded %d (crates) and created %d types of products\n", deptName,
		dairyRecords.size(), dairyProducts.size());

    }

    @Override
    public void listProducts() {
	String aKey = null;
	Set<String> dairyProductKeys = dairyProducts.keySet();

	int totalProducts = dairyProductKeys.size();
	int i = 1;
	for (String pKey : dairyProductKeys) {
	    Product pd = dairyProducts.get(pKey);
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
	    DairyProd pd = dairyProducts.get(pKey);
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
	Image dairyImage = new Image(StoreConstants.DAIRYDEPT);
	ImageView iv = new ImageView();
	iv.setImage(dairyImage);
	iv.setFitWidth(600);
	iv.setPreserveRatio(true);
	iv.setSmooth(true);
	iv.setCache(true);
	HBox furp = new HBox(iv);
	furp.setAlignment(Pos.CENTER);

	Scene dpScene = new Scene(furp, 600, 575);
	// TODO Auto-generated method stub
	return dpScene;
    }
}
