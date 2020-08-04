package tupperware;

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
 * @author chich and Allma M. Johnson
 *
 */

public class TupperwareDept extends Department {
    String deptName = StoreConstants.deptNames.TUPPERWARE.name();
    List<String> tupperwareRecords = null;
    HashMap<Integer, String> keyMap = null;
    // HashMap<K, V> to hold TupperwareProd objects.
    HashMap<String, TupperwareProd> tupperwareProducts;

    /**
     * Constructor
     */
    public TupperwareDept() {
	super.setDeptName(deptName);
	// Record Load
	DataCsvLoad unLoadTrucks = new DataCsvLoad();
	unLoadTrucks.loadData(StoreConstants.TUPPERWARE_TRUCK);
	tupperwareRecords = unLoadTrucks.getRecords();
	this.setLoadedRecords(tupperwareRecords);
	// System.out.printf("%s Department open with %d records\n", deptName,
	// tupperwareRecords.size());

	// Tupperware Product Load
	tupperwareProducts = new HashMap<String, TupperwareProd>();
	loadProducts();
    }

    @Override
    protected void loadProducts() {
	// Load products
	for (String record : tupperwareRecords) {
	    TupperwareProd twp = new TupperwareProd();
	    boolean recordToProductSuccessful = twp.recordToProduct(record);

	    // If it fails to convert any field, don't add that object to autoProducts
	    if (recordToProductSuccessful == true) {
		String prodKey = ProdKeyGen.genKey(twp);
		tupperwareProducts.put(prodKey, twp);
	    }
	}
	System.out.printf("%s Department loaded %d (crates) and created %d types of products\n", deptName,
		tupperwareRecords.size(), tupperwareProducts.size());

    }

    @Override
    public void listProducts() {
	String aKey = null;
	Set<String> tupperwareProductKeys = tupperwareProducts.keySet();

	int totalProducts = tupperwareProductKeys.size();
	int i = 1;
	for (String pKey : tupperwareProductKeys) {
	    Product pd = tupperwareProducts.get(pKey);
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
	    TupperwareProd pd = tupperwareProducts.get(pKey);
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
