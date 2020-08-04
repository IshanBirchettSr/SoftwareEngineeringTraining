/**
 * 
 */
package haircare;

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
 * @author Allma M. Johnson, Roxanne L. Earnest
 *
 */
public class HairCareDept extends Department {
    String deptName = StoreConstants.deptNames.HAIR_CARE.name();
    List<String> haircareRecords = null;
    HashMap<Integer, String> keyMap = null;
    // HashMap<K, V> to hold haircareProd objects.
    HashMap<String, HaircareProd> haircareProducts;

    /**
     * Constructor
     */
    public HairCareDept() {
	super.setDeptName(deptName);
	// Record Load
	DataCsvLoad unLoadTrucks = new DataCsvLoad();
	unLoadTrucks.loadData(StoreConstants.HAIR_CARE_TRUCK);
	haircareRecords = unLoadTrucks.getRecords();
	this.setLoadedRecords(haircareRecords);
	// System.out.printf("%s Department open with %d records\n", deptName,
	// haircareRecords.size());

	// haircare Product Load
	haircareProducts = new HashMap<String, HaircareProd>();
	loadProducts();
    }

    @Override
    protected void loadProducts() {
	// Load products
	for (String record : haircareRecords) {
	    HaircareProd hcp = new HaircareProd();
	    boolean recordToProductSuccessful = hcp.recordToProduct(record);

	    // If it fails to convert any field, don't add that object to haircareProducts
	    if (recordToProductSuccessful == true) {
		String prodKey = ProdKeyGen.genKey(hcp);
		haircareProducts.put(prodKey, hcp);
	    }
	}
	System.out.printf("%s Department loaded %d (crates) and created %d types of products\n", deptName,
		haircareRecords.size(), haircareProducts.size());

    }

    @Override
    public void listProducts() {
	String aKey = null;
	Set<String> haircareProductKeys = haircareProducts.keySet();

	int totalProducts = haircareProductKeys.size();
	int i = 1;
	for (String pKey : haircareProductKeys) {
	    Product pd = haircareProducts.get(pKey);
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
	    HaircareProd pd = haircareProducts.get(pKey);
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
