/**
 * 
 */
package bikes;

import java.util.HashMap;
import java.util.List;

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
    // HashMap<K, V> to hold BikesProd objects.
    HashMap<String, BikesProd> bikesProducts;

    /**
     * Constructor
     */
    public BikesDept() {
	// Record Load
	DataCsvLoad unLoadTrucks = new DataCsvLoad();
	unLoadTrucks.loadData(StoreConstants.BIKES_TRUCK);
	bikesRecords = unLoadTrucks.getRecords();
	this.setLoadedRecords(bikesRecords);
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

	    // If it fails to convert any field, don't add that object to autoProducts
	    if (recordToProductSuccessful == true) {
		String prodKey = ProdKeyGen.genKey(bkp);
		bikesProducts.put(prodKey, bkp);
	    }
	}
	System.out.printf("%s Department loaded %d (crates) and created %d types of products\n", deptName,
		bikesRecords.size(), bikesProducts.size());

    }

    @Override
    public void listProducts() {
	// TODO Auto-generated method stub

    }

    @Override
    public List<Product> getProducts() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public List<Product> getProds(int index, int quantity) {
	// TODO Auto-generated method stub
	return null;
    }
}
