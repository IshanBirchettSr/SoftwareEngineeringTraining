package deli;

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
 * @author Allma M. Johnson and Roxanne L. Earnest
 *
 */
public class DeliDept extends Department {
    String deptName = StoreConstants.deptNames.DELI.name();
    List<String> deliRecords = null;
    HashMap<Integer, String> keyMap = null;
    // HashMap<K, V> to hold DeliProd objects.
    HashMap<String, DeliProd> deliProducts;

    /**
     * Constructor
     */
    public DeliDept() {
	super.setDeptName(deptName);
	// Record Load
	DataCsvLoad unLoadTrucks = new DataCsvLoad();
	unLoadTrucks.loadData(StoreConstants.DELI_TRUCK);
	deliRecords = unLoadTrucks.getRecords();
	this.setLoadedRecords(deliRecords);
	keyMap = new HashMap<Integer, String>();
	// System.out.printf("%s Department open with %d records\n", deptName,
	// deliRecords.size());

	// Deli Product Load
	deliProducts = new HashMap<String, DeliProd>();
	loadProducts();
    }

    @Override
    protected void loadProducts() {
	// TODO Auto-generated method stub
	// Load products
	for (String record : deliRecords) {
	    DeliProd dep = new DeliProd();
	    boolean recordToProductSuccessful = dep.recordToProduct(record);
	    if (recordToProductSuccessful == true) {
		String prodKey = ProdKeyGen.genKey(dep);
		int howMany = dep.getQuantity();
		for (int i = 0; i < howMany; i++) {
		    // System.out.println(prodKey);
		    deliProducts.put(prodKey + i, dep);
		}
		// autoProducts.put(prodKey, ap);

	    }
	}
	System.out.printf("%s Department loaded %d (crates) and created %d types of products\n", deptName,
		deliRecords.size(), deliProducts.size());

    }

    @Override
    public void listProducts() {
	String aKey = null;
	Set<String> deliProductKeys = deliProducts.keySet();

	int totalProducts = deliProductKeys.size();
	int i = 1;
	for (String pKey : deliProductKeys) {
	    Product pd = deliProducts.get(pKey);
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
	    DeliProd pd = deliProducts.get(pKey);
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
