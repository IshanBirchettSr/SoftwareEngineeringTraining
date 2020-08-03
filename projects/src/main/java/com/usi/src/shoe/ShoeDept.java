/**
 * 
 */
package shoe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import automotive.AutomotiveProd;
import util.DataCsvLoad;
import util.Department;
import util.ProdKeyGen;
import util.Product;
import util.StoreConstants;

/**
 * @author Allma M. Johnson and Roxanne L Earnest
 *
 */
public class ShoeDept extends Department {
    String deptName = StoreConstants.deptNames.SHOE.name();
    List<String> shoeRecords = null;
    HashMap<Integer, String>keyMap = null;
    // HashMap<K, V> to hold ShoeProd objects.
    HashMap<String, ShoeProd> shoeProducts;

    /**
     * 
     */
    public ShoeDept() {
	// TODO Auto-generated constructor stub
	DataCsvLoad unLoadTrucks = new DataCsvLoad();
	unLoadTrucks.loadData(StoreConstants.SHOE_TRUCK);
	shoeRecords = unLoadTrucks.getRecords();
	this.setLoadedRecords(shoeRecords);
	// System.out.printf("%s Department open with %d records\n", deptName,
	// autoRecords.size());

	// Automotive Product Load
	shoeProducts = new HashMap<String, ShoeProd>();
	loadProducts();
    }

    @Override
    protected void loadProducts() {

	// Load products
	for (String record : shoeRecords) {
	    ShoeProd shp = new ShoeProd();
	    boolean recordToProductSuccessful = shp.recordToProduct(record);

	    // If it fails to convert any field, don't add that object to autoProducts
	    if (recordToProductSuccessful == true) {
		String prodKey = ProdKeyGen.genKey(shp);
		shoeProducts.put(prodKey, shp);
	    }
	}
	System.out.printf("%s Department loaded %d (crates) and created %d types of products\n", deptName,
		shoeRecords.size(), shoeProducts.size());

    }

    @Override
    public void listProducts() {
	String aKey = null;
	Set<String> shoeProductKeys = shoeProducts.keySet();

	int totalProducts = shoeProductKeys.size();
	int i = 1;
	for (String pKey : shoeProductKeys) {
	    Product pd = shoeProducts.get(pKey);
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
	    ShoeProd pd = shoeProducts.get(pKey);
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
