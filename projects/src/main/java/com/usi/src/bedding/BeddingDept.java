/**
 * 
 */
package bedding;

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
 * @author Allma M. Johnson and Chich
 *
 */
public class BeddingDept extends Department {

    String deptName = StoreConstants.deptNames.BEDDING.name();
    List<String> beddingRecords = null;
    HashMap<Integer, String> keyMap = null;
// HashMap<K, V> to hold ElectronicsProd objects.
    HashMap<String, BeddingProd> BeddingProducts;

    /**
     * Constructor
     */
    public BeddingDept() {
	super.setDeptName(deptName);
// Record Load
	DataCsvLoad unLoadTrucks = new DataCsvLoad();
	unLoadTrucks.loadData(StoreConstants.BEDDING_TRUCK);
	beddingRecords = unLoadTrucks.getRecords();
	this.setLoadedRecords(beddingRecords);
// System.out.printf("%s Department open with %d records\n", deptName,
// autoRecords.size());
	keyMap = new HashMap<Integer, String>();
// Automotive Product Load
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
	System.out.printf("%s Department loaded %d (crates) and created %d types of products\n", deptName,
		beddingRecords.size(), BeddingProducts.size());

    }

    @Override
    public void listProducts() {
	String aKey = null;
	Set<String> aProductKeys = BeddingProducts.keySet();

	int totalProducts = aProductKeys.size();
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
	Image beddingImage = new Image(StoreConstants.BEDDINGDEPT);
	ImageView iv = new ImageView();
	iv.setImage(beddingImage);
	iv.setFitWidth(600);
	iv.setPreserveRatio(true);
	iv.setSmooth(true);
	iv.setCache(true);
	HBox furp = new HBox(iv);
	furp.setAlignment(Pos.CENTER);

	Scene beddScene = new Scene(furp, 600, 575);
	// TODO Auto-generated method stub
	return beddScene;
    }
}
