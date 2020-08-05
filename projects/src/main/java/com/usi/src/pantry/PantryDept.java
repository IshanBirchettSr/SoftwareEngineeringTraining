package pantry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import util.DataCsvLoad;
import util.Department;
import util.ProdKeyGen;
import util.Product;
import util.StoreConstants;

/**
 * @author chich
 *
 */

public class PantryDept extends Department {

    String deptName = StoreConstants.deptNames.PANTRY.name();
    List<String> pantryRecords = null;
    HashMap<Integer, String> keyMap = null;
    // HashMap<K, V> to hold PantryProd objects.
    HashMap<String, PantryProd> pantryProducts;

    /**
     * Constructor
     */
    public PantryDept() {
	super.setDeptName(deptName);
	// Record Load
	DataCsvLoad unLoadTrucks = new DataCsvLoad();
	unLoadTrucks.loadData(StoreConstants.PANTRY_TRUCK);
	pantryRecords = unLoadTrucks.getRecords();
	this.setLoadedRecords(pantryRecords);
	// System.out.printf("%s Department open with %d records\n", deptName,
	// pantryRecords.size());
	keyMap = new HashMap<Integer, String>();
	// Pantry Product Load
	pantryProducts = new HashMap<String, PantryProd>();
	loadProducts();
    }

    @Override
    protected void loadProducts() {
	// Load products
	for (String record : pantryRecords) {
	    PantryProd pp = new PantryProd();
	    boolean recordToProductSuccessful = pp.recordToProduct(record);

	    // If it fails to convert any field, don't add that object to pantryProducts
	    if (recordToProductSuccessful == true) {
		String prodKey = ProdKeyGen.genKey(pp);
		int howMany = pp.getNumUnitsInstock();
		for (int i = 0; i < howMany; i++) {

		    pantryProducts.put(prodKey + 1, pp);
		}

	    }
	}

	System.out.printf("%s Department loaded %d (crates) and created %d types of products\n", deptName,
		pantryRecords.size(), pantryProducts.size());

    }

    @Override
    public void listProducts() {
	String aKey = null;
	Set<String> pantryProductKeys = pantryProducts.keySet();

	int totalProducts = pantryProductKeys.size();
	int i = 1;
	for (String pKey : pantryProductKeys) {
	    Product pd = pantryProducts.get(pKey);
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
	    PantryProd pd = pantryProducts.get(pKey);
	    pdList.add(pd);
	}

	return pdList;
    }

    @Override
    public List<Product> getProducts() {
	List<Product> pList = null;

	return pList;
    }
}
