package toys;

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
 * @author Allma M. Johnson
 *
 */
public class ToysDept extends Department {
    String deptName = StoreConstants.deptNames.TOYS.name();
    List<String> toysRecords = null;
    HashMap<Integer, String> keyMap = null;
    // HashMap<K, V> to hold ToysProd objects.
    HashMap<String, ToysProd> toysProducts;

    /**
     * Constructor
     */

    public ToysDept() {
	super.setDeptName(deptName);
	// Record Load
	DataCsvLoad unLoadTrucks = new DataCsvLoad();
	unLoadTrucks.loadData(StoreConstants.TOYS_TRUCK);
	toysRecords = unLoadTrucks.getRecords();
	this.setLoadedRecords(toysRecords);
	// System.out.printf("%s Department open with %d records\n", deptName,
	// autoRecords.size());
	keyMap = new HashMap<Integer, String>();
	// Automotive Product Load
	toysProducts = new HashMap<String, ToysProd>();
	loadProducts();
    }

    @Override
    protected void loadProducts() {
	// Load products
	for (String record : toysRecords) {
	    ToysProd tp = new ToysProd();
	    boolean recordToProductSuccessful = tp.recordToProduct(record);

	    // If it fails to convert any field, don't add that object to autoProducts
	    if (recordToProductSuccessful == true) {
		String prodKey = ProdKeyGen.genKey(tp);
		int howMany = tp.getNumUnitsInstock();
		for (int i = 0; i < howMany; i++) {

		    toysProducts.put(prodKey + 1, tp);
		}
	    }

	    System.out.printf("%s Department loaded %d (crates) and created %d types of products\n", deptName,
		    toysRecords.size(), toysProducts.size());

	}
    }

    @Override
    public void listProducts() {
	String aKey = null;
	Set<String> toyProductKeys = toysProducts.keySet();

	int totalProducts = toyProductKeys.size();
	int i = 1;
	for (String pKey : toyProductKeys) {
	    Product pd = toysProducts.get(pKey);
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
	    ToysProd pd = toysProducts.get(pKey);
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
	Image toysImage = new Image(StoreConstants.TOYSDEPT);
	ImageView iv = new ImageView();
	iv.setImage(toysImage);
	iv.setFitWidth(600);
	iv.setPreserveRatio(true);
	iv.setSmooth(true);
	iv.setCache(true);

	HBox toysView = new HBox(iv);
	toysView.setAlignment(Pos.CENTER_LEFT);
	Scene toysScene = new Scene(toysView, 600, 575);
	return toysScene;
    }
}
