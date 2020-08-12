package petcare;

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
 * @author chich
 *
 */

public class PetcareDept extends Department {
    String deptName = StoreConstants.deptNames.PETCARE.name();
    List<String> petcareRecords = null;
    HashMap<Integer, String> keyMap = null;
    // HashMap<K, V> to hold PetcareProd objects.
    HashMap<String, PetcareProd> petcareProducts;

    /**
     * Constructor
     */
    public PetcareDept() {
	super.setDeptName(deptName);
	// Record Load
	DataCsvLoad unLoadTrucks = new DataCsvLoad();
	unLoadTrucks.loadData(StoreConstants.PETCARE_TRUCK);
	petcareRecords = unLoadTrucks.getRecords();
	this.setLoadedRecords(petcareRecords);
	// System.out.printf("%s Department open with %d records\n", deptName,
	// autoRecords.size());
	keyMap = new HashMap<Integer, String>();
	// Automotive Product Load
	petcareProducts = new HashMap<String, PetcareProd>();
	loadProducts();
    }

    @Override
    protected void loadProducts() {
	// Load products
	for (String record : petcareRecords) {
	    PetcareProd pp = new PetcareProd();
	    boolean recordToProductSuccessful = pp.recordToProduct(record);

	    // If it fails to convert any field, don't add that object to autoProducts
	    if (recordToProductSuccessful == true) {
		String prodKey = ProdKeyGen.genKey(pp);

		int howMany = pp.getNumUnitsInstock();
		for (int i = 0; i < howMany; i++) {

		    petcareProducts.put(prodKey + 1, pp);
		}

	    }

	    System.out.printf("%s Department loaded %d (crates) and created %d types of products\n", deptName,
		    petcareRecords.size(), petcareProducts.size());
	}
    }

    @Override
    public void listProducts() {
	String aKey = null;
	Set<String> petcareProductKeys = petcareProducts.keySet();

	int totalProducts = petcareProductKeys.size();
	int i = 1;
	for (String pKey : petcareProductKeys) {
	    Product pd = petcareProducts.get(pKey);
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
	    PetcareProd pd = petcareProducts.get(pKey);
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
	Image petcareImage = new Image(StoreConstants.PETCAREDEPT);
	ImageView iv = new ImageView();
	iv.setImage(petcareImage);
	iv.setFitWidth(600);
	iv.setPreserveRatio(true);
	iv.setSmooth(true);
	iv.setCache(true);

	HBox pantryView = new HBox(iv);
	pantryView.setAlignment(Pos.CENTER_LEFT);
	Scene petcareScene = new Scene(pantryView, 600, 575);
	return petcareScene;
    }
}
