/**
 * 
 */
package housewares;

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
 * @author Allma M. Johnson
 *
 */

public class HousewaresDept extends Department {
    String deptName = StoreConstants.deptNames.HOUSEWARES.name();
    List<String> housewaresRecords = null;
    HashMap<Integer, String> keyMap = null;
    // HashMap<K, V> to hold HousewaresProd objects.
    HashMap<String, HousewaresProd> housewaresProducts;

    /**
     * Constructor
     */
    public HousewaresDept() {
	super.setDeptName(deptName);
	// Record Load
	DataCsvLoad unLoadTrucks = new DataCsvLoad();
	unLoadTrucks.loadData(StoreConstants.HOUSEWARES_TRUCK);
	housewaresRecords = unLoadTrucks.getRecords();
	this.setLoadedRecords(housewaresRecords);
	// System.out.printf("%s Department open with %d records\n", deptName,
	// autoRecords.size());

	// Housewares Product Load
	housewaresProducts = new HashMap<String, HousewaresProd>();
	loadProducts();
    }

    @Override
    protected void loadProducts() {
	// Load products
	for (String record : housewaresRecords) {
	    HousewaresProd hwp = new HousewaresProd();
	    boolean recordToProductSuccessful = hwp.recordToProduct(record);

	    // If it fails to convert any field, don't add that object to housewaresProducts
	    if (recordToProductSuccessful == true) {
		String prodKey = ProdKeyGen.genKey(hwp);
		housewaresProducts.put(prodKey, hwp);
	    }
	}
	System.out.printf("%s Department loaded %d (crates) and created %d types of products\n", deptName,
		housewaresRecords.size(), housewaresProducts.size());

    }

    @Override
    public void listProducts() {
	String aKey = null;
	Set<String> housewareProductKeys = housewaresProducts.keySet();

	int totalProducts = housewareProductKeys.size();
	int i = 1;
	for (String pKey : housewareProductKeys) {
	    Product pd = housewaresProducts.get(pKey);
	    if (aKey != pKey) {
		System.out.printf("%d: %s %s\t%.2f\n", i, pd.getBrandName(), pd.getProductName(), pd.getPrice());
	    }
	    aKey = pKey;
	    keyMap.put(i, pKey);
	    i++;
	}
    }

    public List<Product> getProds(int index, int quantity) {
	ArrayList<Product> pdList = new ArrayList<Product>();
	String pKey = keyMap.get(index);
	for (int i = 0; i < quantity; i++) {
	    HousewaresProd pd = housewaresProducts.get(pKey);
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
