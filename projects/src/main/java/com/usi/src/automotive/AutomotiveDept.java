package automotive;

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
 * @author Allma M. Johnson and ibirc
 *
 */
public class AutomotiveDept extends Department {
    String deptName = StoreConstants.deptNames.AUTOMOTIVE.name();
    HashMap<Integer, String> keyMap = null;
    List<String> autoRecords = null;
    // HashMap<K, V> to hold AutomotiveProd objects.
    HashMap<String, AutomotiveProd> autoProducts;

    /**
     * Constructor
     */
    public AutomotiveDept() {
	super.setDeptName(deptName);
	// Record Load
	DataCsvLoad unLoadTrucks = new DataCsvLoad();
	unLoadTrucks.loadData(StoreConstants.AUTOMOTIVE_TRUCK);
	autoRecords = unLoadTrucks.getRecords();
	this.setLoadedRecords(autoRecords);
	// System.out.printf("%s Department open with %d records\n", deptName,
	// autoRecords.size());
	keyMap = new HashMap<Integer, String>();
	// Automotive Product Load
	autoProducts = new HashMap<String, AutomotiveProd>();
	loadProducts();
    }

    @Override
    protected void loadProducts() {
	// Load products
	for (String record : autoRecords) {
	    AutomotiveProd ap = new AutomotiveProd();
	    boolean recordToProductSuccessful = ap.recordToProduct(record);

	    // If it fails to convert any field, don't add that object to autoProducts
	    if (recordToProductSuccessful == true) {
		String prodKey = ProdKeyGen.genKey(ap);
		int howMany = ap.getNumUnitsInstock();
		for (int i = 0; i < howMany; i++) {
		    // System.out.println(prodKey);
		    autoProducts.put(prodKey + i, ap);
		}
		// autoProducts.put(prodKey, ap);

	    }
	}
	System.out.printf("%s Department loaded %d (crates) and created %d types of products\n", deptName,
		autoRecords.size(), autoProducts.size());

    }

    @Override
    public void listProducts() {
	String aKey = null;
	Set<String> aProductKeys = autoProducts.keySet();

	int totalProducts = aProductKeys.size();
	int i = 1;
	for (String pKey : aProductKeys) {
	    Product pd = autoProducts.get(pKey);
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
	    AutomotiveProd pd = autoProducts.get(pKey);
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
