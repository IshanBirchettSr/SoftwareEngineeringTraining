package toys;

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
public class ToysDept extends Department {
    String deptName = StoreConstants.deptNames.TOYS.name();
    List<String> toysRecords = null;
    // HashMap<K, V> to hold ToysProd objects.
    HashMap<String, ToysProd> toysProducts;

    /**
     * Constructor
     */

    public ToysDept() {
	// Record Load
	DataCsvLoad unLoadTrucks = new DataCsvLoad();
	unLoadTrucks.loadData(StoreConstants.TOYS_TRUCK);
	toysRecords = unLoadTrucks.getRecords();
	this.setLoadedRecords(toysRecords);
	// System.out.printf("%s Department open with %d records\n", deptName,
	// toysRecords.size());

	// Toys Product Load
	toysProducts = new HashMap<String, ToysProd>();
	loadProducts();
    }

    @Override
    protected void loadProducts() {
	// Load products
	for (String record : toysRecords) {
	    ToysProd tp = new ToysProd();
	    boolean recordToProductSuccessful = tp.recordToProduct(record);

	    // If it fails to convert any field, don't add that object to toysProducts
	    if (recordToProductSuccessful == true) {
		String prodKey = ProdKeyGen.genKey(tp);
		toysProducts.put(prodKey, tp);
	    }
	}
	System.out.printf("%s Department loaded %d (crates) and created %d types of products\n", deptName,
		toysRecords.size(), toysProducts.size());

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

}
