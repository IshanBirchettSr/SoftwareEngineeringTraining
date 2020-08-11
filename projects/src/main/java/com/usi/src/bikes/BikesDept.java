/**
 * 
 */
package bikes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javafx.scene.Scene;
import util.DataCsvLoad;
import util.Department;
import util.ProdKeyGen;
import util.Product;
import util.StoreConstants;

/**
 * @author Allma M. Johnson
 *
 */
public class BikesDept extends Department {
    String deptName = StoreConstants.deptNames.BIKES.name();
    List<String> bikesRecords = null;
    HashMap<Integer, String> keyMap = null;
    // HashMap<K, V> to hold BikesProd objects.
    HashMap<String, BikesProd> bikesProducts;

    /**
     * Constructor
     */
    public BikesDept() {
	super.setDeptName(deptName);
	// Record Load
	DataCsvLoad unLoadTrucks = new DataCsvLoad();
	unLoadTrucks.loadData(StoreConstants.BIKES_TRUCK);
	bikesRecords = unLoadTrucks.getRecords();
	this.setLoadedRecords(bikesRecords);
	keyMap = new HashMap<Integer, String>();
	// System.out.printf("%s Department open with %d records\n", deptName,
	// bikes.size());

	// Bikes Product Load
	bikesProducts = new HashMap<String, BikesProd>();
	loadProducts();
    }

    @Override
    protected void loadProducts() {
	// Load products
	for (String record : bikesRecords) {
	    BikesProd bkp = new BikesProd();
	    boolean recordToProductSuccessful = bkp.recordToProduct(record);

	    if (recordToProductSuccessful == true) {
		String prodKey = ProdKeyGen.genKey(bkp);
		int howMany = bkp.getNumUnitsInstock();
		for (int i = 0; i < howMany; i++) {
		    // System.out.println(prodKey);
		    bikesProducts.put(prodKey + i, bkp);
		}
	    }
	}
	System.out.printf("%s Department loaded %d (crates) and created %d types of products\n", deptName,
		bikesRecords.size(), bikesProducts.size());

    }

    @Override
    public void listProducts() {
	String aKey = null;
	Set<String> bikeProductKeys = bikesProducts.keySet();

	int totalProducts = bikeProductKeys.size();
	int i = 1;
	for (String pKey : bikeProductKeys) {
	    Product pd = bikesProducts.get(pKey);
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
	    BikesProd pd = bikesProducts.get(pKey);
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
	// TODO Auto-generated method stub
	return null;
    }
}
