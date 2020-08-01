package automotive;

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
    List<String> autoRecords = null;
    // HashMap<K, V> to hold AutomotiveProd objects.
    HashMap<String, AutomotiveProd> autoProducts;

    /**
     * Constructor
     */
    public AutomotiveDept() {
	// Record Load
	DataCsvLoad unLoadTrucks = new DataCsvLoad();
	unLoadTrucks.loadData(StoreConstants.AUTOMOTIVE_TRUCK);
	autoRecords = unLoadTrucks.getRecords();
	this.setLoadedRecords(autoRecords);
	// System.out.printf("%s Department open with %d records\n", deptName,
	// autoRecords.size());

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
		int howMany = ap.getQuantity();
		for (int i = 0; i < howMany; i++) {
		    prodKey = ProdKeyGen.genKey(ap);
		    autoProducts.put(prodKey, ap);
		}
		autoProducts.put(prodKey, ap);

	    }
	}
	System.out.printf("%s Department loaded %d (crates) and created %d types of products\n", deptName,
		autoRecords.size(), autoProducts.size());

    }

    @Override
    public void listProducts() {
	String aKey = null;
	Set<String> aProducts = autoProducts.keySet();

	int totalProducts = aProducts.size();
	int i = 1;
	for (String pKey : aProducts) {
	    if (aKey != pKey) {
		System.out.printf("%d: %s\n", i, pKey);
	    }
	    aKey = pKey;
	    i++;
	}

    }

    @Override
    public List<Product> getProducts() {
	List<Product> pList = null;
	// TODO Auto-generated method stub
	return pList;
    }
}
